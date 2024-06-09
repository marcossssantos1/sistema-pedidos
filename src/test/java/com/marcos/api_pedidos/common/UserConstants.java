package com.marcos.api_pedidos.common;

import com.marcos.api_pedidos.entities.Users;
import com.marcos.api_pedidos.entities.Users.Role;

public class UserConstants {
	
	public static final Users USER_MOCK = new Users(3L, "marcos", "marcos@gmail.com", "999999999", "12345678", Role.ROLE_CLIENTE);
	
	public static final Users USER_MOCK_INVALID = new Users(null, "", "", "", "", Role.ROLE_CLIENTE);
	
	public static final Users USER_MOCK2 = new Users(4L, "marcos", "marcos@gmail.com", "999999999", "12345678", Role.ROLE_CLIENTE);
	
	

}
