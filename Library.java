import java.io.*;
import java.util.*;

public class Library implements Serializable
{
   ArrayList<Book> books;
   String name;
   
   public Library(String name)
   {
      this.name = name;
      books = new ArrayList<Book>();
   }
   
   public int getSize()
   {
      return books.size();
   }
   
   public void add(Book b)
   {
      books.add(b);
   }
   
   public void setBooks(ArrayList<Book> books)
   {
      this.books = books;
   }
   
   public ArrayList<Book> returnBooks()
   {
      return books;
   }
   public void removeBook(String bookDetails)
   {
      for(int i = 0; i< books.size(); i++)
      {
         if(bookDetails.equals(books.get(i).toString()))
         {
            books.remove(i);
         }
       }
    }
   public ArrayList<Book> searchGenre(String genre)
   {
         ArrayList<Book> searchGenre = new ArrayList<Book>();
         for(int i = 0; i < books.size(); i ++)
         {
            if (books.get(i).getGenre().equals(genre))
               searchGenre.add(books.get(i));
         }
           return searchGenre;
   }
   
   public void sortBooks()
   {
      for (int i = 0; i< books.size(); i++)
      {
         for(int j = i+1; j<books.size(); j++)
         {
            if(books.get(i).getName().compareTo(books.get(j).getName()) > 0)
            {
               Book temp = books.get(j);
               books.set(j, books.get(i));
               books.set(i,temp);
            }
         }   
        
      }    
   }
}