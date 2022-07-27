<template>
  <div class="nice">
    <b-form-group ref="form" class="w-75">
      <b-form-input v-model="form.ime"  required :placeholder="ime" class="mt-1" id="ime"></b-form-input>
      <b-form-input v-model="form.opis" required  :placeholder="opis" class="mt-1" id="opis"></b-form-input>
      <b-button variant="outline-primary" class="mt-2"  @click="validation()">Izmeni</b-button>
    </b-form-group>
  </div>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "KategorijaData",
  props: {
    id: Number,
    ime: String,
    opis: String
  },
  data() {
    return {
      form: {
        id:'',
        ime: '',
        opis: ''
      }
    }
  },
  methods: {
    ...mapActions([
      'updateKategorija'
    ]),
    validation() {
      let ime = this.form.ime.length
      let opis = this.form.opis.length

      this.form.id=this.id

      console.log(ime , opis)

      if (ime > 0 && opis > 0 ) {
          this.updateKategorija(this.form)
      } else {
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