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
            <powerAlert-table
            v-if="powerAlerts && powerAlerts.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:powerAlerts="powerAlerts"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-power-alerts="getAllPowerAlerts"
             >

            </powerAlert-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PowerAlertTable from "@/components/PowerAlertTable";
import PowerAlertService from "../services/PowerAlertService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PowerAlertTable,
  },
  data() {
    return {
      powerAlerts: [],
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
    async getAllPowerAlerts(sortBy='powerAlertId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PowerAlertService.getAllPowerAlerts(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.powerAlerts.length) {
					this.powerAlerts = response.data.powerAlerts;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching powerAlerts:", error);
        }
        
      } catch (error) {
        console.error("Error fetching powerAlert details:", error);
      }
    },
  },
  mounted() {
    this.getAllPowerAlerts();
  },
  created() {
    this.$root.$on('searchQueryForPowerAlertsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPowerAlerts();
    })
  }
};
</script>
<style></style>
