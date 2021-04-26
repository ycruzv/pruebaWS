package com.valid.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = true)
	private String name;
	@Column(nullable = true)
	private	String lastName;
	@Column(nullable = true)
	private boolean isProcess;
	
	
	
	
	public Customer() {
		super();
	}
	
	
	
	public Customer(String name, String lastName, boolean isProcess) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.isProcess = isProcess;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean getIsProcess() {
		return isProcess;
	}
	public void setProcess(boolean isProcess) {
		this.isProcess = isProcess;
	}
	
	
	
	

}
