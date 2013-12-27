package com.sample.testproject.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




import org.hibernate.Session;

import com.sample.testproject.client.TestService;
import com.sample.testproject.client.dto.TreeLeafDTO;
import com.sample.testproject.client.dto.TreeNodeDTO;
import com.sample.testproject.domain.TreeLeaf;
import com.sample.testproject.domain.TreeNode;
import com.sample.testproject.util.HibernateUtil;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TestServiceImpl extends RemoteServiceServlet implements
		TestService {

	

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public List<TreeNodeDTO> getTreeNodes() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		    session.beginTransaction();
		    @SuppressWarnings("unchecked")
			List<TreeNode> treeNodes = new ArrayList<TreeNode>(session.createQuery("from TreeNode").list());
		    List<TreeNodeDTO> treeNodeDTOs = new ArrayList<TreeNodeDTO>(treeNodes != null ? treeNodes.size() : 0);
		    	    if (treeNodes != null) {
		    	      for (TreeNode node : treeNodes) {
		    	    	  treeNodeDTOs.add(createTreeNodeDTO(node));
		    	      }
		    	    }
		    session.getTransaction().commit();
		    return treeNodeDTOs;
	}

	

	@Override
	public List<TreeLeafDTO> getTreeLeafs() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    @SuppressWarnings("unchecked")
		List<TreeLeaf> treeLeafs = new ArrayList<TreeLeaf>(session.createQuery("from TreeNode").list());
	    List<TreeLeafDTO> leafDTOs = new  ArrayList<TreeLeafDTO>(treeLeafs != null ? treeLeafs.size() : 0);
	    if(treeLeafs != null)
	    {
	    	for(TreeLeaf leaf: treeLeafs){
	    		leafDTOs.add(createLeafDTO(leaf));
	    	}
	    }
	    session.getTransaction().commit();
	    return leafDTOs;
	}

	@Override
	public Long saveNodes(TreeNodeDTO node) {
		System.out.println("In SaveNodes implementation -->"+node);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(node);
	    session.getTransaction().commit();
	    return node.getId();
	}

	@Override
	public Long saveLeafs(TreeLeafDTO leaf) {
		System.out.println("In SaveLeafs implemnetation -->"+leaf);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(leaf);
	    session.getTransaction().commit();
	    return leaf.getId();
	}

	@Override
	public void saveLeafstoNodes(TreeNodeDTO nodeDTO, TreeLeafDTO leafDTO) {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		    session.beginTransaction();
		 TreeNode   node = (TreeNode) session.load(TreeNode.class, nodeDTO.getId());
		  TreeLeaf  leaf = (TreeLeaf) session.load(TreeLeaf.class, leafDTO.getId());
		    node.addLeafs(leaf);
		    session.save(node);
		    session.getTransaction().commit();
		
	}
	@Override
	  public List<TreeNodeDTO> getAllNodeLeafs() {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    List<TreeNode> nodes = new ArrayList<TreeNode>(session.createQuery("from TreeNode").list());
	    List<TreeNodeDTO> nodeDTOs = new ArrayList<TreeNodeDTO>(nodes != null ? nodes.size() : 0);
	    if (nodes != null) {
	      for (TreeNode node : nodes) {
	    	  nodeDTOs.add(createTreeNodeDTO(node));
	      }
	    }
	    session.getTransaction().commit();
	    return nodeDTOs;
	  }
	
	private TreeNodeDTO createTreeNodeDTO(TreeNode node) {
		Set<TreeLeaf> leafs = node.getLeafs();
	    Set<TreeLeafDTO> leafsDTOs = new HashSet<TreeLeafDTO>( leafs != null ? leafs.size() : 0);
	    if (leafs != null) {
	      for (TreeLeaf leaf : leafs) {
	    	  leafsDTOs.add(createLeafDTO(leaf));
	      }
	    }
		return new TreeNodeDTO(node.getId(), node.getName(), leafsDTOs);
	}

	private TreeLeafDTO createLeafDTO(TreeLeaf leaf) {
		// TODO Auto-generated method stub
		return new TreeLeafDTO(leaf.getId(), leaf.getName(), leaf.getParentId());
	}
}
