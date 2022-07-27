<template>
  <div id="app" class="nice">
    <b-row class="w-75">
      <b-col align-self="center">
        <Header :podnaslov="this.$route.params.kategorija"/>
        <b-table fixed hover @row-clicked="clickF" responsive show-empty ref="table" :items="items" :fields="fields" :current-page="currentPage" :per-page="0">
        </b-table>
        <b-pagination size="md" :total-rows="totalItems" v-model="currentPage" :per-page="perPage"></b-pagination>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Header from "@/components/Header";

export default {
  name: "VestiKategorija",
  components:{
    Header
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
          key: 'tekst',
          label: 'Tekst',
          tdClass:'fix'
        },
        {
          key: 'vreme_kreiranja',
          label: 'Datum objave'
        }
      ],
      currentPage: 1,
      perPage: 5,
      totalItems: 0
    }
  },
  mounted() {
    this.fetchData().catch(error => {
      console.error(error)
    })
  },
  methods: {
    async fetchData() {
      fetch(`http://localhost:8081/cms/vesti/kategorija/`+this.$route.params.kategorija+`?_page=${this.currentPage}&_limit=${this.perPage}`,{
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
            this.items = items.vesti
            this.totalItems=items.vestiSize
          })
    },
    clickF(item)
    {
      this.$router.push({name: 'Vest',params: { id:item.id }})
    }
  },
  watch: {
    $route: {
      handler: function (value) {
        console.log(value)
        this.fetchData().catch(error => {
          console.error(error)
        })
      }
    },
    currentPage: {
      handler: function (value) {
        console.log(value)
        this.fetchData().catch(error => {
          console.error(error)
        })
      }
    }
  }
}
</script>

<style >
.nice
{
  display: flex;
  align-items: center;
  justify-content: center;
}
.fix
{
  margin-left: 20px;
  overflow: hidden;
  max-width: 50ch;
  text-overflow: ellipsis;
  white-space: nowrap;
  display: block;
  font-style: italic;
  padding: 3px 20px;
  border-radius: 8px;
  direction: ltr;
}
</style>