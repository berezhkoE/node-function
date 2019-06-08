package org.cytoscape.sample.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class LoopTaskFactory extends AbstractTaskFactory {

	private CyApplicationManager cyApplicationManager;
	
	public LoopTaskFactory(final CyApplicationManager cyApplicationManager){
		this.cyApplicationManager = cyApplicationManager;
	}
	
	public TaskIterator createTaskIterator(){
		return new TaskIterator(new LoopTask(cyApplicationManager));
	}
}
