package com.vinci.suivieprojet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinci.suivieprojet.models.Profil;
import com.vinci.suivieprojet.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	Optional<User> findByLogin(String Login);
	
	List<User> findByProfil(Profil profile);

	Boolean existsByLogin(String username);

	Boolean existsByEmail(String email);

}