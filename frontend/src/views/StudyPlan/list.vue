<template>
  <ContentWrapper>
    <div class="content-heading">
      <div>
        Sub Menu
        <small>Subtitle</small>
      </div>
    </div>
    <b-card title="Table Card" class="card-default">
      <b-table :items="items" :fields="studyPlanFields">
        <template v-slot:cell(index)="data">
          {{ data.index + 1 }}
        </template>
        <template v-slot:cell(description)="data">
          <div v-html="data.item.description"></div>
        </template>
        <template v-slot:cell(actions)="data">
          <router-link tag="a" :to="getRoute(data)">
            Edit
          </router-link>
        </template>
      </b-table>
    </b-card>
  </ContentWrapper>
</template>
<script>
  import api from './api'
  export default {
    data () {
      return {
        studyPlanFields: [
          'index',
          {
            key: 'id',
            sortable: true
          },
          {
            key: 'title',
            sortable: true
          },
          {
            key: 'description',
            label: 'Translated Description',
            sortable: true,
            // Variant applies to the whole column, including the header and footer
            variant: 'danger'
          },
          'actions'
        ],
        items: []
      }
    },
    watch: {},
    methods: {
      getRoute({item}) {
        return `/study-plan/edit/${item.id}/`
      }
    },
    async mounted() {
      try{
        this.items = await api.getList();

      } catch(err) {
        console.log(err)
      }
    }

  }
</script>

