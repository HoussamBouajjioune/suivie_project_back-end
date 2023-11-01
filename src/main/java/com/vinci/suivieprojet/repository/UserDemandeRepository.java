package com.vinci.suivieprojet.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinci.suivieprojet.models.UserDemande;

@Repository
public interface UserDemandeRepository extends CrudRepository<UserDemande, Long>{
	Optional<UserDemande> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
