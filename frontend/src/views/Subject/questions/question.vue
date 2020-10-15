<template>
  <b-modal size="xl"  id="option-modal" :title="title" v-model="show">
    <single-option v-if="value.type === 'single-option'" v-model="value"/>
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
  }
}
</script>

<style scoped>

</style>