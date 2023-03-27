<template>
    <button @click="onClick(this.type)">{{type}}</button>
</template>

<script>
export default {
    name: "ButtonChoiceType",
    props:{
        type : String,
    },
    methods:{
        onClick(type){
            console.log('|type|'+ type)
            this.$emit('click-button-type', type)
        }
    },  
}
</script>

<style scoped>
button{
    background-color: #B0B0B0;
    border: 4px solid black;
    border-radius: 20px;
    box-shadow: rgba(0, 0, 0, 0.3) 3px 3px;

    color: black;

    padding: 10px 20px;

    text-align: center;

    text-decoration: none;

    margin:  10px 15px;

    cursor: pointer;

    min-width: 300px;

    font-size: 22px;
}
button:hover{
    /* color: white;
    background-color: #606060; */
    transition-duration: 0.3s;
    transform: scale(1.05);
}
</style>