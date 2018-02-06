package unitn.sde.project.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;



@Entity
@Table(name="User")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
//@XmlType(propOrder = {"idPerson", "firstname", "lastname", "birthdate", "activity"})
@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 654354554568768461L;
	
	@Id // defines this attributed as the one that identifies the entity
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="email")
	private String email;
	
	
	public User () {
		
	}
	
	public User(String username, String firstname, String lastname, String password, String email) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
