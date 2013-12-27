package com.sample.testproject.client;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sample.testproject.client.dto.TreeLeafDTO;
import com.sample.testproject.client.dto.TreeNodeDTO;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.Selector;
import com.google.gwt.query.client.Selectors;

import static com.google.gwt.query.client.GQuery.*;
import static com.google.gwt.query.client.css.CSS.*;


public class Home extends Composite {

	private static HomeUiBinder uiBinder = GWT.create(HomeUiBinder.class);
	//MySelectors s = GWT.create(MySelectors.class);
	//List<Widget> allGwtTrees = $("gwt-Tree").widgets();
	
	//allGwtTrees
	
    final TestServiceAsync testService = (TestServiceAsync) GWT.create(TestService.class);

	@UiTemplate("Home.ui.xml")
	interface HomeUiBinder extends UiBinder<Widget, Home> {
	}
	
	
	
	
	
	@UiField(provided = true)
	   final HomeResources res;
	
	
	@UiField
	TextBox addItem;
	
	@UiField
	Button buttonSubmit;
	
	@UiField
	Button buttonDelete;
	
	
	Tree tree = new Tree();
	
	   @UiField
	   HTMLPanel basePanel;
	   
	   @UiField
	   VerticalPanel mainPanel;
	   
	   @UiField
	   HorizontalPanel addPanel;
	   
	   @UiField
	   Label label = new Label("Click on me and I will disappear");
	
	   public Home() {
			this.res = GWT.create(HomeResources.class);
		     this.res.style().ensureInjected();
			initWidget(uiBinder.createAndBindUi(this));
			//mainPanel.add(addPanel);
			mainPanel.add(tree);
			//basePanel.add(mainPanel);
			
			
		}
	   
	  
	   
	@UiHandler("buttonSubmit")
	void doClickSubmit(ClickEvent event)
	{
		 if(tree.getSelectedItem() != null)
   	  {
   		  	addTreeElement(tree.getSelectedItem(),testService);
   	  }
   	  else
   		  addTree(testService);

     }
	
	@UiHandler("addItem")
	void doKeyboardSubmit(KeyDownEvent event)
	{
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
       	 if(tree.getSelectedItem() != null)
	    	  {
	    		  	addTreeElement(tree.getSelectedItem(),testService);
	    		  	
	    	  }
	    	  else
	    		  addTree(testService);
       	 	//tree.getSelectedItem().setSelected(false);
	    	 
	      }
	}
	
	
	@UiHandler("buttonDelete")
	void doClickDelete(ClickEvent event){
		 if(tree.getSelectedItem() != null)
	   	  {
	   		  	tree.getSelectedItem().remove();
	   	  }
	   	  else
	   		  Window.confirm("Please select an item to be deleted");
		
	}
	
	
	@UiHandler("addItem")
	void onClick(ClickEvent evnt)
	{
		addItem.setText("");
	}
	
	
	

	protected void addTreeElement(TreeItem item, TestServiceAsync testService) {
		final String symbol = addItem.getText().toUpperCase().trim();
		// Tree value must be between 1 and 10 chars that are numbers, letters, or dots.
	    if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
	      Window.alert("'" + symbol + "' is not a valid symbol.");
	      
	      addItem.selectAll();
	      return ;
	    }
		TreeItem leafItem = new TreeItem();
		leafItem.setText(symbol);
		leafItem.setStyleName("sub");
		item.addItem(leafItem);
		addItem.setText(""); 
		//String  parent = item.getParentItem().getText();
		TreeLeafDTO  leaf = new TreeLeafDTO(null, symbol, null);
		testService.saveLeafs(leaf,new AsyncCallback<Long>() {
	          public void onFailure(Throwable caught) {
	              Window.alert("Failed to save account: " + caught.getMessage());
	            }
	            public void onSuccess(Long result) {
	              Window.alert("Account saved");
	            }
	          });
		tree.setSelectedItem(null);
	}
	
	protected void addTree(TestServiceAsync testService) {
	
		final String symbol = addItem.getText().toUpperCase().trim();
		System.out.println("hello" +symbol);
	    addItem.setFocus(true);
	    TreeNodeDTO node = new TreeNodeDTO(null, symbol, null);
	    testService.saveNodes(node, new AsyncCallback<Long>() {
	          public void onFailure(Throwable caught) {
	              Window.alert("Failed to save account: " + caught.getMessage());
	            }
	            public void onSuccess(Long result) {
	              Window.alert("Account saved");
	            }
	          });
	
	    // Tree value must be between 1 and 10 chars that are numbers, letters, or dots.
	    if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
	      Window.alert("'" + symbol + "' is not a valid symbol.");
	      addItem.selectAll();
	      return ;
	    }
	    addItem.setText("");
	     final TreeItem outerRoot = new TreeItem();
	     outerRoot.setText(symbol);
	     
	     tree.addItem(outerRoot);
	     tree.setSelectedItem(null);
	     
	}
}
