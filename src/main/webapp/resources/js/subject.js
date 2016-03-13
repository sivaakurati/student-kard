var app = angular.module('subject', ['angularUtils.directives.dirPagination']);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadSubjects", function(){
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
	
	$scope.sort = function(keyname){
		$scope.sortKey = keyname; 
		$scope.reverse = !$scope.reverse;
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
		$scope.render(subject);
	});

	$scope.render = function(subject){
		$scope.subject = subject;
	};
	
	$scope.saveSubject = function(){
		if($scope.subject.subjectName == '' || $scope.subject.subjectName == null) {
			bootbox.alert('Please enter the valid subject name!!');
			e.stopPropagation();
		}
		for(var i=0; i < $scope.subjects.length; i++) {			
			if($scope.subjects[i].subjectName == $scope.subject.subjectName) {
				bootbox.alert('Entered subject name already exists!!!');
				e.stopPropagation();
			}
		}		
		$http.post('/subject', $scope.subject).success(function(){
			$('#subjectmodal').hide();
			bootbox.alert('Subject name '+$scope.subject.subjectName+' saved successfully');
			$scope.$emit('loadSubjects');
		});
	};
});

function stringIt(val) {
    return JSON.stringify(val);
};
