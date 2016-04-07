var app = angular.module('teacher', ['angularUtils.directives.dirPagination']);
var teacherId = window.location.href;

/**
 * teacher.html
 */

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadTeachers", function(){
		$scope.loadTeachers();
	});
	
	$scope.loadTeachers = function(){
		$http.get('/studentkard/teacher').success(function(data){
			$scope.teachers = data;
		});
	};
	
	$scope.sort = function(keyname){
		$scope.sortKey = keyname; 
		$scope.reverse = !$scope.reverse;
	};
	
	$scope.createTeacher = function(teacher){
		window.location.href = '/teacher0';
	};
	
	$scope.openTeacher = function(teacher){
		window.location.href = '/teacher' + teacher.teacherId;
	};
	
	$scope.deleteTeacher = function(subject){
		bootbox.confirm('Are you sure you want to delete <span style=\"font-style:italic\">' + teacher.teacherName + '</span>', function(result) {
			if(result == true) {
				$http.delete('/studentkard/teacher/' + teacher.teacherId).success(function() {
					$scope.$emit('loadTeachers');
				});
			} else {
				return;
			}
		});
	};
	
	$scope.loadTeachers();
});

/**
 * saveteacher.html
 */

app.controller('EditCtrl', function($scope, $http) {
	var param = teacherId.split('/')[3].replace('teacher','');
	
	$scope.$on("loadStates", function(){
		$scope.loadStates();
	});
	
	$scope.loadStates = function(){
		$http.get('/studentkard/states').success(function(data){
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
		if (param != 0) {
			$http.get('/studentkard/teacher/' + param).success(function(data){
				$scope.teacher = data;
				$scope.teacher.dob = new Date($scope.teacher.dob);
				$scope.teacher.doj = new Date($scope.teacher.doj);
				$scope.loadCitiesByStateName();
				$scope.loadLocationsByCityName();
				angular.forEach($scope.subjects, function(subject) {
					angular.forEach($scope.teacher.subjects, function(teacherSubject) {
						if(teacherSubject.subjectName == subject.subjectName) subject.checked = true;
					});
				});				
				angular.forEach($scope.courses, function(course) {
					angular.forEach($scope.teacher.courses, function(teacherCourse) {
						if(teacherCourse.courseName == course.courseName) course.checked = true;
					});
				});
			});
		}
	};
	
	$scope.$on("loadSubjects", function(){
		$scope.loadSubjects();
	});
	
	$scope.loadSubjects = function(){
		$http.get('/studentkard/subject').success(function(data){
			$scope.subjects = data;
		});
	};
	
	$scope.$on("loadDepartments", function(){
		$scope.loadDepartments();
	});
	
	$scope.loadDepartments = function(){
		$http.get('/studentkard/department').success(function(data){
			$scope.departments = data;
		});
	};
	
	$scope.$on('loadCourses', function(){
		$scope.loadCourses();
	});
	
	$scope.loadCourses = function(){
		$http.get('/studentkard/course').success(function(data){
			$scope.courses = data;
		});
	};
	
	$scope.saveTeacher = function(){
		var name = $("#name").val();
		var email = $("#email").val();
		var gender = $("#gender").val();
		var address = $("#address").val();
		var stateName = $("#stateName").val();
		var city = $("#city").val();
		var location = $("#location").val();
		var pincode = $("#pincode").val();
		var cNum = $("#cNum").val();
		var designation = $("#designation").val();
		
		if( name.trim() == '' || name == null) {
			bootbox.alert('Please enter the valid teacher name!!');
			e.stopPropagation();
		}
		if( email.trim() == '' || email == null) {
			bootbox.alert('Please enter the valid email!!');
			e.stopPropagation();
		}
		if(gender == '' || gender == null) {
			bootbox.alert('Please select the valid gender!!');
			e.stopPropagation();
		}
		if( address.trim() == '' || address == null) {
			bootbox.alert('Please enter the valid address!!');
			e.stopPropagation();
		}
		if(stateName == '' || stateName == null) {
			bootbox.alert('Please select the valid stateName!!');
			e.stopPropagation();
		}
		if(city == '' || city == null) {
			bootbox.alert('Please select the valid city!!');
			e.stopPropagation();
		}
		
		if(location == '' || location == null) {
			bootbox.alert('Please select the valid location!!');
			e.stopPropagation();
		}
		if( cNum.trim() == '' || cNum == null) {
			bootbox.alert('Please enter the valid contact number!!');
			e.stopPropagation();
		}
		if( designation.trim() == '' || designation == null) {
			bootbox.alert('Please enter the valid designation!!');
			e.stopPropagation();
		}
		if($scope.teacher.subjects == '' || $scope.teacher.subjects == null) {
			bootbox.alert('Please select atleast one subject name!!');
			e.stopPropagation();
		}
		if($scope.teacher.courses == '' || $scope.teacher.courses == null) {
			bootbox.alert('Please select atleast one course name!!');
			e.stopPropagation();
		}
		if($scope.teacher.departments == '' || $scope.teacher.departments == null) {
			bootbox.alert('Please select atleast one department name!!');
			e.stopPropagation();
		}
		
		$http.post('/studentkard/teacher', $scope.teacher).success(function(){
			window.location.href = '/teacher';
		});
	};
	
	$scope.loadCitiesByState = function() {
		var val = stringIt($scope.teacher.location.stateName).replace('"', '');
		var stateName = val.substring(0, val.length-1);
		$http.get('/studentkard/cities/' + stateName).success(function(data){
			$scope.cities = data;
		});
	};
	
	$scope.loadLocationsByCity = function() {
		var val = stringIt($scope.teacher.location.cityName).replace('"', '');
		var cityName = val.substring(0, val.length-1);
		$http.get('/studentkard/locations/' + cityName).success(function(data){
			$scope.locations = data;
		});
	};
	
	$scope.loadLocationById = function() {
		var val = stringIt($scope.teacher.location.locationId).replace('"', '');
		var locationId = val.substring(0, val.length-1);
		$http.get('/studentkard/location/' + locationId).success(function(data){
			$scope.location = data;
		});
	};
	$scope.$on('loadLoggerUserDetail', function(){
		$scope.loadLoggerUserDetail();
	});
	
	$scope.loadLoggerUserDetail = function(){
		$http.get('/studentkard/user').success(function(data){
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
	
	$scope.$on('loadCitiesByStateName', function(){
		$scope.loadCitiesByStateName();
	});
	
	$scope.loadCitiesByStateName = function() {
		var stateName = $scope.teacher.location.stateName;
		if (stateName != '') {
			$http.get('/studentkard/cities/' + stateName).success(function(data){
				$scope.cities = data;
			});
		}
	};
	
	$scope.$on('loadLocationsByCityName', function(){
		$scope.loadLocationsByCityName();
	});
	
	$scope.loadLocationsByCityName = function() {
		var cityName = $scope.teacher.location.cityName;
		if (cityName != '') {
			$http.get('/studentkard/locations/' + cityName).success(function(data){
				$scope.locations = data;
			});
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

