package com.marcos.api_pedidos.domain;

import static com.marcos.api_pedidos.common.UserConstants.USER_MOCK;
import static com.marcos.api_pedidos.common.UserConstants.USER_MOCK_INVALID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.marcos.api_pedidos.entities.Users;
import com.marcos.api_pedidos.repository.UserRepository;
import com.marcos.api_pedidos.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService service;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void criarUsuario_ComDadosValidos_ReturnUsuario() {
		when(repository.save(USER_MOCK)).thenReturn(USER_MOCK);
		
		Users sut = service.create(USER_MOCK);
		
		assertThat(sut).isEqualTo(USER_MOCK);
	}
	
	@Test
	public void criarUsuario_ComDadosInvalidos_ReturnException() {
		when(repository.save(USER_MOCK_INVALID)).thenThrow(RuntimeException.class);
		
		assertThatThrownBy(() -> service.create(USER_MOCK_INVALID)).isInstanceOf(RuntimeException.class);
	}
	
	@Test
	public void buscarUsuario_ComIdValido_ReturnUsuario() {
		when(repository.findById(3L)).thenReturn(Optional.of(USER_MOCK));
		
		Users sut = service.findById(3L);
		
		assertThat(sut).isNotNull();
		assertThat(sut).isEqualTo(USER_MOCK);
	}
	
	@Test
	public void buscarUsuario_ComIdInvalido_ReturnException() {
		when(repository.findById(3L)).thenThrow(RuntimeException.class);
		
		assertThatThrownBy(() -> service.findById(3L)).isInstanceOf(RuntimeException.class);
	}
	
	@Test
	public void deletarUsuario_ComIdValido_DoesNotThrowAnyException() {
		assertThatCode(() -> service.delete(10L)).doesNotThrowAnyException();
	}
	
	@Test
	public void deletarUsuario_ComIdInvalido_ReturnException() {
		doThrow(new RuntimeException()).when(repository).deleteById(99L);
		
		assertThatThrownBy(() -> service.delete(99L)).isInstanceOf(RuntimeException.class);
	}
	
	@Test
	public void listUsuarios_ReturnUsuarios() {

		List<Users> planets = new ArrayList<>() {
			{
				add(USER_MOCK);
			}
		};

		when(repository.findAll()).thenReturn(planets);

		List<Users> sut = service.findAll();
		assertThat(sut).isNotEmpty();
		assertThat(sut).hasSize(1);
		assertThat(sut.get(0)).isEqualTo(USER_MOCK);

	}
}
