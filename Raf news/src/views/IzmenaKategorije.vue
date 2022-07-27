<template>
  <div id="app">
    <KategorijaData
        :id="kategorijaData[0]"
        :ime="kategorijaData[1]"
        :opis="kategorijaData[2]"
    />
  </div>
</template>

<script>
import KategorijaData from "@/components/KategorijaData";
import {mapActions, mapState} from "vuex";

export default {
  name: "IzmenaKategorije",
  components:{
    KategorijaData
  },
  computed: {
    ...mapState([
      'kategorijaData'
    ])
  },
  mounted()
  {
    this.getKategorijaData(this.$route.params.id);
  },
  methods: {
    ...mapActions([
      'getKategorijaData'
    ])
  }
}
</script>

<style scoped>

</style>