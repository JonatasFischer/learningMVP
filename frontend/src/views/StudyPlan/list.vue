<template>
  <ContentWrapper>
    <div class="content-heading">
      <div>
        Sub Menu
        <small>Subtitle</small>
      </div>
    </div>

    <b-card title="Table Card" class="card-default">
      <b-table :items="items" :fields="fields">
        <template v-slot:cell(index)="data">
          {{ data.index + 1 }}
        </template>

        <template v-slot:cell(actions)="data">
          <router-link tag="a" :to="getRoute(data)">
            Edit
          </router-link>

          <router-link tag="span" :to="getRoute(data)" >
            <a >Edit 2</a>
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
        fields: [
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
        this.items = await api.get();

      } catch(err) {
        console.log(err)
      }
    }

  }
</script>

