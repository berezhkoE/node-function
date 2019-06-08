package org.cytoscape.sample.internal;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class TableArray {
	private List<NodeTruthTable> tableList;
	private static TableArray instance;
	
	public TableArray() {
		tableList = new ArrayList<NodeTruthTable>();
	}
	
	public static synchronized TableArray getInstance() {
		if (instance == null)
			instance = new TableArray();
	    return instance;
	}
	
	public void add(NodeTruthTable table) {
		tableList.add(table);
	}
	
	public String[][] getTable(long nodeID){
		String[][] result = null;
		for (NodeTruthTable table : tableList) {
			if (table.getNodeID() == nodeID) {
				result = table.getTable();
			}
		}
		return result;
	}
	
	public void set(NodeTruthTable table, long nodeID) {
		tableList.set(tableList.indexOf(getTable(nodeID)), table);
	}
	
	public boolean equals (String[] str1, String[] str2) {
		boolean result = true;
		for (int i = 0; i < str1.length; i++) {
			//JOptionPane.showMessageDialog(null, str1[i]);
			//JOptionPane.showMessageDialog(null, str2[i]);
			if (!str1[i].equals(str2[i])) {
				result = false;
				continue;
			}
		}
		return result;
	}
	
	public String getValue(String[] income, long nodeID) {
		String result = "err";
		String[][] table = TableArray.getInstance().getTable(nodeID);
		for (String[] s : table) {
			if (equals(income, s))
				result = s[s.length - 1];
		}
		return result;
	}
	
	public String[] getHeader(long nodeID) {
		String[] result = null;
		for (NodeTruthTable table : tableList) {
			if (table.getNodeID() == nodeID) {
				result = table.getHeader();
			}
		}
		return result;
	}
	
	public boolean isSet(long nodeID) {
		boolean result = false;
		if (!tableList.isEmpty()) {
			for (NodeTruthTable table : tableList) {
				if (table.getNodeID() == nodeID) { //table.getNeighbors().equals(myNet.getNeighborList(myNet.getNode(nodeID), CyEdge.Type.INCOMING))
					result = true;
				}
			}
		}
		return result;
	}
}
