package com.vinci.suivieprojet.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "organisme")
public class Organisme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
	private String adresse;
	@Column(unique = true)
	private String num_telephone;
	private String nom_contact;
	private String email_contact;
	private String adresseweb;
	
	@JsonIgnore
	@OneToMany(
			mappedBy = "organisme", 
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<Project> projets;
	
	
	public Organisme(String nom, String adresse, String num_telephone, String nom_contact, String email_contact,
			String adresseweb) {
		this.nom = nom;
		this.adresse = adresse;
		this.num_telephone = num_telephone;
		this.nom_contact = nom_contact;
		this.email_contact = email_contact;
		this.adresseweb = adresseweb;
	}

	public Organisme() {
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNum_telephone() {
		return num_telephone;
	}

	public void setNum_telephone(String num_telephone) {
		this.num_telephone = num_telephone;
	}

	public String getNom_contact() {
		return nom_contact;
	}

	public void setNom_contact(String nom_contact) {
		this.nom_contact = nom_contact;
	}

	public String getEmail_contact() {
		return email_contact;
	}

	public void setEmail_contact(String email_contact) {
		this.email_contact = email_contact;
	}

	public String getAdresseweb() {
		return adresseweb;
	}

	public void setAdresseweb(String adresseweb) {
		this.adresseweb = adresseweb;
	}

	public Long getId() {
		return id;
	}

	public List<Project> getProjets() {
		return projets;
	}

	public void setProjets(List<Project> projets) {
		this.projets = projets;
	}
	
	
	
	

}
