var app = angular.module('batch', ['angularUtils.directives.dirPagination']);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on('loadBatchs', function(){
		$scope.loadBatchs();
	});
	
	$scope.loadBatchs = function(){
		$http.get('/batch').success(function(data){
			$scope.batchs = data;
		});
	};
	
	$scope.sort = function(keyname){
		$scope.sortKey = keyname; 
		$scope.reverse = !$scope.reverse;
	};
	
	$scope.createBatch = function(modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderBatch', {});
	};
	
	$scope.openBatch = function(batch, modalSelector){
		$(modalSelector).modal();
		$scope.$broadcast('renderBatch', angular.copy(batch));
	};
	
	$scope.deleteBatch = function(batch){
		bootbox.confirm('Are you sure you want to delete <span style=\"font-style:italic\">' + batch.batchName + '</span>', function(result) {
			if(result == true) {
				$http.delete('/batch/' + batch.batchId).success(function() {
					$scope.$emit('loadBatchs');
				});
			} else {
				return;
			}
		});
	};
	
	$scope.loadBatchs();
});

app.controller('EditCtrl', function($scope, $http) {
	$scope.$on('renderBatch', function(event, batch){
		$scope.batch = batch;
		$http.get('/course').success(function(data){
			$scope.courses = data;			
			angular.forEach($scope.courses, function(course) {
				angular.forEach(batch.courses, function(batchCourse) {
					if(batchCourse.courseName == course.courseName) course.checked = true;
				});
			});
		});
	});
	
	$scope.changeBatchCourse = function(course){
		var batchCourseFound = false; 
		var index = 0; var courseId = 0;
		if ($scope.batch.courses != null){
			angular.forEach($scope.batch.courses, function(batchCourse){
				if(batchCourse.courseId == course.courseId){
					batchCourseFound = true;
					$scope.batch.courses.splice(index, 1);
				}
				++index;
			});
		}
		if(batchCourseFound == false){
			if ($scope.batch.courses == null) { $scope.batch.courses = []; }
			$scope.batch.courses.push(course);
		}
	};
	
	$scope.saveBatch = function(){
		if($scope.batch.batchName == '' || $scope.batch.batchName == null) {
			bootbox.alert('Please enter the valid batch name!!');
			e.stopPropagation();
		}
		if($scope.batch.courses == '' || $scope.batch.courses == null) {
			bootbox.alert('Please select atleast one course name!!');
			e.stopPropagation();
		}
		for(var i=0; i < $scope.batchs.length; i++) {			
			if($scope.batchs[i].batchName == $scope.batch.batchName) {
				if($scope.batch.batchId!=null){					
					//
				} else {
					bootbox.alert('Entered batch name already exists!!!');
					e.stopPropagation();
				}
			}
		}
		$http.post('/batch', $scope.batch).success(function(){
			$('#batchmodal').modal('hide');
			bootbox.alert('Batch name '+$scope.batch.batchName+' saved successfully');
			$scope.$emit('loadBatchs');
		});
	};
});

function stringIt(val) {
    return JSON.stringify(val);
};
