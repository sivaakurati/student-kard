    var scotchApp = angular.module('scotchApp', ['ngRoute']);
    // configure our routes
    scotchApp.config(['$routeProvider',function($routeProvider) {
        $routeProvider

        .when('/', {
                templateUrl : '/profile.jsp',
                controller  : 'maiController'
            })

            .when('/calendar', {
                templateUrl : '/calendar.jsp',
                controller  : 'aboutController'
            })
            
            .otherwise({
            redirectTo: '/'
         });
    }]);
    

    // create the controller and inject Angular's $scope
    scotchApp.controller('maiController', function($scope) {
        // create a message to display in our view
    });

    scotchApp.controller('aboutController', function($scope) {
    });
    
    
	var app = angular.module('myApp', ['ngRoute']);
	
function MyController($scope, $http){	
	
	app.config(['$routeProvider',
	               function($routeProvider) {
	                   $routeProvider.
	                       when('/route1', {
	                           templateUrl: 'angular-route-template-1.jsp',
	                           controller: 'RouteController'
	                       }).
	                       when('/route2', {
	                           templateUrl: 'angular-route-template-2.jsp',
	                           controller: 'RouteController'
	                       }).
	                       otherwise({
	                           redirectTo: '/'
	                       });
	               }]);
	
	
		$scope.getPersonDataFromServer = function() {	    	
	    	$http({method: 'GET', url: 'springAngularJS'}).
	        success(function(data, status, headers, config) {
	        	$scope.person = data;
	        }).
	        error(function(data, status, headers, config) {
	          // called asynchronously if an error occurs
	          // or server returns response with an error status.
	        });
	    
	    };
	    
	    
	    $scope.getPersonDetails = function(){
	    	
	    	$http({method: 'GET', url: 'persons'}).
	        success(function(data, status, headers, config) {
	        	$scope.person2 = data;
	        }).
	        error(function(data, status, headers, config) {
	          // called asynchronously if an error occurs
	          // or server returns response with an error status.
	        });
	    	
	    	
	    };
	    
	    $scope.addPersonDetails = function($location){
	    	var postData = {'name': $scope.name, 'country': $scope.country};
	    	
	    	var config = {
					headers: {  'Content-Type': 'application/json; charset=UTF-8' },
				
				};
				var url = 'person/add';

				var response = $http.post(url, postData, config); 
	    	
				response.success(function(response){
					
					$location.path('/dflkdflkd');
	   
	        }).
	        error(function(data, status, headers, config) {
	          // called asynchronously if an error occurs
	          // or server returns response with an error status.
	        });
	    	
	    	
	    };
	    
	};