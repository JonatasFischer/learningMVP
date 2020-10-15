<template>
  <div>

    <b-row>
      <b-col>
        <b-form>
          <b-form-group id="input-group-1" label="Reference:" label-for="reference" description="Question reference">
            <b-form-input id="reference" type="text" required placeholder="Enter the question reference"
                          v-model="value.reference"></b-form-input>
          </b-form-group>
          <b-form-group id="input-group-2" label="Content:" label-for="content"
                        description="Subject description">
            <quill-editor id="content" v-model="value.data" :options="quillEditorOptions"/>
          </b-form-group>
        </b-form>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-card title="Questions" class="card-default">
          <b-row>
            <b-col class="col-xs-12 col-sm-3 offset-9 my-2">
              <b-button class="w-100" variant="outline-success" v-on:click="addOption">Add Question</b-button>
            </b-col>
          </b-row>
          <b-row>
            <b-col class="col-xs-12">
              <b-table :items="value.options" :fields="fields" head-variant="light" :bordered="true">
                <template v-slot:cell(data)="data">
                    <div v-html="data.item.data"></div>
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
          <b-button variant="success" v-on:click="saveQuestion">Save Question</b-button>
          <b-button variant="danger" to="/subject">Cancel</b-button>
        </b-button-group>
      </b-col>
    </b-row>
    <div>{{ value }}</div>
  </div>



</template>

<script>
import {quillEditor} from 'vue-quill-editor'
import vSelect from "vue-select";
import question from "@/views/Subject/questions/question";
export default {
  name: "SingleOptionQuestion",
  props: {
    value: Object
  },
  components: {
    quillEditor
  },
  data() {
    return {
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
      fields: [

        {
          key: 'data',
          sortable: true,
          class: "w-50"
        },
        {
          key: 'selected',
          sortable: true,
          class: "w-25"
        },
        {
          key: 'actions',
          sortable: true

        }

      ]
    }
  },
  methods : {
    saveQuestion() {

    }

  }
}
</script>

<style scoped>

</style>