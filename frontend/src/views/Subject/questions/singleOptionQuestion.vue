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
        <b-card class="card-default" v-if="currentOption !== null">
          <b-form>
            <b-form-row>
              <b-form-checkbox
                  id="checkbox-1"
                  v-model="currentOption.expectedValue"
                  name="expectedValue"
                  value="checked"
                  unchecked-value="unchecked"
              >
                Expected Status
              </b-form-checkbox>
            </b-form-row>

            <b-form-row>

              <b-form-group id="option-data-group" label="Option content:" label-for="option-data"
                            description="Option content">
                <quill-editor id="option-data-editor" v-model="currentOption.data"
                              :options="quillEditorOptions"/>
              </b-form-group>
            </b-form-row>
          </b-form>
          <b-row>
            <b-col>
              <b-button-group class="pull-left">
                <b-button variant="success" v-on:click="saveOption">Save Option</b-button>
                <b-button variant="danger" v-on:click="currentOption = null">Cancel</b-button>
              </b-button-group>
            </b-col>
          </b-row>
        </b-card>

        <b-card title="Options" class="card-default" v-if="currentOption === null">
          <b-row>
            <b-col class="col-xs-12 col-sm-3 offset-9 my-2">
              <b-button class="w-100" variant="outline-success" v-on:click="addOption">Add Option</b-button>
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
      currentOption: null,
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
            ['link', 'image', 'video', 'formula'],
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
  methods: {
    saveQuestion() {
      this.$emit('input', this.value);
      this.$emit('save', this.value);
    },

    addOption() {
      this.currentOption = {data: null, expected_value: null, id: false}
    },

    saveOption() {
      if (!this.currentOption.id) {
        let min = this.value.options.reduce((accumulator, currentValue) => currentValue.id < accumulator ? currentValue.id : acumulator, -1);
        min--;
        this.currentOption.id = min;
        this.value.options.push(this.currentOption);
        this.currentOption = null;
      } else {
        let index = this.value.options.findIndex((el) => el.id === this.currentOption.id);
        if (index !== -1) {
          this.value.options[index] = this.currentOption;
        }
      }
    }

  }
}
</script>

<style scoped>

</style>