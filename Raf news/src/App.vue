<template>
  <div id="app">
    <div >
      <b-navbar toggleable="sm" text="dark" type="dark" variant="dark" fixed="top">
        <b-navbar-brand to="/">Raf News</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>

          <b-navbar-nav>
            <b-nav-form>
              <b-form-input v-model="searchQuery" class="mr-sm-2 w-100 dugme" placeholder="Pretražite vesti"></b-form-input>
            </b-nav-form>
          </b-navbar-nav>
          <b-navbar-nav>
            <b-button @click="search"  size="sm" class="my-2 my-sm-0" type="submit">Pretraga</b-button>
          </b-navbar-nav>

          <b-navbar-nav class="ml-auto">

            <b-nav-item disabled v-if="token"> {{nameU}} </b-nav-item>
            <b-nav-item v-if="token && admin=='1'"  to="/Korisnici">Korisnici</b-nav-item>
            <b-nav-item v-if="token" to="/Kategorije">Kategorije</b-nav-item>
            <b-nav-item v-if="token" to="/Vesti">Vesti</b-nav-item>

            <b-nav-item v-if="!token" to="/login">Log In</b-nav-item>
            <b-nav-item v-else @click="logout()" to="/">Log Out</b-nav-item>

          </b-navbar-nav>
        </b-collapse>
      </b-navbar>

    </div>

    <router-view class="stranica" />

  </div>
</template>

<script>
import {mapState, mapMutations, mapActions} from 'vuex';

  export default {
    name: 'App',

    computed: {
      ...mapState([
        'token',
        'admin',
        'nameU'
      ])
    },


    data() {
      return {
        namePr : this.nameU,
        searchQuery: '',
        searchQueryPrev:'',
        refresh: 0,
      }
    },

    mounted() {
      if (localStorage.token) {
        this.setToken(localStorage.token);
        let obj = JSON.parse(Buffer.from(localStorage.token.split('.')[1], 'base64').toString());
        let tip = obj.tip;
        let name = obj.ime+" "+obj.prezime;
        this.setAdmin(tip);
        this.setName(name)
      }
    },
    methods: {
      ...mapMutations([
        'removeToken',
        'setToken',
        'setAdmin',
        'setStatus',
        'setName'
      ]),
      search(e) {
            const sq = this.searchQuery;
            this.$router.push({name: 'Search', query: {q: sq}});
            window.location.reload();
      },
      logout() {
        this.removeToken();
      }
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
    background-color: rgba(65,41,123)!important;
  }

</style>
