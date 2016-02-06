var app = angular.module('timetracker', []);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on('loadCourses', function(){
		$scope.loadCourses();
	});
	
	$scope.loadCourses = function(){
		$http.get('/course').success(function(data){
			$scope.courses = data;
		});
	};
	
	$scope.loadCourses();
});
