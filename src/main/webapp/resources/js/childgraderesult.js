var app = angular.module('graderesult', []);

app.controller('ViewCtrl', function($scope, $http){
	$scope.$on('loadStudents', function(){
		$scope.loadStudents();
	});
	
	$scope.loadStudents = function(){
		$http.get('/user').success(function(data){
			$scope.parent = data;
			var mainParentId = $scope.parent.mainParentId;
			var parentId = '';
			if (mainParentId == null) parentId = $scope.parent.parentId;
			else parentId = mainParentId;
			$http.get('/student/parent/' + parentId).success(function(data){
				$scope.students = data;
			});
		});
	};
	
	$scope.loadGradesByStudent = function() {
		var studentId = $('#studentId').val();
		$http.get('/grade/student/' + studentId).success(function(data){
			$scope.grades = data;
		});
	};
	
	$scope.loadStudents();
});

function stringIt(val) {
    return JSON.stringify(val);
};
