<template>
  <div id="app">
    <div class="nice">
    <VestiFormIzmena
        :kategorije="kategorije"
        :id="vestData[0]"
        :naslov="vestData[1]"
        :tekst="vestData[2]"
        :datum="vestData[3]"
        :broj_poseta="vestData[4]"
        :autor="vestData[5]"
        :fk_kategorija="vestData[5]"
        :fk_korisnik="vestData[6]"
    />
  </div>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import VestiFormIzmena from "@/components/VestiFormIzmena";
export default {
  name: "IzmenaVesti",
  components:{
    VestiFormIzmena
  },
  methods: {
    ...mapActions([
      'getKategorije',
      'getVestData'
    ])
  },
  computed: {
    ...mapState([
      'kategorije',
      'vestData'
    ])
  },
  mounted()
  {
    this.getKategorije();
    this.getVestData(this.$route.params.id)
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