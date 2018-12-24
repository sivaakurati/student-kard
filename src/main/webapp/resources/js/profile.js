var app = angular.module('profile', []);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on('loadLoggerUserDetail', function(){
		$scope.loadLoggerUserDetail();
	});
	
	$scope.loadLoggerUserDetail = function(){
		$http.get('/user').success(function(data){
			$scope.loggerUser = data;
		});
	};
	
	$scope.editPhoto = function(modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderPhoto', {});
	};
	
	$scope.loadLoggerUserDetail();
});

app.controller('EditCtrl', function($scope, $http) {
	$scope.$on('renderPhoto', function(event){
		$scope.render();
	});
	
	$scope.render = function(){
		
	};
});

function stringIt(val) {
    return JSON.stringify(val);
};
