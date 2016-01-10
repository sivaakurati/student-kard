    var schoolApp = angular.module('schoolApp', []);
    schoolApp.controller('homeController', function($scopoe){
    	
    	$scope.logout = function () {
    		localStorage.clearAll();
            window.location = '/login';
    	};
    	
    });
    

    
    
	