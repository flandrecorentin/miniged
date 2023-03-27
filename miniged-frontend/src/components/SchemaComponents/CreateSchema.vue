<template>
    <div id="create-schema">
        <h2>Création schéma</h2>
        <div id="form-schema">
            <div class="schema-label">
                <label required>Identifiant du schéma à créer:  </label>
                <input required placeholder="identifiant sans espaces" type="text" :value="schema.id" v-model="schema.id">
            </div>
            <div class="schema-label">
                <label required>Nom du schéma à créer:  </label>
                <input required type="text" :value="schema.name" v-model="schema.name">
            </div>
            <div :key="i" v-for="i in nbMetadatas">
                <Metadata @save-metadata="addMetadata" :number="i" />
            </div>
            <Button @click-button="clickButton" text="Nouvelle metadonnee" idButton="addMetadata" />
        </div>
        <br>
        <div id="div-back-button">
            <Button class="back-button" @click-button="clickButton" text="Annuler et retour schemas" idButton="back-all-schemas" />
            <Button class="back-button" @click-button="clickButton" text="Sauvegarder" idButton="save-schema" />
        </div>
    </div>
</template>


<script>
import Button from "../Button.vue";
import Metadata from "./Metadata.vue";
export default {
    name: "CreateSchema",
    components:{
        Button,
        Metadata,
    }, 
    data(){
        return{
            schema:{
                id: null,
                name: null,
                metadonnees: [],
            },
            nbMetadatas: 0,
        }
    },
    created(){

    },
    props:{

    }, 
    methods:{
        async clickButton(id){
            if(id==="back-all-schemas"){
                this.schema.id = null
                this.schema.name = null
                this.schema.metadonnees = []
                this.nbMetadatas = 0
                this.$emit('click-button', id)
            }
            else if(id==="save-schema"){
                this.$emit('click-button', id)
                await this.addSchema()
            }
            else if(id==="addMetadata"){
                this.nbMetadatas++;
            }
        },
        addMetadata(meta){
            this.schema.metadonnees.push(meta)
        },
        async addSchema(){
            var bodyARajouter = this.schema
            console.log('|body|'+ JSON.stringify(bodyARajouter))
            const res = await fetch(`http://localhost:8080/schemas/`,{
                method: 'POST', 
                headers: {
                    'Content-type': 'application/json',
                },
                body: JSON.stringify(bodyARajouter),
            })
            res.status === 200 ? (alert("Le schema d'identifiant "+ this.schema.id+ " a été crée")) : (
                res.status === 500 ? (alert("Erreur lors de la création du schéma dût à un problème côté serveur ")) : (
                   res.status === 404 ? (alert("Erreur lors de la création du schéma dût à une ressource non trouvée")) : (
                        res.status === 409 ? (alert("Erreur lors de la création du schéma dût à un conflit")) : (
                            res.status === 400 ? (alert("Erreur lors de la création du schéma, la requête envoyée est mauvaise")) : (
                                alert("Erreur lors de la création du schéma")
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
#create-schema{
    padding: 10px;
}
h2{
    margin: 15px;
}
.back-button{
    background-color: rgb(255, 180, 40);
}
.back-button:hover{
    background-color: rgb(218, 141, 0);
    transition-duration: 0.3s;
    color:white
}
#div-back-button{
    text-align: center;
}
#form-schema{
    background-color: #EAFCDC;
    text-align: left;
    border: 4px solid rgba(0,0,0,.8);
    box-shadow: 3px 3px 3px rgba(0,0,0,.5);
    border-radius: 10px;
    margin: 10px 50px 0px 50px;
    padding: 15px 25px;
}
.schema-label{
    font-weight: bold;
    font-size: 1.1rem;
    margin: 10px 20px;
}
</style>