<template>
    <div id="control-schema">
        <h2>Outils pour schemas</h2>
        <Button @click-button="clickButton" text="Voir schema(s)" idButton="all-schema" />
        <br>
        <Button @click-button="clickButton" text="Créer un schéma" idButton="create-schema" />
    </div>
</template>

<script>
import Button from '../Button.vue'
export default {
    name: 'ControlSchema',
    components: {
        Button,
    },
    methods:{
        clickButton(id){
            this.$emit('click-button', id)
        },
    },

}
</script>

<style scoped>
#control-schema{
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