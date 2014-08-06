package testing.JettyServer.database.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;


@Entity
@Table(name="foo")
public class Foo 
{

    private long id;
    private String title;
    private Date creationDate;
    private Date modifedDate;
    private String content;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() 
    {
        return id;
    }
    public void setId(long id) 
    {
        this.id = id;
    }
    
    @Column(name="TITLE")
    public String getTitle() 
    {
        return title;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }
    
    @Column(name="CREATION_DATE")
    public Date getCreationDate() 
    {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) 
    {
        this.creationDate = creationDate;
    }
    
    @Column(name="MODIFED_DATE")
    public Date getModifedDate() {
        return modifedDate;
    }
    public void setModifedDate(Date modifedDate) 
    {
        this.modifedDate = modifedDate;
    }
    
    @Column(name="CONTENT")
    public String getContent() 
    {
        return content;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }
}