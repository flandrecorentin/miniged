<template>
    <div id="miniged">
        <ActionHeader />
        <div id="presentation">
            <h2>L'application web Miniged est un POC (Proof Of Concept)d'une application type GED (Gestion Electronique de Documents) minimaliste. 
            </h2>
            <hr>
            <div class="container">
                <h3>Quel est l'objectif ? </h3>
                <p>
                    La mise en place d’une GED basé sur le modèle de Nuxeo ou Alfresco peut parfois être déroutante pour une infrastructure de petite taille car parfois trop coûteuse ou trop complexe au vu de l’utilisation souhaitée. Effectivement le prix des licences de ces modèles est parfois trop élevé pour certains clients, en plus d’une personnalisation qui peut être coûteuse aussi. De plus, ces modèles possèdent des fonctionnalités très poussées donc parfois un grand nombre de ces fonctionnalités sont inutiles pour un client.
                </p> 
                <p>
                    Peu de solutions simplistes de type GED existent actuellement ou alors des solutions sont développés à partir de rien à chaque demande. Aucun modèle adaptable et personnalisable est disponible. De plus, dans les petites infrastructures il y a parfois personne apte à proposer une solution propre à l’infrastructure et doivent donc faire appel à un service extérieur. L’intérêt pour SMILE de créer un modèle de GED simpliste est de pouvoir s’adapter à la demande de plusieurs clients.
                </p> 
            </div>
            <hr>
            <div class="container">
                <h3>Quel est l'intérêt du POC ? </h3>
                <p>La réalisation d'un POC d'une GED simpliste type mini GED permet de: </p>
                <ul>
                    <li>Se rendre compte des limites de la réalisation d'un projet type mini GED</li>
                    <li>Avoir un prototype du produit, une application test réutilisable et adaptable à l'avenir</li>
                    <li>Analyser les fonctions majeures et essentielles à mettre en place pour un modèle de GED plus simpliste</li>
                </ul>
            </div>
            <hr>
            <div class="container">
                <h3>Obtenez la documentation du projet Miniged (pas encore faite)</h3>
                <a id="link-doc" href="https://www.notion.so/flandrecorentin/Miniged-Documentation-d0682cd62f4a48aea9d714b1e5daa6bf">Documentation</a>
            </div>
        </div>
    </div>
</template>

<script>
import ActionHeader from '../components/ActionHeader.vue'
export default {
    name: 'Miniged',
    components: {
        ActionHeader,
    },
}
</script>

<style scoped>
#presentation{
    text-align: center;
    padding: 40px 50px 0px 50px;
}
h3{
    margin-top: 20px;
}
hr{
    width: 50%;
}
.container{
    padding: 30px 50px;
}
#h2{
    margin: 10px 20px 0px 20px;
}
#miniged{
    background-color: #F2FFFF;    
}
#link-doc{
    text-decoration: none;
    color: #713c9a;
    font-size: 1.3rem;
}
#link-doc:hover{
    transform: scale(1.05);
    transition-duration: 0.2s;
    color: #9c69c4;
}
</style>