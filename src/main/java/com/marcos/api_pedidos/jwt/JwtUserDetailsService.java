package com.marcos.api_pedidos.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marcos.api_pedidos.entities.Users;
import com.marcos.api_pedidos.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users usuario = service.buscarPorUsername(username);
		return new JwtUserDetails(usuario);
	}

	public JwtToken getTokenAuthenticated(String username) {
		Users.Role role = service.buscarRolePorUsername(username);
		return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
	}
}
