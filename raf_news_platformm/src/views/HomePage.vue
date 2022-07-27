<template>
<div id="app" class="nice">
  <b-row class="w-75 mt-5">
    <b-col align-self="center">
      <b-table fixed hover @row-clicked="clickF" responsive show-empty ref="table" :items="items" :fields="fields" :current-page="currentPage" :per-page="0">
      </b-table>
     </b-col>
  </b-row>
</div>
</template>

<script>
export default {
  name: "HomePage",
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
          key: 'kategorija',
          label: 'Kategorija'
        },
        {
          key: 'vreme_kreiranja',
          label: 'Datum objave'
        }
      ],
      currentPage: 1,
      perPage: 10,
      totalItems: 10
    }
  },
  mounted() {
    this.fetchData().catch(error => {
      console.error(error)
    })
  },
  methods: {
    async fetchData() {
      fetch(`http://localhost:8081/cms/vesti/allHP?_page=${this.currentPage}&_limit=${this.perPage}`,{
        method: 'GET',
        headers: {
          'Content-Type': `application/json`
        }
      })
          .then(res => {
            return res.json()
          })
          .then(items => {
            this.items = items.vesti
          })
    },
    clickF(item)
    {
      this.$router.push({name: 'Vest',params: { id:item.id }})
    }
  },
  watch: {
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