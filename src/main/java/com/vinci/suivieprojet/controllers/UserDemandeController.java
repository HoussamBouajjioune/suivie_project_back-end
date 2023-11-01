package com.vinci.suivieprojet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.vinci.suivieprojet.models.UserDemande;
import com.vinci.suivieprojet.repository.UserDemandeRepository;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/userdemande")
public class UserDemandeController {
	
	@Autowired
	private UserDemandeRepository userdemanderepository;
	
	@GetMapping("/userdemandes")
	@PreAuthorize("hasRole('ADMIN')")
	public Iterable<UserDemande> getUsers() {
		return userdemanderepository.findAll();
	}

}
