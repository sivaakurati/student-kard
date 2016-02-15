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
	
	$scope.loadLoggerUserDetail();
});

function stringIt(val) {
    return JSON.stringify(val);
};
