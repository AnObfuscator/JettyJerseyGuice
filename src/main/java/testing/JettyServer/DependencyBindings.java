package testing.JettyServer;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.eclipse.jetty.servlet.DefaultServlet;

import testing.JettyServer.database.DatabaseAccess;
import testing.JettyServer.engines.BarEngine;
import testing.JettyServer.engines.FooEngine;
import testing.JettyServer.resources.BarResource;
import testing.JettyServer.resources.FooResource;
import testing.JettyServer.servlets.BarServlet;
import testing.JettyServer.servlets.FooServlet;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class DependencyBindings extends ServletModule 
{	
	final EntityManagerFactory emf;
	
	public DependencyBindings(EntityManagerFactory emf)
	{
		this.emf = emf;
	}
	
    @Override
    protected void configureServlets() 
    {	
    	bindDatabase();
        bindEngines();
        bindResources();
        bindServlets();
        
        Map<String, String> initParams = new HashMap<>();
        initParams.put("com.sun.jersey.config.feature.Trace", "true");
        
        filter("/*").through(GuiceContainer.class, initParams);
        
        serve("/fooServlet").with(FooServlet.class);
        serve("/barServlet").with(FooServlet.class);
        
        DefaultServlet defaultServlet = new DefaultServlet();
        serve("*.html").with(defaultServlet);
        serve("/js/*").with(defaultServlet);
    }
    
    private void bindDatabase()
    {
    	bind(EntityManagerFactory.class).toInstance(emf);
        bind(DatabaseAccess.class);
    }
    
    private void bindEngines()
    {
    	bind(FooEngine.class);
    	bind(BarEngine.class);
    }
    
    private void bindResources()
    {
    	bind(FooResource.class);
    	bind(BarResource.class);
    }
    
    private void bindServlets()
    {
    	bind(FooServlet.class);
    	bind(BarServlet.class);
    }
}
