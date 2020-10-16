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
            <b-row>
              <b-col class="col-xs-12 col-sm-3 offset-9 my-2">
                <b-modal size="xl" id="option-modal" :title="title" v-model="showQuestionModal" hide-footer="true">
                  <question :title="questionTitle" type="single-option" v-model="currentQuestion"
                            v-on:save="saveQuestion" v-on:cancel="cancelQuestion"/>
                </b-modal>
                <b-button class="w-100" variant="outline-success" v-on:click="addQuestion">Add Question</b-button>
              </b-col>
            </b-row>


            <b-row>
              <b-col class="col-xs-12">
                <b-table :items="questions" :fields="questionsFields" head-variant="light" :bordered="true">
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
            <b-button variant="success" v-on:click="saveSubject">Save Subject</b-button>
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
import question from "./questions/question"
import _ from 'lodash'
import {quillEditor} from 'vue-quill-editor'
import vSelect from "vue-select";


export default {
  components: {
    quillEditor,
    vSelect,
    question
  },
  data() {
    return {
      selectedSubject: null,
      selectedSequence: 0,
      selectedMinimumDomain: 100,
      subject: {questions: []},
      questionsFields: [
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
      showQuestionModal: false,
      questionTitle: null,
      currentQuestion: {}
    }
  },
  watch: {},
  methods: {
    saveSubject() {
      this.subject.content = this.content;
      this.subject.questions = this.questions;
      api.save(this.subject).then(this.onSaveSuccess, this.onSaveError)
    },

    onSaveSuccess() {
      this.$router.push("/subject");
      this.currentQuestion = null;
    },

    cancelQuestion() {
      this.showQuestionModal = false;
    },

    saveQuestion() {
      if (!this.currentQuestion.id) {
        debugger;
        let m = this.subject.questions.reduce((min, el) => el.id < min ? el.id : min, -1);
        m--;
        this.currentQuestion.id = m;
        this.subject.questions.push(this.currentQuestion);
        this.currentQuestion = null;
      } else {
        let index = this.subject.questions.findIndex((el) => el.id === this.currentOption.id);
        if (index !== -1) {
          this.subject.questions[index] = this.currentOption;
        }
      }
      this.showQuestionModal = false;
    },

    addQuestion() {
      this.questonTitle = "New Option";
      this.currentQuestion = {
        type: "single-option",
        reference: "",
        options: [],
        data: ""
      };
      this.showQuestionModal = true;
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
        return this.subject.content || {data: null};
      }
    }
  },

  async mounted() {
    this.subject = await api.getSubject(this.$route.params.id);
  }

}
</script>

