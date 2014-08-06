package testing.JettyServer.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testing.JettyServer.engines.FooEngine;

@Singleton
public class FooServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@Inject
	FooEngine fooEngine;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		System.out.println("TestServlet called with engine: "+fooEngine.toString());
		resp.setStatus(200);
	}

}
