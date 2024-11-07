import http from "../http-common"; 

class TemperatureAlertService {
  getAllTemperatureAlerts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/temperatureAlert/temperatureAlerts`, searchDTO);
  }

  get(temperatureAlertId) {
    return this.getRequest(`/temperatureAlert/${temperatureAlertId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/temperatureAlert?field=${matchData}`, null);
  }

  addTemperatureAlert(data) {
    return http.post("/temperatureAlert/addTemperatureAlert", data);
  }

  update(data) {
  	return http.post("/temperatureAlert/updateTemperatureAlert", data);
  }
  
  uploadImage(data,temperatureAlertId) {
  	return http.postForm("/temperatureAlert/uploadImage/"+temperatureAlertId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new TemperatureAlertService();
