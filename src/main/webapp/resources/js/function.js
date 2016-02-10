var app = angular.module('Function', []);
var functionId = window.location.pathname.split("/")[2];

app.factory('alertService', function($rootScope) {
	var alertService = {};
    $rootScope.alerts = [];
    alertService.add = function(type, msg) {
    	$rootScope.alerts.push({'type': type, 'msg': msg});
    };
    alertService.closeAlert = function(index) {
      $rootScope.alerts.splice(index, 1);
    };
    return alertService;
});

app.controller('FunctionController', function($scope, $http, $timeout, alertService) {
	$scope.loadFunction = function(){
		if (functionId == 'new') functionId = 0;
		$http.get("/function/" + functionId).success(function(selectedFunction){
			$scope.Function = selectedFunction;
		});
	};
	
	$scope.fetchApplications = function() {
		$http.get("../../application").success(function(data){
			$scope.Applications = data;
		});
	};
	
	$scope.moveParamDown = function(parameter, $event) {
		$event.stopPropagation();		
		++parameter.order;		
		var from = $scope.Function.parameters.indexOf(parameter);
		--$scope.Function.parameters[from+1].order;		
		$scope.Function.parameters.splice(from+1, 0, $scope.Function.parameters.splice(from, 1)[0]);		
		$scope.save();
	};
	
	$scope.moveParamUp = function(parameter, $event) {
		$event.stopPropagation();		
		--parameter.order;		
		var from = $scope.Function.parameters.indexOf(parameter);
		++$scope.Function.parameters[from-1].order;		
		var from = $scope.Function.parameters.indexOf(parameter);
		$scope.Function.parameters.splice(from-1, 0, $scope.Function.parameters.splice(from, 1)[0]);		
		$scope.save();
	};
	
	$scope.createParamRow = function(){
		if ($scope.Function.parameters == null) { $scope.Function.parameters = []; }
		$scope.Function.parameters.push({order : $scope.Function.parameters.length, name : ''});
	};
	
	$scope.removeParamRow = function(parameter, $event){
		$event.stopPropagation();
		$scope.Function.parameters.splice($scope.Function.parameters.indexOf(parameter), 1);
		$scope.save();
	};
	
	$scope.save = function(){
		$scope.Function.application.users = [];
		if ($scope.Function.parameters == null) $scope.Function.parameters = [];
		$http.post("../function", $scope.Function).success(function(functionSaved){
			window.location = "../function";
		});
	};
	
	$scope.delete = function(){
		bootbox.confirm("Are you sure you want to delete <span style=\"font-style:italic\">" + $scope.Function.name + "</span>", function(result) {
			if(result == true) {
				$http.get("../../step/function/" + functionId).success(function(data){
					$scope.steps = data;
				});
				if ($scope.steps == null) $scope.steps = [];
				var totalSteps = $scope.steps.length;
				if (totalSteps == 0) {
					$http.delete("../function/" + $scope.Function.id).success(function() {
						window.location = "../function";
					});
				} else {
					bootbox.alert("You want to delete <span style=\"font-style:italic\">" + $scope.Function.name + "! First delete " + $scope.Function.name + "'s steps");
				}
			} else {
				return;
			}
		});
	};
	
	$scope.loadFunction();
	$scope.fetchApplications();
});

function stringIt(val) {
    return JSON.stringify(val);
};
