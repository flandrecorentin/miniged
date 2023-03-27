<template>
    <div id="header">
       <router-link id="miniged" to="/">Miniged</router-link> 
       <p>POC d'une GED minimaliste</p>
    </div>
</template>

<style scoped>
#header{
    background-color: #C9C9C9;
    display: flex;
    border: solid 6px black;
    height: 80px;
}
#miniged{
    padding-left: 50px;
    background-color: #eeeeee;
    padding-right: 50px;
    padding-top: 15px;
    font-weight: bold;
    font-size: 2.5rem;
    text-align: left;
    color: black;
    text-shadow: rgba(0, 0, 0, 0.3) 2px 1px;
    text-decoration: none;
    text-decoration-color: black;
    border-right: solid 6px black;
}
#miniged:hover{
    color: white;
    background-color: #606060;
    transition-duration: 0.3s;
    /* transform: scale(1.05);
    transition: 0.2s; */
}
p{
    font-size: 1.5rem;
    padding-left: 150px;
    text-align: center;
}
</style>