package com.sample.testproject.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sample.testproject.client.dto.TreeLeafDTO;
import com.sample.testproject.client.dto.TreeNodeDTO;
import com.sample.testproject.domain.TreeLeaf;
import com.sample.testproject.domain.TreeNode;

/**
 * The async counterpart of <code>TestService</code>.
 */
public interface TestServiceAsync {

	void getTreeNodes(AsyncCallback<List<TreeNodeDTO>> callback);

	void getTreeLeafs(AsyncCallback<List<TreeLeafDTO>> callback);

	void saveLeafs(TreeLeafDTO leaf, AsyncCallback<Long> callback);

	void saveLeafstoNodes(TreeNodeDTO node, TreeLeafDTO leaf,
			AsyncCallback<Void> callback);

	void saveNodes(TreeNodeDTO node, AsyncCallback<Long> callback);

	void getAllNodeLeafs(AsyncCallback<List<TreeNodeDTO>> callback);
}
