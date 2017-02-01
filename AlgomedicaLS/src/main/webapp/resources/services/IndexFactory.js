app.factory('IndexFactory',['$http','localStorageService','ngAuthSettings', function ($http, localStorageService,ngAuthSettings) {
	var indexfactory = {};
	
	indexfactory.logout = function () {
		return $http.get("logout"
			);
}
	
	return indexfactory;
}]);

