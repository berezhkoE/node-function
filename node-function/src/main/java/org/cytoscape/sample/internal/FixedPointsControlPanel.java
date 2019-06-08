package org.cytoscape.sample.internal;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.model.CyNetwork;

public class FixedPointsControlPanel extends JPanel implements CytoPanelComponent {
	
	private static final long serialVersionUID = 8292806967891823933L;
	private CyNetwork myNet;
	private CyApplicationManager cyApplicationManager;

	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btn, btn1, btn2;

	public FixedPointsControlPanel(CyApplicationManager cyApplicationManagerRef) {
			
		cyApplicationManager =  cyApplicationManagerRef;
		
		btn = new JButton("Add Fixed Point");
		btn1 = new JButton("Add New Point");
		btn2 = new JButton("Delete Point");
		
		this.add(btn);
		addInputListeners();
		
		this.setVisible(true);
	}

	public void addTable() {
		myNet = cyApplicationManager.getCurrentNetwork();
		
		tableModel = new DefaultTableModel();
		
		tableModel.setDataVector(FixedPointsTable.getInstance(myNet).getTableData(),
				FixedPointsTable.getInstance(myNet).getColumnsHeader());
		
		table = new JTable(tableModel);
		
		this.add(btn1);
		this.add(btn2);
		
		table.setPreferredScrollableViewportSize(new Dimension(350, 200));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
		JScrollPane sp = new JScrollPane(table); 
		this.add(sp);
		
		this.setVisible(true);
	}
	
	public Component getComponent() {
		return this;
	}

	private void addInputListeners() {
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] rowData = new String[table.getColumnCount()];
				for (int i = 0; i < table.getColumnCount(); i++)
					rowData[i] = "0";
				tableModel.insertRow(0, rowData);
                }
            });
		
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				for(int i = 0; i < table.getSelectedRowCount()+3; i++)
					tableModel.removeRow(table.getSelectedRow());
                }
            });
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				removeAll();
				addTable();
                }
            });
        }

	public CytoPanelName getCytoPanelName() {
		return CytoPanelName.WEST;
	}


	public String getTitle() {
		return "Fixed points";
	}


	public Icon getIcon() {
		return null;
	}
}
