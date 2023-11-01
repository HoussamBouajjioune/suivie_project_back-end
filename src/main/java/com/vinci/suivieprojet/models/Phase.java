package com.vinci.suivieprojet.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "phase")
public class Phase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String libelle;
	private String description;
	private LocalDate datedebut;
	private LocalDate datefin;
	
	@OneToMany
	(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Document> document_technique;

	@OneToOne
	private User etudiantrealise;
	private Boolean etat_realisation=false;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
    @JoinColumn(name="project_id")
	private Project project;
	
	
	public Phase(String libelle, String description, LocalDate datedebut, LocalDate datefin) {
		this.libelle = libelle;
		this.description = description;
		this.datedebut = datedebut;
		this.datefin = datefin;
	}


	public Phase(String libelle, String description, LocalDate datedebut, LocalDate datefin,
			List<Document> document_technique, User etudiantrealise) {
		this.libelle = libelle;
		this.description = description;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.document_technique = document_technique;
		this.etudiantrealise = etudiantrealise;
	}


	public Phase() {
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


	public LocalDate getDatedebut() {
		return datedebut;
	}


	public void setDatedebut(LocalDate datedebut) {
		this.datedebut = datedebut;
	}


	public LocalDate getDatefin() {
		return datefin;
	}


	public void setDatefin(LocalDate datefin) {
		this.datefin = datefin;
	}


	public List<Document> getDocument_technique() {
		return document_technique;
	}


	public void setDocument_technique(List<Document> document_technique) {
		this.document_technique = document_technique;
	}


	public User getEtudiantrealise() {
		return etudiantrealise;
	}


	public void setEtudiantrealise(User etudiantrealise) {
		this.etudiantrealise = etudiantrealise;
	}


	public Boolean getEtat_realisation() {
		return etat_realisation;
	}


	public void setEtat_realisation(Boolean etat_realisation) {
		this.etat_realisation = etat_realisation;
	}


	public Long getId() {
		return id;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
	
}
