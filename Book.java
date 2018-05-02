import java.io.*;

public class Book implements Serializable
{
   private String name;
   private String author;
   private String genre;
   private String date;
   private boolean checkedOut;
   
   public Book (String name, String author, String genre, String date)
   {
      this.name = name;
      this.author = author;
      this.genre = genre;
      this.date = date;
      checkedOut = false;
   }
   
   public String getName()
   {
      return name;
   }
   
   public String getAuthor()
   {
      return author;
   }
   
   public String getGenre()
   {
      return genre;
   }
   public void setName(String name)
   {
      this.name = name;
   }
   
   public void setAuthor(String author)
   {
      this.author = author;
   }
   
   public void setGenre(String genre)
   {
      this.genre = genre;
   }
   
   public boolean isCheckedOut()
   {
      return checkedOut;
   }
     
   
   public boolean equals(Object o)
   {
      return (this == o);
   }
   
   public int compareTo(Object o)
   {
      Book b = (Book) o;
      return this.name.compareTo(b.getName());
   }
   public String toString()
   {
      String s =String.format("Title: %-20s Genre: %-20s Author: %-20s",name,genre,author);
      return s;
   }
}