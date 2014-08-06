package testing.JettyServer.resources;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import testing.JettyServer.engines.FooEngine;
import testing.JettyServer.types.Info;

@Path("foo")
public class FooResource 
{
	
	final FooEngine fooEngine;
	
	@Inject
	public FooResource(FooEngine fooEngine)
	{
		this.fooEngine = fooEngine;
	}
	
	@GET
	@Path("info")
	@Produces(MediaType.APPLICATION_JSON)
	public Info fooInfo(@Context HttpServletRequest request)
	{
		System.out.println("fooInfo called with: "+fooEngine.toString());
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		
		Info info = new Info();
		info.setType("Foo");
		info.setCount(fooEngine.countFoo());
		info.setSessionId(sessionId);
		
		return info;
	}
	
    @GET
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public String addFoo() 
    {
    	System.out.println("addFoo called with: "+fooEngine.toString());
		
    	fooEngine.createNewFoo();
    	
        return "{\"yay\":\"hooray\"}";
    }
}
