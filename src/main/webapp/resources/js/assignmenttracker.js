var app = angular.module('assignmenttracker', ['angularUtils.directives.dirPagination']);

app.controller('ViewCtrl', function($scope, $http){
	$scope.$on('loadAssignments', function(){
		$scope.loadAssignments();
	});
	
	$scope.loadAssignments = function(){
		$http.get('/studentkard/assignment').success(function(data){
			$scope.assignments = data;
		});
	};
	
	$scope.sort = function(keyname){
		$scope.sortKey = keyname; 
		$scope.reverse = !$scope.reverse;
	};
	
	$scope.createAssignment = function(modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderAssignment', {});
	};
	
	$scope.openAssignment = function(assignment, modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderAssignment', angular.copy(assignment));
	};
	
	$scope.createAssignmentResults = function(){
		window.location.href = '/assignmentresults';
	};
	
	$scope.loadAssignments();
});

app.controller('EditCtrl', function($scope, $http){
	$scope.$on('renderAssignment', function(event, assignment){
		$scope.render(assignment);
	});

	$scope.render = function(assignment){
		$scope.assignment = assignment;
		if ($scope.assignment.assignmentId != null) {
			$scope.loadCoursesByBatch();
			$scope.loadSubjectsByCourse();
		}
	};
	
	$scope.$on('loadBatches', function(){
		$scope.loadBatches();
	});
	
	$scope.loadBatches = function(){
		$http.get('/studentkard/batch').success(function(data){
			$scope.batches = data;
		});
	};
	
	$scope.loadCoursesByBatch = function() {
		var batchId = '';
		if ($scope.assignment.assignmentId == null) {
			var val = stringIt($scope.assignment.batch.batchId).replace('"', '');
			batchId = val.substring(0, val.length-1);
		} else {
			batchId = stringIt($scope.assignment.batch.batchId).replace('"', '');
		}
		angular.forEach($scope.batches, function(batch) {
			if(batch.batchId == batchId) $scope.courses = batch.courses;
		});
	};
	
	$scope.$on('loadLoggerUserDetail', function(){
		$scope.loadLoggerUserDetail();
	});
	
	$scope.loadLoggerUserDetail = function(){
		$http.get('/studentkard/user').success(function(data){
			$scope.loggerUser = data;
		});
	};
	
	$scope.loadSubjectsByCourse = function() {
		var courseId = '';
		if ($scope.assignment.assignmentId == null) {
			var val = stringIt($scope.assignment.course.courseId).replace('"', '');
			courseId = val.substring(0, val.length-1);
		} else {
			courseId = stringIt($scope.assignment.course.courseId).replace('"', '');
		}
		$http.get('/studentkard/course/' + courseId).success(function(data){
			$scope.course = data;
			$scope.subjects = [];
			if ($scope.loggerUser != '') {
				angular.forEach($scope.loggerUser.subjects, function(userSubject) {
					angular.forEach($scope.course.subjects, function(subject) {
						if (userSubject.subjectName == subject.subjectName)
							$scope.subjects.push(subject);
						});
				});
			} else {
				angular.forEach($scope.course.subjects, function(subject) {
					$scope.subjects.push(subject);
				});
			}
		});
	};
	
	$scope.saveAssignment = function() {
		var batch = $("#batch").val();
		var course = $("#course").val();
		var subject = $("#subject").val();
		var assignment = $("#assignment").val();
		
		if(batch == '' || batch == null) {
			bootbox.alert('Please select the valid batch!!');
			e.stopPropagation();
		}
		if(course == '' || course == null) {
			bootbox.alert('Please select the valid course!!');
			e.stopPropagation();
		}
		if(subject == '' || subject == null) {
			bootbox.alert('Please select the valid subject!!');
			e.stopPropagation();
		}		
		if(assignment.trim() == '' || assignment == null) {
			bootbox.alert('Please enter the valid assignment!!');
			e.stopPropagation();
		}
		/*if($scope.assignment.course.courseId == '' || $scope.assignment.course.courseId == null) {
			bootbox.alert('Please enter the valid course!!');
			e.stopPropagation();
		}*/
		
		$http.post('/studentkard/assignment', $scope.assignment).success(function(){
			$scope.$emit('loadAssignments');			
		});
	};
	
	$scope.saveAssignmentResults = function() {
		
	};
	
	$scope.loadLoggerUserDetail();
	$scope.loadBatches();
});

function stringIt(val) {
    return JSON.stringify(val);
};
