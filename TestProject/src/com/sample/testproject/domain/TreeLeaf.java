package com.sample.testproject.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.sample.testproject.client.dto.TreeLeafDTO;

@Entity
public class TreeLeaf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long Id;
	private String name;
	private Long parentId;
	
	public TreeLeaf(){
		
	}
	
	
	
	public TreeLeaf(Long id, String name, Long parentId) {
		super();
		Id = id;
		this.name = name;
		this.parentId = parentId;
	}

	public TreeLeaf(TreeLeafDTO treeleafDTO){
		Id = treeleafDTO.getId();
		name = treeleafDTO.getName();
		parentId = treeleafDTO.getParentId();
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
	

}
