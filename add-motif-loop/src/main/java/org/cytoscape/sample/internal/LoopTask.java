package org.cytoscape.sample.internal;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyEdge;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.application.CyApplicationManager;

public class LoopTask extends AbstractTask {
	
	private CyApplicationManager cyApplicationManager;
	
	public LoopTask(final CyApplicationManager cyApplicationManager){
		this.cyApplicationManager = cyApplicationManager;
	}
	
	public void run(TaskMonitor monitor) {
		
		// Create an empty network
		//CyApplicationManager cyApplicationManager;
		CyNetwork myNet = cyApplicationManager.getCurrentNetwork();
		//myNet.getRow(myNet).set(CyNetwork.NAME,
		//		      namingUtil.getSuggestedNetworkTitle("My Network"));
		
		
		int N = myNet.getNodeCount();
		// Add two nodes to the network
		CyNode node1 = myNet.addNode();
		CyNode node2 = myNet.addNode();
		
		// set name for new nodes
		myNet.getDefaultNodeTable().getRow(node1.getSUID()).set("name", "Node "+(N+1));
		myNet.getDefaultNodeTable().getRow(node2.getSUID()).set("name", "Node "+(N+2));
		
		// Add an edge
		CyEdge edge1 = myNet.addEdge(node1, node2, true);
		CyEdge edge2 = myNet.addEdge(node2, node1, true);
		
		myNet.getDefaultEdgeTable().getRow(edge1.getSUID()).set("name", "Node 1 (interacts with) Node 2");
		myNet.getDefaultEdgeTable().getRow(edge2.getSUID()).set("name", "Node 2 (interacts with) Node 1");
		myNet.getDefaultEdgeTable().getRow(edge1.getSUID()).set("interaction", "interacts with");
		myNet.getDefaultEdgeTable().getRow(edge2.getSUID()).set("interaction", "interacts with");

				
		//netMgr.addNetwork(myNet);
		
		// Set the variable destroyNetwork to true, the following code will destroy a network
		/*boolean destroyNetwork = false;
		if (destroyNetwork){
			// Destroy it
			 netMgr.destroyNetwork(myNet);			
		}*/
	}
}
