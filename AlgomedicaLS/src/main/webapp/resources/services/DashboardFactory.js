app.factory('DashboardFactory',['$http','localStorageService','ngAuthSettings', function ($http, localStorageService,ngAuthSettings) {
	var dashboardfactory = {};
	
	dashboardfactory.getCustomer = function () {
		return $http.get("customers"
			);
}
	dashboardfactory.validateSession = function () {
		return $http.get("session"
			);
}
	
	return dashboardfactory;
}]);

