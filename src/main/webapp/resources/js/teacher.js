var app = angular.module('teacher', []);
var teacherId = window.location.href;

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadTeachers", function(){
		$scope.loadTeachers();
	});
	
	$scope.loadTeachers = function(){
		$http.get('/teacher').success(function(data){
			$scope.teachers = data;
		});
	};
	
	$scope.createTeacher = function(){
		window.location.href = '/teacher/0';
	};
	
	$scope.openTeacher = function(teacher){
		window.location.href = '/teacher/' + teacher.teacherId;
	};
	
	$scope.deleteTeacher = function(subject){
		bootbox.confirm('Are you sure you want to delete <span style=\"font-style:italic\">' + teacher.teacherName + '</span>', function(result) {
			if(result == true) {
				$http.delete('/teacher/' + teacher.teacherId).success(function() {
					$scope.$emit('loadTeachers');
				});
			} else {
				return;
			}
		});
	};
	
	$scope.loadTeachers();
});

app.controller('EditCtrl', function($scope, $http) {
	var param = teacherId.split('/')[4]
	
	$scope.$on("loadStates", function(){
		$scope.loadStates();
	});
	
	$scope.loadStates = function(){
		$http.get('/states').success(function(data){
			$scope.states = data;
		});
	};
	
	$scope.$on("loadTeacher", function(){
		$scope.loadTeacher();
	});
	
	$scope.getBack = function(){
		window.location.href = '/teacher';
	};
	
	$scope.loadTeacher = function(){
		$http.get('/teacher/' + param).success(function(data){
			$scope.teacher = data;
			console.log(stringIt($scope.teacher));
		});
	};
	
	$scope.$on("loadSubjects", function(){
		$scope.loadSubjects();
	});
	
	$scope.loadSubjects = function(){
		$http.get('/subject').success(function(data){
			$scope.subjects = data;
		});
	};
	
	$scope.$on("loadDepartments", function(){
		$scope.loadDepartments();
	});
	
	$scope.loadDepartments = function(){
		$http.get('/department').success(function(data){
			$scope.departments = data;
		});
	};
	
	$scope.$on('loadCourses', function(){
		$scope.loadCourses();
	});
	
	$scope.loadCourses = function(){
		$http.get('/course').success(function(data){
			$scope.courses = data;
		});
	};
	
	$scope.saveTeacher = function(){
		$http.post('/teacher', $scope.teacher).success(function(){
			$('#teachermodal').hide();
			window.location.href = '../../teacher';
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
	$scope.$on('loadLoggerUserDetail', function(){
		$scope.loadLoggerUserDetail();
	});
	
	$scope.loadLoggerUserDetail = function(){
		$http.get('/user').success(function(data){
			$scope.loggerUser = data;
		});
	};
	
	$scope.changeTeacherSubject = function(subject){
		var teacherSubjectFound = false; 
		var index = 0; var subjectId = 0;
		if ($scope.teacher.subjects != null){
			angular.forEach($scope.teacher.subjects, function(teacherSubject){
				if(teacherSubject.subjectId == subject.subjectId){
					teacherSubjectFound = true;
					$scope.teacher.subjects.splice(index, 1);
				}
				++index;
			});
		}
		if(teacherSubjectFound == false){
			if ($scope.teacher.subjects == null) { $scope.teacher.subjects = []; }
			$scope.teacher.subjects.push(subject);
		}
	};
	
	$scope.changeTeacherCourse = function(course){
		var teacherCourseFound = false; 
		var index = 0; var subjectId = 0;
		if ($scope.teacher.courses != null){
			angular.forEach($scope.teacher.courses, function(teacherCourse){
				if(teacherCourse.courseId == course.courseId){
					teacherCourseFound = true;
					$scope.teacher.courses.splice(index, 1);
				}
				++index;
			});
		}
		if(teacherCourseFound == false){
			if ($scope.teacher.courses == null) { $scope.teacher.courses = []; }
			$scope.teacher.courses.push(course);
		}
	};
	
	$scope.changeTeacherDepartment = function(department){
		var teacherDepartmentFound = false; 
		var index = 0; var subjectId = 0;
		if ($scope.teacher.departments != null){
			angular.forEach($scope.teacher.departments, function(teacherDepartment){
				if(teacherDepartment.departmentId == department.departmentId){
					teacherDepartmentFound = true;
					$scope.teacher.departments.splice(index, 1);
				}
				++index;
			});
		}
		if(teacherDepartmentFound == false){
			if ($scope.teacher.departments == null) { $scope.teacher.departments = []; }
			$scope.teacher.departments.push(department);
		}
	};
	
	$scope.loadLoggerUserDetail();
	$scope.loadStates();
	$scope.loadTeacher();
	$scope.loadSubjects();
	$scope.loadDepartments();
	$scope.loadCourses();
});

function stringIt(val) {
    return JSON.stringify(val);
};

