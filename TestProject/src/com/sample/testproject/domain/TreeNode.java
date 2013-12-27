package com.sample.testproject.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;



import com.sample.testproject.client.dto.TreeLeafDTO;
import com.sample.testproject.client.dto.TreeNodeDTO;

@Entity
public class TreeNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long Id ;
	private String name;
	Set<TreeLeaf> leafs;
	
	
	public TreeNode()
	{
		
	}
	
	public TreeNode(Long id, String name) {
		Id = id;
		this.name = name;
	}
	
	public TreeNode(TreeNodeDTO treeNodeDTO)
	{
		Id = treeNodeDTO.getId();
		name = treeNodeDTO.getName();
		Set<TreeLeafDTO> treeLeafDTOs = treeNodeDTO.getLeafs();
		if(treeLeafDTOs != null)
		{
			Set<TreeLeaf> treeLeafs = new HashSet<TreeLeaf>(treeLeafDTOs.size());
		    for (TreeLeafDTO leafDTO : treeLeafDTOs) {
		      treeLeafs.add(new TreeLeaf(leafDTO));
		    }
		    this.leafs = treeLeafs;
		}
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
	
	public Set<TreeLeaf> getLeafs() {
		return leafs;
	}

	public void setLeafs(Set<TreeLeaf> leafs) {
		this.leafs = leafs;
	}

	
	
	public void addLeafs(TreeLeaf leaf){
		if(leafs ==null)
			leafs = new HashSet<TreeLeaf>();
		leafs.add(leaf);
	}

	public void removeleafs(TreeLeaf leaf){
	 if (leafs == null) {
	      return;
	    }
	    leafs.remove(leaf);
	}
}
