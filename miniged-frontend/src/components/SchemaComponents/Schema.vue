<template>
    <div id="schema-style" @click="clickSchema(schema.id)">
        <h3>Nom: {{schema.name}}</h3>
        <p id="id-schema">ID: {{schema.id}}</p>
    </div>
</template>

<script>
export default {
    name: 'Schema',
    props:{
        schema: Object,
    },
    methods: {
        clickSchema(id){
            // console.log("GET LE SCHEMA "+ id)
            this.$emit('detail', id)
        },
    },
}
</script>

<style scoped>
#schema-style{
    cursor: pointer;
    background-color: #fff2ddd7;
    margin: 10px 15px;
    padding: 5px 20px 20px 20px;
    border: solid rgb(0, 0, 0, 0.8) 4px;
    box-shadow: 3px 3px 3px rgb(0, 0, 0, 0.5);
    border-radius: 10px;
}
#schema-style:hover{
    transform: scale(1.02);
    transition: 0.2s;
}
#properties{
    font-size: 1.3rem;
}
#id-schema{
    font-weight: bold;
    margin-bottom: 0px;
    padding-bottom: 0px
}
</style>