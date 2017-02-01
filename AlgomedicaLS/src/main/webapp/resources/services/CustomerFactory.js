app.factory('CustomerFactory',['$http','localStorageService','ngAuthSettings', function ($http,localStorageService,ngAuthSettings) {
	var customerfactory = {};
	
	customerfactory.createCustomer = function () {
		return $http.post("customers",
				localStorageService.get('newCustomer')
			);
}
	customerfactory.updateCustomer = function () {
		return $http.put("customers",
				localStorageService.get('editCustomer')
			);
}
	customerfactory.validateSession = function () {
		return $http.get("session"
			);
	}
	return customerfactory;
}]);
