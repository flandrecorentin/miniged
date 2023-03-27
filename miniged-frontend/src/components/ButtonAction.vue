<template>
    <router-link class="link" :to="path">{{text}}</router-link>
</template>

<script>
export default {
    name: 'ButtonAction',
    props:{
        text:{
            type: String,
        },
        idButton:{
            type: String,
            default: "salut",
        },
        path:{
            type: String,
        },
    },
    methods:{ 

    }
}
</script>

<style scoped>
.link{
  background-color: #C0C0C0;

  border: 4px solid black;
  border-radius: 20px;
  box-shadow: rgba(0, 0, 0, 0.3) 3px 3px;

  color:black;

  text-align: center;

  font-size: 1.5rem;
  text-decoration:none;
  display: inline-block;
  
  cursor: pointer;

  padding: 20px 20px 10px 20px;
}
.link:hover{
    /* transform: scale(1.05); */
    color: white;
    background-color: #606060;
    transition-duration: 0.3s;
}
</style>