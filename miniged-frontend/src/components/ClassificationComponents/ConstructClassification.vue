<template>
    <h2>Construction d'un plan de classement</h2>
    <div id="construct-classification">
        <div v-show="!showConstruction">
            <ChoiceConstruction  @click-button-classification="choiceClassification" @click-button="clickButton" :classificationPlans="classificationPlans"/>
        </div>
        <div v-show="showConstruction">
            <ResultConstuction  @click-button="clickButton" :constructPlan="constructPlan" />
        </div>
    </div>
</template>

<script>
import ChoiceConstruction from './ChoiceConstruction.vue'
import ResultConstuction from './ResultConstuction.vue'
export default {
    name: 'ConstructClassification',
    data(){
        return{
            classificationPlan: [],
            showConstruction: false,
            constructPlan: [],
        }
    },
    components:{
        ChoiceConstruction,
        ResultConstuction,
    },
    props:{
        classificationPlans: Array, 
    },
    async created(){
    },
    methods:{
        // async fetchClassification(id){
        //     const res = await fetch(`http://localhost:8080/classification/${id}`)
        //     const data = await res.json()
        //     return data;
        // },
       async  clickButton(id){
            if(id==="all-docs"){
                this.showConstruction = false
                this.$emit('click-button', id)
            }
            else if(id==="construct-classification"){
                console.log(this.classificationPlan )
                if(JSON.stringify(this.classificationPlan) === "[]"){
                    alert('Aucun plan de classement a été selectionné')
                }
                else{
                    this.showConstruction = true
                    this.constructPlan = await this.fetchClassification(this.classificationPlan.id)
                }
            }
            else if (id==="back-all-classifications"){
                console.log('TESTTTTTTTTTTTT')
                this.showConstruction = false
                this.$emit('click-button', 'build-classification')
            }
        },
        choiceClassification(classification){
            this.classificationPlan = classification
            console.log('CLASSIFICATION'+  this.classificationPlan)
        },
        async fetchClassification(id){
            const res = await fetch(`http://localhost:8080/classify/${id}`)
            if(res.status === 500){
                alert('Erreur lors de la construction du plan de classement, probablement que le plan de classement utilise un filtre selon le type n utilisant pas un des schemas du classement, veuillez recharger la page !')
            }
            const data = await res.json()
            return data;
        },
    },
}
</script>

<style scoped>
#construct-classification{
    text-align: center;
}
h2{
    margin-top: 30px;
    margin-left: 25px;
}
</style>