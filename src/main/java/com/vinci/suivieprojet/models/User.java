package com.vinci.suivieprojet.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "user")
public class User {

	@Id
	private Long id;
	private String nom;
	private String prenom;
	@Column(unique = true)
	private String num_telephone;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String login;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@Enumerated(EnumType.STRING)
	private Profil profil;
	
	public User(Long id, String nom, String prenom, String num_telephone, String email, String login, String password,
			Profil profil) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.num_telephone = num_telephone;
		this.email = email;
		this.login = login;
		this.password = password;
		this.profil = profil;
	}
	
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNum_telephone() {
		return num_telephone;
	}

	public void setNum_telephone(String num_telephone) {
		this.num_telephone = num_telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	
	
	



}
