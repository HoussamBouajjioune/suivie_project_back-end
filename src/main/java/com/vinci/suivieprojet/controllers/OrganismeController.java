package com.vinci.suivieprojet.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinci.suivieprojet.models.Organisme;
import com.vinci.suivieprojet.models.User;
import com.vinci.suivieprojet.repository.OrganismeRepository;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/organisme")
public class OrganismeController {

	@Autowired
	private OrganismeRepository organismerepository;

	@PostMapping("/organisme")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT')")
	public ResponseEntity<?> createOrganisme(@RequestBody Organisme organisme) {
		try {
			return ResponseEntity.ok(organismerepository.save(organisme));
		} catch (Exception e) {
			return ResponseEntity.ok("NUMERO TELEPHONE DEJA EXISTE");
		}
	}

	@GetMapping("/organisme/{id}")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT')")
	public ResponseEntity<?> getOrganisme(@PathVariable("id") final Long id) {
		Optional<Organisme> organisme = organismerepository.findById(id);
		if (organisme.isPresent()) {
			return ResponseEntity.ok(organisme.get());
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}
	}

	@GetMapping("/organismes")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT')")
	public ResponseEntity<?> getOrganismes() {
		return ResponseEntity.ok(organismerepository.findAll());
	}

	@PutMapping("/organisme/{id}")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT')")
	public ResponseEntity<?> updateOrganisme(@PathVariable("id") final Long id, @RequestBody Organisme organisme) {
		Optional<Organisme> u = organismerepository.findById(id);
		if (u.isPresent()) {
			Organisme currentOrganisme = u.get();

			String nom = organisme.getNom();
			if (nom != null) {
				currentOrganisme.setNom(nom);
			}

			String adresse = organisme.getAdresse();
			if (adresse != null) {
				currentOrganisme.setAdresse(adresse);
			}

			String num_telephone = organisme.getNum_telephone();
			if (num_telephone != null) {
				currentOrganisme.setNum_telephone(num_telephone);
			}

			String nom_contact = organisme.getNom_contact();
			if (nom_contact != null) {
				currentOrganisme.setNom_contact(nom_contact);
			}

			String email_contact = organisme.getEmail_contact();
			if (email_contact != null) {
				currentOrganisme.setEmail_contact(email_contact);
				;
			}

			String adresseweb = organisme.getAdresseweb();
			if (adresseweb != null) {
				currentOrganisme.setAdresseweb(adresseweb);
			}
			try {
			organismerepository.save(currentOrganisme);
			return ResponseEntity.ok(currentOrganisme);
			} catch (Exception e) {
				return ResponseEntity.ok("NUMERO TELEPHONE DEJA EXISTE");
			}
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}
	}

	@DeleteMapping("/organisme/{id}")
	@PreAuthorize("hasRole('DIRECTEUR')")
	public ResponseEntity<?> deleteOrganisme(@PathVariable("id") final Long id) {
		Optional<Organisme> org = organismerepository.findById(id);
		if (org.isPresent()) {
			organismerepository.deleteById(id);
			return ResponseEntity.ok("SUPPRIMER");
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}
	}

	@GetMapping("/organisme/{id}/projects")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT')")
	public ResponseEntity<?> getOrganismesProjects(@PathVariable("id") final Long id) {
		Optional<Organisme> organisme = organismerepository.findById(id);
		if (organisme.isPresent()) {
			return ResponseEntity.ok(organisme.get().getProjets());
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}
	}
}
