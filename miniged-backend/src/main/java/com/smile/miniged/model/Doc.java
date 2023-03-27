package com.smile.miniged.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;


@Document
public class Doc {

	@Id
    private String id;
	@NonNull
	private  DocType type;
    
	public Doc() {
    }
    public Doc(DocType type) {
		this.type = type;		
    }

	@Override
	public String toString() {
		return "	Doc [id=" + id +"]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public DocType getDocType() {
		return type;
	}
	public void setDocType(DocType type) {
		this.type = type;
	}

	public void modificationMetadata(DocMetadata docMetadata, DocSchema docSchema){
		// il faut tester tout les schemas pour trouver cleui à modifier
        for (DocSchema docSchemaATester : this.getDocType().getDocSchemas()) {
			// si même schéma 
			if(docSchemaATester.getId().equals(docSchema.getId())){
				// il faut tester toutes les métadonnées pour trouver celle à modifier
				for (DocMetadata docMetadataATester : docSchemaATester.getMetadonnees()) {
					// si même métadonnée
					if(docMetadataATester.getName().equals(docMetadata.getName())){
						docMetadataATester.setValue(docMetadata.getValue());
					}
				}
			}
		}
    }


}
