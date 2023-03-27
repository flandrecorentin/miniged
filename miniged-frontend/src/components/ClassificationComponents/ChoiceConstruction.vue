<template>
    <div id="choice-classification">
        <h3>Choisissez le plan de classement à construire</h3>
        <div  :key="classificationPlan.id"  v-for="classificationPlan in classificationPlans">
            <ButtonChoiceClassification :id="classificationPlan.id" @click-button-classification="choiceClassification" :classificationPlan="classificationPlan"  />
        </div>
    </div>
    <Button class="button-classification" @click-button="clickButton" text="Annuler"  idButton="all-docs" />
    <Button class="button-classification" @click-button="clickButton" text="Construire plan classement" idButton="construct-classification" />
</template>

<script>
import Button from "../Button.vue"
import ButtonChoiceClassification from "./ButtonChoiceClassification.vue"
export default {
    name: 'ChoiceConstruction',
    data(){
        return {
            classificationAConstruire: []
        }
    },
    props:{
        classificationPlans: Array,

    }, 
    created(){
        if(this.classificationPlans.length>0){
            classificationAConstruire = this.classificationPlans[0] 
        }
        document.getElementById(this.classificationAConstruire.id).style.backgroundColor = "#606060"
        document.getElementById(this.classificationAConstruire.id).style.color = 'white'
    },
    components:{
        Button,
        ButtonChoiceClassification,
    },
    methods:{
        clickButton(id){
            if(id==="all-docs"){
                this.$emit('click-button', id)
            }
            else if(id==="construct-classification"){
                this.$emit('click-button', id)
            }
        },
        choiceClassification(classification){
            this.$emit("click-button-classification", classification)
        }
    }
}
</script>

<style scoped>
#choice-classification{
    /* background-color: #d0e7dd; */
    background-color: #EAFCDC;
    align-items: center;
    border: 4px solid rgba(0,0,0,.8);
    box-shadow: 3px 3px 3px rgba(0,0,0,.5);
    border-radius: 10px;
    margin: 10px 50px;
    padding: 15px 30px;
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