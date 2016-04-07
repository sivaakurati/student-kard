var app = angular.module('student', ['angularUtils.directives.dirPagination']);
var studentId = window.location.href;

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on("loadStudents", function(){
		$scope.loadStudents();
	});
	
	$scope.loadStudents = function(){
		$http.get('/studentkard/student').success(function(data){
			$scope.students = data;
		});
	};
	
	$scope.sort = function(keyname){
		$scope.sortKey = keyname; 
		$scope.reverse = !$scope.reverse;
	};
	
	$scope.createParent = function(){
		window.location.href = '/parent0';
	};
	
	$scope.createStudent = function(){
		window.location.href = '/student0';
	};
	
	$scope.openStudent = function(student){
		window.location.href = '/student' + student.studentId;
	};
	
	$scope.openParent = function(parent){
		window.location.href = '/parent' + parent.parentId;
	};
	
	$scope.deleteStudent = function(student){
		bootbox.confirm('Are you sure you want to delete <span style=\"font-style:italic\">' + student.studentName + '</span>', function(result) {
			if(result == true) {
				$http.delete('/studentkard/student/' + student.studentId).success(function() {
					$scope.$emit('loadStudents');
				});
			} else {
				return;
			}
		});
	};
	
	$scope.loadStudents();
});

app.controller('EditCtrl', function($scope, $http) {
	var param = studentId.split('/')[3].replace('student','')
	
	$scope.$on("loadStates", function(){
		$scope.loadStates();
	});
	
	$scope.loadStates = function(){
		$http.get('/studentkard/states').success(function(data){
			$scope.states = data;
		});
	};
	
	$scope.getBack = function(){
		window.location.href = '/student';
	};
	
	$scope.$on('loadBatchs', function(){
		$scope.loadBatchs();
	});
	
	$scope.loadBatchs = function(){
		$http.get('/studentkard/batch').success(function(data){
			$scope.batches = data;
		});
	};
	
	$scope.$on("loadMainParents", function(){
		$scope.loadMainParents();
	});
	
	$scope.loadMainParents = function(){
		$http.get('/studentkard/parent/main').success(function(data){
			$scope.mainparents = data;
		});
	};
	
	$scope.loadParentDetailByMainParentId = function() {
		var val = stringIt($scope.student.parent.parentId).replace('"', '');
		var mainParentId = val.substring(0, val.length-1);
		$http.get('/studentkard/parent/' + mainParentId).success(function(data){
			$scope.parent = data;
			$scope.student = [];
			$scope.student.location = [];
			$scope.parent.mainParentId = $scope.parent.parentId;
			$scope.student.location.stateName = $scope.parent.location.stateName;
			$scope.student.location.cityName = $scope.parent.location.cityName;
			$scope.student.location.pinCode = $scope.parent.location.pinCode;
			$scope.student.location.locationId = $scope.parent.location.locationId;
			$scope.loadCitiesByState();
			$scope.loadLocationsByCity();
		});
	};
	
	$scope.loadCoursesByBatch = function() {
		var batchId = '';
		if ($scope.student.studentId == null) {
			var val = stringIt($scope.student.batch.batchId).replace('"', '');
			batchId = val.substring(0, val.length-1);
		} else {
			batchId = stringIt($scope.student.batch.batchId).replace('"', '');
		}
		$scope.courses = [];
		angular.forEach($scope.batches, function(batch) {
			if(batch.batchId == batchId) $scope.courses = batch.courses;
		});
	};
	
	$scope.saveStudent = function(){
		var parent = $("#parent").val();
		var stateName = $("#stateName").val();
		var city = $("#city").val();
		var location = $("#location").val();
		var pincode = $("#pincode").val();
		var batch = $("#batch").val();
		var course = $("#course").val();
		//alert(dob);
		if(parent.trim() == '' || parent == null) {
			bootbox.alert('Please select the valid parent name!!');
			e.stopPropagation();
		}
		if($scope.student.studentName == '' || $scope.student.studentName == null) {
			bootbox.alert('Please enter the valid student name!!');
			e.stopPropagation();
		}		
		if($scope.student.emailAddress == '' || $scope.student.emailAddress == null) {
			bootbox.alert('Please enter the valid email address!!');
			e.stopPropagation();
		}
		if($scope.student.gender == '' || $scope.student.gender == null) {
			bootbox.alert('Please enter the valid gender!!');
			e.stopPropagation();
		}
		if($scope.student.address == '' || $scope.student.address == null) {
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
		if(batch == '' || batch == null) {
			bootbox.alert('Please select the valid batch!!');
			e.stopPropagation();
		}
		if(course == '' || course == null) {
			bootbox.alert('Please select the valid course!!');
			e.stopPropagation();
		}
		if($scope.student.contactNo == '' || $scope.student.contactNo == null) {
			bootbox.alert('Please enter the valid contact number!!');
			e.stopPropagation();
		}
		if(pincode.trim() == '' || pincode == null) {
			bootbox.alert('Please enter the valid pincode!!');
			e.stopPropagation();
		}
		$http.post('/studentkard/student', $scope.student).success(function(){
			window.location.href = '/student';
		});
	};
	
	$scope.loadCitiesByState = function() {
		var val = stringIt($scope.student.location.stateName).replace('"', '');
		var stateName = val.substring(0, val.length-1);
		$http.get('/studentkard/cities/' + stateName).success(function(data){
			$scope.cities = data;
		});
	};
	
	$scope.loadLocationsByCity = function() {
		var val = stringIt($scope.student.location.cityName).replace('"', '');
		var cityName = val.substring(0, val.length-1);
		$http.get('/studentkard/locations/' + cityName).success(function(data){
			$scope.locations = data;
		});
	};
	
	$scope.loadLocationById = function() {
		var val = stringIt($scope.student.location.locationId).replace('"', '');
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
	
	$scope.$on("loadStudent", function(){
		$scope.loadStudent();
	});
	
	$scope.loadStudent = function(){
		if (param != 0) {
			$http.get('/studentkard/student/' + param).success(function(data){
				$scope.student = data;
				$scope.student.dob = new Date($scope.student.dob);
				$scope.student.doj = new Date($scope.student.doj);
				$scope.loadCitiesByState();
				$scope.loadLocationsByCity();
				$scope.loadCoursesByBatch();
				$scope.parent = [];
				$scope.parent.mainParentId = $scope.student.parent.parentId;
			});
		}
	};
	
	$scope.bulkUpload = function(modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderBulk', {});
	};
	
	$scope.$on('renderBulk', function(event){
		$scope.render();
	});
	
	$scope.render = function(){
		
	};
	
	$scope.loadLoggerUserDetail();
	$scope.loadStates();
	$scope.loadBatchs();
	$scope.loadMainParents();
	$scope.loadStudent();
});

function stringIt(val) {
    return JSON.stringify(val);
};

