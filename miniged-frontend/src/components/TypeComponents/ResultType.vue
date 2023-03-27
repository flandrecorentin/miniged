<template>
    <div id="result-type">
        <div v-show="!detailType">
            <h2>Tout les types de la miniged: </h2>
            <div :key="typ.name"  v-for="typ in types">
                <Type @detail="developType" :type="typ" />
            </div>
        </div>
        <div id="detailType" v-show="detailType">
            <h2>Detail du type</h2>
            <DetailType :typeDetail="typeDetail"/>
            <div id="div-back-button">
                <Button id="back-button" @click-button="clickButton" text="Retour aux types" :color="F69F4F" idButton="back-all-types" />
            </div>
        </div>
    </div>
</template>

<script>
import Type from "./Type.vue";
import DetailType from "./DetailType.vue";
import Button from "../Button.vue";
export default {
    name: "ResultDocument",
    data(){
        return{
            typeDetail: {},
        }
    },
    props: {
        types: Array,
        detailType: Boolean,
    },
    methods: {
        async developType(id){
            console.log('developType: '+ id)
            this.typeDetail = await this.fetchType(id)
            this.$emit('detail-type', true)
            // console.log('id type fetch '+ this.typeDetail.id)
        },
        clickButton(id){
            if(id === "back-all-types"){
                this.$emit('detail-type', false)
            }
        },
        async fetchType(id){
            const res = await fetch(`http://localhost:8080/type/${id}`)
            const data = await res.json()
            // console.log('data: ' + data )
            return data;
        },
    },
    components: {
        Type,
        DetailType,
        Button,
    }
}
</script>


<style scoped>
#result-type{
    padding: 10px;
}
h2{
    margin: 15px;
}
#back-button{
    background-color: rgb(255, 180, 40);
}
#back-button:hover{
    background-color: rgb(218, 141, 0);
    transition-duration: 0.3s;
    color:white
}
#div-back-button{
    text-align: center;
}
</style>