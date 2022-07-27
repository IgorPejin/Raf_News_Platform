<template>
  <div class="nice">
    <b-form-group ref="form" class="w-75">
      <b-form-input v-model="form.ime"  required :placeholder="ime" class="mt-1" id="ime"></b-form-input>
      <b-form-input v-model="form.prezime" required  :placeholder="prezime" class="mt-1" id="prezime"></b-form-input>
      <b-form-input type="email" v-model="form.email" required :placeholder="email"  class="mt-1" id="email"></b-form-input>
      <b-form-select v-model="form.tip" :options="options"  class="mt-1"></b-form-select>

      <b-button variant="outline-primary" class="mt-2"  @click="validation()">Izmeni</b-button>
    </b-form-group>
  </div>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "KorisnikData",
  props: {
    id: Number,
    email: String,
    ime: String,
    prezime:String,
    tip: Number,
    status: Number
  },
  data() {
    return {
      options: [
        {value: null, text: 'Tip novog korisnika'},
        {value: '1', text: '1-Admin'},
        {value: '0', text: '0-Content creator'}
      ],
      form: {
        id:'',
        ime: '',
        prezime: '',
        email: '',
        tip: null
      }
    }
  },
  methods: {
    ...mapActions([
      'updateUser'
    ]),
    validation() {
      let ime = this.form.ime.length
      let prezime = this.form.prezime.length
      let email = this.form.email.length
      let tip = this.form.tip

      this.form.id=this.id

      const regexEmail = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;

      if (ime > 0 && prezime > 0 && email > 0 && tip != null ) {
        if (regexEmail.test(this.form.email)) {
          this.updateUser(this.form)
        } else alert("Unesite validan email!")
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