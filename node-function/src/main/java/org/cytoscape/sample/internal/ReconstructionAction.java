package org.cytoscape.sample.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.swing.AbstractCyAction;

public class ReconstructionAction extends AbstractCyAction{

	private static final long serialVersionUID = 1L;

	ReconstructionAction(){
		super("Reconstruct Net");
		
		setPreferredMenu("Apps.Samples");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
