import http from "../http-common"; 

class CapacityAlertService {
  getAllCapacityAlerts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/capacityAlert/capacityAlerts`, searchDTO);
  }

  get(capacityAlertId) {
    return this.getRequest(`/capacityAlert/${capacityAlertId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/capacityAlert?field=${matchData}`, null);
  }

  addCapacityAlert(data) {
    return http.post("/capacityAlert/addCapacityAlert", data);
  }

  update(data) {
  	return http.post("/capacityAlert/updateCapacityAlert", data);
  }
  
  uploadImage(data,capacityAlertId) {
  	return http.postForm("/capacityAlert/uploadImage/"+capacityAlertId, data);
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

export default new CapacityAlertService();
