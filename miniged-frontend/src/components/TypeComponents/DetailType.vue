<template>
    <div id="type-style">
        <h3>Nom: {{typeDetail.name}}</h3>
        <div :key="schema.id" v-for="schema in typeDetail.schemas">
            <p class="type-style-schema">Schema: {{schema.name}}</p>
            <div :key="metadata.name" v-for="metadata in schema.metadonnees">
                <p class="doc-style-properties">{{metadata.name}} : {{metadata.typeMetadata}}</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'DetailType',
    props:{
        typeDetail: {
            type: Object,
            default: null
        }
    },
    methods: {

    },
}
</script>

<style scoped>
#type-style{
    background-color: #f59b95ab;
    margin: 10px 15px;
    padding: 5px 20px 10px 20px;
    border: solid rgb(0, 0, 0, 0.8) 4px;
    box-shadow: 3px 3px 3px rgb(0, 0, 0, 0.5);
    border-radius: 10px;
}
.doc-style-properties{
    margin-left: 30px;
}
.type-style-schema{
    font-weight: bold;
}
</style>