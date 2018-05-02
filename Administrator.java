import java.util.*;
import java.io.*;

public class Administrator implements Serializable
{
   Person p;
   ArrayList<Person> admins;
   
   public Administrator()
   {
      admins = new ArrayList<Person>();
   }
   
   public void add(Person p)
   {
      admins.add(p);
   }
   
   public void remove(Person p)
   {
   }
   
   public int getSize()
   {
      return admins.size();
   }
   
   public void setAdmins(ArrayList<Person> admins)
   {
      this.admins= admins;
   }
   
   public Person search(String ID) throws DataStructureException
   {
      for(int i = 0; i< admins.size(); i++)
      {
         System.out.println(admins.get(i));
         if(admins.get(i).getID().equals(ID))
         {
            return admins.get(i);
         }
      }
      throw new DataStructureException("Invalid ID");
   }
   
}   