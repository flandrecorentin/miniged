<template>
    <ActionHeader />
    <div id="type-view">
        <div id="control">
            <ControlType @click-button="clickButton" />
        </div>
        <div id="result">
            <div v-show="!showConstruct" >
                <ResultType @detail-type="changeDetailType" :detailType="detailType" :types="types"/>
            </div>
            <div v-show="showConstruct" >
                <CreateType :schemas="this.schemas" @click-button="clickButton" />
            </div>
           
        </div>
    </div>
</template>

<script>
import ActionHeader from '../components/ActionHeader.vue'
import ControlType from '../components/TypeComponents/ControlType.vue'
import ResultType from '../components/TypeComponents/ResultType.vue'
import CreateType from '../components/TypeComponents/CreateType.vue'

export default {
    name: 'Type',
    components: {
        ActionHeader,
        ControlType,
        ResultType,
        CreateType,
    },
    data(){
        return{
            types: [],
            detailType: false,
            showConstruct: false,
            schemas: [],
        }
    },
    async created(){
        this.types = await this.fetchTypes()
        this.schemas = await this.fetchSchemas()
        console.log('ALL TYPES TYPE VIEW')
    },
    methods: {
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
        changeDetailType(state){
            this.detailType = state
            console.log('detailType: '+ this.detailType)
        },
        clickButton(id){
            if(id==="all-type"){
                this.showConstruct = false;
                this.detailType = false;
            }
            else if(id==="create-type"){
                this.showConstruct = true;
            }
            else if(id==="back-all-types"){
                this.showConstruct = false;
                this.detailType= false;
            }
            else if(id==="save-type"){
                this.showConstruct = false;
                this.detailType= false;
            }
        },
    },
}
</script>

<style scoped>
#type-view{
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