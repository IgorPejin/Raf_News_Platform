<template>
  <div class="name">
    <h1 class="namee">{{name}}</h1> 
  </div>
</template>

<script>

  export default {
    name: 'Name',
    props: {
      name:String 
    }
  }

</script>

<style scoped>

  .namee {
    color: black;
    font-size: 4em;
  }
</style>