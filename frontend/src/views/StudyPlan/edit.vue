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
            <b-form-group id="input-group-2" label="Description:" label-for="description" description="Study plan description">
              <quill-editor id="description" v-model="studyPlan.description" :options="quillEditorOptions"/>
            </b-form-group>
          </b-form>
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
import {quillEditor} from 'vue-quill-editor'


export default {
  components: {
    quillEditor
  },
  data() {
    return {
      studyPlan: {},
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
      items: [],
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

    onSaveError() {

    }

  },
  async mounted() {
    this.studyPlan = await api.getPlan(this.$route.params.id);
  }

}
</script>

