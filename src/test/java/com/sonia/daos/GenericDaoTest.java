package com.sonia.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.*;
import org.mockito.*;

import com.sonia.entities.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class GenericDaoTest {

	@Mock
	private EntityManagerFactory emf;
	@Mock
	private EntityManager em;
	@Mock
	private EntityTransaction et;
	@Mock
	private User mockUser;
	@Mock
	private User mockUser1;
	@InjectMocks
	private GenericDao<User> genericDao = new GenericDao<User>(User.class);
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	private void setupEntityManagerMock(){
		when(emf.createEntityManager()).thenReturn(em);
		when(em.getTransaction()).thenReturn(et);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void listEntities_should_create_a_query_then_return_a_list_of_entities(){
		TypedQuery<User> userQuery = mock(TypedQuery.class);
		List<User> mockUserQueryReturnList = new ArrayList<>();
		
		setupEntityManagerMock();
		when(em.createQuery("SELECT t FROM User t", User.class)).thenReturn(userQuery);
		when(userQuery.getResultList()).thenReturn(mockUserQueryReturnList);
		
		List<User> result = genericDao.listEntities();
		
		assertSame(mockUserQueryReturnList, result);
	}
	
	@Test
	public void getEntity_should_find_an_entity_by_id() {
		int id = 1;
		setupEntityManagerMock();
		
		genericDao.getEntity(id);
		
		InOrder order = inOrder(et, em);
		order.verify(em).find(User.class, id);
		order.verify(em).close();
	}
	
	@Test
	public void removeEntity_should_find_the_entity_by_id_then_delete(){
		int id = 1;
		setupEntityManagerMock();
		when(em.find(User.class, id)).thenReturn(mockUser);
		
		genericDao.removeEntity(id);
		
		InOrder order = inOrder(et, em);
		order.verify(em).find(User.class, id);
		order.verify(et).begin();
		order.verify(em).remove(mockUser);
		order.verify(et).commit();
		order.verify(em).close();
	}
	
	@Test
	public void addOrUpdateEntity_should_merge_the_entity_and_return_an_entity() {
		setupEntityManagerMock();
		when(em.merge(mockUser)).thenReturn(mockUser1);
		
		User result = genericDao.addOrUpdateEntity(mockUser);
		
		InOrder order = inOrder(et, em);
		order.verify(et).begin();
		order.verify(em).merge(mockUser);
		order.verify(et).commit();
		order.verify(em).close();
		assertEquals(mockUser1, result);
	}
	
}
