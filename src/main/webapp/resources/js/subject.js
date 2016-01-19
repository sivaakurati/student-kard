var app = angular.module('subject', []);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on('loadSubjects', function(event){
		$scope.loadSubjects();
	});
	
	$scope.loadSubjects = function(){
		$http.get('/subject').success(function(data){
			$scope.subjects = data;
		});
	};
	
	$scope.createSubject = function(modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderSubject', {});
	};
	
	$scope.openSubject = function(subject, modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderSubject', angular.copy(subject));
	};
	
	$scope.deleteSubject = function(subject){
		bootbox.confirm('Are you sure you want to delete <span style=\"font-style:italic\">' + subject.subjectName + '</span>', function(result) {
			if(result == true) {
				$http.delete('/subject/' + subject.subjectId).success(function() {
					$scope.$emit('loadSubjects');
				});
			} else {
				return;
			}
		});
	};
	
	$scope.loadSubjects();
});

app.controller('EditCtrl', function($scope, $http) {
	$scope.$on('renderSubject', function(event, subject){
		$scope.subject = subject;
	});
	
	$scope.saveSubject = function(){
		$http.post('/subject', $scope.subject).success(function(){
			$scope.$emit('loadSubjects');
			$('#subjectmodal').modal('hide');
		});
	};
});

function stringIt(val) {
    return JSON.stringify(val);
};
