var app = angular.module('timetracker', []);

app.controller('ViewCtrl', function($scope, $http) {
	$scope.loadTimeTrackerEvents = function(){
		$http.get('/timetracker/events').success(function(data){
			$scope.timetrackerEvents = data;
			console.log(stringIt($scope.timetrackerEvents));
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
			//events: [{"eventId":1,"title":"Course101 - Sekhar - English","start":"2016-02-18T10:00:00.000+05:30","end":"2016-02-18T11:00:00.000+05:30","allDay":false}]
			events: $scope.loadTimeTrackerEvents()
	    });
	};
	
	$scope.saveTimeTracker = function(){
		$http.post('/timetracker', $scope.timeTracker).success(function(){
			$scope.$emit('loadCourses');
			$scope.$emit('loadTeachers');
			$scope.$emit('loadCalendar');
			$scope.$emit('loadTimeTrackerEvents');
		});
	};
	
	$scope.loadCourses();
	$scope.loadTeachers();
	$scope.loadCalendar();
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

function stringIt(val) {
    return JSON.stringify(val);
};
