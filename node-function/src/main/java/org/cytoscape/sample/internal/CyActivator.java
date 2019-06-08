package org.cytoscape.sample.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.model.events.NetworkAddedListener;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.session.events.SessionLoadedListener;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context){

		CyApplicationManager cyApplicationManagerRef = getService(context, CyApplicationManager.class);
		CySwingApplication cytoscapeDesktopService = getService(context,CySwingApplication.class);
				
		SimulationAction simulationAction = new SimulationAction(cyApplicationManagerRef);
		registerService(context,simulationAction,CyAction.class,new Properties());
		
		NetworkEventsListener networkEventsListener = new NetworkEventsListener(cyApplicationManagerRef);
		registerService(context,networkEventsListener,NetworkAddedListener.class, new Properties());
		registerService(context,networkEventsListener,SessionLoadedListener.class, new Properties());
		
		FixedPointsControlPanel fixedPointsControlPanel = new FixedPointsControlPanel(cyApplicationManagerRef);
		FPControlPanelAction FPcontrolPanelAction = new FPControlPanelAction(cytoscapeDesktopService, fixedPointsControlPanel);
		
		registerService(context,fixedPointsControlPanel,CytoPanelComponent.class, new Properties());
		registerService(context,FPcontrolPanelAction,CyAction.class, new Properties());
		
		RecParamControlPanel recParamControlPanel = new RecParamControlPanel(cyApplicationManagerRef);
		RPControlPanelAction RPcontrolPanelAction = new RPControlPanelAction(cytoscapeDesktopService, recParamControlPanel);
		
		registerService(context,recParamControlPanel,CytoPanelComponent.class, new Properties());
		registerService(context,RPcontrolPanelAction,CyAction.class, new Properties());

		NodeFunctionControlPanel nodeFunctionControlPanel = new NodeFunctionControlPanel(cyApplicationManagerRef);
		NFControlPanelAction NFcontrolPanelAction = new NFControlPanelAction(cytoscapeDesktopService, nodeFunctionControlPanel);

		registerService(context,nodeFunctionControlPanel,CytoPanelComponent.class, new Properties());
		registerService(context,NFcontrolPanelAction,CyAction.class, new Properties());

	}

}
