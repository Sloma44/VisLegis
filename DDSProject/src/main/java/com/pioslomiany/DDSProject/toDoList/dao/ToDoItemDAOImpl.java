package com.pioslomiany.DDSProject.toDoList.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.toDoList.entity.ToDoItem;

@Repository
public class ToDoItemDAOImpl {

	@Autowired
	EntityManager entityManager;
	
//	load the list sorted by status (task done are in the end), and then by date
	public List<ToDoItem> getAllItems() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<ToDoItem> query = session.createQuery("FROM ToDoItem l ORDER BY l.isDone DESC, l.deadLineDate ASC", ToDoItem.class);
		
		return query.getResultList();
	}

	public ToDoItem getItemById(int itemId) {
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(ToDoItem.class, itemId);
	}
	
	public void saveItem(ToDoItem toDoItem) {
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(toDoItem);
	}

	/*
	 * every time the button is cliked the task status is changed
	 * there are only two states possible, both hard coded:
	 * done / not done ("W trakcie" / "Zakończono")
	 */
	public void setItemAsDone(int itemId) {
		Session session = entityManager.unwrap(Session.class);
		
		ToDoItem toDoItem = session.get(ToDoItem.class, itemId);
		
		String status = toDoItem.getIsDone();
		
		if (status.equals("W trakcie")) {
			toDoItem.setIsDone("Zakończono");			
		} else {
			toDoItem.setIsDone("W trakcie");
		}
	}

	public void deleteItemById(int itemId) {
		Session session = entityManager.unwrap(Session.class);
		
		ToDoItem toDoItem = session.get(ToDoItem.class, itemId);

		session.delete(toDoItem);
	}

	public void deleteDoneItems() {
		Session session = entityManager.unwrap(Session.class);
		
//		had to add SuppressWarning, if the type is specified it throws InvalidDataAccessApiUsageException
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("DELETE FROM ToDoItem l WHERE l.isDone = :stat");
		query.setParameter("stat", "Zakończono");
		
		query.executeUpdate();
	}

	
}