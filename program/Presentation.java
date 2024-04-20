/**
  * Presentation - Presentation layer for users (like us) to interact with database.
  * @author Group 03
  * @Version 1.0
  * @Date Feb-21-2024
  * @Course ISTE-330.01
  * @Semester 2235 (Spring 2024)
*/
public class Presentation
{
   MySQLDatabase msqldb = new MySQLDatabase();
   
   public int loggedUser;
   
   public Presentation()
   {
      promptConnect();
      
      
      program();
      //promptCreateStudentAccount();
      //promptMatch();
      //promptLogin("student");
      promptClose();
      
   }
   
   public void promptConnect()
   {
      System.out.println("Connecting to Database Server");
      // Connecting to database server 
      System.out.println("\n>---------------[ CONNECTING TO DATABASE ] ---------------<\n");
      
      System.out.print("Please enter username (Default: root) -----------> ");
      String username    = GetInput.readLine();
     
      System.out.print("Please enter password (Default: student) --------> ");
      String password   = GetInput.readLine();

      System.out.print("Please enter database (Default: faculty_matcher) ---------> ");
      String database   = GetInput.readLine();
      
      // Assigning Default username.
      if(username.equals(""))
      {
         username = "root";
      }
      
      // Assigning Default password.
      if(password.equals(""))
      {
         password = "student";
      }
      
      // Assigning Default database.
      if(database.equals(""))
      {
         database = "faculty_matcher";
      }
      
      msqldb.connect(username, password, database);
   }
   
   public void promptMatch()
   {
      System.out.println("\n>---------------[ MATCH FACULTY ] ---------------<\n");
            
      System.out.println("Interest Based Matches");
      System.out.println("---------------------------------------------------------------");
      msqldb.matchInterests(loggedUser);
      
      System.out.println("Abstract Based Matches");
      System.out.println("---------------------------------------------------------------");
      msqldb.matchAbstracts(loggedUser);
   }   
   
   public void promptMatchInterestsFaculty()
   {
      System.out.println("\n>---------------[ MATCH FACULTY ] ---------------<\n");
            
      System.out.println("Interest Based Matches");
      System.out.println("---------------------------------------------------------------");
      msqldb.matchInterestsFaculty(loggedUser);
   }   
   
   public void promptLogin(String userType)
   {
      System.out.println("\n>---------------[ LOGIN ] ---------------<\n");
            

      System.out.print("Please enter username ---------> ");
      String username    = GetInput.readLine();
     
      System.out.print("Please enter password ----------> ");
      String password   = GetInput.readLine();
      
      loggedUser = msqldb.login(username, password, userType);
      
      
      if(loggedUser == 0)
      {
         System.out.print("LOGIN FAILED - Exiting Program");
         System.exit(0);
      }
      
      
      System.out.println("LOGGED USER: "+loggedUser);
      
//       // Deleting row from table passenger 
//       int rowsDeleted = msqldb.deletePassenger(2);
//       System.out.println(rowsDeleted + " row(s) deleted from passengers table.\n");
   }   
   
   public void promptCreateStudentAccount()
   {
      System.out.println("\n>---------------[ CREATING STUDENT ACCOUNT ] ---------------<\n");
      
      System.out.print("Please enter userID --------> ");
      int userID = GetInput.readLineInt();
      
      System.out.print("Please enter username ---------> ");
      String username    = GetInput.readLine();
     
      System.out.print("Please enter password ----------> ");
      String password   = GetInput.readLine();
	        
      System.out.print("Please enter first name ---------> ");
      String fname    = GetInput.readLine();
     
      System.out.print("Please enter last name ----------> ");
      String lname   = GetInput.readLine();

      System.out.print("Please enter email -------------> ");
      String email   = GetInput.readLine();
      
      msqldb.getAllMajors();
      System.out.println("\n");
      System.out.print("Please enter majorCode -----------> ");
      int majorCode = GetInput.readLineInt();
      
      // Adding row to table students and accounts
      int rowsAdded = msqldb.createStudentAccount(userID, username, password, fname, lname, email, majorCode);
      //System.out.println(rowsAdded + " row(s) added to student table.\n");
            
//       // Adding row to table passenger 
//       int rowsAdded = msqldb.addPassenger(11, "Ali", "Jafri", "1 Lomb Memorial Drive", "14623");
//       System.out.println(rowsAdded + " row(s) added to passengers table.\n");
   }
   
   public void promptCreateFacultyAccount()
   {
      System.out.println("\n>---------------[ CREATING FACULTY ACCOUNT ] ---------------<\n");
      
      System.out.print("Please enter userID --------> ");
      int userID = GetInput.readLineInt();
      
      System.out.print("Please enter username ---------> ");
      String username    = GetInput.readLine();
     
      System.out.print("Please enter password ----------> ");
      String password   = GetInput.readLine();
	        
      System.out.print("Please enter first name ---------> ");
      String fname    = GetInput.readLine();
     
      System.out.print("Please enter last name ----------> ");
      String lname   = GetInput.readLine();

      System.out.print("Please enter email -------------> ");
      String email   = GetInput.readLine();
      
      System.out.print("Please enter institution -------------> ");
      String institution   = GetInput.readLine();

      System.out.print("Please enter office # -----------> ");
      String office = GetInput.readLine();
      System.out.print("Please enter department -----------> ");
      String department = GetInput.readLine();
      
      // Adding row to table faculty and accounts
      int rowsAdded = msqldb.createFacultyAccount(userID, username, password, fname, lname, email, institution, office, department);
 
      //System.out.println(rowsAdded + " row(s) added to faculty table.\n");
   }
  
   public void program()
   {
      System.out.println("What type of user are you?");
      System.out.println("[1] Student");
      System.out.println("[2] Faculty");
      int typeOption = GetInput.readLineInt();
      
      switch(typeOption) 
      {
         case 1:
            System.out.println("\n");
            studentPrompt();
            break;
         case 2:
            System.out.println("\n");
            facultyPrompt();
            break;
        default:
            System.out.println("\n");
            System.out.println("Invalid Input Selected");
      }
   }
   
   public void facultyPrompt()
   {
      boolean exit = false;
      while(!exit)
      {
         System.out.println("Please choose from the following options: ");
         System.out.println("[1] Login");
         System.out.println("[2] Create Account");
         System.out.println("[0] Exit");
         
         int option = GetInput.readLineInt();
         
         switch(option) 
         {
            case 1:
               System.out.println("\n");
               promptLogin("faculty");// exits program if login fails.
               facultyOptionsPrompt();
               
               break;
            case 2:
               System.out.println("\n");
               promptCreateFacultyAccount();
               break;
            case 0:
               System.out.println("Exiting...");
               exit = true;
               break;
           default:
               System.out.println("\n");
               System.out.println("Invalid Input Selected");
               
               break;
         }
      
      }
   
   }
   
   public void studentPrompt()
   {
      boolean exit = false;
      while(!exit)
      {
         System.out.println("Please choose from the following options: ");
         System.out.println("[1] Login");
         System.out.println("[2] Create Account");
         System.out.println("[0] Exit");
         
         int option = GetInput.readLineInt();
         
         switch(option) 
         {
            case 1:
               System.out.println("\n");
               promptLogin("student");// exits program if login fails.
               studentOptionsPrompt();
               
               break;
            case 2:
               System.out.println("\n");
               promptCreateStudentAccount();
               break;
            case 0:
               System.out.println("Exiting...");
               exit = true;
               break;
           default:
               System.out.println("\n");
               System.out.println("Invalid Input Selected");
               
               break;
         }
      
      }
         
   }
   
   public void facultyOptionsPrompt()
   {
      boolean exit = false;
      while(!exit)
      {
         System.out.println("\nPlease choose from the following options: ");
         System.out.println("[1] Find Student Match");
         System.out.println("[2] Add Abstract");
         System.out.println("[3] View My Abstracts");
         System.out.println("[4] Create Abstract");
         System.out.println("[5] Add Interest");
         System.out.println("[6] View My Intrests");
         System.out.println("[7] Create Interest");
         System.out.println("[0] Logout & Go to Main Menu");
         System.out.print("Enter Option ---------> ");
         int option = GetInput.readLineInt();
         
         switch(option) 
         {
            case 1:
               System.out.println("\nFind Student Match\n");     
               promptMatchInterestsFaculty();  
               break;
            case 2:
               System.out.println("\nAdd Abstract\n");     
               promptAddAbstract();  
               break;
            case 3:
               System.out.println("\nView My Abstracts\n");      
               msqldb.getUserAbstracts(loggedUser); 
               break;
            case 4:
               System.out.println("\nCreate Abstract\n");   
               promptCreateAbstract();    
               break;
            case 5:
               System.out.println("\nAdd Interest\n");
               promptAddInterest();
               break;
            case 6:
               System.out.println("\nView My Intrests\n");
               msqldb.getUserIntrests(loggedUser);
               break;
            case 7:
               System.out.println("\nCreate a new Intrest\n");
               promptCreateInterest();
               break;
            case 0:
               System.out.println("Logging Out...\n");
               loggedUser = 0;
               exit = true;
               break;
           default:
               System.out.println("\n");
               System.out.println("Invalid Input Selected");
               
               break;
         }
      }
   }
   
   public void studentOptionsPrompt()
   {
      boolean exit = false;
      while(!exit)
      {
         System.out.println("\nPlease choose from the following options: ");
         System.out.println("[1] Find Faculty Match");
         System.out.println("[2] Add Interest");
         System.out.println("[3] View My Intrests");
         System.out.println("[4] Create Interest");
         System.out.println("[0] Logout & Go to Main Menu");
         System.out.print("Enter Option ---------> ");
         int option = GetInput.readLineInt();
         
         switch(option) 
         {
            case 1:
               System.out.println("\nFinding Faculty matches... \n\n");
               promptMatch();           
               break;
            case 2:
               System.out.println("\nAdd Interest:\n");
               promptAddInterest();
               break;
            case 3:
               System.out.println("\nView My Intrests\n");
               msqldb.getUserIntrests(loggedUser);
               break;
            case 4:
               System.out.println("\nCreate a new Intrest\n");
               promptCreateInterest();
               break;
            case 0:
               System.out.println("Logging Out...\n");
               loggedUser = 0;
               exit = true;
               break;
           default:
               System.out.println("\n");
               System.out.println("Invalid Input Selected");
               
               break;
         }
      }
   }
   
   public void promptCreateInterest()
   {  
      msqldb.getAllIntrests();
      System.out.print("\n");
      System.out.print("Add a new Interest that doesn't exist, make sure it is given a unique ID");
      System.out.print("Please enter InterestID ---------> ");
      int interestID = GetInput.readLineInt();
      System.out.print("Please enter Domain of Interest ---------> ");
      String domain = GetInput.readLine();
      System.out.print("Please enter Interest ---------> ");
      String interest = GetInput.readLine();
      
      msqldb.createInterest(interestID, domain, interest);
   }
   
   public void promptCreateAbstract()
   {  
      msqldb.getAllAbstracts();
      System.out.print("\n");
      System.out.print("Add a new Abstract that doesn't exist, make sure it is given a unique ID");
      System.out.print("Please enter AbstractID ---------> ");
      int abstractID = GetInput.readLineInt();
      System.out.print("Please enter Title of Abstract ---------> ");
      String title = GetInput.readLine();
      System.out.print("Please enter Abstract ---------> ");
      String _abstract = GetInput.readLine();
      
      msqldb.createAbstract(abstractID, title, _abstract);
   }
   
   public void promptAddInterest()
   {
      msqldb.getAllIntrests();
      System.out.print("\n");
      System.out.print("Please enter InterestID ---------> ");
      int interestID = GetInput.readLineInt();
           
      msqldb.addInterest(loggedUser, interestID);

   }
   
   public void promptAddAbstract()
   {
      msqldb.getAllAbstracts();
      System.out.print("\n");
      System.out.print("Please enter AbstractID ---------> ");
      int abstractID = GetInput.readLineInt();
           
      msqldb.addAbstract(loggedUser, abstractID);

   }
   
   public void promptClose()
   {
      System.out.println("Closing Connection");
      //Closing connection
      msqldb.close();
   }

   public static void main(String [] args)
   {
      new Presentation();
   }
}