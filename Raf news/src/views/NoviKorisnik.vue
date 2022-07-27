<template>

  <div id="app">
    <div class="nice">
      <b-form-group class="w-75">
        <b-form-input v-model="form.ime" required :placeholder="'Ime: '"  class="mt-1" id="ime"></b-form-input>
        <b-form-input v-model="form.prezime" required :placeholder="'Prezime: '"  class="mt-1" id="prezime"></b-form-input>
        <b-form-input type="email" v-model="form.email" required :placeholder="'Email: '"  class="mt-1" id="email"></b-form-input>
        <b-form-select v-model="form.tip" :options="options" class="mt-1"></b-form-select>
        <b-form-input type="password" v-model="form.lozinka" required :placeholder="'Lozinka: '"  class="mt-1" id="lozinka"></b-form-input>
        <b-form-input type="password" v-model="form.confirmLozinka" required :placeholder="'Potvrdite lozinku: '"  class="mt-1" id="confirmlozinka"></b-form-input>
        <input type="button" class="mt-2" value="Unesi" @click="validation()">
      </b-form-group>
    </div>
  </div>

</template>

<script>
import {mapActions} from 'vuex';
export default {
  name: "NoviKorisnik",

  data() {
    return {
      options: [
        {value: null, text: 'Tip novog korisnika'},
        {value: '1', text: '1-Admin'},
        {value: '0', text: '0-Content creator'}
      ],
      form: {
        ime: '',
        prezime: '',
        email: '',
        tip: null,
        lozinka: '',
        confirmLozinka: ''
      }}},
      methods: {
        ...mapActions([
          'insertUser'
        ]),
        validation() {
          let ime = this.form.ime.length
          let prezime = this.form.prezime.length
          let email = this.form.email.length
          let tip = this.form.tip
          let lozinka = this.form.lozinka.length
          let confirmLoznika = this.form.confirmLozinka.length

          const regexEmail = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;

          if (ime > 0 && prezime> 0 && email>0 && tip!=null )
          {
            if(regexEmail.test(this.form.email) ) {
              if (lozinka > 0 && confirmLoznika > 0 && this.form.lozinka === this.form.confirmLozinka) {
                this.insertUser(this.form)
              } else {
                alert("Proverite polja za lozinku.")
              }
            } else alert("Unesite validan email!")
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