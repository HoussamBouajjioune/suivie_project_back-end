package com.vinci.suivieprojet.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import com.vinci.suivieprojet.models.Document;
import com.vinci.suivieprojet.models.Livrable;
import com.vinci.suivieprojet.models.Organisme;
import com.vinci.suivieprojet.models.Phase;
import com.vinci.suivieprojet.models.Profil;
import com.vinci.suivieprojet.models.Project;
import com.vinci.suivieprojet.models.User;
import com.vinci.suivieprojet.repository.DocumentRepository;
import com.vinci.suivieprojet.repository.LivrableRepository;
import com.vinci.suivieprojet.repository.OrganismeRepository;
import com.vinci.suivieprojet.repository.PhaseRepository;
import com.vinci.suivieprojet.repository.ProjectRepository;
import com.vinci.suivieprojet.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	UserRepository userrepo;

	@Autowired
	DocumentRepository documentrepo;

	@Autowired
	OrganismeRepository organismerepo;

	@Autowired
	private ProjectRepository projectrepository;

	@Autowired
	private PhaseRepository phaserepository;

	@Autowired
	private LivrableRepository livrablerepository;

	@PostMapping("/project")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT')")
	public ResponseEntity<?> createProject(@RequestBody Project project) {
		Project p2 = new Project(project.getNom(), project.getDescription(), project.getDatedebut(),
				project.getDatefin());
		
		if (project.getEtudiant1().getId() != null) {
			p2.setEtudiant1(userrepo.findById(project.getEtudiant1().getId()).get());
			if (p2.getEtudiant1().getProfil() != Profil.ROLE_ETUDIANT)
				return ResponseEntity.ok("INSERER UN ETUDIANT");
		}
		
		if (project.getEtudiant2() != null) {
			if (project.getEtudiant2().getId() == -1) {
				p2.setEtudiant2(null);
			} else {
				p2.setEtudiant2(userrepo.findById(project.getEtudiant2().getId()).get());
				if (p2.getEtudiant2().getProfil() != Profil.ROLE_ETUDIANT)
					return ResponseEntity.ok("INSERER UN ETUDIANT");
			}
		}
		
		p2.setEncaderent(userrepo.findById(project.getEncaderent().getId()).get());
		if (p2.getEncaderent().getProfil() != Profil.ROLE_ENCADERENT)
			return ResponseEntity.ok("INSERER UN ENCADERENT");
		p2 = projectrepository.save(p2);
		p2 = projectrepository.findById(p2.getId()).get();
		p2.setDocument_technique(project.getDocument_technique());
		p2.setOrganisme(organismerepo.findById(project.getOrganisme().getId()).get());
		p2 = projectrepository.save(p2);
		return ResponseEntity.ok(p2);
	}

	@PutMapping("/project/{id}")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT')")
	public ResponseEntity<?> updateProject(@PathVariable("id") final Long id, @RequestBody Project project) {
		Optional<Project> p = projectrepository.findById(id);
		if (p.isPresent()) {
			Project currentProject = p.get();

			String nom = project.getNom();
			if (nom != null) {
				currentProject.setNom(nom);
			}

			String description = project.getDescription();
			if (description != null) {
				currentProject.setDescription(description);
			}

			LocalDate datedebut = project.getDatedebut();
			if (datedebut != null) {
				currentProject.setDatedebut(datedebut);
			}

			LocalDate datefin = project.getDatefin();
			if (datefin != null) {
				currentProject.setDatefin(datefin);
			}

			User encaderent = project.getEncaderent();
			if (encaderent != null) {
				currentProject.setEncaderent(encaderent);
				if (userrepo.findById(encaderent.getId()).get().getProfil() != Profil.ROLE_ENCADERENT)
					return ResponseEntity.ok("INSERER UN ENCADERENT");
			}

			User etudiant1 = project.getEtudiant1();
			if (etudiant1 != null) {
				currentProject.setEtudiant1(etudiant1);
				if (userrepo.findById(etudiant1.getId()).get().getProfil() != Profil.ROLE_ETUDIANT)
					return ResponseEntity.ok("INSERER UN ETUDIANT");
			}

			User etudiant2 = project.getEtudiant2();
			if (etudiant2 != null) {
				if (project.getEtudiant2().getId() == -1)
					currentProject.setEtudiant2(null);
				else {
					currentProject.setEtudiant2(etudiant2);
					if (userrepo.findById(etudiant2.getId()).get().getProfil() != Profil.ROLE_ETUDIANT)
						return ResponseEntity.ok("INSERER UN ETUDIANT");
				}
			}

			Organisme organisme = project.getOrganisme();
			if (organisme != null) {
				if (organismerepo.findById(organisme.getId()).isPresent()) {
					currentProject.setOrganisme(organismerepo.findById(organisme.getId()).get());
				} else {
					return ResponseEntity.ok("INSERER UN ORGANISME");
				}
			}

			List<Document> document = project.getDocument_technique();
			if (document != null) {
				currentProject.setDocument_technique(document);
			}

			projectrepository.save(currentProject);
			return ResponseEntity.ok(projectrepository.findById(currentProject.getId()).get());
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}
	}

	@GetMapping("/project/{id}")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT') or hasRole('ENCADERENT') ")
	public ResponseEntity<?> getProject(@PathVariable("id") final Long id) {
		Optional<Project> project = projectrepository.findById(id);
		if (project.isPresent()) {
			return ResponseEntity.ok(project.get());
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}
	}

	@GetMapping("/project/{id}/Phases")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT') or hasRole('ENCADERENT') or hasRole('ETUDIANT')")
	public ResponseEntity<?> getProjectPhases(@PathVariable("id") final Long id) {
		Optional<Project> project = projectrepository.findById(id);
		if (project.isPresent()) {
			return ResponseEntity.ok(project.get().getPhases());
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}
	}

	@GetMapping("/project/{id}/Phases/{idphase}")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT') or hasRole('ENCADERENT') or hasRole('ETUDIANT')")
	public ResponseEntity<?> getProjectPhase(@PathVariable("id") final Long id,
			@PathVariable("idphase") final Long idphase) {
		Optional<Project> project = projectrepository.findById(id);
		if (project.isPresent()) {
			if (phaserepository.findById(idphase).isPresent()) {
				Phase ph = phaserepository.findById(idphase).get();
				if (project.get().getId() == ph.getProject().getId())
					return ResponseEntity.ok(ph);
				else
					return ResponseEntity.ok("PHASE EXIST BUT NOT INCLUD FOT THIS PROJECT");
			} else {
				return ResponseEntity.ok("PHASE NOT EXIST");
			}

		} else {
			return ResponseEntity.ok("PROJECT NOT EXIST");
		}
	}

	@GetMapping("/project/{id}/Phases/{idphase}/livrable")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT') or hasRole('ETUDIANT') or hasRole('ENCADERENT')")
	public ResponseEntity<?> getProjectPhaselivrable(@PathVariable("id") final Long id,
			@PathVariable("idphase") final Long idphase) {
		Optional<Project> project = projectrepository.findById(id);
		if (project.isPresent()) {
			if (phaserepository.findById(idphase).isPresent()) {
				Phase ph = phaserepository.findById(idphase).get();
				if (project.get().getId() == ph.getProject().getId()) {
					Optional<Livrable> lb = livrablerepository.findByPhase(ph);
					if (lb.isPresent())
						return ResponseEntity.ok(lb.get());
					else
						return ResponseEntity.ok("LIVRABLE NOT EXIST YET");
				} else
					return ResponseEntity.ok("PHASE EXIST BUT NOT INCLUD FOT THIS PROJECT");
			} else {
				return ResponseEntity.ok("PHASE NOT EXIST");
			}

		} else {
			return ResponseEntity.ok("PROJECT NOT EXIST");
		}
	}

	@GetMapping("/projects")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT')")
	public ResponseEntity<?> getProjects() {
		return ResponseEntity.ok(projectrepository.findAll());
	}

	@GetMapping("/projects/encaderent/{id}")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT') or hasRole('ENCADERENT')")
	public ResponseEntity<?> getProjectsbyEncadenet(@PathVariable("id") final Long id) {
		if (userrepo.findById(id).isPresent())
			return ResponseEntity.ok(projectrepository.findByEncaderent(userrepo.findById(id).get()));
		else
			return ResponseEntity.ok("USER NOT EXIST");
	}

	@GetMapping("/projects/etudiant/{id}")
	@PreAuthorize("hasRole('DIRECTEUR') or hasRole('SURVEILLANT') or hasRole('ETUDIANT')")
	public ResponseEntity<?> getProjectsbyEtudiant(@PathVariable("id") final Long id) {
		List<Project> p1 = null;
		List<Project> p2 = null;
		if (userrepo.findById(id).isPresent())
			p1 = projectrepository.findByEtudiant1(userrepo.findById(id).get());
		if (userrepo.findById(id).isPresent())
			p2 = projectrepository.findByEtudiant2(userrepo.findById(id).get());
		if (userrepo.findById(id).isPresent()) {
			ArrayList<Project> p3 = new ArrayList<>();
			p3.addAll(p1);
			p3.addAll(p2);
			return ResponseEntity.ok(p3);
		} else {
			return ResponseEntity.ok("USER NOT EXIST");
		}
	}

}
