package testing.JettyServer.resources;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import testing.JettyServer.engines.BarEngine;
import testing.JettyServer.types.Info;

@Path("bar")
public class BarResource 
{
	
	final BarEngine barEngine;
	
	@Inject
	public BarResource(BarEngine barEngine)
	{
		this.barEngine = barEngine;
	}
	
	@GET
	@Path("info")
	@Produces(MediaType.APPLICATION_JSON)
	public Info barInfo(@Context HttpServletRequest request)
	{
		System.out.println("BarResource called with: "+barEngine.toString());
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		
		Info info = new Info();
		info.setType("Bar");
		info.setCount(barEngine.countBar());
		info.setSessionId(sessionId);
		
		return info;
	}
	
    @GET
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public String addBar() 
    {
    	System.out.println("addFoo called with: "+barEngine.toString());
		
    	barEngine.createNewBar();
    	
        return "{\"yay\":\"hooray\"}";
    }
	
}
