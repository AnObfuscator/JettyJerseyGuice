package testing.JettyServer.database;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class DatabaseAccess 
{
	final EntityManagerFactory emf;
	
	@Inject
	public DatabaseAccess(EntityManagerFactory emf)
	{
		this.emf = emf;
	}
	
	public <M> List<M> executeQuery(String query, Class<M> entityType)
	{
		EntityManager em = emf.createEntityManager();
		List<M> resultList = null;
		
		try
		{
			resultList = em.createQuery(query, entityType).getResultList();
		}
		catch (Throwable t)
		{
			System.err.println("Error: "+t.getMessage());
		}
		finally
		{
			em.close();
		}
		
		return resultList;
	}

	public <M> List<M> executeQuery(String queryStr, Map<String, Object> parameters, Class<M> entityType)
	{
		EntityManager em = emf.createEntityManager();
		List<M> resultList = null;
		
		try
		{
			TypedQuery<M> query = em.createQuery(queryStr, entityType);
			
			for (String parameter : parameters.keySet())
			{
				Object value = parameters.get(parameter);
				query.setParameter(parameter, value);
			}
			
			resultList = query.getResultList();
		}
		catch (Throwable t)
		{
			System.err.println("Error: "+t.getMessage());
		}
		finally
		{
			em.close();
		}
		
		return resultList;
	}
	
	public <M> void saveAll(Collection<M> entities)
	{
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try
		{
			for (M entity : entities)
			{
				em.merge(entity);
			}
			
			em.flush();
			tx.commit();
		}
		catch (Throwable t)
		{
			System.err.println("Error: "+t.getMessage());
			tx.rollback();
		}
		finally
		{
			em.close();
		}
	}
	
	public <M> void save(M entity)
	{
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try
		{
			em.merge(entity);
			em.flush();
			tx.commit();
		}
		catch (Throwable t)
		{
			System.err.println("Error: "+t.getMessage());
			tx.rollback();
		}
		finally
		{
			em.close();
		}
	}
}
