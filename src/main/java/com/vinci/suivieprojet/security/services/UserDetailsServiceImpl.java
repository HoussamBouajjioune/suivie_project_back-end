package com.vinci.suivieprojet.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinci.suivieprojet.models.User;
import com.vinci.suivieprojet.models.UserDemande;
import com.vinci.suivieprojet.repository.UserDemandeRepository;
import com.vinci.suivieprojet.repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("user Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
	
}
