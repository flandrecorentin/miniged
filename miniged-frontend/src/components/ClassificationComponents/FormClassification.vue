<template>
    <div id="form-classification">
        <!-- Identifiant of the classification plan -->
        <label required>Identifiant du plan de classement à créer:  </label>
        <input required placeholder="identifiant sans espaces" type="text" :value="classificationPlan.id" v-model="classificationPlan.id">
        <!-- First level classification plan -->
        <div class="form required">
            <h3>Premier niveau de classement: </h3>
            <p>Schéma à classer:</p>
            <div class="container">
                <div class="schemaoption" :key="schema.id" v-for="schema in schemas">
                    <input type="radio" name="level1Schema" :id="schema.id+'1'" @change="changeSchema1(schema.id)">
                    <label :for="schema.id+'1'">{{schema.name}}</label>
                </div>
            </div>
            <p>Metadata à classer:</p>
            <div class="container">
                <div class="schemaoption" :key="metadata.name" v-for="metadata in schema1.metadonnees">
                    <input type="radio" name="level1Metadata" :id="metadata.name+'1'" @change="changeMetadata1(metadata.name)">
                    <label :for="metadata.name+'1'">{{metadata.name}}</label>
                </div>
            </div>
        </div>
        <br>
        <br>
        <!-- Second level classification plan -->
        <label class="option">Ajouter un deuxième niveau de classement: </label>
        <input type="radio" name="optionnal" id="secondlevelfalse" @change="changeShowOptionnal" checked>
        <label for="secondlevelfalse">NON</label>
        <input  type="radio" name="optionnal" id="secondleveltrue" @change="changeShowOptionnal">
        <label for="secondleveltrue">OUI</label>
        <div class="form optional" v-show="showOptionnal">
            <h3>Deuxième niveau de classement: </h3>
            <p class="">Schéma à classer:</p>
            <div class="container">
                <div class="schemaoption" :key="schema.id" v-for="schema in schemas">
                    <input class="option2radio"  type="radio" name="level2Schema" :id="schema.id+'2'" @change="changeSchema2(schema.id)">
                    <label class="option2" :for="schema.id+'2'">{{schema.name}}</label>
                </div>
            </div>
            <p>Metadata à classer:</p>
            <div class="container">
                <div class="schemaoption" :key="metadata.name" v-for="metadata in schema2.metadonnees">
                    <input class="option2radio" type="radio" name="level2Metadata" :id="metadata.name+'2'" @change="changeMetadata2(metadata.name)">
                    <label class="option2" :for="metadata.name+'2'">{{metadata.name}}</label>
                </div>
            </div>
        </div>
        <br>
        <br>
        <!-- Filter with type for the classification plan -->
        <label class="option">Ajouter un filtre selon le type de document: </label>
        <input type="radio" name="type" id="typefalse" @change="changeType" checked>
        <label for="typefalse">NON</label>
        <input type="radio" name="type" id="typetrue" @change="changeType">
        <label for="typetrue">OUI</label>
        <div class="form type" v-show="showType">
             <h3>Filtre selon le type de document: </h3>
             <label>Nom du type à filtrer:  </label>
             <input type="text" :value="classificationPlan.idType" v-model="classificationPlan.idType">
             <!-- <div class="container">
                <div class="schemaoption" :key="typee.name" v-for="typee in typees">
                    <p>{{typee.name}}</p>
                    <input type="radio" name="filter" :id="typee.name"  @change="changeTypeFilter(typee.name)">
                    <label :for="typee.name">{{typee.name}}</label>
                </div>
            </div> -->
        </div>        
    </div>
    <Button class="button-classification" @click-button="clickButton" text="Annuler"  idButton="all-docs" />
    <Button class="button-classification" @click-button="clickButton" text="Créer plan de classement" idButton="created-classification" />
    <!-- <p>id {{classificationPlan.id}}</p>
    <p>idSchema1{{classificationPlan.idSchema1}}</p>
    <p>metadata1{{classificationPlan.metadata1}}</p>
    <p>idSchema2{{classificationPlan.idSchema2}}</p>
    <p>metadata2{{classificationPlan.metadata2}}</p>
    <p>idType{{classificationPlan.idType}}</p> -->
</template>
<script>
import Button from "../Button.vue"
export default {
    name: 'FormClassification',
    data() {
        return{
            classificationPlan: {
                id: null,
                idSchema1: null,
                metadata1: null,
                idSchema2: null,
                metadata2: null,
                idType: null,
            },
            showOptionnal: false,
            showType: false,
        }
    },
    props:{
        schemas: Array,
        typees: Array,
        schema1: Array,
        schema2: Array,
    },
    components:{
        Button,
    },
    methods:{
        changeShowOptionnal(){
            this.showOptionnal = (!this.showOptionnal)
            if(this.showOptionnal===false){
                this.classificationPlan.idSchema2= null
                this.classificationPlan.metadata2= null
                const elementsOption2 = document.getElementsByClassName("option2radio")
                for (let i=0; i < elementsOption2.length; i++) {
                    elementsOption2[i].checked = false
                }
            }
        },
        changeType(){
            this.showType = (!this.showType)
            if(this.showType===false){
                this.classificationPlan.idType= null
            }
        },
        changeTypeFilter(id){
            console.log('Change type filter, id:'+ id)
            this.classificationPlan.idType = id;
        },
        changeSchema1(id){
            this.classificationPlan.idSchema1 = id;
            this.classificationPlan.metadata1 = null;
            this.$emit('change-metadatas1', id)
        },
        changeMetadata1(id){
            this.classificationPlan.metadata1 = id;
        },
        changeMetadata2(id){
            this.classificationPlan.metadata2 = id;
        },
        changeSchema2(id){
            this.classificationPlan.idSchema2 = id;
            this.classificationPlan.metadata2 = null;
            this.$emit('change-metadatas2', id)
        },
        async fetchSchema(id){
            const res = await fetch(`http://localhost:8080/schemas/${id}`)
            const data = await res.json()
            return data;
        },
        async clickButton(id){
            if(id==="created-classification"){
                console.log('addClassification')
                await this.addClassification()
            }
            this.$emit("click-button", id)
        },
        async addClassification(){
            console.log('|classificationPlan|'+ this.classificationPlan)
            var bodyARajouter = this.classificationPlan
            console.log('|add|'+ JSON.stringify(bodyARajouter))
            const res = await fetch(`http://localhost:8080/classification/`,{
                method: 'POST', 
                headers: {
                    'Content-type': 'application/json',
                },
                body: JSON.stringify(bodyARajouter),
            })
            res.status === 200 ? (alert("Le plan de classification d'identifiant "+ this.classificationPlan.id + " a été crée")) : (
                res.status === 500 ? (alert("Erreur lors de la création du plan de classement dût à un problème côté serveur, êtes vous sur de ne pas avoir oublié de donneés ?")) : (
                   res.status === 404 ? (alert("Erreur lors de la création du plan de classement dût à une ressource non trouvée")) : (
                        res.status === 409 ? (alert("Erreur lors de la création du plan de classement dût à un conflit")) : (
                            res.status === 400 ? (alert("Erreur lors de la création du plan de classement, la requête envoyée est mauvaise")) : (
                                alert("Erreur lors de la création du plan de classement")
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
#form-classification{
    /* background-color: #d0e7dd; */
    background-color: #EAFCDC;
    text-align: left;
    border: 4px solid rgba(0,0,0,.8);
    box-shadow: 3px 3px 3px rgba(0,0,0,.5);
    border-radius: 10px;
    margin: 10px 50px;
    padding: 15px 25px;
}
input[type="radio"]{
    display: none;
}
input[type="radio"]+label{
    border: 3px solid black;
    padding: 3px 25px;
    margin: 0px 10px;
    background-color: #C0C0C0;
    color: black;
    border-radius: 5px;
}
input[type="radio"]:checked+label {
    background-color: #606060;
    color: white;
}
.schemaoption{
    margin: 15px 10px;
}
.option{
    font-weight: bold;
    font-size: 1rem;
}
.button-classification{
    background-color: rgb(255, 180, 40);
}
.button-classification:hover{
    background-color: rgb(218, 141, 0);
    transition-duration: 0.3s;
    color:white
}
</style>