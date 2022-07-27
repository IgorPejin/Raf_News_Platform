<template>
  <div id="app">
    <div class="nice">
      <b-form-group class="w-75">
        <b-form-input v-model="form.ime" required :placeholder="'Ime: '"  class="mt-1" id="ime"></b-form-input>
        <b-form-input v-model="form.opis" required :placeholder="'Opis: '"  class="mt-1" id="prezime"></b-form-input>
        <input type="button" class="mt-2" value="Unesi" @click="validation()">
      </b-form-group>
    </div>
  </div>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "NovaKategorija",
  data() {
    return {
      form: {
        ime: '',
        opis: ''
      }}},
  methods: {
    ...mapActions([
      'insertKategorija'
    ]),
    validation() {
      let ime = this.form.ime.length
      let opis = this.form.opis.length

      if (ime > 0 && opis>0) {
        this.insertKategorija(this.form)
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
.nice
{
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>