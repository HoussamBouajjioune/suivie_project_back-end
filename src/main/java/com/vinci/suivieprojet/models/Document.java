package com.vinci.suivieprojet.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document")
public class Document {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_document;
	
	private String adresse;
	
//	@ManyToOne(
//			cascade = { 
//					CascadeType.PERSIST, 
//					CascadeType.MERGE 
//					}
//			)
//	@JoinColumn(name="project_id")
//	private Project project;
	
	public Document(String adresse) {
		this.adresse = adresse;
	}

	public Document() {
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Long getId() {
		return id_document;
	}
	
	
	
}
