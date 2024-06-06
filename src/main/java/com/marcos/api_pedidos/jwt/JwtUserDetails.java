package com.marcos.api_pedidos.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.marcos.api_pedidos.entities.Users;




public class JwtUserDetails extends User {

	private static final long serialVersionUID = 1L;
	private Users users;

    public JwtUserDetails(Users users) {
        super(users.getName(), users.getPassword(), AuthorityUtils.createAuthorityList(users.getRole().name()));
        this.users = users;
    }

    public Long getId() {
        return this.users.getId();
    }

    public String getRole() {
        return this.users.getRole().name();
    }
}
