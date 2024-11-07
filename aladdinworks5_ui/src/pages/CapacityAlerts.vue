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
            <capacityAlert-table
            v-if="capacityAlerts && capacityAlerts.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:capacityAlerts="capacityAlerts"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-capacity-alerts="getAllCapacityAlerts"
             >

            </capacityAlert-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CapacityAlertTable from "@/components/CapacityAlertTable";
import CapacityAlertService from "../services/CapacityAlertService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CapacityAlertTable,
  },
  data() {
    return {
      capacityAlerts: [],
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
    async getAllCapacityAlerts(sortBy='capacityAlertId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CapacityAlertService.getAllCapacityAlerts(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.capacityAlerts.length) {
					this.capacityAlerts = response.data.capacityAlerts;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching capacityAlerts:", error);
        }
        
      } catch (error) {
        console.error("Error fetching capacityAlert details:", error);
      }
    },
  },
  mounted() {
    this.getAllCapacityAlerts();
  },
  created() {
    this.$root.$on('searchQueryForCapacityAlertsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCapacityAlerts();
    })
  }
};
</script>
<style></style>
