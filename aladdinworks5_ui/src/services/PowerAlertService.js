import http from "../http-common"; 

class PowerAlertService {
  getAllPowerAlerts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/powerAlert/powerAlerts`, searchDTO);
  }

  get(powerAlertId) {
    return this.getRequest(`/powerAlert/${powerAlertId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/powerAlert?field=${matchData}`, null);
  }

  addPowerAlert(data) {
    return http.post("/powerAlert/addPowerAlert", data);
  }

  update(data) {
  	return http.post("/powerAlert/updatePowerAlert", data);
  }
  
  uploadImage(data,powerAlertId) {
  	return http.postForm("/powerAlert/uploadImage/"+powerAlertId, data);
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

export default new PowerAlertService();
