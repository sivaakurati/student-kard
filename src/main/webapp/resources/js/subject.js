var app = angular.module('subject', []);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadSubjects", function(){
		$scope.loadSubjects();
	});
	$scope.loadSubjects = function(){
		$http.get('subject').success(function(subjects){
			$scope.subjects = subjects;
		});
	};	
	$scope.loadSubjects();
});

app.controller('AddCtrl', function($scope, $http) {
	$scope.createSubject = function(modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderSubject', {});
	};
});

app.controller('EditCtrl', function($scope, $http) {
	$scope.$on('renderSubject', function(event, subject){
		$scope.render(subject);
	});
	$scope.render = function(subject){
		$scope.subject = subject;
	};
	$scope.saveSubject = function(){
		$http.post('subject', $scope.subject).success(function(){
			$('#subjectmodal').hide();
			$scope.$emit('loadSubjects');
		});
	};
});
