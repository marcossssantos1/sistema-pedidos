package com.marcos.api_pedidos.common;

import static com.marcos.api_pedidos.common.UserConstants.USER_MOCK;
import static com.marcos.api_pedidos.common.UserConstants.USER_MOCK2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.marcos.api_pedidos.entities.Users;
import com.marcos.api_pedidos.entities.Users.Role;
import com.marcos.api_pedidos.repository.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TestEntityManager manager;
	
	@AfterEach
	private void afterEach() {
		USER_MOCK.setId(null);;
	}
	
	@Test
	public void criarUsuario_ComDadosValidos_ReturnUsuario() {
		Users user = repository.save(USER_MOCK);
		
		Users sut = manager.find(Users.class, user.getId());
		
		assertThat(sut).isNotNull();
		assertThat(sut.getName()).isEqualTo(USER_MOCK.getName());
		assertThat(sut.getEmail()).isEqualTo(USER_MOCK.getEmail());
		assertThat(sut.getTelefone()).isEqualTo(USER_MOCK.getTelefone());
		assertThat(sut.getPassword()).isEqualTo(USER_MOCK.getPassword());
	}
	
	@Test
	public void criarUsuario_ComDadosInvalidos_ReturnException() {
		Users userNull = new Users();
		Users invalidoUser = new Users(null, "","","","",Role.ROLE_ADMIN);
		
		assertThatThrownBy( () -> repository.save(userNull)).isInstanceOf(RuntimeException.class);
		assertThatThrownBy( () -> repository.save(invalidoUser)).isInstanceOf(RuntimeException.class);
	}
	
	@Test
	public void buscarUsuario_ComIdValido_ReturnUsuario() {
		Users user = manager.persistFlushFind(USER_MOCK2);
		
		manager.detach(user);
		
		Optional<Users> sut = repository.findById(user.getId());
		
		assertThat(sut).isNotEmpty();
		assertThat(sut.get()).isEqualTo(USER_MOCK2.getId());
		
	}
	
	@Test
	public void buscarUsuario_comIdInvalido_ReturnsEmpty() {
		Optional<Users> sut = repository.findById(1L);
		
		assertThat(sut).isEmpty();

	}
}
