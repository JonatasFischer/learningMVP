<template>
  <b-modal size="xl"  id="option-modal" :title="title" v-model="showModal">
    <single-option v-if="value.type === 'single-option'" v-model="value" v-on:save="saveQuestion"/>
  </b-modal>
</template>

<script>
import {quillEditor} from "vue-quill-editor";
import singleOption from "./singleOptionQuestion";
import vSelect from "vue-select";

export default {
  components: {
    quillEditor,
    singleOption,
    vSelect
  },
  name: "Question",
  props: {
    value : Object,
    show: Boolean,
    title: String,
  },
  methods : {
    saveQuestion(e) {
      debugger;
      this.$emit('input', e);
      this.$emit('save', e);

    }

  },
  computed: {
    showModal: {
      get() {
        return this.show;
      },
      set(newValue) {

      }
    },
    content: {
      get() {
        return this.subject.content || {data:null};
      }
    }
  },
}
</script>

<style scoped>

</style>