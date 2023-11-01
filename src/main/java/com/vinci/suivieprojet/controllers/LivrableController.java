package com.vinci.suivieprojet.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinci.suivieprojet.models.Document;
import com.vinci.suivieprojet.models.Livrable;
import com.vinci.suivieprojet.models.Phase;
import com.vinci.suivieprojet.models.Project;
import com.vinci.suivieprojet.repository.LivrableRepository;
import com.vinci.suivieprojet.repository.PhaseRepository;
import com.vinci.suivieprojet.repository.ProjectRepository;
import com.vinci.suivieprojet.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/project")
public class LivrableController {

	@Autowired
	private ProjectRepository projectrepository;

	@Autowired
	private PhaseRepository phaserepository;

	@Autowired
	UserRepository userrepo;

	@Autowired
	LivrableRepository livrablerepository;

	@PostMapping("{iduser}/livrable")
	@PreAuthorize("hasRole('ETUDIANT')")
	public ResponseEntity<?> createLivrable(@PathVariable("iduser") final Long iduser, @RequestBody Livrable livrable) {
//		Optional<Project> p = projectrepository.findById(phaserepository.findById(livrable.getPhase().getId()).get().getProject().getId());
		Optional<Phase> ph = phaserepository.findById(livrable.getPhase().getId());
		if (ph.isPresent()) {
			if (ph.get().getEtudiantrealise().getId() == iduser) {
				Livrable lb = new Livrable(livrable.getLibelle(), livrable.getDescription());
				try {
					lb.setPhase(ph.get());
					livrablerepository.save(lb);
					lb.setDocument_technique(livrable.getDocument_technique());
					lb = livrablerepository.save(lb);
					return ResponseEntity.ok(livrablerepository.findById(lb.getId()));
				} catch (Exception e) {
					return ResponseEntity.ok("LA PHASE EST DEJA LIVRER");
				}
			} else
				return ResponseEntity.ok("ETUDIANT NON AUTORISER");
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}
	}

	@PutMapping("{iduser}/livrable/{id}")
	@PreAuthorize("hasRole('ETUDIANT')")
	public ResponseEntity<?> updateLivrable(@PathVariable("iduser") final Long iduser,
			@PathVariable("id") final Long id, @RequestBody Livrable livrable) {
//		Optional<Phase> ph = phaserepository.findById(livrablerepository.findById(id).get().getPhase().getId());
		Optional<Livrable> ll = livrablerepository.findById(id);
		if (ll.isPresent()) {
			if (ll.get().getPhase().getEtudiantrealise().getId() == iduser) {
				Livrable currentLivrable = ll.get();

				String libelle = livrable.getLibelle();
				if (libelle != null) {
					currentLivrable.setLibelle(libelle);
				}

				String description = livrable.getDescription();
				if (description != null) {
					currentLivrable.setDescription(description);
				}

				List<Document> document = livrable.getDocument_technique();
				if (document != null) {
					currentLivrable.setDocument_technique(document);
				}

				Phase phase = livrable.getPhase();
				if (phase != null) {
					currentLivrable.setPhase(phaserepository.findById(livrable.getPhase().getId()).get());
				}
				try {
					livrablerepository.save(currentLivrable);
					return ResponseEntity.ok(livrablerepository.findById(currentLivrable.getId()).get());
				} catch (Exception e) {
					return ResponseEntity.ok("LA PHASE EST DEJA LIVRER");
				}

			} else
				return ResponseEntity.ok("ETUDIANT NON AUTORISER");
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}

	}
}
