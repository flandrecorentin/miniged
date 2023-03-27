<template>
    <div id="type-style" @click="clickType(type.name)">
        <h3>Nom: {{type.name}}</h3>
        <div>
            <p id="text-schemas">
                Schemas:  
            </p>
            <div :key="schema.id" v-for="schema in type.schemas">
                    {{schema.name}}
            </div> 
        </div>
    </div>
</template>

<script>
export default {
    name: 'Type',
    props:{
        type: Object,
    },
    methods: {
        clickType(id){
            console.log("GET LE TYPE "+ id)
            this.$emit('detail', id)
        },
    },
}
</script>

<style scoped>
#type-style{
    background-color: #f59b95ab;
    margin: 10px 15px;
    padding: 5px 20px 20px 20px;
    border: solid rgb(0, 0, 0, 0.8) 4px;
    box-shadow: 3px 3px 3px rgb(0, 0, 0, 0.5);
    border-radius: 10px;
    cursor: pointer;
}
#type-style:hover{
    transform: scale(1.02);
    transition: 0.2s;
}
#properties{
    font-size: 1.3rem;
}
#text-schemas{
    font-weight: bold;
    margin-bottom: 10px;
    padding-bottom: 0px;
}
</style>