package com.vinci.suivieprojet.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinci.suivieprojet.models.Profil;
import com.vinci.suivieprojet.models.User;
import com.vinci.suivieprojet.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userrepository;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createUser(@RequestBody User user) {

		if (!userrepository.findById(user.getId()).isPresent()) {
			try {
				user.setPassword(encoder.encode(user.getPassword()));
				return ResponseEntity.ok(userrepository.save(user));
			} catch (Exception e) {
				return ResponseEntity.ok("EMAIL OR LOGIN DEJA EXISTE");
			}

		} else {
			return ResponseEntity.ok("USER DEJA EXISTE");
		}
	}

	@GetMapping("/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUser(@PathVariable("id") final Long id) {
		Optional<User> user = userrepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}
	}

	@GetMapping("{iduser}/user/{id}")
//	@PreAuthorize("")
	public ResponseEntity<?> getUserbyUser(@PathVariable("id") final Long id,
			@PathVariable("iduser") final Long iduser) {
		Optional<User> user = userrepository.findById(id);
		if (user.isPresent()) {
			if (user.get().getId() == iduser)
				return ResponseEntity.ok(user.get());
			else
				return ResponseEntity.ok("NOT AUTORISER");
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}
	}

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUsers() {

		return ResponseEntity.ok(userrepository.findAll());
	}

	@GetMapping("/users/{profile}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('DIRECTEUR') or hasRole('SURVEILLANT')")
	public ResponseEntity<?> getUsersbyProfile(@PathVariable("profile") final Profil profile) {
		try {
			return ResponseEntity.ok(userrepository.findByProfil(profile));
		} catch (Exception e) {
			return ResponseEntity.ok("MAUVAISE SAISIE");
		}
	}

	@PutMapping("/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable("id") final Long id, @RequestBody User user) {
		Optional<User> u = userrepository.findById(id);
		if (u.isPresent()) {
			User currentUser = u.get();

			String nom = user.getNom();
			if (nom != null) {
				currentUser.setNom(nom);
			}

			String prenom = user.getPrenom();
			if (prenom != null) {
				currentUser.setPrenom(prenom);
				;
			}

			String num_telephone = user.getNum_telephone();
			if (num_telephone != null) {
				currentUser.setNum_telephone(num_telephone);
			}

			String email = user.getEmail();
			if (email != null) {
				currentUser.setEmail(email);
			}

			String login = user.getLogin();
			if (login != null) {
				currentUser.setLogin(login);
				;
			}

			String password = user.getPassword();
			if (password != null) {
				currentUser.setPassword(password);
			}

			Profil profil = user.getProfil();
			if (profil != null) {
				currentUser.setProfil(profil);
			}

			try {
				userrepository.save(currentUser);
				return ResponseEntity.ok(currentUser);
			} catch (Exception e) {
				return ResponseEntity.ok("EMAIL OR LOGIN DEJA EXISTE");
			}
		} else {
			return ResponseEntity.ok("USER NOT EXIST");
		}
	}

	@DeleteMapping("/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable("id") final Long id) {
		Optional<User> user = userrepository.findById(id);
		if (user.isPresent()) {
			userrepository.deleteById(id);
			return ResponseEntity.ok("SUPPRIMER");
		} else {
			return ResponseEntity.ok("NOT EXIST");
		}
	}

}
