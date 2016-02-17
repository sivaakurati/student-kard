var app = angular.module('changepwd', []);

app.controller('EditCtrl', function($scope, $http) {
	$scope.$on('loadLoggerUserDetail', function(){
		$scope.loadLoggerUserDetail();
	});
	
	$scope.loadLoggerUserDetail = function(){
		$http.get('/user').success(function(data){
			$scope.loggerUser = data;
			console.log(stringIt($scope.loggerUser));
		});
	};
	
	$scope.$on('loadLoggerUser', function(){
		$scope.loadLoggerUser();
	});
	
	$scope.loadLoggerUser = function(){
		$http.get('/user/logged').success(function(data){
			$scope.user = data;
			console.log(stringIt($scope.user));
		});
	};
	
	$scope.changePassword = function() {
		var newPassword = $('#newPassword').val();
		var retypePassword = $('#retypePassword').val();
		if (newPassword == retypePassword) {
			$scope.user.password = newPassword;
			$http.post('/user/logged/save', $scope.user).success(function(){
				$scope.$emit('loadLoggerUserDetail');
				$scope.$emit('loadLoggerUser');
			});			
		} else {
			bootbox.alert('The New Password and Retype Password should be same!!!');
		}
	};
	
	$scope.loadLoggerUserDetail();
	$scope.loadLoggerUser();
});

function stringIt(val) {
    return JSON.stringify(val);
};
