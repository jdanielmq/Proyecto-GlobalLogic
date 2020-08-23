package com.bci.pruebatecnica.data.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.bci.pruebatecnica.data.funcionales.DtoEntity;

public class UserDto implements Serializable, DtoEntity {

	private static final long serialVersionUID = 1L;

	private Long idUser;  
	private String name;
	private String email;
	private String password;
	private LocalDateTime  createDate;
	private LocalDateTime lastLogin;
	private LocalDateTime modified;
	private boolean active;
	

	
	public UserDto(String name, String email, String password, LocalDateTime createDate,
			LocalDateTime lastLogin, LocalDateTime modified, boolean active) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.createDate = createDate;
		this.lastLogin = lastLogin;
		this.modified = modified;
		this.active = active;
	}

	public UserDto(Long idUser) {
		super();
		this.idUser = idUser;
	}

	public UserDto() {
		super();
	}
		
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
