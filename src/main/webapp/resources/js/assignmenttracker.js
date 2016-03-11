var app = angular.module('assignmenttracker', []);

app.controller('ViewCtrl', function($scope, $http){
	$scope.$on('loadAssignments', function(){
		$scope.loadAssignments();
	});
	
	$scope.loadAssignments = function(){
		$http.get('/assignment').success(function(data){
			$scope.assignments = data;
		});
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
		$http.get('/batch').success(function(data){
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
		$http.get('/user').success(function(data){
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
		$http.get('/course/' + courseId).success(function(data){
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
		$http.post('/assignment', $scope.assignment).success(function(){
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
