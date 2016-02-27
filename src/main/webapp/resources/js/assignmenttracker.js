var app = angular.module('assignmenttracker', []);

app.controller('ViewCtrl', function($scope, $http){
	
	$scope.$on('loadBatches', function(){
		$scope.loadBatches();
	});
	
	$scope.loadBatches = function(){
		$http.get('/batch').success(function(data){
			$scope.batches = data;
		});
	};
	
	$scope.loadCoursesByBatch = function() {
		var val = stringIt($scope.gradetracker.batch.batchId).replace('"', '');
		var batchId = val.substring(0, val.length-1);
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
		var val = stringIt($scope.gradetracker.course.courseId).replace('"', '');
		var courseId = val.substring(0, val.length-1);
		$http.get('/course/' + courseId).success(function(data){
			$scope.course = data;
			$scope.subjects = [];
			angular.forEach($scope.loggerUser.subjects, function(userSubject) {
				angular.forEach($scope.course.subjects, function(subject) {
					if (userSubject.subjectName == subject.subjectName)
						$scope.subjects.push(subject);
					});
			});
			if ($scope.subjects.length != 0) $('#formDiv').show();
			else $('#formDiv').hide();
		});
	};
	
	$scope.saveAssignments = function() {
		var val = stringIt($scope.gradetracker.batch.batchId).replace('"', '');
		var batchId = val.substring(0, val.length-1);
		var val = stringIt($scope.gradetracker.course.courseId).replace('"', '');
		var courseId = val.substring(0, val.length-1);
		$scope.assignment = [];
		angular.forEach($scope.subjects, function(subject) {
			$scope.assignment.push({batch: batch, course: course, subject: subject, question: $('#assignment' + subject.subjectName).val()});
		});
		//$http.post('/assignment', $scope.assignment).success(function(){
		//	$scope.$emit('loadBatches');			
		//});
		console.log(stringIt($scope.assignment));
	};
	
	$scope.loadLoggerUserDetail();
	$scope.loadBatches();
});

function stringIt(val) {
    return JSON.stringify(val);
};
