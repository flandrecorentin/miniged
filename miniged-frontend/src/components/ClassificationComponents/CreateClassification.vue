<template>
    <h2>Création d'un plan de classement dans miniged</h2>
    <div id="create-classification">
        
        <!-- FormClassification a besoin des schémas (pour métadonnées), types -->
        <FormClassification @click-button="clickButton" @change-metadatas2="changeMetadata2" @change-metadatas1="changeMetadata1" :typees="typees" :schemas="schemas" :schema1="schema1" :schema2="schema2"/>
    </div>
</template>

<script>
import FormClassification from "./FormClassification.vue"
import Button from "../Button.vue"
export default {
    name: 'CreateClassification',
    data(){
        return{
            typees: [],
            schema1: [],
            schema2: [],
            schemas: [],
        }
    },
    components:{
        FormClassification,
        Button
    },
    props:{
        
    },
    async created(){
        console.log('test create classification')
        this.typees = await this.fetchTypes()
        this.schemas = await this.fetchSchemas()
    },
    methods:{
        async fetchTypes(){
            const res = await fetch('http://localhost:8080/type')
            const data = await res.json()
            return data;
        },
        async fetchSchemas(){
            const res = await fetch('http://localhost:8080/schemas')
            const data = await res.json()
            return data;
        },
        clickButton(id){
            if(id==="all-docs"){
                this.$emit("click-button", id)
            }
            else if(id==="created-classification"){
                this.$emit("click-button", "all-docs")
            }
        },
        async fetchSchema(id){
            const res = await fetch(`http://localhost:8080/schemas/${id}`)
            const data = await res.json()
            return data;
        },
        async changeMetadata1(id){
            console.log('emit succeed 2')
            this.schema1 = await this.fetchSchema(id);
            console.log(' this.metadatas1 '+ this.schema1)
        },
        async changeMetadata2(id){
            console.log('emit succeed 2')
            this.schema2 = await this.fetchSchema(id);
            console.log(' this.metadatas1 '+ this.schema2)
        },
    },
}
</script>

<style scoped>
#create-classification{
    text-align: center;
}
h2{
    margin-top: 30px;
    margin-left: 25px;
}
</style>