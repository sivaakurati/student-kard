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
		window.location.href = '/parent0';
	};
	
	$scope.deleteParent = function(parent){
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
	var param = parentId.split('/')[3].replace('parent','')
	
	$scope.openParent = function(parent){
		window.location.href = '../parent' + parent.parentId;
	};
	
	$scope.$on("loadStates", function(){
		$scope.loadStates();
	});
	
	$scope.loadStates = function(){
		$http.get('/states').success(function(data){
			$scope.states = data;
		});
	};
	
	$scope.$on("loadMainParents", function(){
		$scope.loadMainParents();
	});
	
	$scope.loadMainParents = function(){
		$http.get('/parent/main').success(function(data){
			$scope.mainparents = data;
		});
	};
	
	$scope.getBack = function(){
		if ($scope.parent.mainParentId == '')  window.location.href = '/student';
		else window.location.href = '/parent' + $scope.parent.mainParentId;
	};
	
	$scope.loadCoursesByBatch = function() {
		var val = stringIt($scope.student.batch.batchId).replace('"', '');
		var batchId = val.substring(0, val.length-1);
		$scope.courses = [];
		angular.forEach($scope.batches, function(batch) {
			if(batch.batchId == batchId) $scope.courses = batch.courses;
		});
	};
	
	$scope.saveParent = function(){
		$http.post('/parent', $scope.parent).success(function(){
			window.location.href = '/student';
		});
	};
	
	$scope.loadParentDetailByMainParentId = function() {
		var val = stringIt($scope.parent.mainParentId).replace('"', '');
		var mainParentId = val.substring(0, val.length-1);
		$http.get('/parent/' + mainParentId).success(function(data){
			$scope.parent = data;
			$scope.parent.mainParentId = $scope.parent.parentId;
			$scope.loadCitiesByState();
			$scope.loadLocationsByCity();
			$scope.parent.parentId = '';
			$scope.parent.parentName = '';
			$scope.parent.emailAddress = '';
			$scope.parent.contactNo = '';
			$scope.parent.gender = '';
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
			$('#pinCode').val($scope.location.pinCode);
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
	
	$scope.loadParent = function(){
		if (param != 0) {
			$http.get('/parent/' + param).success(function(data){
				$scope.parent = data;
				if ($scope.parent.mainParentId == null) $scope.parent.mainParentId = '';
				$scope.loadCitiesByState();
				$scope.loadLocationsByCity();
			});
		}
	};
	
	$scope.loadLoggerUserDetail();
	$scope.loadStates();
	$scope.loadMainParents();
	$scope.loadParent();
});

function stringIt(val) {
    return JSON.stringify(val);
};

