<template>
  <div id="app">
    <div>
      <b-navbar  text="dark" type="dark" variant="dark" fixed="top">
        <b-navbar-brand class="m-lg-1" to="/">Raf News</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>

          <b-navbar-nav class="m-1">
            <b-nav-form class="">
              <b-form-input v-model="searchQuery" class="mr-sm-2 w-100 dugme" placeholder="Pretražite vesti"></b-form-input>
            </b-nav-form>
          </b-navbar-nav>
          <b-navbar-nav class="me-5">
            <b-button @click="search"  size="sm" class="my-2 my-sm-0" type="submit">Pretraga</b-button>
          </b-navbar-nav>

          <b-navbar-nav class="me-5">
            <b-nav-item to="/HomePage">Home page</b-nav-item>
            <b-nav-item v-for="item in kategorije" :key="item" v-on:click="vestiKat(item)" >{{item}}</b-nav-item>
          </b-navbar-nav>

        </b-collapse>
      </b-navbar >

    </div>

    <router-view class="stranica" />

  </div>
</template>

<script>

import {mapActions, mapState} from "vuex";

export default {
  name: 'App',
  data() {
    return {
      searchQuery: '',
    }
  },
  computed: {
    ...mapState([
      'kategorije'
    ])
  },
  methods: {
    ...mapActions([
      'getKategorije'
    ]),
    search() {
      const sq = this.searchQuery;
      if(sq!=='')
      {this.$router.push({name: 'Search', query: {q: sq}});}
      else alert("Popunite polje za pretragu")
    },
    vestiKat(kategorija)
    {
      this.$router.push({name: 'VestiKategorija', params: { kategorija: kategorija }})
    }
  },
  mounted() {
    this.getKategorije()
  }
}
</script>

<style>
*
{
  color: black;
}

body
{
  background-color: #F5F5F5;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  padding-bottom: 10px;
  height: 100vh;
  color: black;
}
.stranica {
  width: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-content: center;
}
.navbar.navbar-dark.bg-dark{
  background-color: rgba(0,41,123)!important;
}
</style>
