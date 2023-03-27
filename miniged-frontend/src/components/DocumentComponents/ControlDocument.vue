<template>
    <div id="control-document">
        <h2>Outils pour documents</h2>
        <Button @click-button="clickButton" text="Voir document(s)" idButton="all-docs" />
        <br>
        <Button @click-button="clickButton" text="Créer un document" idButton="create-doc" />
        <br>
        <Button @click-button="clickButton" text="Modifier un document" idButton="modify-doc" />
        <br>
        <Button @click-button="clickButton" text="Créer plan classement" idButton="create-classification" />
        <br>
        <Button @click-button="clickButton" text="Constuire plan classement" idButton="build-classification" />
    </div>
</template>

<script>
import Button from '../Button.vue'
export default {
    name: 'ControlDocument',
    components: {
        Button,
    },
    methods: {
        clickButton(id){
            console.log('!'+id)
            this.$emit('click-button', id)
        },
    },
}
</script>

<style scoped>
#control-document{
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