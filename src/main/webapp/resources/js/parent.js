var app = angular.module('parent', []);
var parentId = window.location.href;

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadParents", function(){
		$scope.loadParents();
	});
	
	$scope.loadParents = function(){
		$http.get('/parent').success(function(data){
			$scope.parents = data;
		});
	};
	
	$scope.createParent = function(){
		window.location.href = '/parent/0';
	};
	
	$scope.openParent = function(parent){
		window.location.href = '/parent/' + parent.parentId;
	};
	
	$scope.deleteParent = function(subject){
		bootbox.confirm('Are you sure you want to delete <span style=\"font-style:italic\">' + parent.parentName + '</span>', function(result) {
			if(result == true) {
				$http.delete('/parent/' + parent.parentId).success(function() {
					$scope.$emit('loadParents');
				});
			} else {
				return;
			}
		});
	};
	
	$scope.loadParents();
});

app.controller('EditCtrl', function($scope, $http) {
	var param = parentId.split('/')[3]
	
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
		window.location.href = '/parent';
	};
	
	$scope.loadParent = function(){
		$http.get('/' + param).success(function(data){
			$scope.parent = data;
		});
	};
	
	$scope.saveParent = function(){
		$http.post('/parent', $scope.parent).success(function(){
			$scope.$emit('loadParents');
			$('#parentmodal').hide();
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
	$scope.loadParent();
});

function stringIt(val) {
    return JSON.stringify(val);
};

