<template>
  <div id="app" class="nice">
    <b-row class="w-75">
      <b-col align-self="center">
        <Header podnaslov="Korisnici"/>
        <b-table hover show-empty ref="table" :items="items" :fields="fields" :current-page="currentPage" :per-page="0">
          <template v-slot:cell(akcije)="{item}" >
              <b-button class="mr-2" variant="outline-primary" @click="izmeni(item.id)" >Izmeni</b-button>
              <b-button v-if=" item.tip === 0" variant="outline-danger" @click="aktivacijaClick(item.id)" >Aktivacija</b-button>
          </template>
        </b-table>

        <b-pagination size="md" :total-rows="totalItems" v-model="currentPage" :per-page="perPage"></b-pagination>
        <b-button variant="outline-primary" @click="noviKorisnik()" >Novi korisnik</b-button>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Header from '@/components/Header.vue'
import {mapActions, mapState} from "vuex";

export default {
  name: 'Korisnici',

  components: {
    Header
  },
  data() {
    return {
      output: null,
      items: [],
      fields: [{
        key: 'id',
        label: 'Korisnik id'
      },
        {
          key: 'email',
          label: 'Email'
        },
        {
          key: 'ime',
          label: 'Ime'
        },
        {
          key: 'prezime',
          label: 'Prezime'
        },
        {
          key: 'tip',
          label: 'Tip'
        },
        {
          key: 'status',
          label: 'Status'
        },
        { key: "akcije" }
      ],
      currentPage: 1,
      perPage: 4,
      totalItems: 0
    }
  },
  mounted() {
    this.fetchData().catch(error => {
      console.error(error)
    })
  },
  computed: {
    ...mapState([
      'token',
      'admin'
    ])
  },
  methods: {
    ...mapActions([
      'aktivacija'
    ]),
    async fetchData() {
      fetch(`http://localhost:8081/cms/korisnici/all/?_page=${this.currentPage}&_limit=${this.perPage}`,{
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${localStorage.token}`,
          'Content-Type': `application/json`
        }
      })
          .then(res => {
            return res.json()
          })
          .then(items => {
            if(!items.msg)
            {
              this.items = items.korisnici
              this.totalItems=items.korisniciSize
            }
            else
            {
              alert(items.msg)
              this.$router.push({name: 'Home'})
            }
          })
    }
    ,noviKorisnik() {
      this.$router.push({ name: 'Novi Korisnik'});
    },
    izmeni(id) {
      this.$router.push({name: 'Izmena Korisnika', params: { id: id }})
    },
    aktivacijaClick(id) {
     this.aktivacija(id);
    },
  },
  watch: {
    currentPage: {
      handler: function (value) {
        this.fetchData().catch(error => {
          console.error(error)
        })
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