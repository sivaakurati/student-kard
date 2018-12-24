var app = angular.module('assignmentresults', []);

app.controller('ViewCtrl', function($scope, $http){
	$scope.getBack = function(){
		window.location.href = '/assignmenttracker';
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
	
	$scope.loadStudentsByBatchNCourse = function() {
		var val = stringIt($scope.gradetracker.batch.batchId).replace('"', '');
		var batchId = val.substring(0, val.length-1);
		var val = stringIt($scope.gradetracker.course.courseId).replace('"', '');
		var courseId = val.substring(0, val.length-1);
		$http.get('/student/batch/' + batchId + '/course/' + courseId).success(function(data){
			$scope.students = data;
		});
	};
	
	$scope.loadAssignmentResultsByStudent = function() {
		
	};
	
	$scope.loadLoggerUserDetail();
	$scope.loadBatches();
});

function stringIt(val) {
    return JSON.stringify(val);
};
