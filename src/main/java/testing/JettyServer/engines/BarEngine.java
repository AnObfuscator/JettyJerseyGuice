package testing.JettyServer.engines;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import testing.JettyServer.database.DatabaseAccess;
import testing.JettyServer.database.entities.Bar;

@Singleton
public class BarEngine 
{
	@Inject
	DatabaseAccess database;
	
	public Bar getBar(int id)
	{
		String query = String.format("select b from Bar b where b.id = %d", id);
		Bar bar = database.executeQuery(query, Bar.class).get(0);
		
		return bar;
	}
	
	public long countBar()
	{
		String query = "select count(b) from Bar b";
		Long result = database.executeQuery(query, Long.class).get(0);
		return result;
	}
	
	public void createNewBar() 
	{
		Bar bar = new Bar();
		bar.setCreationDate(new Date());
		database.save(bar);
	}

}
