<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <temperatureAlert-table
            v-if="temperatureAlerts && temperatureAlerts.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:temperatureAlerts="temperatureAlerts"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-temperature-alerts="getAllTemperatureAlerts"
             >

            </temperatureAlert-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import TemperatureAlertTable from "@/components/TemperatureAlertTable";
import TemperatureAlertService from "../services/TemperatureAlertService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    TemperatureAlertTable,
  },
  data() {
    return {
      temperatureAlerts: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllTemperatureAlerts(sortBy='temperatureAlertId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await TemperatureAlertService.getAllTemperatureAlerts(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.temperatureAlerts.length) {
					this.temperatureAlerts = response.data.temperatureAlerts;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching temperatureAlerts:", error);
        }
        
      } catch (error) {
        console.error("Error fetching temperatureAlert details:", error);
      }
    },
  },
  mounted() {
    this.getAllTemperatureAlerts();
  },
  created() {
    this.$root.$on('searchQueryForTemperatureAlertsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllTemperatureAlerts();
    })
  }
};
</script>
<style></style>
