package com.sonia.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.entities.User;

public class UserDaoTest {

	@Mock
	EntityManagerFactory emf;
	@Mock
	EntityManager em;
	@Mock
	TypedQuery<User> mockTypedQuery;
	@Mock
	List<User> mockUserList;
	@Mock
	User mockUser;
	@InjectMocks
	UserDao userDao = new UserDao();
	
	String username = "abc";
	String password = "pass";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(emf.createEntityManager()).thenReturn(em);
	}
	
	@Test
	public void verifyLoginInfo_should_return_a_user_if_user_exists() {
		when(em.createQuery("SELECT t FROM User t WHERE username = \'abc\' AND password = \'pass\'", User.class)).thenReturn(mockTypedQuery);
		when(mockTypedQuery.getResultList()).thenReturn(mockUserList);
		when(mockUserList.size()).thenReturn(1);
		when(mockUserList.get(0)).thenReturn(mockUser);
		
		User result = userDao.verifyLoginInfo(username, password);
		
		assertEquals(mockUser, result);
	}

	@Test
	public void verifyLoginInfo_should_return_null_if_user_does_not_exist() {
		when(em.createQuery("SELECT t FROM User t WHERE username = \'abc\' AND password = \'pass\'", User.class)).thenReturn(mockTypedQuery);
		when(mockTypedQuery.getResultList()).thenReturn(mockUserList);
		when(mockUserList.size()).thenReturn(0);
		
		User result = userDao.verifyLoginInfo(username, password);
		
		assertNull(result);
	}
	
}
