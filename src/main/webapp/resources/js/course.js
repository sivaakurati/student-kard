var app = angular.module('course', []);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadCourses", function(){
		$scope.loadCourses();
	});
	$scope.loadCourses = function(){
		$http.get('course').success(function(courses){
			$scope.courses = courses;
		});
	};	
	$scope.loadCourses();
});

app.controller('AddCtrl', function($scope, $http) {
	$scope.createCourse = function(modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderCourse', {});
	};
});

app.controller('EditCtrl', function($scope, $http) {
	$scope.$on('renderCourse', function(event, course){
		$scope.render(course);
	});
	$scope.render = function(course){
		$scope.course = course;
	};
	$scope.saveCourse = function(){
		$http.post('course', $scope.course).success(function(){
			$('#coursemodal').hide();
			$scope.$emit('loadCourses');
		});
	};
});
