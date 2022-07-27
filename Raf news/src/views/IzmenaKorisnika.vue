<template>
  <div id="app">
    <KorisnikData
        :id="korisnikData[0]"
        :email="korisnikData[1]"
        :ime="korisnikData[2]"
        :prezime="korisnikData[3]"
        :tip="korisnikData[4]"
        :status="korisnikData[5]"
    />

  </div>
</template>

<script>
import KorisnikData from '@/components/KorisnikData';
import { mapActions,mapState } from 'vuex';
export default {
  name: "IzmenaKorisnika",
  components:{
    KorisnikData
  },
  computed: {
    ...mapState([
      'korisnikData'
    ])
  },
  mounted()
  {
    this.getKorisnikData(this.$route.params.id);
  },
  methods: {
    ...mapActions([
      'getKorisnikData'
    ])
  }
}
</script>

<style scoped>

</style>