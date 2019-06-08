package org.cytoscape.sample.internal;

import java.util.List;

import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;

public class NodeTruthTable {
	private long nodeID;
	private String[][] tableData;	
	private String[] columnsHeader;
	CyNetwork myNet;
	
	public NodeTruthTable() {
		tableData = null;
	}
	
	public NodeTruthTable(String[] columnsHeader, String[][] tableData) {
		this.columnsHeader = columnsHeader;
		this.tableData = tableData;
	}
	
	public NodeTruthTable(long ID, CyNetwork myNet){
		this.nodeID = ID;
		this.myNet = myNet;
		
		CyNode selectedNode = myNet.getNode(nodeID);
		
		List<CyNode> neighborList = myNet.getNeighborList(selectedNode, CyEdge.Type.INCOMING);			
		
		List<CyRow> rows = myNet.getDefaultNodeTable().getAllRows();
		
		int columnCount = neighborList.size() + 1;
		columnsHeader = new String[columnCount];
		int cc = 0;
				
		for(CyNode node : neighborList) {
			for (CyRow row : rows) {
				if (row.get("SUID", Long.class) == node.getSUID()) {
					columnsHeader[cc] = row.get("shared name", String.class);
					cc++;				
				}
			}			
		}
		
		for (CyRow row : rows) {
			if (row.get("SUID", Long.class) == nodeID) {
				columnsHeader[cc] = row.get("shared name", String.class);
			}				
		}
		int rowCount = (int) Math.pow(2, columnCount - 1);
		
		this.tableData = new String[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++)
         	for (int j = 0; j < columnCount; j++)
         		this.tableData[i][j] = "0";
		
	}
	
	public long getNodeID() {
		return nodeID;
	}
	
	public String[][] getTable(){
		return tableData;
	}
	
	public String[] getHeader() {
		return columnsHeader;
	}
	
	public List<CyNode> getNeighbors(){
		return myNet.getNeighborList(myNet.getNode(nodeID), CyEdge.Type.INCOMING);			
	}
}
