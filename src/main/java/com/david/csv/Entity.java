package com.david.csv;

import java.util.Date;

public class Entity {
	private int id;
	private String name;
	private Date date;
	private long uuid;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getUuid() {
		return uuid;
	}

	public void setUuid(long uuid) {
		this.uuid = uuid;
	}

	public Entity(int id, String name, Date date, long uuid) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.uuid = uuid;
	}

	public Entity() {
		
	}
}
