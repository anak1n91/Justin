import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;

public class LibraryDriver extends Application
{   
    Stage window;
    Scene populate, login, studentMenu, adminMenu;
    Student studentUser;
    Person adminUser;
    
    public static void main (String[] args)
    {
      launch(args);
    } 
        
    @Override
    public void start(Stage primaryStage) 
    {
      //initial list/object declaration-------------------------------------
      studentUser = new Student();
      adminUser = new Person();
      window = primaryStage;
      Administrator admins = new Administrator();
      Students students = new Students();
      LinkedList<Library> libraries = new LinkedList<Library>();
      libraries.add(new Library("Library 1"));
      //--------------------------------------------------------------------
     
      //define populate scene-----------------------------------------------
      BorderPane root = new BorderPane();
      root.setStyle("-fx-background-color: #004E38");
      root.getChildren().clear();
      TextField studFile = new TextField();
      studFile.setPromptText("Please enter student file name");
      TextField adminFile = new TextField();
      adminFile.setPromptText("Please enter admin file name");
      TextField bookFile = new TextField();
      bookFile.setPromptText("Please enter book file name");
      Button studBtn = new Button("Submit");
      Button adminBtn = new Button("Submit");
      Button bookBtn = new Button("Submit");
      Button confirm = new Button("Continue");
      VBox prompts = new VBox();
      VBox buttons = new VBox();
      buttons.setPadding(new Insets(0,10,0,10));
      prompts.getChildren().addAll(studFile,adminFile,bookFile);
      buttons.getChildren().addAll(studBtn,adminBtn,bookBtn,confirm);
      root.setLeft(prompts);
      root.setCenter(buttons);
      populate = new Scene(root, 600,400);
      //------------------------------------------------------------------
      
      //Define student menu Scene---------------------------------
      BorderPane studentMenuPane = new BorderPane();
      Text userInfo = new Text(studentUser.toString());
      studentMenuPane.setStyle("-fx-background-color: #004E38");
      Button searchBtn = new Button("Search");
      Button checkedBtn = new Button("View your books");
      Button logOutBtn = new Button("Logout");
      userInfo.setStyle("-fx-fill: white");
      userInfo.setFont(new Font(15));
      HBox hbox = new HBox();
      HBox textH = new HBox();
      textH.setPadding(new Insets(100, 60 , 0 ,60));
      hbox.setPadding(new Insets(0,150,0,150));
      hbox.setSpacing(20);
      hbox.setStyle("-fx-background-color: #C6B880");
      textH.setStyle("-fx-border-color: #FFFFFF");
      hbox.getChildren().add(searchBtn);
      hbox.getChildren().add(checkedBtn);
      hbox.getChildren().add(logOutBtn);
      textH.getChildren().add(userInfo);
      studentMenuPane.setCenter(textH);
      studentMenuPane.setTop(hbox);
      studentMenu = new Scene(studentMenuPane, 600,400); 
     // window.setScene(studentMenu);         
      //---------------------------------------------------------
      
      //Define admin menu Scene----------------------------------
      BorderPane adminMenuPane = new BorderPane();
      Text adminInfo = new Text(adminUser.toString());
      adminMenuPane.setStyle("-fx-background-color: #004E38");
      Button addBookBtn = new Button("Add book");
      Button deleteBookBtn = new Button("Remove book"); 
      Button mngStudentBtn = new Button("Manage students");
      Button logOutBtn2 = new Button("Logout");    
      adminInfo.setStyle("-fx-fill: white");
      adminInfo.setFont(new Font(15));
      HBox adminHbox = new HBox();
      HBox adminText = new HBox();
      adminText.setPadding(new Insets(150, 60 , 0 ,60));
      adminHbox.setPadding(new Insets(0,130,0,130));
      adminHbox.setSpacing(5);
      adminHbox.setStyle("-fx-background-color: #C6B880");
      adminText.setStyle("-fx-border-color: #FFFFFF");
      adminHbox.getChildren().addAll(addBookBtn, deleteBookBtn, mngStudentBtn,logOutBtn2);
      adminText.getChildren().add(adminInfo);
      adminMenuPane.setCenter(adminText);
      adminMenuPane.setTop(adminHbox);
      adminMenu = new Scene(adminMenuPane, 600,400);   
      
      //admin menu event handlers---------------------------------------------------------------------------------
      logOutBtn2.setOnAction((ActionEvent e) ->
      {
         window.setScene(login);
         
      }); 
      
      mngStudentBtn.setOnAction((ActionEvent e)->
      {
         BorderPane deletePane = new BorderPane();
         VBox backButton = new VBox();
         VBox results = new VBox();
         deletePane.setStyle("-fx-background-color: #004E38");
         Button back = new Button(" Back ");
         backButton.getChildren().add(back);
         deletePane.setLeft(backButton);  
         window.setScene(new Scene(deletePane, 600,400));
         results.setStyle("-fx-background-color: #C6B880");
         results.setStyle("-fx-fill:white");
         backButton.setStyle("-fx-border-color: #FFFFFF");
         Text returnPrompt = new Text("Click on a book to remove it");
         HBox returnPromptHBox = new HBox();
         deletePane.setBottom(returnPromptHBox);
         returnPromptHBox.setStyle("-fx-border-color: #FFFFFF");
         returnPrompt.setStyle("-fx-fill : white");
         returnPrompt.setFont(new Font(15)); 
         returnPromptHBox.getChildren().add(returnPrompt);             
         displayMngStudents(students,results,deletePane);
         deletePane.setCenter(results);
         window.show();
            
         back.setOnAction((ActionEvent goBack)  ->{
           window.setScene(adminMenu);
         });  
      });
      
      deleteBookBtn.setOnAction((ActionEvent e) ->
      {
         BorderPane deletePane = new BorderPane();
         VBox backButton = new VBox();
         VBox results = new VBox();
         deletePane.setStyle("-fx-background-color: #004E38");
         Button back = new Button(" Back ");
         backButton.getChildren().add(back);
         deletePane.setLeft(backButton);  
         window.setScene(new Scene(deletePane, 600,400));
         results.setStyle("-fx-background-color: #C6B880");
         results.setStyle("-fx-fill:white");
         backButton.setStyle("-fx-border-color: #FFFFFF");
         Text returnPrompt = new Text("Click on a book to remove it");
         HBox returnPromptHBox = new HBox();
         deletePane.setBottom(returnPromptHBox);
         returnPromptHBox.setStyle("-fx-border-color: #FFFFFF");
         returnPrompt.setStyle("-fx-fill : white");
         returnPrompt.setFont(new Font(15)); 
         returnPromptHBox.getChildren().add(returnPrompt);             
         displayAllBooks(libraries,results);
         deletePane.setCenter(results);
         window.show();
            
         back.setOnAction((ActionEvent goBack)  ->{
           window.setScene(adminMenu);
         });  
           
      });
           
      addBookBtn.setOnAction((ActionEvent e ) ->{
        Button back = new Button("Back");
        BorderPane bookAddPane = new BorderPane();
        bookAddPane.setStyle("-fx-background-color: #004E38");
        TextField bookName = new TextField();
        bookName.setPromptText("Book name");
        TextField bookAuthor = new TextField();
        bookAuthor.setPromptText("Author");
        TextField bookSubject = new TextField();
        bookSubject.setPromptText("Subject");
        TextField bookDate = new TextField();
        bookDate.setPromptText("Date");
        Text error = new Text();
        error.setFont(new Font(15));
        Button confirmBook = new Button("Continue");
        VBox bookPrompts = new VBox();
        VBox bookButtons = new VBox();
        bookButtons.setPadding(new Insets(0,10,0,10));
        bookPrompts.getChildren().addAll(bookName,bookAuthor,bookSubject, bookDate,error);
        bookButtons.getChildren().addAll(confirmBook,back);
        bookAddPane.setLeft(bookPrompts);
        bookAddPane.setCenter(bookButtons);
        window.setScene(new Scene(bookAddPane, 600,400));
        
        
        
        confirmBook.setOnAction((ActionEvent c) ->{
        if(!bookName.getText().equals("") &&!bookDate.getText().equals("") && !bookAuthor.getText().equals("") && !bookSubject.getText().equals(""))
         {
            addBookToLibrary(libraries,bookName,bookAuthor,bookSubject, bookDate);
            error.setText("Book succesfully added");
         }else{
            error.setText("Please fill out all fields");
         }
        });
        
        back.setOnAction((ActionEvent goBack)  ->{
          window.setScene(adminMenu);
        });
                     
      });
      //---------------------------------------------------------------------------------------------------------
      //---------------------------------------------------------
      
      //Button event handlers for populate scene-----------------------------------------------------------------------------------------------------------------------------------
      //if all files are entered, return to main
      confirm.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
              //display login scene if all files have been entered
              if(adminFile.getText().equals("File succesfully read") && studFile.getText().equals("File succesfully read")&& bookFile.getText().equals("File succesfully read") )
                  window.setScene(login); 
         }
         });
               
      //populate admin list
      adminBtn.setOnAction(new EventHandler<ActionEvent>()
      {         
            @Override
            public void handle(ActionEvent event) {
             try{
               File admin = new File(adminFile.getText());
               Scanner input = new Scanner (admin);
               adminFile.setText("File succesfully read");
               adminFile.setDisable(true);
               adminBtn.setDisable(true);
               populateAdmins(input, admins);              
            }catch (FileNotFoundException e){
               adminFile.clear();
               adminFile.setPromptText("File not found");
            }  
            }
        });  
        
       //populate student list
       studBtn.setOnAction(new EventHandler<ActionEvent>()
       {   
            @Override
            public void handle(ActionEvent event) {
             try{
               File student = new File(studFile.getText());
               Scanner input = new Scanner (student);
               studFile.setText("File succesfully read");
               studFile.setDisable(true);
               studBtn.setDisable(true);
               populateStudents(input,students);
             }catch (FileNotFoundException e){
               studFile.clear();
               studFile.setPromptText("File not found");
             }                
            }
        });   
      
       //populater book list within the library
       bookBtn.setOnAction(new EventHandler<ActionEvent>()
       {          
            @Override
            public void handle(ActionEvent event) {
             try{
               File book = new File(bookFile.getText());
               Scanner input = new Scanner (book);
               bookFile.setText("File succesfully read");
               bookFile.setDisable(true);
               bookBtn.setDisable(true);
               populateLibrary(input,libraries);

            }catch (FileNotFoundException e){
               bookFile.clear();
               bookFile.setPromptText("File not found");
            }                 
            }
        });              
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------
        
       //define student menu button event handlers---------------------------
       logOutBtn.setOnAction((ActionEvent e) ->
       {
         window.setScene(login);
         
       });
       
       checkedBtn.setOnAction((ActionEvent getBooks) ->{
            ArrayList<Book> booksCheckedOut = studentUser.getBooksCheckedOut();
            BorderPane returnPane = new BorderPane();
            VBox backButton = new VBox();
            VBox results = new VBox();
            returnPane.setStyle("-fx-background-color: #004E38");
            Button back = new Button(" Back ");
            backButton.getChildren().add(back);
            returnPane.setLeft(backButton);  
            window.setScene(new Scene(returnPane, 600,400));
            results.setStyle("-fx-background-color: #C6B880");
            results.setStyle("-fx-fill:white");
            backButton.setStyle("-fx-border-color: #FFFFFF");
            Text returnPrompt = new Text("Checked out books (" + studentUser.getPerson().getID() + ")");
            HBox returnPromptHBox = new HBox();
            returnPane.setBottom(returnPromptHBox);
            returnPromptHBox.setStyle("-fx-border-color: #FFFFFF");
            returnPrompt.setStyle("-fx-fill : white");
            returnPrompt.setFont(new Font(15)); 
            returnPromptHBox.getChildren().add(returnPrompt);           
            
            back.setOnAction((ActionEvent goBack)  ->{
                  window.setScene(studentMenu);
            });            
            
            
            for(int i = 0; i < booksCheckedOut.size(); i++)
            {
               Text t = new Text(booksCheckedOut.get(i).toString());
               results.getChildren().add(t);
               t.setStyle("-fx-fill:white");
               t.setFont(new Font(15));
            }
           returnPane.setCenter(results);
           window.show();
           
           
        }); 
        
        //Search button action event------------------------------------------------------------           
        searchBtn.setOnAction((ActionEvent event) -> {
            BorderPane  searchPane = new BorderPane();
            searchPane.setStyle("-fx-background-color: #004E38");
            root.getChildren().clear();
            Button subGenre = new Button("Search");
            Button subAuthor = new Button("Search");
            Button subKeyword = new Button("Search");
            Button back = new Button(" Back ");
            TextField genre = new TextField();
            TextField author = new TextField();
            TextField keyWord = new TextField();
            genre.setPromptText("Search by genre");
            author.setPromptText("Search by author");
            keyWord.setPromptText("Search by key word");
            Text t = new Text("Search for a book to checkout (" + studentUser.getPerson().getID() + ")");
            VBox v1 = new VBox();
            VBox v2 = new VBox();
            HBox text = new HBox();
            VBox results = new VBox();
            v2.setStyle("-fx-background-color: #C6B880");
            v1.setStyle("-fx-border-color: #FFFFFF");
            t.setStyle("-fx-fill: white");
            t.setFont(new Font(15));
            text.setPadding(new Insets(100, 60 , 0 ,60));
            v2.getChildren().setAll(genre,author,keyWord);
            v1.getChildren().setAll(subGenre,subAuthor,subKeyword,back);
            text.getChildren().add(t);
            searchPane.setLeft(v1);
            searchPane.setCenter(v2);
            searchPane.setBottom(t);
            window.setScene(new Scene(searchPane, 600,400));      
            
                           
            back.setOnAction((ActionEvent goBack)  ->{
               window.setScene(studentMenu);
            });
                 
            subGenre.setOnAction((ActionEvent genreSearch) ->{
               results.getChildren().clear();
               ArrayList<Book> searchedBooks = libraries.get(0).searchGenre(genre.getText());
               searchGenre(searchedBooks,results,studentUser); 
               searchPane.setRight(results);
               window.show();
                  
            }); 
            //------------------------------------------------------------------------------------- 
        });   
        //---------------------------------------------------------------------
        
        //define login/ define login scene-----------------------
        BorderPane loginPane = new BorderPane();
        loginPane.setStyle("-fx-background-color: #004E38");
        Button adminLogin = new Button("Administrator");
        Button studentLogin = new Button("Student");
        Button save = new Button("Save");
        TextField enterID = new TextField();
        enterID.setPromptText("Enter ID");
        HBox selection = new HBox();
        HBox saveBox = new HBox();
        saveBox.setPadding(new Insets(0,260,0,260));
        saveBox.getChildren().add(save);
        selection.setStyle("-fx-border-color: #FFFFFF");
        VBox log = new VBox();
        loginPane.getChildren().clear();
        log.setPadding(new Insets(100,15,100,15));
        selection.setPadding(new Insets(0,200,0,200));
        selection.setSpacing(40);
        selection.getChildren().add(adminLogin);
        selection.getChildren().add(studentLogin);
        log.getChildren().add(enterID);
        selection.setStyle("-fx-background-color: #C6B880"); 
        loginPane.setTop(selection);
        loginPane.setCenter(log);
        loginPane.setBottom(saveBox);
        login = new Scene(loginPane, 600,400);
        //------------------------------------------------------
        
        //action events for login scene-------------------------
        save.setOnAction((ActionEvent e ) ->
        {
         try{
          save(students, libraries, admins);
          save.setText("Saved");
          save.setDisable(true);
         }catch (IOException io)
         {
            System.err.println(io.getMessage());
         }
        });
        
        adminLogin.setOnAction((ActionEvent event) -> {
            //display admin menu on Admin login
               try{
                  adminUser = admins.search(enterID.getText()); 
                  adminInfo.setText(adminUser.toString());
                  window.setScene(adminMenu);
               }catch (DataStructureException d){
                  enterID.clear();
                  enterID.setPromptText(d.getMessage()); 
             }
         });           
        
        studentLogin.setOnAction((ActionEvent event) -> {
            //display student menu on student login
              try{
                 studentUser = students.search(enterID.getText());
                 userInfo.setText(studentUser.getPerson().toString());
                 window.setScene(studentMenu);
          
              }catch (DataStructureException d){
                  enterID.clear();
                  enterID.setPromptText(d.getMessage());       
              }        
        });        
        //-------------------------------------------------------
        
        //initialize window with populate scene unless dat files are detected 
        if(!new File("students.dat").isFile() && !new File("admins.dat").isFile() && !new File("books.dat").isFile())
        {   
          window.setScene(populate);
        }else{     
          try{
          readFile(admins,students,libraries);
          window.setScene(login);
          }catch (IOException i)
          {
            System.err.println("no");
          }
        }
        window.show();
        window.setTitle("Library");
   } 
   
   
   
//Methods for searching/adding---------------------------------------------------------------------------------
   //Add a book to the current users cart
   public static void addBookToCart(Student studentUser, String bookDetails, ArrayList<Book> searchedBooks, Button b)
   {
      ArrayList<Book> booksCheckedOut = studentUser.getBooksCheckedOut();
      for(int i = 0; i < searchedBooks.size(); i++)
      {
         if(bookDetails.equals(searchedBooks.get(i).toString()))
         {
            for(int j = 0; j < booksCheckedOut.size(); j++)
             {
               if(booksCheckedOut.get(j).equals(searchedBooks.get(i)))
               {
                  b.setText("You already have this item checked out");
                  return;
               }
             }            
            studentUser.addBook(searchedBooks.get(i));
            b.setDisable(true);
            return;
         }
      }
   }  
   
   //Search a genre
   public static void searchGenre(ArrayList<Book> searchedBooks, VBox results, Student studentUser)
   {
       for(int i =0; i< searchedBooks.size(); i++)
       {

            Button b = new Button(searchedBooks.get(i).toString());
            results.getChildren().add(b);
            b.setOnAction((ActionEvent book) ->{
               if(!studentUser.hasPenalty())
                  addBookToCart(studentUser, b.getText(), searchedBooks, b);
               else
                  b.setText("You cannot check out a book with a penalty");
            });   
       }
   }  
   
   //display all books in a library
   public static void displayAllBooks(LinkedList<Library> libraries, VBox results)
   {
       libraries.get(0).sortBooks();
       ArrayList<Book> books = libraries.get(0).returnBooks();
       for(int i =0; i< books.size(); i++)
       {
            Button b = new Button(books.get(i).toString());
            results.getChildren().add(b);
            b.setOnAction((ActionEvent book) ->{
            libraries.get(0).removeBook(b.getText());
            b.setDisable(true);
            b.setText("Book Removed");
            });   
       }   
   }
   
   public static void displayMngStudents(Students students, VBox results,BorderPane pane)
   {
      students.sortStudents();
      ArrayList<Student> allStudents = students.returnStudents();
      for(int i = 0; i< allStudents.size(); i++)
      {
         Button b = new Button(allStudents.get(i).getPerson().toString());
         results.getChildren().add(b);
         b.setOnAction((ActionEvent e) ->
         {
            Student s = students.searchByString(b.getText()); 
            Button delete = new Button("Delete");
            Button setPenalty = new Button();
            if(s.hasPenalty())
            {
               setPenalty.setText("Remove penalty");
               setPenalty.setDisable(false);
            }
            else
            {
               setPenalty.setText("Set penalty"); 
               setPenalty.setDisable(false);
            }
            VBox buttons = new VBox();
            buttons.getChildren().addAll(delete,setPenalty);
            pane.setRight(buttons);
            buttons.setPadding(new Insets(0,40,0,40));         
            buttons.setStyle("-fx-background-color: #C6B880"); 
            
                       
            //delete selected student 
            delete.setOnAction((ActionEvent del) ->
            {
               students.remove(s);
               b.setDisable(true);
               setPenalty.setDisable(true);

            }); 
            //Set a penalty on student selected
            setPenalty.setOnAction((ActionEvent pen)->
            {
               s.setPenalty();
               setPenalty.setDisable(true);
            });                
         });   
      }
   }
   
   //return a book   
   public static void removeBook(Student studentUser, String bookDetails,ArrayList<Book> booksCheckedOut)
   {
  
   }
   public static void addBookToLibrary(LinkedList<Library> libraries, TextField bookName,TextField bookAuthor,TextField bookSubject,TextField  bookDate)
   {
      String name = bookName.getText();
      String author = bookAuthor.getText();
      String subject = bookSubject.getText();
      String date = bookDate.getText();
      Book b = new Book(name,author,subject,date);
      libraries.get(0).add(b);  
   }
   
   public static void populateAdmins(Scanner input, Administrator admins)
   {
     while (input.hasNextLine())
     {
      String name = input.nextLine();
      String id = input.nextLine();
      String email = input.nextLine();
      String phone = input.nextLine();
      Person p = new Person(name,id,email,phone);
      admins.add(p);
     }
   }  
   
   public static void populateStudents(Scanner input, Students students)
   {
     while (input.hasNextLine())
     {
      String name = input.nextLine();
      String id = input.nextLine();
      String email = input.nextLine();
      String phone = input.nextLine();
      Student s = new Student(new Person(name,id,email,phone));
      students.add(s);
      }
   }
   
   public static void populateLibrary(Scanner input, LinkedList<Library> libraries)
   {
     while (input.hasNextLine())
     {
        String name = input.nextLine();
        String author = input.nextLine();
        String genre = input.nextLine();
        String date = input.nextLine();;
        Book b = new Book(name,author,genre,date);
        libraries.get(0).add(b);
      }            
   }   
   
   public static void save(Students students, LinkedList<Library> libraries, Administrator admins)throws IOException
   {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.dat", true));
      out.writeObject(students);
      
      ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream("admins.dat", true));
      out2.writeObject(admins);
     
      ObjectOutputStream out3 = new ObjectOutputStream(new FileOutputStream("books.dat", true));
      out3.writeObject(libraries);
   }
   
   public static void readFile(Administrator admins, Students students, LinkedList<Library> libraries) throws IOException
   {
     try{
       //read students from dat file
       ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.dat")); 
       students = (Students)in.readObject();
       //read admins from dat file
       ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("admins.dat"));
       admins = (Administrator)in2.readObject();
       //read books from dat file
       ObjectInputStream in3 = new ObjectInputStream(new FileInputStream("books.dat"));
       libraries = (LinkedList<Library>)in3.readObject();                
    }catch(ClassNotFoundException i)
    {
      System.err.println(i.getMessage());
    }
   }
}
//-----------------------------------------------------------------------------------------------------------------