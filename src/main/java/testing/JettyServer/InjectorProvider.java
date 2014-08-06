package testing.JettyServer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;


public class InjectorProvider extends GuiceServletContextListener 
{	
	final ServletModule dependencyBindings;
	final Injector injector;
	
	public InjectorProvider(ServletModule servletModule)
	{
		this.dependencyBindings = servletModule;
		this.injector = Guice.createInjector(dependencyBindings);
	}
	
    @Override
    protected Injector getInjector() 
    {
        return injector;
    }
}