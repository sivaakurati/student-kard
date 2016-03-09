var app = angular.module('student', []);
var studentId = window.location.href;

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadStudents", function(){
		$scope.loadStudents();
	});
	
	$scope.loadStudents = function(){
		$http.get('/student').success(function(data){
			$scope.students = data;
		});
	};
	
	$scope.createParent = function(){
		window.location.href = '/parent0';
	};
	
	$scope.createStudent = function(){
		window.location.href = '/student0';
	};
	
	$scope.openStudent = function(student){
		window.location.href = '/student' + student.studentId;
	};
	
	$scope.deleteStudent = function(student){
		bootbox.confirm('Are you sure you want to delete <span style=\"font-style:italic\">' + student.studentName + '</span>', function(result) {
			if(result == true) {
				$http.delete('/student/' + student.studentId).success(function() {
					$scope.$emit('loadStudents');
				});
			} else {
				return;
			}
		});
	};
	
	$scope.loadStudents();
});

app.controller('EditCtrl', function($scope, $http) {
	var param = studentId.split('/')[3].replace('student','')
	
	$scope.$on("loadStates", function(){
		$scope.loadStates();
	});
	
	$scope.loadStates = function(){
		$http.get('/states').success(function(data){
			$scope.states = data;
		});
	};
	
	$scope.getBack = function(){
		window.location.href = '/student';
	};
	
	$scope.$on('loadBatchs', function(){
		$scope.loadBatchs();
	});
	
	$scope.loadBatchs = function(){
		$http.get('/batch').success(function(data){
			$scope.batches = data;
		});
	};
	
	$scope.$on("loadMainParents", function(){
		$scope.loadMainParents();
	});
	
	$scope.loadMainParents = function(){
		$http.get('/parent/main').success(function(data){
			$scope.mainparents = data;
		});
	};
	
	$scope.loadParentDetailByMainParentId = function() {
		var val = stringIt($scope.parent.mainParentId).replace('"', '');
		var mainParentId = val.substring(0, val.length-1);
		$http.get('/parent/' + mainParentId).success(function(data){
			$scope.parent = data;
			$scope.student = [];
			$scope.student.location = [];
			$scope.parent.mainParentId = $scope.parent.parentId;
			$scope.loadCitiesByState();
			$scope.loadLocationsByCity();
			$scope.student.location.stateName = $scope.parent.location.stateName;
			$scope.student.location.cityName = $scope.parent.location.cityName;
			$scope.student.location.pinCode = $scope.parent.location.pinCode;
			$scope.student.location.locationId = $scope.parent.location.locationId;
		});
	};
	
	$scope.loadCoursesByBatch = function() {
		var val = stringIt($scope.student.batch.batchId).replace('"', '');
		var batchId = val.substring(0, val.length-1);
		$scope.courses = [];
		angular.forEach($scope.batches, function(batch) {
			if(batch.batchId == batchId) $scope.courses = batch.courses;
		});
	};
	
	$scope.saveStudent = function(){
		$http.post('/student', $scope.student).success(function(){
			window.location.href = '/student';
		});
	};
	
	$scope.loadCitiesByState = function() {
		var val = stringIt($scope.student.location.stateName).replace('"', '');
		var stateName = val.substring(0, val.length-1);
		$http.get('/cities/' + stateName).success(function(data){
			$scope.cities = data;
		});
	};
	
	$scope.loadLocationsByCity = function() {
		var val = stringIt($scope.student.location.cityName).replace('"', '');
		var cityName = val.substring(0, val.length-1);
		$http.get('/locations/' + cityName).success(function(data){
			$scope.locations = data;
		});
	};
	
	$scope.loadLocationById = function() {
		var val = stringIt($scope.student.location.locationId).replace('"', '');
		var locationId = val.substring(0, val.length-1);
		$http.get('/location/' + locationId).success(function(data){
			$scope.location = data;
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
	
	$scope.$on("loadStudent", function(){
		$scope.loadStudent();
	});
	
	$scope.loadStudent = function(){
		if (param != 0) {
			$http.get('/student/' + param).success(function(data){
				$scope.student = data;
				$scope.student.dob = new Date($scope.student.dob);
				$scope.student.doj = new Date($scope.student.doj);
				$scope.loadCitiesByState();
				$scope.loadLocationsByCity();
				$scope.loadCoursesByBatch();
				$scope.parent = [];
				$scope.parent.mainParentId = $scope.student.parent.parentId;
			});
		}
	};
	
	$scope.loadLoggerUserDetail();
	$scope.loadStates();
	$scope.loadBatchs();
	$scope.loadMainParents();
	$scope.loadStudent();
});

function stringIt(val) {
    return JSON.stringify(val);
};

