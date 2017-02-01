app.controller('DashboardController', function($scope, $rootScope,localStorageService, $location,DashboardFactory
		) {
	$scope.customerList={};
	$scope.customerDetails={};
	 $scope.sortKey = 'id';  
	 $scope.reverse=true;
		$scope.areCustomers = false;
	$scope.role=localStorageService.get("role");
	 $scope.userName=localStorageService.get("userName");
	if(localStorageService.get('loggedIn')!=true) {
		
		$location.url('/');
	}
	else {
	$rootScope.$emit('logged-in',true);
	

	DashboardFactory
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

	DashboardFactory
	.getCustomer()
	.then(
			function(response) {
				if(response.data.status!='error') {
				
				if(response.data.data.length>0) {
					
					$scope.customerList = response.data.data;
					
					$scope.areCustomers = true;
					$scope.customerSize=$scope.customerList.length;
					
					for(var i in $scope.customerList) {
						var count=0;
						if($scope.customerList[i].address.address2!=''&&$scope.customerList[i].address.address2!=null) {
							$scope.customerList[i].address2=','+$scope.customerList[i].address.address2;
						}
						else {
							$scope.customerList[i].address2='';
						}
						if( $scope.customerList[i].licenseDetails.length>0) {
							
							for(var j in $scope.customerList[i].licenseDetails) {
								
								if($scope.customerList[i].licenseDetails[j].lsStatus==1) {
									count++;
								}
								
							}
							
							$scope.customerList[i].licenseCount=count;
							
							
						}
						else {
							$scope.customerList[i].licenseCount=0;
						}
					}
				
				}
				else {
					
					$scope.areCustomers = false;
					
				}
				
			
			}	
				else {
					if(response.data.errors.code=1000) {
					
						localStorageService.set('loggedIn',false);
						$rootScope.isInvalid= true;
						$rootScope.errorMessage = response.data.errors.message;
						$location.url('/');
					}
					
				}
			});
	
	}
	$scope.view = function(customerId) {
		localStorageService.set("isEditable",true);
		localStorageService.set("newCustomer",false);
	
		for(var i in $scope.customerList) {
			if($scope.customerList[i].id==customerId) {
				//$rootScope.customerDetails =$scope.customerList[i];
				//$rootScope.customerDetails.id=$scope.customerList[i].id;
				localStorageService.set("customerId",$scope.customerList[i].id);
				localStorageService.set("customer",$scope.customerList[i]);
				$scope.customerDetails={};
				$scope.customerDetails.id=$scope.customerList[i].id;
				$scope.customerDetails.orgEmail=$scope.customerList[i].orgEmail;
				$scope.customerDetails.orgFax=$scope.customerList[i].orgFax;
				$scope.customerDetails.orgName=$scope.customerList[i].orgName;
				$scope.customerDetails.orgPersonalEmail=$scope.customerList[i].orgPersonEmail;
				$scope.customerDetails.orgPersonName=$scope.customerList[i].orgPersonName;
				$scope.customerDetails.orgPersonPhone=$scope.customerList[i].orgPersonPhone;
				$scope.customerDetails.orgPhone=$scope.customerList[i].orgPhone;
				$scope.customerDetails.postalCode=$scope.customerList[i].address.city.zipCode;
				$scope.customerDetails.addressLine1=$scope.customerList[i].address.address1;
				$scope.customerDetails.addressLine2=$scope.customerList[i].address.address2;
				$scope.customerDetails.cityName=$scope.customerList[i].address.city.cityName;
				$scope.customerDetails.stateName=$scope.customerList[i].address.city.state.stateName;
				$scope.customerDetails.countryName=$scope.customerList[i].address.city.state.country.countryName;
		
			}
			
		}
		localStorageService.set("customerData",$scope.customerDetails);
		$location.url('/Customer');
	}
	$scope.edit = function(customerId) {
		localStorageService.set("isEditable",false);
		localStorageService.set("newCustomer",false);
		
		for(var i in $scope.customerList) {
			if($scope.customerList[i].id==customerId) {
			//	$rootScope.customerDetails.id=$scope.customerList[i].id;
				$scope.customerDetails={};
				localStorageService.set("customerId",$scope.customerList[i].id);
				localStorageService.set("customer",$scope.customerList[i]);
				$scope.customerDetails.id=$scope.customerList[i].id;
				$scope.customerDetails.orgEmail=$scope.customerList[i].orgEmail;
				$scope.customerDetails.orgFax=$scope.customerList[i].orgFax;
				$scope.customerDetails.orgName=$scope.customerList[i].orgName;
				$scope.customerDetails.orgPersonalEmail=$scope.customerList[i].orgPersonEmail;
				$scope.customerDetails.orgPersonName=$scope.customerList[i].orgPersonName;
				$scope.customerDetails.orgPersonPhone=$scope.customerList[i].orgPersonPhone;
				$scope.customerDetails.orgPhone=$scope.customerList[i].orgPhone;
				$scope.customerDetails.postalCode=$scope.customerList[i].address.city.zipCode;
				$scope.customerDetails.addressLine1=$scope.customerList[i].address.address1;
				$scope.customerDetails.addressLine2=$scope.customerList[i].address.address2;
				$scope.customerDetails.cityName=$scope.customerList[i].address.city.cityName;
				$scope.customerDetails.stateName=$scope.customerList[i].address.city.state.stateName;
				$scope.customerDetails.countryName=$scope.customerList[i].address.city.state.country.countryName;
			}
			
		}
		localStorageService.set("customerData",$scope.customerDetails);
		$location.url('/Customer');
	}
	$scope.customerReg = function() {
		localStorageService.set("isEditable",false);
		localStorageService.set("newCustomer",true);
		
	
		$rootScope.customerDetails={};
		localStorageService.set("customerData",$scope.customerDetails);
		$location.url('/Customer');
	}
	$scope.logout=function() {
		
		$rootScope.$emit('logout',true);
	}
	$scope.sort = function(keyname){
        $scope.sortKey = keyname;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
    }
});

app.filter('orderObjectBy', function() {
	  return function(items, field, reverse) {
	    var filtered = [];
	    angular.forEach(items, function(item) {
	      filtered.push(item);
	    });
	    filtered.sort(function (a, b) {
	      return (a[field] > b[field] ? 1 : -1);
	    });
	    if(reverse) filtered.reverse();
	    return filtered;
	  };
});

