package testing.JettyServer.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Info 
{
	String type;
	long count;
	String sessionId;
	
	@JsonProperty
	public String getType() 
	{
		return type;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	
	@JsonProperty
	public long getCount() 
	{
		return count;
	}
	public void setCount(long count) 
	{
		this.count = count;
	}
	
	@JsonProperty(value="session_id")
	public String getSessionId() 
	{
		return sessionId;
	}
	public void setSessionId(String sessionId) 
	{
		this.sessionId = sessionId;
	}
}
