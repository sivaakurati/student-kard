var app = angular.module('timetracker', []);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.$on('loadTimeTrackers', function(){
		$scope.loadTimeTrackers();
	});
	
	$scope.loadTimeTrackers = function(){
		$http.get('/timetracker').success(function(data){
			$scope.timetrackers = data;
			console.log(stringIt($scope.timetrackers));
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
	
	$scope.$on('loadCalendar', function() {
		$scope.loadCalendar();
	});
	
	$scope.loadCalendar = function() {
		//ini_events($('#external-events div.external-event'));
		
		var date = new Date();
	    var d = date.getDate(), m = date.getMonth(), y = date.getFullYear();
	    
	    $('#calendar').fullCalendar({
	    	header: {
				left: 'prev,next today',
	            center: 'title',
	            right: 'month,agendaWeek,agendaDay'
			},
			buttonText: {
	            today: 'today',
	            month: 'month',
	            week: 'week',
	            day: 'day'
			},
			slotEventOverlap: false,
	        eventLimit: true,
			events: $scope.timetrackers,
			selectable: true,
	        selectHelper: true,
			editable: true,
	        droppable: true
	    });
	};
	
	$scope.saveTimeTracker = function(){
		$http.post('/timetracker', $scope.timeTracker).success(function(){
			$scope.$emit('loadCourses');
			$scope.$emit('loadTeachers');
			$scope.$emit('loadCalendar');
			$scope.$emit('loadTimeTrackers');
		});
	};
	
	$scope.loadCourses();
	$scope.loadTeachers();
	$scope.loadCalendar();
	$scope.loadTimeTrackers();
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
		$('#startDate').hide();
		$('#endDate').hide();
		$('#startTime').hide();
		$('#endTime').hide();
	} else {
		$('#startDate').show();
		$('#endDate').show();
		$('#startTime').show();
		$('#endTime').show();
	}
};

/*$(function(){
	ini_events($('#external-events div.external-event'));
	
	var date = new Date();
    var d = date.getDate(), m = date.getMonth(), y = date.getFullYear();
	
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
		},
		buttonText: {
            today: 'today',
            month: 'month',
            week: 'week',
            day: 'day'
		},
		slotEventOverlap: false,
        eventLimit: true,
		events: $http.get('/timetracker'),
		selectable: true,
        selectHelper: true,
		editable: true,
        droppable: true,
        drop: function (date, allDay) {
        	var originalEventObject = $('div.external-event').data('eventObject');
        	var copiedEventObject = $.extend({}, originalEventObject);
        	copiedEventObject.start = date;
            copiedEventObject.allDay = allDay;
            copiedEventObject.backgroundColor = $('div.external-event').css("background-color");
            copiedEventObject.borderColor = $('div.external-event').css("border-color");
            $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
        }
	});
});*/

function stringIt(val) {
    return JSON.stringify(val);
};
