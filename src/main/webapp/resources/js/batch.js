var app = angular.module('batch', []);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on('loadBatchs', function(){
		$scope.loadBatchs();
	});
	
	$scope.loadBatchs = function(){
		$http.get('/batch').success(function(data){
			$scope.batchs = data;
		});
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
		$http.post('/batch', $scope.batch).success(function(){
			$scope.$emit('loadBatchs');
			$('#batchmodal').modal('hide');
		});
	};
});

function stringIt(val) {
    return JSON.stringify(val);
};
