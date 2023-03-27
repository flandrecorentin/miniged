<template>
    <button @click="onClick(this.idButton)" :id="idButton">{{text}}</button>
</template>

<script>
export default{
    name: 'Button',
    props:{
        text:{
            type: String,
            default: "default value button"
        },
        idButton:{
            type: String,
        },
    },
    methods:{
        onClick(id){
            console.log('!')
            this.$emit('click-button', id)
        },
    },

}
</script>

<style scoped>
button{
    background-color: #B0B0B0;
    border: 4px solid black;
    border-radius: 20px;
    box-shadow: rgba(0, 0, 0, 0.3) 3px 3px 3px;

    color: black;

    padding: 10px 15px;

    text-align: center;

    text-decoration: none;

    margin:  25px 15px;

    cursor: pointer;

    font-size: 20px;
}

button:hover{
  color: white;
  background-color: #606060;
  transition-duration: 0.3s;
}
</style>