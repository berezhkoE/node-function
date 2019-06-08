package org.cytoscape.sample.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.events.NetworkAddedEvent;
import org.cytoscape.model.events.NetworkAddedListener;
import org.cytoscape.session.events.SessionLoadedEvent;
import org.cytoscape.session.events.SessionLoadedListener;

public class NetworkEventsListener implements NetworkAddedListener, SessionLoadedListener{
	
	public static boolean networkIsSelected = false;
	private CyApplicationManager cyApplicationManager;
	
	public NetworkEventsListener (CyApplicationManager cyApplicationManagerRef) {
		cyApplicationManager = cyApplicationManagerRef;
	}
	
	public void handleEvent (NetworkAddedEvent e){
		cyApplicationManager.getCurrentNetwork().getDefaultNodeTable().createColumn("value", String.class, false);
	}
	
	public void handleEvent (SessionLoadedEvent e) {
		cyApplicationManager.getCurrentNetwork().getDefaultNodeTable().createColumn("value", String.class, false);
	}
}
