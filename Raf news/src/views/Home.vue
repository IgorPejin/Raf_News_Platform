<template>
  <div id="app">

  <b-container fluid="lg" class="kontejner h-50">
          <b-col align-h="center">
            <Header v-if="!token"
            naslov="Raf News CMS!"
            podnaslov="Log in as admin or content creator"
            />
            <b-col v-else>
              <Header naslov="Welcome"/>
            </b-col>
          </b-col>
  </b-container>

  </div>
</template>

<script>
  import {mapState} from 'vuex';
  import Header from '@/components/Header.vue'
  import Footer from '@/components/Footer.vue'
  import Name from '@/components/Name.vue'

  export default {
    name: 'Home',
    components: {
      Header,
      Footer,
      Name
    },

     computed: {
      ...mapState([
        'token'
      ])
    }

  }
</script>
<style scoped>
.center
{
  text-align: center;
  font-size: 2.5em;
}
</style>
