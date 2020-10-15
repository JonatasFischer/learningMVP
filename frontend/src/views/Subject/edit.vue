<template>
  <ContentWrapper>
    <div class="content-heading">
      <div>
        Sub Menu
        <small>Subtitle</small>
      </div>
    </div>

    <b-card title="Subject" class="card-default">
      <b-row>
        <b-col>
          <b-form>
            <b-form-group id="input-group-1" label="Name:" label-for="title" description="Subject name">
              <b-form-input id="name" type="text" required placeholder="Enter the subject name"
                            v-model="subject.name"></b-form-input>
            </b-form-group>
            <b-form-group id="input-group-2" label="Content:" label-for="content"
                          description="Subject description">
              <quill-editor id="content" v-model="content.data" :options="quillEditorOptions"/>
            </b-form-group>
          </b-form>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-card title="Questions" class="card-default">
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
                <b-button class="w-100" variant="outline-success" v-on:click="addQuestion">Add Subject</b-button>
              </b-col>
            </b-row>

            <b-row>
              <b-col class="col-xs-12">
                <b-table :items="questions" :fields="fields" head-variant="light" :bordered="true">
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
            <b-button variant="success" v-on:click="savePlan">Save Subject</b-button>
            <b-button variant="danger" to="/subject">Cancel</b-button>
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
      subject: {questions: []},
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
      this.subject.content = this.content;
      this.subject.questions = this.questions;
      api.save(this.subject).then(this.onSaveSuccess, this.onSaveError)
    },

    onSaveSuccess() {
      this.$router.push("/subject");
    },
    addQuestion() {
      let question = {
        subject: this.selectedSubject,
        sequence: this.selectedSequence,
        minimumDomain: this.selectedMinimumDomain
      }
      this.subject.questions.push(question);

    },

    onSaveError() {

    },

    remove(element) {
      const index = this.subject.questions.indexOf(element);
      if (index > -1) {
        this.subject.subjects.splice(index, 1);
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
    questions: {
      get() {
        return this.subject.questions || [];
      }
    },
    content: {
      get() {
        return this.subject.content || {data:null};
      }
    }
  },

  async mounted() {
    this.subject = await api.getSubject(this.$route.params.id);
  }

}
</script>

