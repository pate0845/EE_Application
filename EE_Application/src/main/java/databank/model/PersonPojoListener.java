/*****************************************************************
 * File: PersonPojo.java Course materials (21S) CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.model;

import java.time.Instant;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * What annotations should be used on these method.
 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/entity-listeners.html<br>
 */
public class PersonPojoListener {

	private static final Logger LOG = LogManager.getLogger();

	@PrePersist
	// called before persist to add the dates
	public void setCreatedOnDate( PersonPojo person) {
		LOG.debug( "Person @PrePersist before = {}", person);
		Instant now = Instant.now();
		person.setCreated( now);
		//might as well call setUpdatedDate as well
		person.setUpdated( now);
		LOG.debug( "Person @PrePersist after = {}", person);
	}

	// called before update to update the date
	@PreUpdate
	public void setUpdatedDate( PersonPojo person) {
		LOG.debug( "Person @PreUpdate before = {}", person);
		person.setUpdated( Instant.now());
		LOG.debug( "Person @PreUpdate after = {}", person);
	}

}