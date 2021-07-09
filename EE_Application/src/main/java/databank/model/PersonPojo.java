/*****************************************************************
 * File: PersonPojo.java Course materials (21S) CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;



@Entity( name = "Person")
@Table( name = "Person", catalog = "databank", schema = "")
@Access( AccessType.FIELD)
@NamedQueries( { @NamedQuery( name = PersonPojo.PERSON_FIND_ALL, query = "SELECT a From Person a"),
   @NamedQuery( name = PersonPojo.PERSON_FIND_ID, query = "SELECT a FROM Person a  WHERE a.id=:id") })
@EntityListeners(PersonPojoListener.class)
public class PersonPojo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String PERSON_FIND_ALL = "Person.findAll";
	public static final String PERSON_FIND_ID = "Person.findById";

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Basic( optional = false)
	@Column( name = "id")
	private int id;

	@Basic( optional=false)
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	@Basic( optional=false)
	private String lastName;

	@Column(name="email")
	@Basic( optional=false)
	private String email;

	@Column(name="phone")
	@Basic( optional=false)
	private String phoneNumber;

	@Column(name="created")
	@Basic( optional=false)
	private long epochCreated;

	@Column(name="updated")
	@Basic( optional=false)
	private long epochUpdated;

	@Version
	private int version = 1;

	@Transient
	private Instant updated;

	@Transient
	private Instant created;

	@Transient
	private boolean editable;

	public boolean getEditable() {
		return editable;
	}

	public void setEditable( boolean editable) {
		this.editable = editable;
	}

	public int getId() {
		return id;
	}

	/**
	 * @param id new value for id
	 */
	public void setId( int id) {
		this.id = id;
	}

	/**
	 * @return the value for firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName new value for firstName
	 */
	public void setFirstName( String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the value for lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName new value for lastName
	 */
	public void setLastName( String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber( String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Instant getCreated() {
		if ( created == null)
			setCreated( epochCreated);
		return created;
	}

	public long getCreatedEpochMilli() {
		return created.toEpochMilli();
	}

	public void setCreated( Instant created) {
		setCreated( created.toEpochMilli());
	}

	public void setCreated( long created) {
		this.epochCreated = created;
		this.created = Instant.ofEpochMilli( created);
	}

	public void setUpdated( Instant updated) {
		setUpdated( updated.toEpochMilli());
	}

	public void setUpdated( long updated) {
		this.epochUpdated = updated;
		this.updated = Instant.ofEpochMilli( updated);
	}

	public Instant getUpdated() {
		if ( updated == null)
			setCreated( epochUpdated);
		return updated;
	}

	public long getUpdatedEpochMilli() {
		return updated.toEpochMilli();
	}

	public int getVersion() {
		return version;
	}

	public void setVersion( int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals( Object obj) {
		if ( this == obj) {
			return true;
		}
		if ( !( obj instanceof PersonPojo)) {
			return false;
		}
		PersonPojo other = (PersonPojo) obj;
		if ( id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "Person [id=").append( id);
		if ( firstName != null) {
			builder.append( ", ").append( "firstName=").append( firstName);
		}
		if ( lastName != null) {
			builder.append( ", ").append( "lastName=").append( lastName);
		}
		if ( phoneNumber != null) {
			builder.append( ", ").append( "phoneNumber=").append( phoneNumber);
		}
		if ( email != null) {
			builder.append( ", ").append( "email=").append( email);
		}
		if ( created != null) {
			builder.append( ", ").append( "created=").append( created);
		}
		if ( updated != null) {
			builder.append( ", ").append( "updated=").append( updated);
		}
		builder.append( "]");
		return builder.toString();
	}

}