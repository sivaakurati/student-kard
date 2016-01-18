var app = angular.module('batch', []);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadBatchs", function(){
		$scope.loadBatchs();
	});
	$scope.loadBatchs = function(){
		$http.get('batch').success(function(batchs){
			$scope.batchs = batchs;
		});
	};	
	$scope.loadBatchs();
});

app.controller('AddCtrl', function($scope, $http) {
	$scope.createBatch = function(modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderBatch', {});
	};
});

app.controller('EditCtrl', function($scope, $http) {
	$scope.$on('renderBatch', function(event, batch){
		$scope.render(batch);
	});
	$scope.render = function(batch){
		$scope.batch = batch;
	};
	$scope.saveBatch = function(){
		$http.post('batch', $scope.batch).success(function(){
			$('#batchmodal').hide();
			$scope.$emit('loadBatchs');
		});
	};
});
