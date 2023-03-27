<template>
    <div id="doc-style" @click="clickDoc(doc.id)">
        <h3>ID: {{doc.id}}</h3>
        <div :key="schema.id" v-for="schema in doc.docType.docSchemas">
            <div v-if="schema.id=='dublincore'">
                <div :key="metadata.name" v-for="metadata in schema.metadonnees">
                    <div v-if="metadata.name=='title'">
                        <p class="properties">Titre: {{metadata.value}}</p>
                    </div>
                </div>
            </div>
        </div>
        <p class="properties">Type du document: {{doc.docType.name}}</p>
    </div>
</template>

<script>
export default {
    name: 'Document',
    props:{
        doc: Object,
    },
    methods: {
        clickDoc(id){
            console.log("GET LE DOCUMENT "+ id)
            this.$emit('detail', id)
        },
    },
}
</script>

<style scoped>
h3{
    padding: 0;
    margin-top: 5px;
    margin-bottom: 0px;
    padding-bottom: 0px;
}
#doc-style{
    /* background-color: #d0e7dd; */
    background-color: #EAFCDC;
    margin: 10px 25px;
    padding: 5px 20px 5px 20px;
    cursor: pointer;
    border: solid rgb(0, 0, 0, 0.8) 4px;
    box-shadow: 3px 3px 3px rgb(0, 0, 0, 0.5);
    border-radius: 10px;
}
#doc-style:hover{
    transform: scale(1.02);
    transition: 0.2s;
}
.properties{
    padding: 0;
    margin: 5px 30px;
}
</style>