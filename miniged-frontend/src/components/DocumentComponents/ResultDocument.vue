<template>
    <div id="result-document">
        <div v-show="!modifyDoc">
            <div v-show="!detailDoc">
                <h2>Tout les documents visibles dans la miniged: </h2>
                <div :key="doc.id"  v-for="doc in docs">
                    <Document @detail="developDoc" :doc="doc" />
                </div>
            </div>
            <div id="detailDoc" v-show="detailDoc">
                <h2>Detail de document</h2>
                <div class="down-up-load-div">
                    <!-- <a :href="downloadAttachment(this.docDetail.id)" download>TRUC TRUC </a> -->
                    <!-- <a id="tructruc2" download>TRUCTRUC2</a> -->
                    <Button class="down-up-load-button" @click-button="clickButton" text="Télécharger la pièce jointe" :color="F69F4F" idButton="download-attached-file" />
                </div>
                <DetailDocument :docDetail="docDetail"/>
                <div id="div-back-button">
                    <Button id="back-button" @click-button="clickButton" text="Retour aux documents" :color="F69F4F" idButton="back-all-docs" />
                </div>
            </div>
        </div>
        <div v-show="modifyDoc">
            <div v-show="!detailDoc">
                <h2>Choisissez le document à modifier dans la miniged: </h2>
                <div :key="doc.id"  v-for="doc in docs">
                    <Document @detail="developDoc" :doc="doc" />
                </div>
            </div>
            <div id="detailDoc" v-show="detailDoc">
                <h2>Detail de document</h2>
                <div class="down-up-load-div">
                        <input type=file id="upload" :v-model="test" >
                        <label id="upload-label" for="upload"> Selectionner pièce jointe <img id="img-upload" src="../../assets/icon-document.png" alt=""></label>
                        <Button class="down-up-load-button" @click-button="clickButton" text="Sauvegarder pièce jointe" idButton="upload-attached-file" />
                </div>
                <DetailDocumentAModifier @click-button="clickButton" :docDetail="docDetail"/>
            </div>
        </div>
    </div>
</template>

<script>
import Document from "./Document.vue";
import DetailDocument from "./DetailDocument.vue";
import DetailDocumentAModifier from "./DetailDocumentAModifier.vue";
import Button from "../Button.vue";
export default {
    name: "ResultDocument",
    data(){
        return{
            docDetail: {},
            test: [],
        }
    },
    async created(){  
        console.log('TESTTTTTTT')
        console.log(JSON.stringify(await this.fetchDocs()))
        var testt = await this.fetchDocs()
        var testt2 = testt[testt.length-1]
        console.log(testt2)
        console.log(JSON.stringify(testt2))
        this.docDetail = testt2
    },
    props: {
        docs: Array,
        detailDoc: Boolean,
        modifyDoc: Boolean,
    },
    methods: {
        async developDoc(id){
            this.docDetail = await this.fetchDoc(id)
            this.$emit('detail-doc', true)
            console.log('id doc fetch '+ this.docDetail.id)
        },
        async fetchDocs(){
            const res = await fetch('http://localhost:8080/document')
            const data = await res.json()
            return data;
        },
        // a modifier apres avoir ajouter le bouton pour faire retour a tout les documents
        async clickButton(id){
            if(id === "back-all-docs"){
                this.$emit('detail-doc', false)
            }
            else if(id==="modify-action-doc"){
                this.$emit('detail-doc', false)
            }
            else if(id==="upload-attached-file"){
                // upload
                this.attachment()
            }
            else if(id==="download-attached-file"){
                // download
                this.downloadAttachment(this.docDetail.id)
            }
        },
        async fetchDoc(id){
            const res = await fetch(`http://localhost:8080/document/${id}`)
            const data = await res.json()
            console.log('data: ' + data )
            return data;
        },
        async attachment(){
            console.log('start attachment')
            await this.addAttachment(this.docDetail.id)
            console.log('stop attachment')
        },
        async addAttachment(id){
            console.log('|id doc attachment|' + id)
            const bodyData = new FormData()
            const fileField = document.querySelector('input[type="file"]')
            bodyData.append('file', fileField.files[0])
            const res = await fetch(`http://localhost:8080/attachment/${id}`,{
                method: 'POST', 
                body: bodyData,
            })
            res.status === 200 ? (alert("La pièce jointe du document d'identifiant "+id+ " a été chargé")) : (
                res.status === 500 ? (alert("Erreur lors du chargement de la pièce jointe dût à un problème côté serveur, êtes vous sur d'avoir sélectionner un type ?")) : (
                   res.status === 404 ? (alert("Erreur lors du chargement de la pièce jointe dût à une ressource non trouvée")) : (
                        res.status === 409 ? (alert("Erreur lors du chargement de la pièce jointe dût à un conflit")) : (
                            res.status === 400 ? (alert("Erreur lors du chargement de la pièce jointe, la requête envoyée est mauvaise")) : (
                                alert("Erreur lors du chargement de la pièce jointe")
                            )
                        )
                   )
                )
            )
        },

        async downloadAttachment(id){
            const res = await fetch(`http://localhost:8080/attachment/${id}`)
            res.status === 200 ? (
                                    console.log('download')// alert("La pièce jointe du document d'identifiant "+id+ " a été téléchargé")
                                ) : (
                res.status === 500 ? (alert("Erreur lors du téléchargement de la pièce jointe dût à un problème côté serveur, êtes vous sur d'avoir sélectionner un type ?")) : (
                   res.status === 404 ? (alert("Erreur lors du téléchargement de la pièce jointe dût à une ressource non trouvée")) : (
                        res.status === 409 ? (alert("Erreur lors du téléchargement de la pièce jointe dût à un conflit")) : (
                            res.status === 400 ? (alert("Erreur lors du téléchargement de la pièce jointe, la requête envoyée est mauvaise")) : (
                                alert("Erreur lors du téléchargement de la pièce jointe")
                            )
                        )
                   )
                )
            )
            if(res.status===200){
                fetch(`http://localhost:8080/attachment/${id}`)
                .then(resp => resp.blob())
                .then(blob => {
                    const url = window.URL.createObjectURL(blob);
                    const a = document.createElement('a');
                    a.style.display = 'none';
                    a.href = url;
                    // the filename you want
                    a.download = id;
                    document.body.appendChild(a);
                    a.click();
                    window.URL.revokeObjectURL(url);
                    alert("La pièce jointe du document d'identifiant "+id+ " a été téléchargé")
                })
                .catch(() => 
                    this.status === 500 ? (alert("Erreur lors du téléchargement de la pièce jointe dût à un problème côté serveur, êtes vous sur d'avoir attaché une pièce jointe à ce document ?")) : (
                    res.status === 404 ? (alert("Erreur lors du téléchargement de la pièce jointe dût à une ressource non trouvée")) : (
                            res.status === 409 ? (alert("Erreur lors du téléchargement de la pièce jointe dût à un conflit")) : (
                                res.status === 400 ? (alert("Erreur lors du téléchargement de la pièce jointe, la requête envoyée est mauvaise")) : (
                                    alert("Erreur lors du téléchargement de la pièce jointe")
                                )
                            )
                    )
                    )
                );
            }
            
        },
    },
    components: {
        Document,
        DetailDocument,
        Button,
        DetailDocumentAModifier,
    }
}
</script>

<style scoped>
#result-document{
    padding: 10px;
}
h2{
    margin-left: 15px;
}
#detailDoc{
    margin-left: auto;
    margin-right:auto
}
#back-button, #modify-button{
    background-color: rgb(255, 180, 40);
}
#back-button:hover{
    background-color: rgb(218, 141, 0);
    transition-duration: 0.3s;
    color:white
}
#div-back-button{
    text-align: center;
}
.down-up-load-button{
    background-color: rgb(255, 180, 40);
}
.down-up-load-button:hover{
    background-color: rgb(218, 141, 0);
    transition-duration: 0.3s;
    color:white
}
input[type="file"]{
    display: none;
}
#upload-label{
    margin-left: 20px;
    font-size: 1.3rem;
    padding: 10px 30px 10px 20px;
    border: dashed black 4px;
    border-radius: 10px;
    /* background-color: rgb(255, 180, 40); */
}
#upload-label:hover{
    cursor: pointer ;
    transform: scale(1.05);
    transition-duration: 0.3s;
}
#img-upload{
    height: 40px;
    width: 40px;
}
</style>