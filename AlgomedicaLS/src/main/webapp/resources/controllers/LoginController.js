app.controller('LoginController',function($scope, $rootScope,localStorageService,LoginFactory, $location
		) {
	
	if(localStorageService.get('loggedIn')==true) {
		
		$location.url('/dashboard');
	}
	else {
	$rootScope.$emit('Notlogged-in',true);
	$rootScope.loggedIn=false;
	}
	
	$scope.loginDetails = {};
	
	$scope.login = function() {
	console.log($scope.loginDetails);
	if($scope.loginDetails.username!=undefined && $scope.loginDetails.password!=undefined ) {
		
	localStorageService.set('loginCredentials', {
		username : $scope.loginDetails.username,
		password :  $scope.loginDetails.password,
		
	});
	
	
	LoginFactory
	.loginUser()
	.then(
			function(response) { 
				
				if(response.data.status=="success") {
					$rootScope.isInvalid= false;
					localStorageService.set('loginCredentials', {});
					localStorageService.set('loggedIn',true);
					$scope.userName=response.data.data.firstName+" "+response.data.data.lastName;
					localStorageService.set("userName",$scope.userName);
					localStorageService.set("role",response.data.data.role.name);
					$location.url('/dashboard');
				}
				else {
					$rootScope.isInvalid= true;
					$scope.errorMessage = response.data.errors.message;
				}
				
			
 				
			});
	}
	else {
		$rootScope.isInvalid= true;
		$scope.errorMessage = "Required Fields cannot be blank";
	}
	}
	
	$scope.closeAlert = function() {
		$rootScope.isInvalid= false;
		 $("#myAlert").alert("close");
		 
	}
});