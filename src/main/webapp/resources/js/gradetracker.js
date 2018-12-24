var app = angular.module('gradetracker', []);

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
	
	$scope.loadStudentsByBatchNCourse = function() {
		var val = stringIt($scope.gradetracker.batch.batchId).replace('"', '');
		var batchId = val.substring(0, val.length-1);
		var val = stringIt($scope.gradetracker.course.courseId).replace('"', '');
		var courseId = val.substring(0, val.length-1);
		$http.get('/course/' + courseId).success(function(data){
			$scope.course = data;
			$scope.subjects = [];
			if ($scope.loggerUser.courses.length == 0) {
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
			console.log($scope.subjects.length);
			if ($scope.subjects.length != 0) $('#formDiv').show();
			else $('#formDiv').hide();
		});
		$http.get('/student/batch/' + batchId + '/course/' + courseId).success(function(data){
			$scope.students = data;
		});
	};
	
	$scope.saveMarks = function() {
		angular.forEach($scope.students, function(student) {
			if (student.grades == null) student.grades = [];
			angular.forEach($scope.course.subjects, function(subject) {
				student.grades.push({subject: subject, marks: $('#marksOf' + subject.subjectName).val()});
			});
		});
		$http.post('/student/grades', $scope.students).success(function(){
			$scope.$emit('loadBatches');			
		});
	};
	
	$scope.loadLoggerUserDetail();
	$scope.loadBatches();
});

function stringIt(val) {
    return JSON.stringify(val);
};
