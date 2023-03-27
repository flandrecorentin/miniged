<template>
    <div id="action-header">
        <ButtonAction text="Documents" path="/document" idButton="documents"/>
        <ButtonAction text="Types" path="/type" idButton="types"/>
        <ButtonAction text="Schemas" path="/schema" idButton="schemas"/>
    </div>
</template>

<script>

import ButtonAction from './ButtonAction.vue'
export default {
    name: 'ActionHeader',
    components:{
        ButtonAction,
    },
    methods:{

    },
}
</script>

<style scoped>
#action-header{
    /* background-color: #ffc4ac; */
    background: linear-gradient(74deg, rgba(255,162,124,1) 0%, rgba(255,184,154,1) 46%, rgba(255,196,171,1) 100%); 
    display: flex;
    border-right: solid 6px black;
    border-left: solid 6px black;
    border-bottom: solid 6px black;
    height: 80px;

    padding: 20px 10%;
    justify-content: space-around;
}
</style>