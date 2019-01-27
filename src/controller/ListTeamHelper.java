package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListTeam;

public class ListTeamHelper {

	// create a global instance of the EntityManagerFactory
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleTeamList");

	
	public void insertPlayer(ListTeam toAdd) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toAdd);
		em.getTransaction().commit();
		em.close();
	}

	public void deletePlayer(ListTeam toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<ListTeam> typedQuery = em.createQuery("select t from ListTeam t where t.name = :selectedName and t.jersey = :selectedJersey", ListTeam.class);

		//Substitute parameter with actual data from the toDelete player
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedJersey", toDelete.getJersey());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list player
		ListTeam result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public void updatePlayer(ListTeam toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListTeam> searchForPlayerByName(String playerName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListTeam> typedQuery = em.createQuery("select t from ListTeam t where t.name = :selectedName", ListTeam.class);
		typedQuery.setParameter("selectedName", playerName);
		List<ListTeam> foundPlayers = typedQuery.getResultList();
		em.close();
		return foundPlayers;
	}

	public List<ListTeam> searchForPlayerByJersey(int playerJersey) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListTeam> typedQuery = em.createQuery("select t from ListTeam t where t.jersey = :selectedJersey", ListTeam.class);
		typedQuery.setParameter("selectedJersey", playerJersey);
		List<ListTeam> foundPlayers = typedQuery.getResultList();
		em.close();
		return foundPlayers;
	}

	public ListTeam searchForPlayerById(int idToEdit) {
		// TODO Auto-generated method stub
		// Find by primary key
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListTeam found = em.find(ListTeam.class, idToEdit);
		em.close();
		return found;
	}


	public void cleanUp() {
		// TODO Auto-generated method stub
		emfactory.close();
	}

	public List<ListTeam> showAllPlayers() {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		List<ListTeam> allPlayers = em.createQuery("SELECT t FROM ListTeam t").getResultList();
		// use this syntax to get ONE player:
		// ListTeam onePlayer = em.createQuery("SELECT i FROM ListTeam
		// i").getSingleResult();
		return allPlayers;
	}

}
