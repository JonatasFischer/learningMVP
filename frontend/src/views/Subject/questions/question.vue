<template>
    <single-option v-if="value.type === 'single-option'" v-model="value" v-on:save="saveQuestion" v-on:cancel="cancelQuestion"/>
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
    title: String
  },
  methods : {
    saveQuestion(e) {
      this.$emit('input', e);
      this.$emit('save', e);

    },

    cancelQuestion(e) {
      this.$emit('cancel', e);

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