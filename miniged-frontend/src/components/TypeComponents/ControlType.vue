<template>
    <div id="control-type">
        <h2>Outils pour types</h2>
        <Button @click-button="clickButton" text="Voir type(s)" idButton="all-type" />
        <br>
        <Button @click-button="clickButton" text="Créer un type" idButton="create-type" />
    </div>
</template>

<script>
import Button from '../Button.vue'
export default {
    name: 'ControlType',
    components: {
        Button,
    },
    methods:{
        clickButton(id){
            if(id==="all-type"){
                this.$emit('click-button', id)
            }
            else if(id==="create-type"){
                this.$emit('click-button', id)
            }
        }
    },
}
</script>

<style scoped>
#control-type{
    margin-top: 10px;
    padding: 10px;
    text-align: center;
}
br{
    height: 0px;
    margin: 0px;
    padding: 0px;
}
</style>