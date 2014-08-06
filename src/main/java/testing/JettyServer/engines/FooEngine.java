package testing.JettyServer.engines;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import testing.JettyServer.database.DatabaseAccess;
import testing.JettyServer.database.entities.Foo;

@Singleton
public class FooEngine 
{
	@Inject
	DatabaseAccess database;
	
	public Foo getFoo(int id)
	{
		String query = String.format("select f from Foo f where f.id = %d", id);
		Foo foo = database.executeQuery(query, Foo.class).get(0);
		
		return foo;
	}

	public long countFoo()
	{
		String query = "select count(f) from Foo f";
		Long result = database.executeQuery(query, Long.class).get(0);
		return result;
	}
	
	public void createNewFoo() 
	{
		Foo foo = new Foo();
		foo.setCreationDate(new Date());
		database.save(foo);
	}

}
