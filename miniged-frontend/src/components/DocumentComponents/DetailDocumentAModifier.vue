<template>
    <div id="doc-style">
        <h3>ID: {{docDetail.id}}</h3>
        <p id="doc-style-type">Type du document: {{docDetail.docType.name}}</p>
        <div :key="schema.id" v-for="schema in docDetail.docType.docSchemas">
            <p class="doc-style-schema">Schema: {{schema.name}}</p>
            <div id="design-metadata-modification" :key="metadata.name" v-for="metadata in schema.metadonnees">
                <label>{{metadata.name}} ({{metadata.type}}): </label>
                <input v-if="metadata.type=='STRING'" type="text" :value="metadata.value" v-model="metadata.value">
                <input v-if="metadata.type=='INT'" type="number" :value="metadata.value" v-model="metadata.value">
                <input v-if="metadata.type=='DATE'" type="date" :value="metadata.value" v-model="metadata.value">
            </div>
        </div>
    </div>
    <div id="div-back-button">
        <Button id="back-button" @click-button="clickButton" text="Annuler"  idButton="back-all-docs" />
        <Button id="modify-button" @click-button="clickButton" text="Sauvegarder" idButton="modify-action-doc" />
    </div>
</template>

<script>
import Button from "../Button.vue";
export default {
    name: 'DetailDocumentAModifier',
    props:{
        docDetail: {
            type: Object,
            default: null,
        }
    },
    components:{
        Button,
    },
    methods: {
        async clickButton(id){
            if(id==="back-all-docs"){
                this.$emit("click-button",id)
            }
            if(id==="modify-action-doc"){
                console.log(JSON.stringify(this.docDetail))
                this.$emit("click-button",id)
                await this.modifyDoc(this.docDetail.id, this.docDetail)
            }
        },
        async modifyDoc(id, docAModifier){
            const res = await fetch(`http://localhost:8080/document/${id}`,{
                method: 'PUT', 
                headers: {
                    'Content-type': 'application/json',
                },
                body: JSON.stringify(docAModifier),
            })
            res.status === 200 ? (alert("Le document d'identifiant "+ id+" a été modifié")) : (
                res.status === 500 ? (alert("Erreur lors de la modofication du document dût à un problème côté serveur")) : (
                   res.status === 404 ? (alert("Erreur lors de la modofication du document dût à une ressource non trouvée")) : (
                        res.status === 409 ? (alert("Erreur lors de la modofication du document dût à un conflit")) : (
                            res.status === 400 ? (alert("Erreur lors de la modification, la requête envoyée est mauvaise")) : (
                                alert("Erreur lors de la modification du document")
                            )
                        )
                   )
                )
            )
            location.reload()
        },
    },
}
</script>

<style scoped>
#doc-style{
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
    margin: 10px 0px;
}

#back-button, #modify-button{
    background-color: rgb(255, 180, 40);
}
#back-button:hover, #modify-button:hover{
    background-color: rgb(218, 141, 0);
    transition-duration: 0.3s;
    color:white
}
#div-back-button{
    text-align: center;
}
#design-metadata-modification {
    margin: 10px 30px;
}
</style>