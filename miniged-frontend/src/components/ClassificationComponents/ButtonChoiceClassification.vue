<template>
    <button class="classification-class" :id="classificationPlan.id"  @click="onClick(this.classificationPlan)">
    <h3>{{classificationPlan.id}}</h3>
    <p>niveau 1: {{classificationPlan.metadata1}} ({{classificationPlan.idSchema1}})  </p>
    <p v-if="classificationPlan.idSchema2!=null">niveau 2: {{classificationPlan.metadata2}} ({{classificationPlan.idSchema2}})  </p>
    <p v-if="classificationPlan.idType!=null">type filtre: {{classificationPlan.idType}}</p>
    </button>
</template>

<script>
export default {
    name: "ButtonChoiceClassification",
    props:{
        classificationPlan : Array,
    },
    methods:{
        onClick(classification){
            this.$emit('click-button-classification', classification)
            const elementsClassification = document.getElementsByClassName("classification-class")
            for (let i=0; i < elementsClassification.length; i++) {
                document.getElementsByClassName("classification-class")[i].style.backgroundColor = "#B0B0B0";
                document.getElementsByClassName("classification-class")[i].style.color = "black";
            }
            document.getElementById(classification.id).style.backgroundColor = '#606060'
            document.getElementById(classification.id).style.color = 'white'
        },
    },  
}
</script>

<style scoped>
button{
    background-color: #B0B0B0;
    border: 4px solid black;
    border-radius: 20px;
    box-shadow: rgba(0, 0, 0, 0.3) 3px 3px;
    color: black;
    text-align: center;
    text-decoration: none;
    margin: 5px 10px;
    padding: 10px 30px;
    min-width: 400px;
    cursor: pointer;
}
h3{
    margin: 5px;
    padding: 0px; 
}
p{
    margin: 0px;
    padding: 2px;
}
button:hover{
    /* color: white;
    background-color: #606060; */
    transition-duration: 0.3s;
    transform: scale(1.05);
}
</style>