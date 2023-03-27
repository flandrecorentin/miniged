<template>
    <div id="create-type">
        <h2>Création type</h2>
        <div id="form-type">
            <div class="type-label">
                <label required>Nom du type à créer:  </label>
                <input required placeholder="nom sans espaces" type="text" :value="type.type" v-model="type.type">
            </div>
            <div class="typeoption" id="choice-schemas" :key="schema.id" v-for="schema in schemas">

                <div v-if="schema.id=='dublincore'">
                    <input class="radio-schema" type="checkbox" name="choice-schema" :id="schema.id" @change="changeSchema(schema.id)" checked>
                    <label class="radio-schema" :for="schema.id">{{schema.name}}</label>
                </div>
                <div v-else>
                    <input class="radio-schema" type="checkbox" name="choice-schema" :id="schema.id" @change="changeSchema(schema.id)" >
                    <label class="radio-schema" :for="schema.id">{{schema.name}}</label>
                </div>
                
            </div>
        </div>
        <br>
        <div id="div-back-button">
            <Button class="back-button" @click-button="clickButton" text="Annuler et retour types" idButton="back-all-types" />
            <Button class="back-button" @click-button="clickButton" text="Sauvegarder" idButton="save-type" />
        </div>
    </div>
</template>


<script>
import Button from "../Button.vue";
export default {
    name: "CreateType",
    components:{
        Button,
    }, 
    data(){
        return{
            type:{
                type: null,
                idSchemas: [],
            },
        }
    },
    created(){
        this.type.idSchemas.push('dublincore')
    },
    props:{
        schemas: Array
    }, 
    methods:{
        async clickButton(id){
            if(id==="back-all-types"){
                this.type.type = null
                this.type.idSchemas = []
                this.type.idSchemas.push('dublincore')
                this.$emit('click-button', id)
            }
            else if(id==="save-type"){
                console.log('TESTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT')
                this.$emit('click-button', id)
                await this.addType()
            }
        },
        changeSchema(schema){
            if(schema==="dublincore"){
                document.getElementById(schema).checked = true
            }
            else{
                if(document.getElementById(schema).checked == true){
                    this.type.idSchemas.push(schema)
                }
                else if(document.getElementById(schema).checked == false){
                    var index = this.type.idSchemas.indexOf(schema)
                    this.type.idSchemas.splice(index, 1)
                }
            }
            
        },
        async addType(){
            var bodyARajouter = this.type
            console.log('|body|'+ JSON.stringify(bodyARajouter))
            const res = await fetch(`http://localhost:8080/type/`,{
                method: 'POST', 
                headers: {
                    'Content-type': 'application/json',
                },
                body: JSON.stringify(bodyARajouter),
            })
            res.status === 200 ? (alert("Le type d'identifiant "+ this.type.type+ " a été crée")) : (
                res.status === 500 ? (alert("Erreur lors de la création du type dût à un problème côté serveur ")) : (
                   res.status === 404 ? (alert("Erreur lors de la création du type dût à une ressource non trouvée")) : (
                        res.status === 409 ? (alert("Erreur lors de la création du type dût à un conflit")) : (
                            res.status === 400 ? (alert("Erreur lors de la création du type, la requête envoyée est mauvaise")) : (
                                alert("Erreur lors de la création du type")
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
#create-type{
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
#form-type{
    background-color: #f59b95ab;
    text-align: left;
    border: 4px solid rgba(0,0,0,.8);
    box-shadow: 3px 3px 3px rgba(0,0,0,.5);
    border-radius: 10px;
    margin: 10px 50px 0px 50px;
    padding: 15px 25px;
}
.type-label{
    font-weight: bold;
    font-size: 1.1rem;
    margin: 10px 20px;
}
input[type="checkbox"]{
    display: none;
}
input[type="checkbox"]+label{
    border: 3px solid black;
    padding: 3px 25px;
    margin: 0px 10px;
    background-color: #C0C0C0;
    color: black;
    border-radius: 5px;
}
input[type="checkbox"]:checked+label {
    background-color: #606060;
    color: white;
}
.typeoption{
    margin: 15px 10px;
}
</style>