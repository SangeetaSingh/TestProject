package com.sample.testproject.client.dto;

import java.io.Serializable;

public class TreeLeafDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long Id;
	private String name;
	private Long parentId;
	
	public TreeLeafDTO(){
		
	}
	
	public TreeLeafDTO(Long id)
	{
		this.Id = id;
	}
	
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public TreeLeafDTO(Long id, String name, Long parentId) {
		super();
		Id = id;
		this.name = name;
		this.parentId = parentId;
	}
	
	

}
