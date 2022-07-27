<template>
  <b-form-group class="w-75">
    <b-form-input v-model="form.naslov" required :placeholder="naslov"  class="mt-1" id="naslov"></b-form-input>
    <b-form-textarea v-model="form.tekst" no-resize required :placeholder="tekst"  class="mt-1" id="tekst"></b-form-textarea>
    <b-form-select v-model="form.kategorija" :options="kategorije"   class="mt-1"></b-form-select>
    <b-form-input v-model="form.tag" required :placeholder="'Tagovi: '"  class="mt-1" id="tag"></b-form-input>
    <b-button variant="outline-primary" class="mt-2"  @click="validation()">Izmeni</b-button>
  </b-form-group>
</template>

<script>
import kategorije from "@/views/Kategorije";
import {mapActions} from "vuex";

export default {
  name: "VestiFormIzmena",
  props:{
    kategorije: [],
    id: Number,
    naslov: String,
    tekst: String,
    fk_kategorija: Number
  },
  data() {
    return {
      selected: kategorije[0],
      form: {
        naslov: '',
        tekst: '',
        autor:'',
        tag:'',
        kategorija: null
      }}},
  methods: {
    ...mapActions([
      'updateVest'
    ]),
    validation() {
      let naslov = this.form.naslov.length
      let tekst = this.form.tekst.length
      let tag = this.form.tag.length
      let kategorija=this.form.kategorija

      let name = localStorage.name.split(" ")

      this.form.autor = name[0];

      this.form.id=this.id

      if (naslov > 0 && tekst>0 && tag>0 && kategorija!=null) {
        this.updateVest(this.form)
        this.form.naslov='';
        this.form.tekst='';
        this.form.tag='';
        this.form.kategorija=null;
      }
      else
      {
        alert("Sva polja su obavezna!")
      }
    }
  }

}
</script>

<style scoped>

</style>