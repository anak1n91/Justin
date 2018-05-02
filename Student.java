import java.util.*;
import java.io.*;

public class Student implements Serializable
{
   private Person p;
   private boolean penalty;
   ArrayList<Book> booksCheckedOut;
   
   public Student()
   {
   }
   
   public Student(Person p)
   {
      this.p = p;
      this.penalty = false;
      booksCheckedOut = new ArrayList<Book>();
   }
   
   public ArrayList<Book> getBooksCheckedOut()
   {
      return booksCheckedOut;
   }
   
   public Person getPerson()
   {
      return p;
   }
   
   public Book getBook(int i)
   {
      return booksCheckedOut.get(i);
   }
   
   public void addBook(Book b)
   {
      booksCheckedOut.add(b);
   }
   
   public void setPenalty()
   {
      if(penalty)
        this.penalty = false;
      else
        this.penalty = true;
   }
   
   public void setPerson(Person p)
   {
      this.p = p;
   }
   
   public boolean hasPenalty()
   {
      return penalty;
   }
   
   public void setBooksCheckedOut(ArrayList<Book> booksCheckedOut)
   {
      this.booksCheckedOut = booksCheckedOut;
   }
   
}