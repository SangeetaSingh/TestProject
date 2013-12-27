package com.sample.testproject.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sample.testproject.client.dto.TreeLeafDTO;
import com.sample.testproject.client.dto.TreeNodeDTO;
import com.sample.testproject.domain.TreeLeaf;
import com.sample.testproject.domain.TreeNode;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("mytest")
public interface TestService extends RemoteService {
	public List<TreeNodeDTO> getTreeNodes();
	public List<TreeLeafDTO> getTreeLeafs();
	
	public Long saveNodes(TreeNodeDTO node);
	public Long saveLeafs(TreeLeafDTO leaf);
	
	public void saveLeafstoNodes(TreeNodeDTO node, TreeLeafDTO leaf);
	List<TreeNodeDTO> getAllNodeLeafs();
	
}
