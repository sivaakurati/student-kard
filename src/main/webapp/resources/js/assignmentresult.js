var app = angular.module('assignmentresult', []);

app.controller('ViewCtrl', function($scope, $http){
	$scope.$on('loadAssignments', function(){
		$scope.loadAssignments();
	});
	
	$scope.loadAssignments = function(){
		$http.get('/studentkard/user').success(function(data){
			$scope.student = data;
			$http.get('/studentkard/assignment/student/' + $scope.student.studentId).success(function(data){
				$scope.assignments = data;
			});
		});
	};
	
	$scope.saveAssignmentResult = function() {
		if ($scope.student.assignmentResults == null) $scope.student.assignmentResults = [];
		angular.forEach($scope.assignments, function(assignment) {
			var res = $('#result' + assignment.assignmentId).val();
			if (res != '') $scope.student.assignmentResults.push({assignment: assignment, result: res});
		});
		$http.post('/studentkard/student/assignment', $scope.student).success(function(){
			$scope.loadAssignments();			
		});
	};
	
	$scope.loadAssignments();
});

function stringIt(val) {
    return JSON.stringify(val);
};
