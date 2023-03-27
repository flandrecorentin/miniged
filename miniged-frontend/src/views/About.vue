<template>
    <div id="about-infos">
        <h3>Version 1.0.0</h3>
        <p>Database mongodb</p>
        <p>Javascript framework Vue.js 3</p>
        <p>Java framework Spring</p>
        <p>Plugin Maven</p>
        <p>Platform service Docker/Docker-compose</p>
        <p>corentin.flandre@gmail.com</p>
        <p><a href="https://www.smile.eu/fr" id="link-smile"> Smile - https://www.smile.eu/fr</a></p>
        <router-link id="goback" to="/">Retour à Miniged</router-link>
    </div>
</template>

<script>

</script>

<style scoped>
#goback{
    border: solid 3px black;
    border-radius: 10px;
    padding: 10px 20px;
    font-size: 1.1rem;
    text-decoration: none;
    text-decoration-color: black;
    color: black;
    background-color: #B0B0B0;
    text-align: center;
    
}
#goback:hover{
    transform: scale(1.05);
   background-color: #606060;
    color: white;
    transition-duration: 0.2s;
}
#link-smile{
    text-decoration: none;
    color: #522674;
}
#link-smile:hover{
    transform: scale(1.05);
    transition-duration: 0.2s;
    color: #9c69c4;
}
#about-infos{
    margin: 0px;
    text-align: center;
    background-color: #F2FFFF;
}
h3{
    padding-top: 15px;
    margin: 0px;
}

</style>