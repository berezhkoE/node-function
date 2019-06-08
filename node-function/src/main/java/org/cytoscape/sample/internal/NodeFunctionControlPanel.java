package org.cytoscape.sample.internal;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyRow;

public class NodeFunctionControlPanel extends JPanel implements CytoPanelComponent {
	
	private CyApplicationManager cyApplicationManager;
	public static TableArray myTableArray;
	
	private static final long serialVersionUID = 8292806967891823933L;
	private CyNetwork myNet;

	private JTable table;
	private DefaultTableModel tableModel;
	private JTabbedPane tabbedPane;
	private JPanel tab;
	private JButton btn, btn1, btn2, btn3;
	
	private String[][] tableData;
	private String[] columnsHeader;
	
	private int count = 0;
	static private long selectedNodeID = 0;

	public NodeFunctionControlPanel(CyApplicationManager cyApplicationManagerRef) {
		cyApplicationManager = cyApplicationManagerRef;
		tabbedPane = new JTabbedPane();
		
		btn = new JButton("Set Node Functions");
		btn1 = new JButton("Next Node");
		btn2 = new JButton("Save");
		btn3 = new JButton("Delete");

		addInputListeners();
		this.add(btn);
		this.setVisible(true);
	}
	
	public void addTable() {
		myNet = cyApplicationManager.getCurrentNetwork();		
		myTableArray = TableArray.getInstance();

		
		CyRow row = myNet.getDefaultNodeTable().getAllRows().get(count);
		
		selectedNodeID = row.get("SUID", Long.class);
		//JOptionPane.showMessageDialog(null, selectedNodeID);
		
		tab = new JPanel();
		//tabbedPane.addTab(row.get("shared name", String.class), tab);
		
		
		myTableArray.add(new NodeTruthTable(selectedNodeID, myNet));
		tableData = myTableArray.getTable(selectedNodeID);
		columnsHeader = myTableArray.getHeader(selectedNodeID);
		
		tableModel = new DefaultTableModel();
		tableModel.setDataVector(tableData,columnsHeader);
		table = new JTable(tableModel);
		
		
		table.setPreferredScrollableViewportSize(new Dimension(350, 200));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		JScrollPane sp = new JScrollPane(table);
		tab.add(sp);
		
		//JOptionPane.showMessageDialog(null, tabbedPane.getTabCount());
		//JOptionPane.showMessageDialog(null, count);
		
		if (tabbedPane.getTabCount() == 0) {
			//JOptionPane.showMessageDialog(null, "1");
			tabbedPane.addTab(row.get("shared name", String.class), tab);
			this.add(tabbedPane);
		}
		else {
		if (tabbedPane.getTabCount() >= count) {
			//JOptionPane.showMessageDialog(null, "2");
			tabbedPane.insertTab(row.get("shared name", String.class), new ImageIcon(), tab, " ", count);			
		}
		else {
			//JOptionPane.showMessageDialog(null, "11");
			tabbedPane.addTab(row.get("shared name", String.class), tab);
		}
		}

		
		this.add(btn1);
		this.add(btn2);
		this.add(btn3);

	}
	
	private void addInputListeners() {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				removeAll();
				addTable();
				count++;
				btn1.setEnabled(false);
            }
        });
		
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				addTable();
				count++;
				btn1.setEnabled(false);
            }
        });
		
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabbedPane.getTabCount() < myNet.getDefaultNodeTable().getAllRows().size()) {					
					btn1.setEnabled(true);
				}
				
				for (int i = 0; i < table.getRowCount(); i++)
					for (int j = 0; j < table.getColumnCount(); j++)
						tableData[i][j] = table.getValueAt(i, j).toString();
				myTableArray.set(new NodeTruthTable(columnsHeader, tableData), selectedNodeID);
				
            }
        });
		
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count = tabbedPane.getSelectedIndex();
				//JOptionPane.showMessageDialog(null, count);
				tabbedPane.removeTabAt(count);
				addTable();
				btn1.setEnabled(false);
            }
        }); 
    }
	
	
	public Component getComponent() {
		return this;
	}


	public CytoPanelName getCytoPanelName() {
		return CytoPanelName.WEST;
	}


	public String getTitle() {
		return "Node Function";
	}


	public Icon getIcon() {
		return null;
	}
}
