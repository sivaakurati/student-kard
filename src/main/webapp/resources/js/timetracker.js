var app = angular.module('timetracker', ['ui.calendar']);

app.controller('ViewCtrl', function($scope, $http){
	
	$scope.$on('loadCourses', function(){
		$scope.loadCourses();
	});
	
	$scope.loadCourses = function(){
		$http.get('/course').success(function(data){
			$scope.courses = data;
		});
	};
	
	$scope.$on('loadTeachers', function(){
		$scope.loadTeachers();
	});
	
	$scope.loadTeachers = function(){
		$http.get('/teacher').success(function(data){
			$scope.teachers = data;
		});
	};
	
	$scope.loadSubjectsById = function() {
		var teacherId = $('#teacherId').val();
		$scope.subjects = [];
		angular.forEach($scope.teachers, function(teacher) {
			if (teacher.teacherId == teacherId) {
				angular.forEach(teacher.subjects, function(teacherSubject) {
					$scope.subjects.push(teacherSubject);
				});
			}
		});
	};
	
	$scope.$on('loadTimeTrackerEvents', function(){
		$scope.loadTimeTrackerEvents();
	});
	
	$scope.events = [];	
	$scope.loadTimeTrackerEvents = function() {
		$scope.events = [];
		$http.get('/timetracker/events').success(function(data){
			for(var i=0;i<data.length;i++) {
				$scope.events.push({
					idx: data[i].idx,
					title: data[i].title,
					allDay: data[i].allDay,
					start: new Date(data[i].start),
					end:  new Date(data[i].end),
				});
			}
		});
	};
	
	 var date = new Date();
	 var d = date.getDate();
	 var m = date.getMonth();
	 var y = date.getFullYear();
	 var currentView = "month";
	
    /* config object */
    $scope.uiConfig = {
    	calendar:{
    		height: 450,
    		editable: true,
    		header:{
    			left: 'prev,next today',
	            center: 'title',
	            right: 'month,agendaWeek,agendaDay'
    		},
    		buttonText: {
	            today: 'today',
	            month: 'month',
	            week: 'week',
	            day: 'day'
			}
    	}
    };
    
    $scope.saveTimeTracker = function(){
		var course = $("#courseId").val();
		var teacher = $("#teacherId").val();
		var subject = $("#subjectId").val();
		
		if(course == '' || course == null) {
			bootbox.alert('Please select the valid course!!');
			e.stopPropagation();
		}
		if(teacher == '' || teacher == null) {
			bootbox.alert('Please select the valid teacher!!');
			e.stopPropagation();
		}
		if(subject == '' || subject == null) {
			bootbox.alert('Please select the valid subject!!');
			e.stopPropagation();
		}
		
		$http.post('/timetracker', $scope.timeTracker).success(function(){
			$scope.$emit('loadCourses');
			$scope.$emit('loadTeachers');
			$scope.$emit('loadTimeTrackerEvents');
		});
	};
	
	$scope.loadCourses();
	$scope.loadTeachers();
	$scope.loadTimeTrackerEvents();
    
    /* event sources array*/
	$scope.eventSources = [$scope.events];
});

function ini_events(ele){
	if (ele.length != 0) {
		ele.each(function(){
			var eventObject = { title: $.trim($('div.external-event').text()) };
			$('div.external-event').data('eventObject', eventObject);
			$('div.external-event').draggable({
				zIndex: 1070,
				revert: true, // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
		})
	}
};

function allDayClicked() {
	var allDayCheckBoxVal = $("#allDay").is(':checked')?1:0;
	if (allDayCheckBoxVal == 1) {
		//$('#startDate').hide();
		$('#endDate').hide();
		//$('#startTime').hide();
		$('#endTime').hide();
	} else {
		//$('#startDate').show();
		$('#endDate').show();
		//$('#startTime').show();
		$('#endTime').show();
	}
};

function stringIt(val) {
    return JSON.stringify(val);
};
