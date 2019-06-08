package org.cytoscape.sample.internal;

import java.util.List;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyRow;

public class FixedPointsTable {
	
	private CyNetwork myNet;
	
	private String[][] tableData;	
	private String[] columnsHeader;
	
	private static FixedPointsTable instance;
	
	public static synchronized FixedPointsTable getInstance(CyNetwork myNet) {
		if (instance == null)
			instance = new FixedPointsTable(myNet);
	    return instance;
	}
	
	FixedPointsTable(CyNetwork myNet){
		this.myNet = myNet;
		List<CyRow> rows = myNet.getDefaultNodeTable().getAllRows();
		columnsHeader = new String[rows.size()];
		int k = 0;
		for(CyRow row : rows) {
			columnsHeader[k] = row.get("shared name", String.class);
			k++;				
		}			
		
		
		this.tableData = new String[rows.size()][rows.size()];
		for (int j = 0; j < rows.size(); j++)
        	this.tableData[0][j] = "0";
	}
	
	public String[][] getTableData(){
		return tableData;
	}
	
	public String[] getColumnsHeader() {
		return columnsHeader;
	}
	
	public void addRow() {
		for (int i = 0; i < myNet.getDefaultNodeTable().getAllRows().size(); i++)
         		tableData[tableData.length][i] = "0";
	}
}
