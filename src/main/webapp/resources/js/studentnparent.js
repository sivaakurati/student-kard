var app = angular.module('studentnparent', []);
var parentId = window.location.href;

/**
 * parent.html and student.html
 */

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadStudents", function(){
		$scope.loadStudents();
	});
	
	$scope.loadStudents = function(){
		$http.get('/student').success(function(data){
			$scope.students = data;
		});
	};
	
	$scope.createStudentNParent = function(){
		window.location.href = '/studentnparent0';
	};
	
	$scope.openStudentNParent = function(student){
		window.location.href = '/studentnparent' + student.parent.parentId;
	};
	
	$scope.deleteStudentNParent = function(student){
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

/**
 * saveparent.html and savestudent.html
 */

app.controller('EditCtrl', function($scope, $http) {
	var param = parentId.split('/')[3].replace('studentnparent','')
	
	$scope.$on("loadStates", function(){
		$scope.loadStates();
	});
	
	$scope.loadStates = function(){
		$http.get('/states').success(function(data){
			$scope.states = data;
		});
	};
	
	$scope.$on("loadParent", function(){
		$scope.loadParent();
	});
	
	$scope.getBack = function(){
		window.location.href = '/studentnparent';
	};
	
	$scope.loadParent = function(){
		$http.get('/parent/' + param).success(function(data){
			$scope.parent = data;
			if (param != 0) {
				$scope.loadCitiesByState();
				$scope.loadLocationsByCity();
			}
		});
	};
	
	$scope.$on('loadBatchs', function(){
		$scope.loadBatchs();
	});
	
	$scope.loadBatchs = function(){
		$http.get('/batch').success(function(data){
			$scope.batchs = data;
		});
	};
	
	$scope.$on('loadCourses', function(){
		$scope.loadCourses();
	});
	
	$scope.loadCourses = function(){
		$http.get('/course').success(function(data){
			$scope.courses = data;
		});
	};
	
	$scope.saveParent = function(){
		$http.post('/parent', $scope.parent).success(function(){
			window.location.href = '/studentnparent';
		});
	};
	
	$scope.loadCitiesByState = function() {
		var val = stringIt($scope.parent.location.stateName).replace('"', '');
		var stateName = val.substring(0, val.length-1);
		$http.get('/cities/' + stateName).success(function(data){
			$scope.cities = data;
		});
	};
	
	$scope.loadLocationsByCity = function() {
		var val = stringIt($scope.parent.location.cityName).replace('"', '');
		var cityName = val.substring(0, val.length-1);
		$http.get('/locations/' + cityName).success(function(data){
			$scope.locations = data;
		});
	};
	
	$scope.loadLocationById = function() {
		var val = stringIt($scope.parent.location.locationId).replace('"', '');
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
	
	$scope.loadLoggerUserDetail();
	$scope.loadStates();
	$scope.loadBatchs();
	$scope.loadCourses();
	$scope.loadParent();
});

function stringIt(val) {
    return JSON.stringify(val);
};

