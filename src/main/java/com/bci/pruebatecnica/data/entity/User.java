package com.bci.pruebatecnica.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "user")
public class User  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "id_user")
	private Long idUser; 
	
	@Column(nullable = false, name = "name")
	private String name;
	@Column(nullable = false, name = "email")
	
	private String email;
	@Column(nullable = false, name = "password")
	private String password;
	@NotNull
	@Column(nullable = false, name = "create_date")
	private LocalDateTime  createDate;
	
	@NotNull
	@Column(nullable = false, name = "last_login")
	private LocalDateTime lastLogin;
	
	@NotNull
	@Column(nullable = false, name = "modified")
	private LocalDateTime modified;
	

	@Column(name = "active")
	private boolean active;
	
	
	public User() {
		super();
	}	
		
	public User(Long idUser) {
		super();
		this.idUser = idUser;
	}
	
	
	
	/*gest y sets*/
	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
