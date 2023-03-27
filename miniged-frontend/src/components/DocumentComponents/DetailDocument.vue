<template>
    <div id="doc-style">
        <h3>ID: {{docDetail.id}}</h3>
        <p id="doc-style-type">Type du document: {{docDetail.docType.name}}</p>
        <div :key="schema.id" v-for="schema in docDetail.docType.docSchemas">
            <p class="doc-style-schema">Schema: {{schema.name}}</p>
            <div :key="metadata.name" v-for="metadata in schema.metadonnees">
                <p class="doc-style-properties">{{metadata.name}} ({{metadata.type}}): {{metadata.value}}</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'DetailDocument',
    props:{
        docDetail: {
            type: Object,
            default: null
        }
    },
    methods: {

    },
}
</script>

<style scoped>
#doc-style{
    /* background-color: #d0e7dd; */
    background-color: #EAFCDC;
    margin: 10px 15px;
    padding: 5px 20px 10px 20px;
    border: solid rgb(0, 0, 0, 0.8) 4px;
    box-shadow: 3px 3px 3px rgb(0, 0, 0, 0.5);
    border-radius: 10px;
}
#doc-style-type{
    font-size: 1.15rem;
    font-weight: bold;
}
.doc-style-properties{
    margin-left: 30px;
}
.doc-style-schema{
    font-weight: bold;
}
</style>