var app = angular.module('department', []);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadDepartments", function(){
		$scope.loadDepartments();
	});
	
	$scope.loadDepartments = function(){
		$http.get('/department').success(function(data){
			$scope.departments = data;
		});
	};
	
	$scope.createDepartment = function(modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderDepartment', {});
	};
	
	$scope.openDepartment = function(department, modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderDepartment', angular.copy(department));
	};
	
	$scope.deleteDepartment = function(department){
		bootbox.confirm('Are you sure you want to delete <span style=\"font-style:italic\">' + department.departmentName + '</span>', function(result) {
			if(result == true) {
				$http.delete('/department/' + department.subjectId).success(function() {
					$scope.$emit('loadDepartments');
				});
			} else {
				return;
			}
		});
	};
	
	$scope.loadDepartments();
});

app.controller('EditCtrl', function($scope, $http) {
	$scope.$on('renderDepartment', function(event, department){
		$scope.render(department);
	});

	$scope.render = function(department){
		$scope.department = department;
	};
	
	$scope.saveDepartment = function(){
		$http.post('/department', $scope.department).success(function(){
			$('#departmentmodal').hide();
			$scope.$emit('loadDepartments');
		});
	};
});

function stringIt(val) {
    return JSON.stringify(val);
};
