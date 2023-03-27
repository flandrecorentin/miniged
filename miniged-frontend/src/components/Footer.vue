<template>
    <footer>
        <div id="line"></div>
        <p>Copyright &copy; 2022</p>
        <!-- <a href="/about">About lien</a> -->
        <router-link id="a-propos" to="/about">A propos</router-link >
    </footer>
</template>

<script>

</script>

<style scoped>
#a-propos{
    text-decoration: none;
    color: #522674;
}
#a-propos:hover{
    transform: scale(1.1);
    color: #9c69c4;
    transition: 0.2s;
}
#line{
    margin-left: auto;
    margin-right: auto;
    border-top: 4px solid black;
    width: 90%;
}
footer{
    padding-top: 15px;
    padding-bottom: 25px;
    text-align: center;
    background-color: #F2FFFF;
}
</style>