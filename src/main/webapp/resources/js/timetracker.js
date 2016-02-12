var app = angular.module('timetracker', []);

app.controller('ViewCtrl', function($scope, $http) {
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
	
	$scope.loadSubjectById = function() {
		var teacherId = $('#teacherId').val();
		$http.get('/teacher/' + teacherId).success(function(data){
			$scope.teacher = data;
		});
	};
	
	var currColor = "#3c8dbc"; //Red by default
	var courseName = "", teacherName = "", subjectName = "";
	var eventVal = "";
	
	$scope.saveTimeTracker = function(){
		$http.get('/course/' + $scope.timeTracker.course.courseId).success(function(data){
			$scope.course = data;
			courseName = $scope.course.courseName;
		});		
		$http.get('/teacher/' + $scope.timeTracker.teacher.teacherId).success(function(data){
			$scope.teacher = data;
			teacherName = $scope.teacher.teacherName;
			subjectName = $scope.teacher.subject.subjectName;
		});
		eventVal = "For, " + courseName + " :: " + teacherName + " teaches the subject " + subjectName;		
		console.log(eventVal);
		var event = $("<div />");
        event.css({"background-color": currColor, "border-color": currColor, "color": "#fff"}).addClass("external-event");
        event.html(eventVal);
        $('#external-events').prepend(event);
        ini_events(event);
	};
	
	$scope.loadCourses();
	$scope.loadTeachers();
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

$(function(){
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
		events: [],
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
});

function stringIt(val) {
    return JSON.stringify(val);
};
