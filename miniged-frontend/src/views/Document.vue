<template>
    <ActionHeader />
    <div id="document">
        <div id="control">
            <ControlDocument @click-button="clickButton" />
        </div>
        <div v-show="showResult" class="result">
            <div v-show="showDocs">
                <ResultDocument @detail-doc="changeDetailDoc" :modifyDoc="modifyDoc" :detailDoc="detailDoc" :docs="docs"/>
            </div>
            <div v-show="showCreateDoc">
                <CreationDocument  @click-button="clickButton" />
            </div>
        </div>
        <div class="result" v-show="!showResult">
            <div v-show="showCreateClassification"> 
                <CreateClassification @click-button="clickButton" />
            </div>
            <div v-show="!showCreateClassification">
                <ConstructClassification :modifyCreate="modifyCreate" :classificationPlans="classificationPlans" @click-button="clickButton" />
            </div>
        </div>
    </div>
</template>

<script>
import ActionHeader from '../components/ActionHeader.vue'
import ControlDocument from '../components/DocumentComponents/ControlDocument.vue'
import ResultDocument from '../components/DocumentComponents/ResultDocument.vue'
import CreationDocument from '../components/DocumentComponents/CreationDocument.vue'
import CreateClassification from '../components/ClassificationComponents/CreateClassification.vue'
import ConstructClassification from '../components/ClassificationComponents/ConstructClassification.vue'

export default {
    name: 'Document',
    components: {
        ActionHeader,
        ControlDocument,
        ResultDocument,
        CreationDocument,
        CreateClassification,
        ConstructClassification,
    },
    data(){
        return{
            docs: [],
            showDocs: true, 
            detailDoc: false,
            modifyDoc: false,
            showCreateDoc: false,
            showResult: true,
            showCreateClassification: false,
            classificationPlans: [],
            // modifyCreate: false,
        }
    },
    async created(){
        this.docs = await this.fetchDocs()
        this.showDocs = true
        this.showResult= true
        this.classificationPlans = await this.fetchClassifications()
    },
    methods: {
        async fetchDocs(){
            const res = await fetch('http://localhost:8080/document')
            const data = await res.json()
            return data;
        },
        async fetchClassifications(){
            const res = await fetch('http://localhost:8080/classification')
            const data = await res.json()
            return data;
        },
        async clickButton(id){
            console.log("idButton|"+ id +"|")
            if(id==="all-docs"){
                this.showDocs = true
                this.detailDoc = false
                this.modifyDoc = false
                this.showCreateDoc = false
                this.showResult = true
                console.log('!!!!!!!!'+id)
            }
            else if (id==="create-doc"){
                this.showDocs = false
                this.detailDoc = false
                this.modifyDoc = false
                this.showCreateDoc = true
                this.showResult = true
            }
            else if(id==="modify-doc"){
                this.showDocs = true
                this.detailDoc = false
                this.modifyDoc = true
                this.showCreateDoc = false
                this.showResult = true
            }
            else if(id==="modify-action-doc"){
                this.showDocs = true
                this.detailDoc = false
                this.modifyDoc = true
                this.showCreateDoc = false
                this.showResult = true
            }
            else if(id==="create-classification"){
                console.log('!!!!!!!!'+id)
                this.showDocs = false
                this.detailDoc = false
                this.modifyDoc = false
                this.showCreateDoc = false
                this.showResult = false
                this.showCreateClassification= true
                
            }
            else if(id==="build-classification"){
                this.showDocs = false
                this.detailDoc = false
                this.modifyDoc = false
                this.showCreateDoc = false
                this.showResult = false
                this.showCreateClassification= false
            }
            else if(id==="created-modify-document"){
                this.showDocs = true
                this.detailDoc = true
                this.modifyDoc = true
                this.showCreateDoc = false
                this.showResult = true
            }
        },
        changeDetailDoc(state){
            this.detailDoc = state;
        }
    },
    
}
</script>

<style scoped>
#document{
  background-color: #F2FFFF;
  display: grid;
  grid-template-columns: 1fr 3fr;
}
#control{
  background-color: F2FFFF;
}
.result{
  margin: 30px 15px;
  background-color: #F2FFFF;
  border: solid 6px black;
  box-shadow: 5px 2px 5px rgba(0, 0, 0, 0.3);
  border-radius: 40px;
}
</style>