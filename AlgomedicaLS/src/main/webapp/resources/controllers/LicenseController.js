app
.controller(
		'LicenseController',
		function($scope, $rootScope, localStorageService, $location,LicenseFactory) {

			$scope.LicenseCategory = ['Small','Medium','Large'];
			$scope.LicenseType = ['Trial','Perpetual'];
			$scope.displayForm = false;
			$scope.showModal = false;
			$scope.userName=localStorageService.get("userName");
			$scope.role = localStorageService.get("role");
			if (localStorageService.get('loggedIn') != true) {

				$location.url('/');
			}


			LicenseFactory
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



			//call getCustomers
			$scope.customerId=localStorageService.get('customerId');
			LicenseFactory
			.getCustomer()
			.then(
					function(response) { 

						if(response.data.data!=null) {

							$scope.customerList = response.data.data;
							for(var i in $scope.customerList) {
								if($scope.customerId== $scope.customerList[i].id) {

									$scope.customer = 	$scope.customerList[i];

								}
							}
							if($scope.customer.licenseDetails!=null) {
								$scope.licenseListRaw = $scope.customer.licenseDetails;
								$scope.licenseList=[];
								if ($scope.licenseListRaw.length > 0) {
									var k=0;
									for(var i in $scope.licenseListRaw) {
										if($scope.licenseListRaw[i].lsStatus==1) {
											$scope.licenseList[k]= $scope.licenseListRaw[i];
											k++;
										}
									}






									$scope.licensePresent = true;
								} else {
									$scope.licensePresent = false;
								}
							}	
						}



					});


			$scope.showForm = false;
			$scope.showNewMachineForm = function() {


				LicenseFactory
				.validateSession()
				.then(
						function(response) {
							if(response.data.status!='success') {
								localStorageService.set('loggedIn',false);
								$rootScope.isInvalid= true;
								$rootScope.errorMessage = response.data.errors.message;
								$location.url('/');
							}
							else {
								$scope.licenseDetails = {};
								localStorageService.set("isLicenseEditable", false);
								$scope.isLicenseEditable = false;
								localStorageService.set("newMachine", true);
								$scope.newMachine = true;
								$scope.displayForm = true;
								$scope.licenseForm.$setUntouched();
								$scope.licenseForm.$setPristine();
								$scope.licenseForm.$submitted = false;
								localStorageService.set("license",
										$scope.licenseDetails);
							}
						});
			};





			$scope.hideForm = function() {
				
				LicenseFactory
				.validateSession()
				.then(
						function(response) {
							if(response.data.status!='success') {
								localStorageService.set('loggedIn',false);
								$rootScope.isInvalid= true;
								$rootScope.errorMessage = response.data.errors.message;
								$location.url('/');
							}
							else {
				
				
				
				$scope.isInvalidData=false;
				$scope.licenseForm.$setUntouched();
				$scope.licenseForm.$setPristine();
				$scope.licenseForm.$submitted = false;
				$scope.displayForm = false;
							}});
			};

			$scope.gotoHome = function() {
				$location.url('/dashboard');
			}

			$scope.logout=function() {

				$rootScope.$emit('logout',true);
			}
			$scope.back = function() {

				$location.url('/Customer');
			}
			$scope.view = function(licenseId) {
				
				
				
				
				LicenseFactory
				.validateSession()
				.then(
						function(response) {
							if(response.data.status!='success') {
								localStorageService.set('loggedIn',false);
								$rootScope.isInvalid= true;
								$rootScope.errorMessage = response.data.errors.message;
								$location.url('/');
							}
							else {
				
				localStorageService.set("isLicenseEditable", true);
				$scope.isLicenseEditable = true;
				localStorageService.set("newMachine", false);
				$scope.newMachine = false;

				for ( var i in $scope.licenseList) {
					if ($scope.licenseList[i].id == licenseId) {
						localStorageService.set("license",
								$scope.licenseList[i]);
						$scope.licenseDetails = {};
						$scope.licenseDetails.id = $scope.licenseList[i].id;
						$scope.licenseDetails.lsmacAddress = $scope.licenseList[i].lsMacAddress;
						$scope.licenseDetails.lsmodelNumber = $scope.licenseList[i].lsModelNumber;
						$scope.licenseDetails.lsmodelName = $scope.licenseList[i].lsModelName;
						$scope.licenseDetails.lsregenerationReason = $scope.licenseList[i].lsreason;
						if($scope.licenseList[i].lsLicenseType=="T") {
							$scope.licenseDetails.lsType = "Trial";
						}
						else if($scope.licenseList[i].lsLicenseType=="P") {
							$scope.licenseDetails.lsType = "Perpetual";
						}
						$scope.licenseDetails.lsCost = $scope.licenseList[i].lsCost;

						$scope.licenseDetails.lsCategory = $scope.licenseList[i].lsCategory;
						$scope.licenseDetails.lsOpsEmail = $scope.licenseList[i].lsOpsEmail;
						$scope.licenseDetails.lsOpsName = $scope.licenseList[i].lsOpsName;
						$scope.licenseDetails.lsOpsPhone = $scope.licenseList[i].lsOpsPhone;
						$scope.licenseDetails.lsExipryDays = $scope.licenseList[i].lsExpiryDays;

					}

				}
				$scope.licenseForm.$setUntouched();
				$scope.licenseForm.$setPristine();
				$scope.licenseForm.$submitted = false;
				$scope.displayForm = true;
			}});
			}
			$scope.edit = function(licenseId) {
				
				LicenseFactory
				.validateSession()
				.then(
						function(response) {
							if(response.data.status!='success') {
								localStorageService.set('loggedIn',false);
								$rootScope.isInvalid= true;
								$rootScope.errorMessage = response.data.errors.message;
								$location.url('/');
							}
							else {
				
				
				
				localStorageService.set("isLicenseEditable", false);
				$scope.isLicenseEditable = false;
				localStorageService.set("newMachine", false);
				$scope.newMachine = false;
				for ( var i in $scope.licenseList) {
					if ($scope.licenseList[i].id == licenseId) {
						localStorageService.set("license",
								$scope.licenseList[i]);
						$scope.licenseDetails = {};
						$scope.licenseDetails.id = $scope.licenseList[i].id;
						$scope.licenseDetails.lsmacAddress = $scope.licenseList[i].lsMacAddress.replace(/\:/g,"");
						$scope.licenseDetails.lsmodelNumber = $scope.licenseList[i].lsModelNumber;
						$scope.licenseDetails.lsmodelName = $scope.licenseList[i].lsModelName;
						$scope.licenseDetails.lsregenerationReason = $scope.licenseList[i].lsreason;

						if($scope.licenseList[i].lsLicenseType=="T") {
							$scope.licenseDetails.lsType = "Trial";
						}
						else if($scope.licenseList[i].lsLicenseType=="P") {
							$scope.licenseDetails.lsType = "Perpetual";
						}
						$scope.licenseDetails.lsCost = $scope.licenseList[i].lsCost;
						$scope.licenseDetails.lsCategory = $scope.licenseList[i].lsCategory;
						$scope.licenseDetails.lsOpsEmail = $scope.licenseList[i].lsOpsEmail;
						$scope.licenseDetails.lsOpsName = $scope.licenseList[i].lsOpsName;
						$scope.licenseDetails.lsOpsPhone = $scope.licenseList[i].lsOpsPhone;
						$scope.licenseDetails.lsExipryDays = $scope.licenseList[i].lsExpiryDays;

					}

				}
				$scope.licenseForm.$setUntouched();
				$scope.licenseForm.$setPristine();
				$scope.licenseForm.$submitted = false;
				$scope.displayForm = true;  }});
			}
			$scope.addNewMachine=function() {
				LicenseFactory
				.validateSession()
				.then(
						function(response) {
							if(response.data.status!='success') {
								localStorageService.set('loggedIn',false);
								$rootScope.isInvalid= true;
								$rootScope.errorMessage = response.data.errors.message;
								$location.url('/');
							}
							else {
				
				
				
				

				if($scope.newMachine) {
					$scope.licenseDetails.lsregenerationReason ="New Machine Request";
					$scope.licenseDetails.customerId=localStorageService.get("customerId");
					if($scope.licenseDetails.lsType=="Trial") {
						$scope.licenseDetails.lsType='T';
					}
					else if($scope.licenseDetails.lsType=="Perpetual") {
						$scope.licenseDetails.lsType='P';
					}
					$scope.licenseDetails.lsmacAddress=$scope.licenseDetails.lsmacAddress.substr(0, 17);
					localStorageService.set('newMachineDetails',$scope.licenseDetails);
					localStorageService.set('macAddress',$scope.licenseDetails.lsmacAddress);
					console.log($scope.licenseDetails);

					LicenseFactory
					.addMachine()
					.then(
							function(response) { 

								if(response.data.status=="success") {

									$scope.licenseListRaw = response.data.data.licenseDetails;
									var j=0;
									$scope.macAddress= localStorageService.get('macAddress');
									$scope.licenseList=[];
									for(var i in $scope.licenseListRaw) {
										if($scope.licenseListRaw[i].lsStatus==1) {
											$scope.licenseList[j]=$scope.licenseListRaw[i];
											j++;
										}
										if($scope.macAddress==$scope.licenseListRaw[i].lsMacAddress.replace(/\:/g,"")){
											$scope.LicenseKey=$scope.licenseListRaw[i].lsLicenseKey;
										}


									}

									$scope.displayForm = false;
									$scope.licensePresent = true;
									$scope.showModal = !$scope.showModal;
									//$location.url('/license');
								}
								else if(response.data.code==1000) {
									$rootScope.isInvalid= true;
									localStorageService.set('loggedIn',false);
									$rootScope.errorMessage = response.data.errors.message;
									$location.url('/');
								}
								else {
									$scope.isInvalidData=true;
									$scope.LicenseerrorMessage=response.data.errors.message;
									if($scope.licenseDetails.lsType!=undefined) {
										if($scope.licenseDetails.lsType=='P') {
											$scope.licenseDetails.lsType='Perpetual';
										}else if($scope.licenseDetails.lsType=='T') {
											$scope.licenseDetails.lsType='Trial';

										}
									}





								}



							});
				}
				else {
					$scope.licenseDetails.customerId=localStorageService.get("customerId");
					if($scope.licenseDetails.lsType=="Trial") {
						$scope.licenseDetails.lsType='T';
					}
					else if($scope.licenseDetails.lsType=="Perpetual") {
						$scope.licenseDetails.lsType='P';
					}
					localStorageService.set('editMachineDetails',$scope.licenseDetails);
					localStorageService.set('macAddress',$scope.licenseDetails.lsmacAddress);

					LicenseFactory
					.editMachine()
					.then(
							function(response) { 

								if(response.data.status=="success") {

									$scope.licenseListRaw = response.data.data.licenseDetails;
									$scope.licenseList=[];
									var j=0;
									$scope.macAddress= localStorageService.get('macAddress');

									for(var i in $scope.licenseListRaw) {
										if($scope.licenseListRaw[i].lsStatus==1) {
											$scope.licenseList[j]=$scope.licenseListRaw[i];
											j++;
										}
										if($scope.macAddress==$scope.licenseListRaw[i].lsMacAddress.replace(/\:/g,"")){
											$scope.LicenseKey=$scope.licenseListRaw[i].lsLicenseKey;
										}


									}

									$scope.displayForm = false;

									$scope.showModal = !$scope.showModal;
									//$location.url('/license');
								}
								else if(response.data.code==1000) {
									$rootScope.isInvalid= true;
									$rootScope.errorMessage = response.data.errors.message;
									$location.url('/');
								}
								else {
									console.log(response);

									$scope.isInvalidData=true;
									$scope.LicenseerrorMessage=response.data.errors.message;
									if($scope.licenseDetails.lsType!=undefined) {
										if($scope.licenseDetails.lsType=='P') {
											$scope.licenseDetails.lsType='Perpetual';
										}else if($scope.licenseDetails.lsType=='T') {
											$scope.licenseDetails.lsType='Trial';

										}
									}
									//show error on same page

								}



							});
				}
			}});
			}
			$scope.closeAlert = function() {
				$scope.isInvalidData=false;
				$("#myLicenseAlert").alert("close");
			}
			$scope.download = function(licenseId) {
				
				LicenseFactory
				.validateSession()
				.then(
						function(response) {
							if(response.data.status!='success') {
								localStorageService.set('loggedIn',false);
								$rootScope.isInvalid= true;
								$rootScope.errorMessage = response.data.errors.message;
								$location.url('/');
							}
							else {
				
				
				
				
				localStorageService.set("downloadId",licenseId);
				LicenseFactory.download();
							}
						});
			}
			
			
			
			
		});

app.directive('modal', function () {
	return {
		template: '<div class="modal fade">' + 
		'<div class="modal-dialog">' + 
		'<div class="modal-content">' + 
		'<div class="modal-header">' + 
		'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
		'<h4 class="modal-title">Generated License Key</h4>' + 
		'</div>' + 
		'<div class="modal-body" ng-transclude></div>' + 
		'</div>' + 
		'</div>' + 
		'</div>',
		restrict: 'E',
		transclude: true,
		replace:true,
		scope:true,
		link: function postLink(scope, element, attrs) {
			scope.$watch(attrs.visible, function(value){
				if(value == true)
					$(element).modal('show');
				else
					$(element).modal('hide');
			});

			$(element).on('shown.bs.modal', function(){
				scope.$apply(function(){
					scope.$parent[attrs.visible] = true;
				});
			});

			$(element).on('hidden.bs.modal', function(){
				scope.$apply(function(){
					scope.$parent[attrs.visible] = false;
				});
			});
		}
	};
});