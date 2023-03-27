<template>
    <div id="creation-document">
        <h2>Création d'un document dans miniged: </h2>
        <div id="div-back-button">
            <div id="div-button-choice">
                <h3>Choisissez le type de document à créer</h3>
                <div  :key="typ.name"  v-for="typ in this.types">
                    <ButtonChoiceType :id="typ.name" @click-button-type="clickButtonType" :type="typ.name" />
                </div>
            </div>
            <Button id="back-button" @click-button="clickButton" text="Annuler"  idButton="all-docs" />
            <Button id="save-button" @click-button="clickButton" text="Créer le document" idButton="created-document" />
            <!-- <Button id="save-button" @click-button="clickButton" text="Créer et modifier le document" idButton="created-modify-document" /> -->
            <!-- <Button id="save-modify-button" @click-button="clickButton" text="Créer et modifier le document (à faire)" idButton="created-modify-document" /> -->
        </div>
    </div>
</template>

<script>
import Button from "../Button.vue";
import ButtonChoiceType from "./ButtonChoiceType.vue";
export default {
    name: "CreationDocument",
    data(){
        return{
            types: [], 
            typeACreer: "File",
        }
    },   
    async created(){
        this.types = await this.fetchTypes();
        document.getElementById(this.typeACreer).style.backgroundColor = "#606060"
        document.getElementById(this.typeACreer).style.color = 'white'
    },
    props: {
        type:{
            type: String,
            default: "",
        },
    },
    methods: {
        async clickButton(id){
            if(id==="created-document"){
                await this.addDoc(this.typeACreer)
                
            }
            else if(id === "all-docs"){
                this.$emit('detail-doc', false)
                this.$emit('click-button', id)
            } 
            else if(id==="created-modify-document"){
                await this.addDoc(this.typeACreer)
                this.$emit('click-button', id)
            }
        },
        clickButtonType(name){
            console.log('|variable name|'+name)
            document.getElementById(this.typeACreer).style.backgroundColor = "#B0B0B0"
            document.getElementById(this.typeACreer).style.color = 'black'
            this.typeACreer = name
            document.getElementById(this.typeACreer).style.backgroundColor = "#606060"
            document.getElementById(this.typeACreer).style.color = 'white'
            console.log('|type à créer|'+ this.typeACreer)
        },
        async addDoc(type){
            console.log('|type|'+ type)
            var bodyARajouter = {"idType": type}
            console.log('|add|'+ JSON.stringify(bodyARajouter))
            const res = await fetch(`http://localhost:8080/document/`,{
                method: 'POST', 
                headers: {
                    'Content-type': 'application/json',
                },
                body: JSON.stringify(bodyARajouter),
            })
            location.reload()
            console.log('|||||||||||'+JSON.stringify(res))
            res.status === 200 ? (alert("Le document de type "+ type+ " a été crée")) : (
                res.status === 500 ? (alert("Erreur lors de la création dût à un problème côté serveur, êtes vous sur d'avoir sélectionner un type ?")) : (
                   res.status === 404 ? (alert("Erreur lors de la création du document dût à une ressource non trouvée")) : (
                        res.status === 409 ? (alert("Erreur lors de la création du document dût à un conflit")) : (
                            res.status === 400 ? (alert("Erreur lors de la création, la requête envoyée est mauvaise")) : (
                                alert("Erreur lors de la création du document")
                            )
                        )
                   )
                )
            )
        },
        async fetchTypes(){
            const res = await fetch('http://localhost:8080/type')
            const data = await res.json()
            return data;
        },

    },
    components: {
        Button,
        ButtonChoiceType,
    }
}
</script>

<style scoped>
#creation-document{
    padding: 10px;
}
h2{
    margin-left: 15px;
}
#back-button, #save-button, #save-modify-button{
    background-color: rgb(255, 180, 40);
}
#back-button:hover, #save-button:hover, #save-modify-button:hover{
    background-color: rgb(218, 141, 0);
    transition-duration: 0.3s;
    color:white
}
#div-back-button{
    text-align: center;
}
#div-button-choice{
    /* background-color: #d0e7dd; */
    background-color: #EAFCDC;
    align-items: center;
    border: 4px solid rgba(0,0,0,.8);
    box-shadow: 3px 3px 3px rgba(0,0,0,.5);
    border-radius: 10px;
    margin: 10px 50px;
    padding: 15px 30px;
}
#File{
    background-color: #606060;
    color: white;
}
</style>