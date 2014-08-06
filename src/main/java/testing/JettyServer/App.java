package testing.JettyServer;

import java.util.EnumSet;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.servlet.GuiceFilter;

public class App 
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Initializing Database...");
        final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("ServerDatabase");

        System.out.println("\nBinding services...");
        DependencyBindings dependencyBindings = new DependencyBindings(EMF);
        InjectorProvider injectorProvider = new InjectorProvider(dependencyBindings);
        
        Server server = new Server(8080);
        ServletContextHandler servletContext = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);

        servletContext.addEventListener(injectorProvider);
        servletContext.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
                
        System.out.println("\nSetting static resources...");
        servletContext.setResourceBase("./web");
        
        System.out.println("\nStarting server.");
        server.start();
    }
}