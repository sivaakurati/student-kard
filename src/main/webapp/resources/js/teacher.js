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
		$http.get('/state').success(function(data){
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
	
	$scope.saveTeacher = function(){
		console.log(stringIt($scope.teacher));
		//$http.post('/teacher', $scope.teacher).success(function(){
		//	$scope.$emit('loadTeachers');
		//	$('#teachermodal').hide();
		//});
	};
	
	$scope.getCities = function(val) {
		alert(val);
	};
	
	$scope.loadStates();
	$scope.loadTeacher();
});

function stringIt(val) {
    return JSON.stringify(val);
};

