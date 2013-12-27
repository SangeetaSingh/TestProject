package com.sample.testproject.client.dto;

import java.io.Serializable;
import java.util.Set;

import com.sample.testproject.domain.TreeLeaf;

public class TreeNodeDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long Id ;
	private String name;
	Set<TreeLeafDTO> leafs;
	
	 public TreeNodeDTO() {
	  }

	  public TreeNodeDTO(Long id) {
	    this.Id = id;
	  }
	public TreeNodeDTO(Long id, String name, Set<TreeLeafDTO> leafs) {
		super();
		Id = id;
		this.name = name;
		this.leafs = leafs;
		System.out.println("TreeNodeDTO-->"+name);
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

	public Set<TreeLeafDTO> getLeafs() {
		return leafs;
	}

	public void setLeafs(Set<TreeLeafDTO> leafs) {
		this.leafs = leafs;
	}
	
	

}
