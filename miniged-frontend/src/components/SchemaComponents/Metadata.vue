<template>
    <div id="metadata">
        <p>Metadonnée {{number}}</p>
        <label class="labeltext">Nom de la metadonnee: </label>
        <input class="inputtext" required :id="'nametest'+number" placeholder="Nom sans espaces" type="text" :value="metadata.name" v-model="metadata.name">
        <input type="radio" :name="'typemetadata'+number" class="type-radio" :id="'type-STRING'+ number" @change="changeTypeMetadata('STRING')" checked>
        <label :for="'type-STRING'+ number">STRING</label>
        <input type="radio" :name="'typemetadata'+number" class="type-radio" :id="'type-INT'+ number" @change="changeTypeMetadata('INT')">
        <label :for="'type-INT'+ number">INT</label>
        <input type="radio" :name="'typemetadata'+number" class="type-radio" :id="'type-DATE'+ number" @change="changeTypeMetadata('DATE')">
        <label :for="'type-DATE'+ number">DATE</label>
        <Button class="button" :id="'button-metadata-'+number" @click-button="clickButton" text="Ajouter au schema" idButton="save-metadata" />
    </div>
</template>

<script>
import Button from '../Button.vue'
export default {
    name: "Metadata",
    data() {
        return {
            metadata: {
                name: "",
                typeMetadata: "STRING",
            },
            metadataAdded: false, 
        };
    },
    created() {
        this.typeMetadata = "STRING";
    },
    props: {
        number: Int8Array,
    },
    compontents: {},
    methods: {
        changeTypeMetadata(typemetadata) {
            this.metadata.typeMetadata = typemetadata;
        },
        clickButton(id){
            if(id==="save-metadata"){
                if(this.metadataAdded===false){
                    if(this.metadata.name===""){
                        alert("La metadonnée n'a pas de nom, veuillez en mettre un")
                    }
                    else{
                       this.$emit('save-metadata', this.metadata)
                        this.metadataAdded=true 
                        document.getElementById('nametest'+this.number).disabled = "disabled";

                        var elems = document.getElementsByClassName("type-radio")
                        elems.forEach(element => {
                            element.disabled= true;
                        });
                    }
                }
                else{
                    alert("La metadonnée a déjà été ajoutée, si vous vous êtes trompé annulez la création du schéma actuel et recréer le")
                }
            }
            else if(id===""){

            }
        }
    },
    components: { Button }
}
</script>

<style scoped>
#metadata{
    border: black solid 1.5px;
    border-radius: 15px;
    margin: 10px;
    padding: 0px;
}
.button{
    font-size: 1.1rem;
    margin: 0px;
    margin: 2px 10px 10px 10px;
}
p{
    margin: 10px 0px 0px 15px; 
}
input[type="radio"]{
    display: none;
}
input[type="radio"]+label{
    border: 3px solid black;
    padding: 3px 5px;
    margin: 0px 10px;
    background-color: #C0C0C0;
    color: black;
    border-radius: 5px;
}
input[type="radio"]:checked+label {
    background-color: #606060;
    color: white;
}
.inputtext{
    margin-right: 20px;
}
.labeltext{
    margin-left:15px
}
</style>