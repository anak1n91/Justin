import java.util.*;
import java.io.*;

public class Students implements Serializable
{
   //Person p;
   ArrayList<Student> students;
   Person currentUser;
   
   public Students()
   {
      students = new ArrayList<Student>();
   }
   
   public void add(Student s)
   {
      students.add(s);
   }
   
   public void remove(Student s)
   {
      for(int i = 1; i < students.size(); i++)
      {
         if(students.get(i).equals(s))
            students.remove(i);
      }
   }
   public ArrayList<Student> returnStudents()
   {
      return students;
   }
   
   public void setStudents(ArrayList<Student> students)
   {
      this.students = students;
   }
   
   public Student search(String id) throws DataStructureException
   {
      for(int i = 0; i< students.size(); i++)
      {
         Person p = students.get(i).getPerson();
         System.out.println(p);
         if(p.getID().equals(id))
         {
            return students.get(i);
         }      
       }
       throw new DataStructureException("Invalid ID"); 
   }
   
   public Student searchByString(String info)
   {
      for(int i = 0; i< students.size(); i++)
      {
         if(students.get(i).getPerson().toString().equals(info))
         {
            return students.get(i);
         }      
       }
       return students.get(0); 
   }
   //selection sort
   public void sortStudents()
   {
      for (int i = 0; i< students.size(); i++)
      {
         for(int j = i+1; j<students.size(); j++)
         {
            if(students.get(i).getPerson().compareTo(students.get(j).getPerson()) > 0)
            {
               Student temp = students.get(j);
               students.set(j, students.get(i));
               students.set(i,temp);
            }
         }   
        
      } 
    } 
 }