<template>
  <ContentWrapper>
    <div class="content-heading">
      <div>
        Subjects
        <small>Existing subjects</small>
      </div>
    </div>
    <b-card title="Table Card" class="card-default">
      <b-table :items="items" :fields="subjectListFields">
        <template v-slot:cell(index)="data">
          {{ data.index + 1 }}
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
      subjectListFields: [
        'index',
        {
          key: 'id',
          sortable: true
        },
        {
          key: 'name',
          sortable: true
        },
        'actions'
      ],
      items: []
    }
  },
  watch: {},
  methods: {
    getRoute({item}) {
      return `/subject/edit/${item.id}/`
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

