var app = angular.module('course', []);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on('loadCourses', function(){
		$scope.loadCourses();
	});
	
	$scope.loadCourses = function(){
		$http.get('/course').success(function(data){
			$scope.courses = data;
		});
	};
	
	$scope.createCourse = function(modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderCourse', {});
	};
	
	$scope.openCourse = function(course, modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderCourse', angular.copy(course));
	};
	
	$scope.deleteCourse = function(course){
		bootbox.confirm('Are you sure you want to delete <span style=\"font-style:italic\">' + course.courseName + '</span>', function(result) {
			if(result == true) {
				$http.delete('/course/' + course.courseId).success(function() {
					$scope.$emit('loadCourses');
				});
			} else {
				return;
			}
		});
	};
	
	$scope.loadCourses();
});

app.controller('EditCtrl', function($scope, $http) {
	$scope.$on('renderCourse', function(event, course){
		$scope.course = course;
		$http.get('/subject').success(function(data){
			$scope.subjects = data;			
			angular.forEach($scope.subjects, function(subject) {
				angular.forEach(course.subjects, function(courseSubject) {
					if(courseSubject.subjectName == subject.subjectName) subject.checked = true;
				});
			});
		});
	});
	
	$scope.changeCourseSubject = function(subject){
		var courseSubjectFound = false; 
		var index = 0; var subjectId = 0;
		if ($scope.course.subjects != null){
			angular.forEach($scope.course.subjects, function(courseSubject){
				if(courseSubject.subjectId == subject.subjectId){
					courseSubjectFound = true;
					$scope.course.subjects.splice(index, 1);
				}
				++index;
			});
		}
		if(courseSubjectFound == false){
			if ($scope.course.subjects == null) { $scope.course.subjects = []; }
			$scope.course.subjects.push(subject);
		}
	};
	
	$scope.saveCourse = function(){
		//console.log(stringIt($scope.course));
		$http.post('/course', $scope.course).success(function(){
			$scope.$emit('loadCourses');
			$('#coursemodal').modal('hide');
		});
	};
});

function stringIt(val) {
    return JSON.stringify(val);
};
