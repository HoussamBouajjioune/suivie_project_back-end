package com.vinci.suivieprojet.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "livrable")
public class Livrable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String libelle;
	private String description;
	
	@OneToMany
	(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Document> document_technique;
	
	@OneToOne
	@JoinColumn(name = "phase_id",unique = true,nullable = false)
	private Phase phase;
	
	public Livrable(String libelle, String description, List<Document> document_technique, Phase phase) {
		this.libelle = libelle;
		this.description = description;
		this.document_technique = document_technique;
		this.phase = phase;
	}
	
	

	public Livrable(String libelle, String description) {
		super();
		this.libelle = libelle;
		this.description = description;
	}



	public Livrable() {
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Document> getDocument_technique() {
		return document_technique;
	}

	public void setDocument_technique(List<Document> document_technique) {
		this.document_technique = document_technique;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Long getId() {
		return id;
	}
	
	
	
	
}
