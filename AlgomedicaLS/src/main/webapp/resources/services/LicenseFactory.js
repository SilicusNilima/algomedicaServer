app.factory('LicenseFactory',['$http','localStorageService','ngAuthSettings', function ($http,localStorageService,ngAuthSettings) {
	var licensefactory = {};
	
	licensefactory.addMachine = function () {
		return $http.post("customers/license",
				localStorageService.get('newMachineDetails')
			);
}
	licensefactory.editMachine = function () {
		return $http.put("customers/license",
				localStorageService.get('editMachineDetails')
			);
		
}

	licensefactory.getCustomer = function () {
		return $http.get("customers"
		);
	}
	licensefactory.download = function () {
		window.open("customers/license/"+localStorageService.get("downloadId")
	);
		
	}
	licensefactory.validateSession = function () {
		return $http.get("session"
			);
}
	
	return licensefactory;
}]);
