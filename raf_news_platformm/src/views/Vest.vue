<template>
     <div class="correct">
       <b-col>
         <Header :naslov="vestData[0]"/>
         <Header :mali-naslov="vestData[3]"/>
         <Header :mali-naslov="this.vestTags"/>
         <Header :mali-naslov="vestData[1]"/>
         <div class="wrapper">
          <div class="vest" >
             <p style="text-align: center;text-align: justify">{{vestData[2]}}</p>
          </div>
         </div>
         <div class="wrapperComments">
           <div v-for="comment in comments" :key="comment.id" >
             <span class="m-lg-2">{{comment.autor}}</span>
             <span>{{comment.datum_kreiranja}}</span>
             <span class="m-lg-4">{{comment.tekst}}</span>
           </div>
         </div>
         <div class="wrapper mt-5">
          <b-form-group class="w-50">
              <b-form-input v-model="form.ime" required placeholder="Ime"  class="mt-1" id="naslov"></b-form-input>
              <b-form-textarea v-model="form.tekst" no-resize required placeholder="Tekst"  class="mt-1" id="tekst"></b-form-textarea>
              <b-button variant="outline-primary" class="mt-2"  @click="validation()">Unesi komentar</b-button>
          </b-form-group>
         </div>
       </b-col>
     </div>
</template>

<script>

 import Header from "@/components/Header";
import {mapActions, mapState} from "vuex";
export default {
  name: "Vest",
  components: {Header},
  data(){
    return{
      form:
      {
        id:0,
        ime:'',
        tekst:''
      },
    }
  },
  computed: {
    ...mapState([
      'vestTags',
      'comments',
      'vestData'
    ])
  },
  methods: {
    ...mapActions([
      'getVestTags',
      'insertComment',
      'getVestData'
    ]),
    validation()
    {
      let imeLen = this.form.ime.length;
      let tekstLen = this.form.tekst.length;

      this.form.id=this.$route.params.id;
      if(imeLen>0 && tekstLen>0)
      {
        this.insertComment(this.form)
      }else{alert("Popunite sva polja")}
    },
  },
  mounted() {
    this.getVestData(this.$route.params.id);
    this.getVestTags(this.$route.params.id);
  },
  watch: {
    '$store.state.comments': function() {
      this.getVestTags(this.$route.params.id)
    },
    '$store.state.tags': function() {
      this.getVestTags(this.$route.params.id)
    },
  }
}
</script>

<style scoped>
.correct{
  height: 100vh;
  margin-top: 5rem;
}
.vest
{
  width: 70rem;
}
.wrapper
{
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin-left: 3em;
  margin-top: 2em;
  align-items: center;
}
.wrapperComments
{
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 3em;
  margin-top: 2em;
}
</style>