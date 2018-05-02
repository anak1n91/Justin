import java.util.*;
import java.io.*;

public class Person implements Serializable
{
  private String name;
  private String email;
  private String id;
  private String phone;

  public Person(String name, String id, String email,String phone)
  {
   this.name = name;
   this.email = email;
   this.id = id;
   this.phone = phone;
  }
 
  public Person()
  {
  }
  
  public String getName( )
  {
    return name; 
  }

  public String getID( )
  {
     return id;
  }
  
  public String getPhone()
  {
     return phone;
  }
  
  public void setName( String name )
  {
      this.name = name;  
  }
  
  public String getEmail()
  {
      return email;
  }

  public void setID( String newID )
  {
      this.id = newID;   
  }
  public Person getPerson()
  {
      return this;
  }
   
  public boolean equals( Object o )
  {
   if(o instanceof Person)
   {
      Person p  = (Person)o;
	   return p.getID().equals(id);
   }else{
      return false;
   } 
  }

  public int compareTo(Object o)
  {
   Person p = (Person) o;
   return this.name.compareTo(p.getName());
  }
  
  public String toString( )
  {
     String s = String.format("Name: %-20s ID: %-20s Email: %-20s",name,id,email);
     return s;  
  } 
}