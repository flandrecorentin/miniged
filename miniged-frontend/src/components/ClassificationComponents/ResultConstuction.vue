<template>
    <div id="result-classification">
        <div id="div-display">
            <div id="div-display-plan">
                <h3>Plan de classement utilisé: </h3>
                <p>Identifiant : {{constructPlan.classificationPlanModel.id}}</p>
                <p>Niveau 1: {{constructPlan.classificationPlanModel.metadata1}} ({{constructPlan.classificationPlanModel.idSchema1}})</p>
                <p v-if="constructPlan.classificationPlanModel.idSchema2!=null">Niveau 2: {{constructPlan.classificationPlanModel.metadata2}} ({{constructPlan.classificationPlanModel.idSchema2}})</p>
                <p v-if="constructPlan.classificationPlanModel.idType!=null">Type filtré: {{constructPlan.classificationPlanModel.idType}}</p>
            </div>
            <div id="div-display-classification">
                <!-- Niveau 1 -->
                <div :key="folder.id"  v-for="folder in constructPlan.folders">
                    <Folder :show="true" @changeDetail="changeDetail" niveau=1 :folder="folder" />
                    <!-- Si niveau 2 présent : Folders-->
                    <div v-if="constructPlan.classificationPlanModel.idSchema2!=null">
                    <div  :key="subfolder.id" v-for="subfolder in folder.subfolders">
                        <Folder :show="exist(folder.id)===true" @changeDetail="changeDetail" niveau=2 :folder="subfolder"/>
                        <!-- Niveau 3: Documents -->
                        <div v-show="exist(subfolder.id)==true && exist(folder.id)==true">
                        <div :key="document.id" v-for="document in subfolder.documents">
                            <Document niveau=3 :document="document" :detail="false" />
                        </div>
                        </div>
                    </div>
                    </div>
                    <!-- Sinon Niveau 2 : Documents-->
                    <div v-if="constructPlan.classificationPlanModel.idSchema2==null">
                    <div v-show="exist(folder.id)==true">
                    <div  :key="document.id" :show="true" v-for="document in folder.documents">
                        <Document niveau=2 :document="document" :detail="false" />
                    </div>
                    </div>    
                    </div>
                </div>
                <!-- {{constructPlan}} -->
            </div>
        </div>
    </div>
    <Button class="button-classification" @click-button="clickButton" text="Retour aux plans de classements"  idButton="back-all-classifications" />
</template>

<script>
var listeVisi = new Map();
import Button from '../Button.vue'
import Folder from './Folder.vue'
import Document from './Document.vue'
export default {
    name: 'ResultConstruction',
    data(){
        return {
            listeVisibilite: Map,
        }
    },
    props:{
        constructPlan: Array,
    }, 
    created(){

    },
    components:{
        Button,
        Folder,
        Document,
    },
    methods:{
        clickButton(id){
            if(id==="back-all-classifications"){
                this.$emit('click-button', id)
                listeVisi.clear()
            }
        },
        changeDetail(id){
            this.listeVisibilite.set(id, !this.listeVisibilite.get(id))
        },
        exist(id){
            if(listeVisi.size==0){
                this.constructPlan.folders.forEach(element => {
                    listeVisi.set(element.id, false)
                    if(this.constructPlan.classificationPlanModel.idSchema2!=null){
                        element.subfolders.forEach(element2 => {
                            listeVisi.set(element2.id, false)
                        });
                    }
                });
            }
            this.listeVisibilite = listeVisi
            return this.listeVisibilite.get(id)
        },
    }
}
</script>

<style scoped>
#result-classification{
    /* background-color: #d0e7dd; */
    background-color: #EAFCDC;
    align-items: center;
    border: 4px solid rgba(0,0,0,.8);
    box-shadow: 3px 3px 3px rgba(0,0,0,.5);
    border-radius: 10px;
    margin: 10px 50px;
    padding: 10px 35px;
}
#div-display{
    /* border: red 2px solid; */
    margin: 0px 20px 40px 20px;
}
#div-display-plan{
    /* border: green 2px solid; */
    padding: 5px;
    margin-bottom: 5px;
}
#div-display-plan h3{
    padding: 0px;
    margin: 5px 0px;
}
#div-display-plan p{
    padding: 0px;
    margin: 3px 0px;
}
#div-display-classification{
    border: black 2px solid;
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