<template>
  <div id="app" class="nice">
    <b-row class="w-75">
      <b-col align-self="center">
        <Header podnaslov="Vesti"/>
        <b-table hover  @row-clicked="noviTab" responsive show-empty ref="table" :items="items" :fields="fields" :current-page="currentPage" :per-page="0">
          <template v-slot:cell(akcije)="{item}" >
            <b-button class="mr-2" variant="outline-primary" @click="izmeni(item.id)" >Izmeni</b-button>
            <b-button variant="outline-danger" @click="brisanje(item.id)" >Obrisi</b-button>
          </template>
        </b-table>
        <b-pagination size="md" :total-rows="totalItems" v-model="currentPage" :per-page="perPage"></b-pagination>
        <b-button variant="outline-primary" @click="novaVest()" >Nova vest</b-button>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import {mapActions} from "vuex";

export default {
  name: 'Vesti',

  components: {
    Header,
    Footer
  },
  data() {
    return {
      output: null,
      items: [],
      fields: [
        {
          key: 'naslov',
          label: 'Naslov'
        },
        {
          key: 'autor',
          label: 'Autor'
        },
        {
          key: 'vreme_kreiranja',
          label: 'Datum'
        },
        {key: "akcije"}
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
  methods: {
    ...mapActions([
      'obrisiVest'
    ]),
    async fetchData() {
      fetch(`http://localhost:8081/cms/vesti/all/?_page=${this.currentPage}&_limit=${this.perPage}`,{
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
            console.log(items)
            this.items = items.vesti
            this.totalItems=items.vestiSize
          })
    }
    ,novaVest() {
      this.$router.push({name: 'NovaVest'})
    },
    izmeni(id) {
      this.$router.push({name: 'IzmenaVesti', params: { id: id }})
    },
    brisanje(id) {
       this.obrisiVest(id)
    },
    noviTab(item)
    {
      window.open("http://localhost:8000/vest/"+item.id, "_blank");
    }
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