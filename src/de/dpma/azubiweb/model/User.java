package de.dpma.azubiweb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	private int rights;
	
	private static final long serialVersionUID = 1L;
	
	public User() {
		
		super();
	}
	
	public int getId() {
		
		return this.id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public String getUsername() {
		
		return this.username;
	}
	
	public void setUsername(String username) {
		
		this.username = username;
	}
	
	public String getPassword() {
		
		return this.password;
	}
	
	public void setPassword(String password) {
		
		this.password = password;
	}
	
	public int getRights() {
		
		return this.rights;
	}
	
	public void setRights(int rights) {
		
		this.rights = rights;
	}
	
	@Override
	public String toString() {
		
		return String.format("id=%d, Username=%s, Password=%s, Rolle=%d", id, username, password, rights);
	}
	
}
