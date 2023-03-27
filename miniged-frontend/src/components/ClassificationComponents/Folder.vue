<template>
    <div id="folder" @click="onClick()" v-show="show" @mouseover="invertImage(folder.id)" @mouseout="notInvertImage(folder.id)">
        <label class="space" v-if="niveau==2"></label>
        <label class="lt" v-if="visibility">></label>
        <label class="lt" v-else-if="!visibility">&lt;</label>
        <img :id="folder.id" src="../../assets/icon-folder.png" alt="dossier">
        <label >{{folder.id}}</label>
    </div>
</template>

<script>
export default {
    name: 'Folder',
    data() {
        return{
            visibility: Boolean,
        }
    },
    props:{
        niveau: Number,
        folder: Array,
        visual: Boolean,
        show: Boolean,
    },
    created(){
        this.visibility = false;
    },
    methods:{
        onClick(){
            this.visibility = !this.visibility
            this.$emit('changeDetail', this.folder.id)
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
#folder{
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
.space{
    margin-left: 50px
}
#folder:hover{
    color: white;
    background-color: #606060;
}
.lt{
    font-size: 2.3rem;
    font-weight: bold;
}
img{
    margin: 5px 0px 0px 0px;
    padding:  0px;
    height: 40px;
    widows: 40px;
}
</style>