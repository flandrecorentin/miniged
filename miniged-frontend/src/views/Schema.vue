<template>
    <ActionHeader />
    <div id="schema">
        <div id="control">
            <ControlSchema @click-button="clickButton" />
        </div>
        <div id="result">
            <div v-show="!showConstruct" >
                <ResultSchema @detail-schema="changeDetailSchema" :detailSchema="detailSchema" :schemas="schemas"/>
            </div>
            <div v-show="showConstruct" >
                <CreateSchema @click-button="clickButton" />
            </div>
        </div>
    </div>
</template>

<script>
import ActionHeader from '../components/ActionHeader.vue'
import ControlSchema from '../components/SchemaComponents/ControlSchema.vue'
import ResultSchema from '../components/SchemaComponents/ResultSchema.vue'
import CreateSchema from '../components/SchemaComponents/CreateSchema.vue'

export default {
    name: 'Schema',
    components: {
        ActionHeader,
        ControlSchema,
        ResultSchema,
        CreateSchema,
    },
    data(){
        return{
            schemas: [],
            detailSchema: false,
            showConstruct: false,
        }
    },
    async created(){
        this.schemas = await this.fetchSchemas()
    },
    methods: {
        async fetchSchemas(){
            const res = await fetch('http://localhost:8080/schemas')
            const data = await res.json()
            return data;
        },
        changeDetailSchema(state){
            this.detailSchema = state
            console.log('detailSchema: '+ this.detailSchema)
        },
        clickButton(id){
            if(id==="all-schema"){
                this.showConstruct = false;
                this.detailSchema= false;
            }
            else if(id==="back-all-schemas"){
                this.showConstruct = false;
                this.detailSchema= false;
            }
            else if(id==="create-schema"){
                this.showConstruct = true;
            }
        },
    },
}
</script>

<style scoped>
#schema{
  background-color: #F2FFFF;
  display: grid;
  grid-template-columns: 1fr 3fr;
}
#control{
  background-color: F2FFFF;
}
#result{
  margin: 30px 15px;
  background-color: F2FFFF;
  border: solid 6px black;
  box-shadow: 5px 2px 5px rgba(0, 0, 0, 0.3);
  border-radius: 40px;
}
</style>