package org.cytoscape.sample.internal;

import org.cytoscape.sample.internal.RegulatoryChainTaskFactory;
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
		
		RegulatoryChainTaskFactory createNetworkTaskFactory1 = new RegulatoryChainTaskFactory(cyApplicationManagerRef);
				
		Properties sample05TaskFactoryProps1 = new Properties();
		sample05TaskFactoryProps1.setProperty("preferredMenu","Apps");
		sample05TaskFactoryProps1.setProperty("title","One step simulation");
		registerService(bc,createNetworkTaskFactory1,TaskFactory.class, sample05TaskFactoryProps1);
		
		RegulatoryChainTaskFactory createNetworkTaskFactory2 = new RegulatoryChainTaskFactory(cyApplicationManagerRef);
		
		Properties sample05TaskFactoryProps2 = new Properties();
		sample05TaskFactoryProps2.setProperty("preferredMenu","Apps");
		sample05TaskFactoryProps2.setProperty("title","Network reconstruction");
		registerService(bc,createNetworkTaskFactory2,TaskFactory.class, sample05TaskFactoryProps2);
	}
}

