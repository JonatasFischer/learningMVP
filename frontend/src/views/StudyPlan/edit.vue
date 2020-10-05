<template>
  <ContentWrapper>
    <div class="content-heading">
      <div>
        Sub Menu
        <small>Subtitle</small>
      </div>
    </div>

    <b-card title="Inline form" class="card-default">
      <b-row>
        <b-col>
          <b-form>
            <b-form-group id="input-group-1" label="Title:" label-for="title" description="Study plan title">
              <b-form-input id="title" type="text" required placeholder="Enter the study plan title"
                            v-model="studyPlan.title"></b-form-input>
            </b-form-group>
            <b-form-group id="input-group-2" label="Description:" label-for="description"
                          description="Study plan description">
              <quill-editor id="description" v-model="studyPlan.description" :options="quillEditorOptions"/>
            </b-form-group>
          </b-form>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-card title="Content" class="card-default">
            <b-form>
              <b-form-row>
                <b-form-group class="col-sm-6" id="input-group-subject" label="Subject:" label-for="subject"
                              description="Subject">
                  <v-select label="name" :filterable="false" :options="options" @search="onSearch"
                            v-model="selectedSubject">
                    <template slot="no-options">
                      Digite para pesquisar
                    </template>
                    <template slot="option" slot-scope="option">
                      <div class="d-center">
                        {{ option.name }}
                      </div>
                    </template>
                    <template slot="selected-option" slot-scope="option">
                      <div class="selected d-center">
                        {{ option.name }}
                      </div>
                    </template>
                  </v-select>


                </b-form-group>


                <b-form-group class="col-xs-12 col-sm-3" id="input-group-subject2" label="Sort Order:"
                              label-for="sortOrder" description="Subject sort order">
                  <b-form-input id="sortOrder" type="text" required v-model="selectedSequence"
                                placeholder="Please inform a sort order"></b-form-input>
                </b-form-group>
                <b-form-group class="col-xs-12 col-sm-3" id="input-group-subject3" label="Minimum Domain:"
                              label-for="minimumDomain" description="Minimum subject Domain">
                  <b-form-input id="minimumDomain" type="text" required v-model="selectedMinimumDomain"
                                placeholder="Minimum subject domain"></b-form-input>
                </b-form-group>

              </b-form-row>
            </b-form>
            <b-row>
              <b-col class="col-xs-12 col-sm-3 offset-9 my-2">
                <b-button class="w-100" variant="outline-success" v-on:click="addSubject">Add Subject</b-button>
              </b-col>
            </b-row>

            <b-row>
              <b-col class="col-xs-12">
                <b-table :items="subjects" :fields="fields" head-variant="light" :bordered="true">
                  <template v-slot:cell(name)="data">
                    {{ data.item.subject ? data.item.subject.name : '-' }}
                  </template>
                  <template v-slot:cell(actions)="data">
                    <b-button v-on:click="remove(data.item)">
                      Delete
                    </b-button>
                  </template>
                </b-table>
              </b-col>
            </b-row>
          </b-card>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-button-group>
            <b-button variant="success" v-on:click="savePlan">Save Study Plan</b-button>
            <b-button variant="danger" to="/study-plan">Cancel</b-button>
          </b-button-group>
        </b-col>
      </b-row>

    </b-card>
  </ContentWrapper>
</template>
<script>
import api from './api'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import "vue-select/dist/vue-select.css";
import _ from 'lodash'
import {quillEditor} from 'vue-quill-editor'
import vSelect from "vue-select";


export default {
  components: {
    quillEditor,
    vSelect
  },
  data() {
    return {
      selectedSubject: null,
      selectedSequence: 0,
      selectedMinimumDomain: 100,
      studyPlan: {subjects: []},
      fields: [
        {
          key: 'name',
          sortable: true,
          class: "w-50"
        },
        {
          key: 'sequence',
          sortable: true,
          class: "w-25"
        },
        {
          key: 'minimumDomain',
          sortable: true,
          class: "w-25"
        },
        {
          key: 'actions',
          sortable: true

        }
      ],
      items: [],
      options: [],
      quillEditorOptions: {
        modules: {
          toolbar: [
            [{'header': [1, 2, 3, 4, 5, 6, false]}, {'font': []}, {'size': []}],
            ['bold', 'italic', 'underline', 'strike'],
            [{'color': []}, {'background': []}],
            [{'script': 'sub'}, {'script': 'super'}],
            ['blockquote', 'code-block'],
            [{'list': 'ordered'}, {'list': 'bullet'}, {'indent': '-1'}, {'indent': '+1'}],
            [{'direction': 'rtl'}, {'align': []}],
            ['link', 'image', 'video'],
            ['clean']
          ]
        }
      },
    }
  },
  watch: {},
  methods: {
    savePlan() {
      api.save(this.studyPlan).then(this.onSaveSuccess, this.onSaveError)
    },

    onSaveSuccess() {
      this.$router.push("/study-plan");
    },
    addSubject() {
      let subject = {
        subject: this.selectedSubject,
        sequence: this.selectedSequence,
        minimumDomain: this.selectedMinimumDomain
      }
      this.studyPlan.subjects.push(subject);

    },

    onSaveError() {

    },

    remove(element) {
      const index = this.studyPlan.subjects.indexOf(element);
      if (index > -1) {
        this.studyPlan.subjects.splice(index, 1);
      }
    },

    onSearch(search, loading) {
      loading(true);
      this.search(loading, search, this);
    },

    search: _.debounce((loading, search, vm) => {
      if (search) {
        api.searchSubject(search).then(res => {
          vm.options = res;
          loading(false);
        });
      } else {
        loading(false);
      }
    }, 350)

  },

  computed: {
    subjects: {
      get() {
        return this.studyPlan.subjects || [];
      }
    }
  },

  async mounted() {
    this.studyPlan = await api.getPlan(this.$route.params.id);
  }

}
</script>

