app.controller('IndexController', function($scope, $rootScope, $location,localStorageService,$window,IndexFactory
		) {
	
	console.log("In IndexController");
	
	
	$rootScope.$on('logged-in', function() {
		$rootScope.loggedIn = true;
		$scope.bodyClass="";
	});
	
	$rootScope.$on('Notlogged-in', function() {
		$rootScope.loggedIn = false;
		$scope.bodyClass="bg";
	});
	$rootScope.$on("logout", function(){

		IndexFactory
		.logout()
		.then(
				function(response) { 
					
					if(response.data.status=="success") {
						
					      $rootScope.loggedIn = false;
					      $window.localStorage.clear();
					     // $ionicHistory.clearCache();
					     // $ionicHistory.clearHistory();
					      $location.url('/');
					}
					
				
	 				
				});
	   
	});
});

