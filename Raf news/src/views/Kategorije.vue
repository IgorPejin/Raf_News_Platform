<template>
  <div id="app" class="nice">
    <b-row class="w-75">
      <b-col align-self="center">
        <Header podnaslov="Kategorije"/>
        <b-table @row-clicked="vestiPoKategoriji" hover show-empty ref="table" :items="items" :fields="fields" :current-page="currentPage" :per-page="0">
          <template v-slot:cell(akcije)="{item}" >
            <b-button class="mr-2" variant="outline-primary" @click="izmeni(item.id)" >Izmeni</b-button>
            <b-button variant="outline-danger" @click="brisanje(item.id)" >Obrisi</b-button>
          </template>
        </b-table>
        <b-pagination size="md" :total-rows="totalItems" v-model="currentPage" :per-page="perPage"></b-pagination>
        <b-button variant="outline-primary" @click="novaKategorija()" >Nova kategorija</b-button>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import {mapActions} from "vuex";

export default {
  name: 'Kategorije',

  components: {
    Header
  },
  data() {
    return {
      output: null,
      items: [],
      fields: [
        {
          key: 'ime',
          label: 'Kategorija'
        },
        {
          key: 'opis',
          label: 'Opis'
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
  methods: {
    ...mapActions([
      'obrisiKategoriju'
    ]),
    async fetchData() {
      fetch(`http://localhost:8081/cms/kategorije/all/?_page=${this.currentPage}&_limit=${this.perPage}`,{
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
            this.items = items.kategorije
            this.totalItems=items.kategorijeSize
          })
    }
    ,novaKategorija() {
       this.$router.push({ name: 'Nova Kategorija'});
    },
    izmeni(id) {
      this.$router.push({name: 'Izmena Kategorije', params: { id: id }})
    },
    brisanje(id) {
      this.obrisiKategoriju(id)
    },
    vestiPoKategoriji(item)
    {
      this.$router.push({name: 'VestiKategorija', params: { kategorija: item.ime }})
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