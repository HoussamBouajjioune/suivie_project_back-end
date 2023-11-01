package com.vinci.suivieprojet.controllers;

import java.time.LocalDate;
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
import com.vinci.suivieprojet.models.Phase;
import com.vinci.suivieprojet.models.Project;
import com.vinci.suivieprojet.models.User;
import com.vinci.suivieprojet.repository.PhaseRepository;
import com.vinci.suivieprojet.repository.ProjectRepository;
import com.vinci.suivieprojet.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/project")
public class PhaseController {

	@Autowired
	private ProjectRepository projectrepository;

	@Autowired
	private PhaseRepository phaserepository;

	@Autowired
	UserRepository userrepo;

	@PostMapping("/{idproject}/{iduser}/phase")
	@PreAuthorize("hasRole('ENCADERENT')")
	public ResponseEntity<?> createPhase(@PathVariable("idproject") final Long idproject,
			@PathVariable("iduser") final Long iduser, @RequestBody Phase phase) {
		Optional<Project> p = projectrepository.findById(idproject);
		if (p.isPresent()) {
			if (p.get().getEncaderent().getId() == iduser) {
				Phase ph = new Phase(phase.getLibelle(), phase.getDescription(), phase.getDatedebut(),
						phase.getDatefin());
				ph.setEtudiantrealise(userrepo.findById(phase.getEtudiantrealise().getId()).get());
				if (p.get().getEtudiant1().getId() == phase.getEtudiantrealise().getId()) {
					ph.setProject(p.get());
					ph = phaserepository.save(ph);
					ph = phaserepository.findById(ph.getId()).get();
					ph.setDocument_technique(phase.getDocument_technique());
					ph = phaserepository.save(ph);
					return ResponseEntity.ok(ph);
				} else {
					if (p.get().getEtudiant2() != null) {
						if (p.get().getEtudiant2().getId() == phase.getEtudiantrealise().getId()) {
							ph.setProject(p.get());
							ph = phaserepository.save(ph);
							ph = phaserepository.findById(ph.getId()).get();
							ph.setDocument_technique(phase.getDocument_technique());
							ph = phaserepository.save(ph);
							return ResponseEntity.ok(ph);
						} else {
							return ResponseEntity.ok("ETUDIANT PAS INTEGRER AU PROJECT");
						}
					} else {
						return ResponseEntity.ok("ETUDIANT PAS INTEGRER AU PROJECT");
					}
				}
			} else {

				return ResponseEntity.ok("ENCADERENT NON AUTORISER");
			}
		} else {

			return ResponseEntity.ok("NOT EXIST");
		}

	}

	@PutMapping("/{iduser}/phase/{id}")
	@PreAuthorize("hasRole('ENCADERENT')")
	public ResponseEntity<?> updatePhase(@PathVariable("id") final Long id, @PathVariable("iduser") final Long iduser,
			@RequestBody Phase phase) {
		Optional<Project> p = projectrepository.findById(phaserepository.findById(id).get().getProject().getId());
		Optional<Phase> ph = phaserepository.findById(id);
		if (ph.isPresent()) {
			if (p.get().getEncaderent().getId() == iduser) {

				Phase phasecurrent = ph.get();

				String libelle = phase.getLibelle();
				if (libelle != null) {
					phasecurrent.setLibelle(libelle);
				}

				String description = phase.getDescription();
				if (description != null) {
					phasecurrent.setDescription(description);
				}

				LocalDate datedebut = phase.getDatedebut();
				if (datedebut != null) {
					phasecurrent.setDatedebut(datedebut);
				}

				LocalDate datefin = phase.getDatefin();
				if (datefin != null) {
					phasecurrent.setDatefin(datefin);
				}

				Boolean etat = phase.getEtat_realisation();
				if (etat != null) {
					phasecurrent.setEtat_realisation(etat);
				}
				
				User etudiant = phase.getEtudiantrealise();
				if (etudiant != null) {
					if (p.get().getEtudiant1().getId() == phase.getEtudiantrealise().getId()) {
						phasecurrent.setEtudiantrealise(userrepo.findById(phase.getEtudiantrealise().getId()).get());
					}
					else {
						if (p.get().getEtudiant2() != null) {
							if (p.get().getEtudiant2().getId() == phase.getEtudiantrealise().getId()) {
								phasecurrent.setEtudiantrealise(userrepo.findById(phase.getEtudiantrealise().getId()).get());
							}
						}else {
							return ResponseEntity.ok("ETUDIANT PAS INTEGRER AU PROJECT");
							}
						}
				}
				
				List<Document> document = phase.getDocument_technique();
				if (document != null) {
					phasecurrent.setDocument_technique(document);
				}
				
				phaserepository.save(phasecurrent);
				return ResponseEntity.ok(phaserepository.findById(phasecurrent.getId()).get());

			} else {
				return ResponseEntity.ok("ENCADERENT NON AUTORISER");
			}

		} else {
			return ResponseEntity.ok("NOT EXIST");
		}

	}

}
