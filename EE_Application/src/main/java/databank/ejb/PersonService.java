package databank.ejb;
/*****************************************************************
 * File: PersonPojo.java Course materials (21S) CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */

import java.io.Serializable;
import java.util.List;

import javax.ejb.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import databank.model.PersonPojo;



@Singleton
public class PersonService implements Serializable{
	/** explicitly set serialVersionUID */
	private static final long serialVersionUID = 1L;

	//get the log4j2 logger for this class
	private static final Logger LOG = LogManager.getLogger();

	
	@PersistenceContext(name="PU_DataBank")
	protected EntityManager em;


	
	public List< PersonPojo> readAllPeople() {
		LOG.debug( "reading all people");
		//use the named JPQL query from the PersonPojo class to grab all the people
		TypedQuery< PersonPojo> allPeopleQuery = em.createNamedQuery( PersonPojo.PERSON_FIND_ALL, PersonPojo.class);
		//execute the query and return the result/s.
		return allPeopleQuery.getResultList();
	}

	
	@Transactional
	public PersonPojo createPerson( PersonPojo person) {
		LOG.debug( "creating a person = {}", person);
		em.persist(person);
		return person;
	}

	
	
	public PersonPojo readPersonById( int personId) {
		LOG.debug( "read a specific person = {}", personId);
		return em.find(PersonPojo.class, personId);
	}
	

	@Transactional
	public PersonPojo updatePerson( PersonPojo personWithUpdates) {
		LOG.debug( "updating a specific person = {}", personWithUpdates);
		PersonPojo mergedPersonPojo = em.merge( personWithUpdates);
		return mergedPersonPojo;
	}

	
	@Transactional
	public void deletePersonById( int personId) {
		LOG.debug( "deleting a specific personID = {}", personId);
		PersonPojo person = readPersonById( personId);
		LOG.debug( "deleting a specific person = {}", person);
		if ( person != null) {
			em.refresh( person);
			em.remove( person);
		}
	}

}