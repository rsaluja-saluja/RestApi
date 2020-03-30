package com.restapi.RestApi.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {

	private String name;
	private Integer id;
	private String role;
	private List<Links> links = new ArrayList<Links>();
	
	
	public Employee() {
		super();
	}
	
	public Employee(String name, Integer id, String role) {
		super();
		this.name = name;
		this.id = id;
		this.role = role;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public List<Links> getListLinks() {
		return links;
	}

	public void setListLinks(List<Links> listLinks) {
		this.links = listLinks;
	}
	
	public void addLink(String uri, String rel) {
		this.links.add(new Links(uri, rel));
	}
	
}
