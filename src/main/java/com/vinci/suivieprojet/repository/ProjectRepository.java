package com.vinci.suivieprojet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinci.suivieprojet.models.Project;
import com.vinci.suivieprojet.models.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{

	Iterable<Project> findByEncaderent(User encaderent);
	
	List<Project> findByEtudiant1(User etudiant1);
	
	List<Project> findByEtudiant2(User etudiant2);
	
}
