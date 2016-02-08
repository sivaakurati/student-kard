var app = angular.module('teacher', []);
var teacherId = window.location.href;

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadTeachers", function(){
		$scope.loadTeachers();
	});
	
	$scope.loadTeachers = function(){
		$http.get('/teacher').success(function(data){
			$scope.teachers = data;
		});
	};
	
	$scope.createTeacher = function(){
		window.location.href = '/teacher/0';
	};
	
	$scope.openTeacher = function(teacher){
		window.location.href = '/teacher/' + teacher.teacherId;
	};
	
	$scope.deleteTeacher = function(subject){
		bootbox.confirm('Are you sure you want to delete <span style=\"font-style:italic\">' + teacher.teacherName + '</span>', function(result) {
			if(result == true) {
				$http.delete('/teacher/' + teacher.teacherId).success(function() {
					$scope.$emit('loadTeachers');
				});
			} else {
				return;
			}
		});
	};
	
	$scope.loadTeachers();
});

app.controller('EditCtrl', function($scope, $http) {
	var param = teacherId.split('/')[4]
	
	$scope.$on("loadStates", function(){
		$scope.loadStates();
	});
	
	$scope.loadStates = function(){
		$http.get('/states').success(function(data){
			$scope.states = data;
		});
	};
	
	$scope.$on("loadTeacher", function(){
		$scope.loadTeacher();
	});
	
	$scope.getBack = function(){
		window.location.href = '/teacher';
	};
	
	$scope.loadTeacher = function(){
		$http.get('/teacher/' + param).success(function(data){
			$scope.teacher = data;
		});
	};
	
	$scope.$on("loadSubjects", function(){
		$scope.loadSubjects();
	});
	
	$scope.loadSubjects = function(){
		$http.get('/subject').success(function(data){
			$scope.subjects = data;
		});
	};
	
	$scope.saveTeacher = function(){
		/*$scope.teacherVal = [];
		$scope.teacherVal.push({
			teacherId: $scope.teacher.teacherId,
			firstName: $scope.teacher.firstName,
			middleName: $scope.teacher.middleName,
			lastName: $scope.teacher.lastName,
			//photo: $('#teacherPhoto').val(),
			gender: $scope.teacher.gender,
			address: $scope.teacher.address,
			location: $scope.teacher.location,
			qualification: $scope.teacher.qualification,
			contactNo: $scope.teacher.contactNo,
			dateOfBirth: $('#dateOfBirth').val(),
			dateOfJoining: $('#dateOfJoining').val(),
			subject: $scope.teacher.subject,
			adminRight: $scope.teacher.adminRight
		});*/
		console.log(stringIt($scope.teacher));
		$http.post('/teacher', $scope.teacher).success(function(){
			$('#teachermodal').hide();
			window.location.href = '../../teacher';
		});
	};
	
	$scope.loadCitiesByState = function() {
		var val = stringIt($scope.teacher.location.stateName).replace('"', '');
		var stateName = val.substring(0, val.length-1);
		$http.get('/cities/' + stateName).success(function(data){
			$scope.cities = data;
		});
	};
	
	$scope.loadLocationsByCity = function() {
		var val = stringIt($scope.teacher.location.cityName).replace('"', '');
		var cityName = val.substring(0, val.length-1);
		$http.get('/locations/' + cityName).success(function(data){
			$scope.locations = data;
		});
	};
	
	$scope.loadLocationById = function() {
		var val = stringIt($scope.teacher.location.locationId).replace('"', '');
		var locationId = val.substring(0, val.length-1);
		$http.get('/location/' + locationId).success(function(data){
			$scope.location = data;
		});
	};
	
	$scope.loadStates();
	$scope.loadTeacher();
	$scope.loadSubjects();
});

function stringIt(val) {
    return JSON.stringify(val);
};

