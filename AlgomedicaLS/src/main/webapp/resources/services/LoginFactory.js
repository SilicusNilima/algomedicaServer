app.factory('LoginFactory',['$http','localStorageService','ngAuthSettings', function ($http, localStorageService,ngAuthSettings) {
	var loginfactory = {};
	
	loginfactory.loginUser = function () {
		return $http.post("login",
				localStorageService.get('loginCredentials')
			);
}
	
	return loginfactory;
}]);

