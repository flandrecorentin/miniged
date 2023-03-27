<template>
    <div id="document" @click="onClick()" @mouseover="invertImage(document.id)" @mouseout="notInvertImage(document.id)" >
        <label class="space" ></label>
        <label class="space" v-if="niveau==3"></label>
        <label class="lt" v-if="visibility">></label>
        <label class="lt" v-else-if="!visibility">&lt;</label>
        <img :id="document.id" src="../../assets/icon-document.png" alt="document">
        <label>
            <label :key="schema.id" v-for="schema in document.docType.docSchemas">
                <label v-if="schema.id=='dublincore'">
                    <label :key="metadata.name" v-for="metadata in schema.metadonnees">
                        <label v-if="metadata.name=='title'">
                            <label class="properties">{{metadata.value}} </label>
                        </label>
                    </label>
                </label>
            </label>
            (id: {{document.id}})
        </label>
        <!-- Détail du document si actif -->
        <div v-if="visibility" id="detail-doc">
            <p id="type-document">Type: {{document.docType.name}}</p>
            <div :key="schema.id" v-for="schema in document.docType.docSchemas">
                <p class="schema-document">Schema: {{schema.name}}</p>
                <div :key="metadata.name" v-for="metadata in schema.metadonnees">
                    <p class="properties2">{{metadata.name}} ({{metadata.type}}):  {{metadata.value}}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Document',
    data() {
        return{
            visibility: Boolean,
            invertImage: Boolean,
        }
    },
    created(){
        this.visibility = false;
        this.invertImage = false;
    },
    props:{
        niveau: Number,
        document: Array,
        detail: Boolean,
    },
    methods:{
        onClick(){
            this.visibility = !this.visibility
        },
        invertImage(id){
            document.getElementById(id).style.filter="invert(100%)";
            console.log('hover')
        }, 
        notInvertImage(id){
            document.getElementById(id).style.filter="invert(0%)";
        },
    },
}
</script>

<style scoped>
#document{
    padding: 0px;
    min-height: 40px;
    border-right: 2px solid black;
    border-left: 2px solid black;
    border-bottom: 2px solid black;
    border-top: 2px solid black;
    text-align: left;
    font-size: 1.3rem;
    background-color: #D9D9D9;
}
#document:hover{
    color: white;
    background-color: #606060;
}
.space{
    margin-left: 50px
}
img{
    margin: 5px 0px 0px 0px;
    padding:  0px;
    height: 40px;
    widows: 40px;
}
.lt{
    font-size: 2.3rem;
    font-weight: bold;
}
#detail-doc{
    padding: 5px;
}
#type-document{
    font-weight: bold;
    margin-left: 5px;
    font-size: 1.1rem;
}
.schema-document{
    font-weight: bold;
    font-size: 1rem;
    margin: 4px 10px;
}
.properties2{
    font-size: 1rem;
    margin: 3px 40px;
}
</style>