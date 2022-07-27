<template>
  <div id="app">
    <div class="nice">
    <VestiForm :kategorije="kategorije"/>
    </div>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import VestiForm from "@/components/VestiForm";

export default {
  name: "Nova vest",
  components: {VestiForm},
  methods: {
    ...mapActions([
      'getKategorije'
    ])
  },
  computed: {
    ...mapState([
      'kategorije'
    ])
  },
  mounted()
  {
    this.getKategorije();
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