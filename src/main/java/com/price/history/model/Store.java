package com.price.history.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the store database table.
 * 
 */
@Entity
@Table(name="store")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	public Store() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}