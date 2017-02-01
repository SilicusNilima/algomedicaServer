app.controller('CustomerRegController', function($scope, $rootScope,localStorageService, $location,CustomerFactory
		) {
	$scope.role=localStorageService.get("role");
	$scope.isEditable=localStorageService.get("isEditable");;
	$scope.newCustomer=localStorageService.get("newCustomer");
	 $scope.userName=localStorageService.get("userName");
if(localStorageService.get('loggedIn')!=true) {
		
		$location.url('/');
	}


CustomerFactory
.validateSession()
.then(
		function(response) {
			if(response.data.status!='success') {
				localStorageService.set('loggedIn',false);
				$rootScope.isInvalid= true;
				$rootScope.errorMessage = response.data.errors.message;
				$location.url('/');
			}
			
			
			
			
		});




	if(!($rootScope.newCustomer)){
		$scope.customerDetails=localStorageService.get('customerData');
	}
	else {
		$scope.customerDetails={};
	}
	$scope.licenseDetails = function() {
		$location.url('/license');
	}
	$scope.gotoHome=function() {
		$location.url('/dashboard');
	}
	$scope.logout=function() {
		
		$rootScope.$emit('logout',true);
	}
$scope.saveCustomer=function() {
		
	if($scope.newCustomer) {
		localStorageService.set('newCustomer',$scope.customerDetails);
		$scope.newCustomer=false;

		localStorageService.set("customerData",$scope.customerDetails);
		CustomerFactory
		.createCustomer()
		.then(
				function(response) { 
					
					if(response.data.status=="success") {
						localStorageService.set("customer",response.data.data);
						localStorageService.set("customerId",response.data.data.id);
						$scope.customerDetails.id = response.data.data.id;
						localStorageService.set("customerData",$scope.customerDetails);
						
						localStorageService.set("newCustomer",false);

						$location.url('/license');
						
					}
					else if(response.data.code==1000) {
						$rootScope.isInvalid= true;
						$rootScope.errorMessage = response.data.errors.message;
						$location.url('/');
					}
					else {
						console.log(response);
						
						$scope.isInvalidData=true;
							$scope.CustomererrorMessage=response.data.errors.message;
							//show error on same page
					
					}
					
				
	 				
				});
}
	else {
		localStorageService.set('editCustomer',$scope.customerDetails);
		localStorageService.set("customerData",$scope.customerDetails);
		console.log($scope.customerDetails);
		CustomerFactory
		.updateCustomer()
		.then(
				function(response) { 
					
					if(response.data.status=="success") {
						
						$location.url('/license');
					}
					else if(response.data.code==1000) {
						$rootScope.isInvalid= true;
						$rootScope.errorMessage = response.data.errors.message;
						$location.url('/');
					}
					else {
						console.log(response);
						
						$scope.isInvalidData=true;
							$scope.CustomererrorMessage=response.data.errors.message;
							//show error on same page
					
					}
				
	 				
				});
	}
	}
$scope.closeAlert = function() {
	$scope.isInvalidData=false;
	 $("#myCustomerAlert").alert("close");
}

	});