<template>
    <div id="result-schema">
        <div v-show="!detailSchema">
            <h2>Tout les schemas de la miniged: </h2>
            <div :key="schema.id"  v-for="schema in schemas">
                <Schema @detail="developSchema" :schema="schema" />
            </div>
        </div>
        <div id="detailSchema" v-show="detailSchema">
            <h2>Detail du schema</h2>
            <DetailSchema :schemaDetail="schemaDetail"/>
            <div id="div-back-button">
                <Button id="back-button" @click-button="clickButton" text="Retour aux schemas" :color="F69F4F" idButton="back-all-schemas" />
            </div>
        </div>
    </div>
</template>

<script>
import Schema from "./Schema.vue";
import DetailSchema from "./DetailSchema.vue";
import Button from "../Button.vue";
export default {
    name: "ResultSchema",
    data(){
        return{
            schemaDetail: {},
        }
    },
    props: {
        schemas: Array,
        detailSchema: Boolean,
    },
    methods: {
        async developSchema(id){
            console.log('developSchema: '+ id)
            this.schemaDetail = await this.fetchSchema(id)
            this.$emit('detail-schema', true)
            // console.log('id schema fetch '+ this.schemaDetail.id)
        },
        clickButton(id){
            if(id === "back-all-schemas"){
                this.$emit('detail-schema', false)
            }
        },
        async fetchSchema(id){
            const res = await fetch(`http://localhost:8080/schemas/${id}`)
            const data = await res.json()
            // console.log('data: ' + data )
            return data;
        },
    },
    components: {
        Schema,
        DetailSchema,
        Button,
    }
}
</script>


<style scoped>
#result-schema{
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