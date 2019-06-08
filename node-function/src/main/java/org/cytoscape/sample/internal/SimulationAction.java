package org.cytoscape.sample.internal;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;

public class SimulationAction  extends AbstractCyAction{

	private static final long serialVersionUID = 1L;
	
	private CyApplicationManager cyApplicationManager;
	private TableArray myTableArray;
	private CyNetwork myNet;
	
	public SimulationAction(CyApplicationManager cyApplicationManagerRef){
		super("One step simulation");
		
		setPreferredMenu("Apps");
		cyApplicationManager = cyApplicationManagerRef;	
		myTableArray = TableArray.getInstance();
		myNet = cyApplicationManager.getCurrentNetwork();
	}
	
	public boolean isReady() {
		boolean isReady = true;
		List<CyNode> nodeList = myNet.getNodeList();
		for (CyNode node : nodeList) {
			if(!myTableArray.isSet(node.getSUID())) {
				isReady = false;
				JOptionPane.showMessageDialog(null, "Please, set node functions.");
				break;
			}
		}
				
		for (String value : myNet.getDefaultNodeTable().getColumn("value").getValues(String.class))
			if (value == null) { 
				isReady = false;
				JOptionPane.showMessageDialog(null, "Please, set node values.");
				break;
			}
		return isReady;
	}
	
	public void actionPerformed(ActionEvent e) {
		/*if (cyApplicationManager.getCurrentNetwork() == null){			
			return;
		}*/
		if (isReady()) {
			List<CyRow> rows = myNet.getDefaultNodeTable().getAllRows();
			List<CyNode> nodes = myNet.getNodeList();
			
			List<String> tempValues = new ArrayList<String>();
			for(CyNode node : nodes) {
				//JOptionPane.showMessageDialog(null, node.getSUID());
				List<CyNode> neighborList = myNet.getNeighborList(node, CyEdge.Type.INCOMING);
				String[] incomeValues = new String[neighborList.size()];
				int cc = 0;
				
				for(CyNode neighbor : neighborList) {
					for (CyRow row : rows) {
						if (row.get("SUID", Long.class) == neighbor.getSUID()) {
							incomeValues[cc] = row.get("value", String.class);
							cc++;
						}
					}			
				}
				tempValues.add(TableArray.getInstance().getValue(incomeValues, node.getSUID()));
				//JOptionPane.showMessageDialog(null, TableArray.getInstance().getValue(incomeValues, node.getSUID()));
			}
			
			int i = 0;
			for(CyNode node : nodes) {
				//JOptionPane.showMessageDialog(null, node.getSUID());
				for (CyRow row : rows) {
					if (row.get("SUID", Long.class) == node.getSUID()) {
						row.set("value", tempValues.get(i)); 
						i++;
					}
				}
			}
		}
		
		
	}
}
