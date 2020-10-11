package com.xadmin.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.xadmin.mockito.dao.UserRepository;
import com.xadmin.mockito.model.User;
import com.xadmin.mockito.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockitoApplicationTests {

	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	@org.junit.Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream.of(new User(101, "ashish", "user")).collect(Collectors.toList()));
		assertEquals(1, service.getAllUsers().size());
	}

	@org.junit.Test
	public void addUserTest() {
		User user = new User(201, "bhagat", "superAdmin");

		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.saveUser(user));
	}

	@org.junit.Test
	public void deleteUser() {
		User user = new User(201, "bhagat", "superAdmin");
		service.deleteUSerById(user.getId());
		verify(repository, times(1)).deleteById(user.getId());
	}

}
