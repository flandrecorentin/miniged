<template>
    <div id="schema-style">
        <h3>Nom: {{schemaDetail.name}}</h3>
        <p id="id-schema">ID: {{schemaDetail.id}}</p>
        <div :key="metadata.name" v-for="metadata in schemaDetail.metadonnees">
            <p class="doc-style-properties">{{metadata.name}} : {{metadata.typeMetadata}}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'DetailSchema',
    props:{
        schemaDetail: {
            schema: Object,
            default: null
        }
    },
    methods: {

    },
}
</script>

<style scoped>
#schema-style{
    background-color: #fff2ddd7;
    margin: 10px 15px;
    padding: 5px 20px 10px 20px;
    border: solid rgb(0, 0, 0, 0.8) 4px;
    box-shadow: 3px 3px 3px rgb(0, 0, 0, 0.5);
    border-radius: 10px;
}
.doc-style-properties{
    margin-left: 30px;
}
#id-schema{
    font-weight: bold;
}
</style>