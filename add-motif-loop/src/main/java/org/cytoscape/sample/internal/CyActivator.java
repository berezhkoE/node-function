package org.cytoscape.sample.internal;

import org.cytoscape.sample.internal.LoopTaskFactory;
import org.cytoscape.work.TaskFactory;
import org.osgi.framework.BundleContext;
import org.cytoscape.service.util.AbstractCyActivator;
import java.util.Properties;
import org.cytoscape.application.CyApplicationManager;


public class CyActivator extends AbstractCyActivator {
	public CyActivator() {
		super();
	}


	public void start(BundleContext bc) {

		/*
		CyNetworkManager cyNetworkManagerServiceRef = getService(bc,CyNetworkManager.class);
		CyNetworkNaming cyNetworkNamingServiceRef = getService(bc,CyNetworkNaming.class);
		CyNetworkFactory cyNetworkFactoryServiceRef = getService(bc,CyNetworkFactory.class);
		*/
		CyApplicationManager cyApplicationManagerRef = getService(bc,CyApplicationManager.class);
		
		LoopTaskFactory createNetworkTaskFactory = new LoopTaskFactory(cyApplicationManagerRef);
				
		Properties sample05TaskFactoryProps = new Properties();
		sample05TaskFactoryProps.setProperty("preferredMenu","Tools.Add motif");
		sample05TaskFactoryProps.setProperty("title","Loop");
		registerService(bc,createNetworkTaskFactory,TaskFactory.class, sample05TaskFactoryProps);
	}
}

