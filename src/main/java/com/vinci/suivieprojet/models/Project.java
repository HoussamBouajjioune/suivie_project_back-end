package com.vinci.suivieprojet.models;

import java.time.LocalDate;
import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
	private String description;
	private LocalDate datedebut;
	private LocalDate datefin;
	
//	@JsonProperty(access = Access.WRITE_ONLY)
//	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Document> document_technique;
	
	@ManyToOne
	private User encaderent;

	@OneToOne
	@JoinColumn(name = "etudiant1")
	private User etudiant1;
	@OneToOne
	@JoinColumn(name = "etudiant2",nullable = true)
	private User etudiant2;
	
//	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(
			cascade = CascadeType.ALL
			)
	@JoinColumn(name="organisme_id")
	private Organisme organisme;
	
//	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(
			mappedBy = "project", 
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<Phase> phases;

	public Project(String nom, String description, LocalDate datedebut, LocalDate datefin) {
		this.nom = nom;
		this.description = description;
		this.datedebut = datedebut;
		this.datefin = datefin;
	}

	public Project(String nom, String description, LocalDate datedebut, LocalDate datefin,
			List<Document> document_technique, User encaderent) {
		this.nom = nom;
		this.description = description;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.document_technique = document_technique;
		this.encaderent = encaderent;
	}

	public Project() {

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public Long getId() {
		return id;
	}

	public List<Document> getDocument_technique() {
		return document_technique;
	}

	public void setDocument_technique(List<Document> document_technique) {
		this.document_technique = document_technique;
	}

	public User getEncaderent() {
		return encaderent;
	}

	public void setEncaderent(User encaderent) {
		this.encaderent = encaderent;
	}

	public User getEtudiant1() {
		return etudiant1;
	}

	public void setEtudiant1(User etudiant1) {
		this.etudiant1 = etudiant1;
	}

	public User getEtudiant2() {
		return etudiant2;
	}

	public void setEtudiant2(User etudiant2) {
		this.etudiant2 = etudiant2;
	}

	public Organisme getOrganisme() {
		return organisme;
	}

	public void setOrganisme(Organisme organisme) {
		this.organisme = organisme;
	}

	public List<Phase> getPhases() {
		return phases;
	}

	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}

	
}
