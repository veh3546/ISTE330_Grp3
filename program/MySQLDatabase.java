import java.sql.*;

/**
  * MySQLDatabase - Connects to database and manages flow to and from database.
  * @author Group 03
  * @Version 1.0
  * @Date Feb-21-2024
  * @Course ISTE-330.01
  * @Semester 2235 (Spring 2024)
*/
public class MySQLDatabase
{
   private Connection conn;
   //private ResultSet rs;
   private Statement stmt;
   //private String sql;
   //private int col;

   final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
   
   final String USER = "root";
   final String PASSWORD = "student"; // Password set as constant  
   final String DATABASE = "faculty_matcher";
   
   /**
     * connect - Establishes connection to database server.
     
     * @param user            - user of database server
     * @param password        - password for database server user
     * @param database        - name of database to use.

     * @return boolean indicating if connection was established successfully. 
   */
   public boolean connect(String user, String password, String database)
   {    
      conn = null;
        
      String url = "jdbc:mysql://localhost/" + database;
      //url = url + "?serverTimezone=UTC"; //added only required for Mac Users
      
      try
      {
         Class.forName(DEFAULT_DRIVER);
         System.out.println("CLASSPATH is set correctly!");
         
         conn = DriverManager.getConnection(url, user, password);
         System.out.println("\nCreated Connection!\n");
      }
      catch(ClassNotFoundException cnfe)
      {
         System.out.println("ERROR, CAN NOT CONNECT!!");
         //System.out.println("Class");
         //System.out.println("ERROR MESSAGE-> "+cnfe);
         System.exit(0);
      }
      catch(SQLException sqle)
      {
         System.out.println("ERROR, UNABLE TO CONNECT!!");
         System.out.println("ERROR SQLExcepiton in connect()");
         //System.out.println("ERROR MESSAGE -> "+sqle);
         
         System.exit(0);
      }
      catch(Exception e)
      {
         System.out.println("ERROR, UNABLE TO CONNECT!!");
         System.out.println("ERROR Excepiton in connect()");
         //System.out.println("ERROR MESSAGE -> "+e);
         
         System.exit(0);
      }
      
      
      // conn is null if connection fails.
      if(conn != null)
      {
         System.out.println("Connected to Database Successfully\n");
      }
      
      return (conn != null);
   }
   
   /**
     * close - Clooses connection to database.
     
     * @return boolean indicating if connection was closed successfully. 
   */
   public boolean close()
   {
   
      // if connection is open then attempt to close.
      if(conn != null)
      {
         try 
         {
            //rs.close();
            //stmt.close();
            conn.close();
            // return true if successfully closed.
            return true;
         }
         catch(SQLException sqle)
         {
            System.out.println("ERROR, UNABKE TO CLOSE CONNECTION");
            System.out.println("ERROR IN METHOD close()");
            //System.out.println("ERROR MESSAGE -> "+sqle);
            // return false if there were issues closing the connection.
            return false;
         }
         catch(Exception e)
         {
            System.out.println("ERROR, UNABKE TO CLOSE CONNECTION");
            System.out.println("ERROR Excepiton in close()");
            //System.out.println("ERROR MESSAGE -> "+e);
            
            System.exit(0);
         }
      }
      else
      {
         // return true (because the connection was closed to begin with)
         return true;
      }
      return false;
   }
   /**
     * createFacultyAccount - Creates account for Faculty.
     
     * @param userID        - Primary key
     * @param username      - username
     * @param password      - password
     * @param fname         - first name
     * @param lname         - last name
     * @param email         - email
     * @param office        - office
     * @param department    - department

     * @return the number of rows affected 
   */

  public int createFacultyAccount(int userID, String username, String password, String fname, String lname, String email, String institution, String office, String department)
  {
     int rows = 0;

     //System.out.println("-----INSERT started-----");
     try
     {
        String sql2 = "INSERT INTO account VALUES (?,?,?,?)";

        PreparedStatement stmt2 = conn.prepareStatement(sql2);
        // bind values into the parameters
        stmt2.setInt(1, userID);
        stmt2.setString(2, username);
        stmt2.setString(3, password);
        stmt2.setString(4, "faculty");
        // prepared statement
        String sql = "INSERT INTO faculty VALUES (?,?,?,?,?,?,?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        // bind values into the parameters
        stmt.setInt(1, userID);
        stmt.setString(2, fname);
        stmt.setString(3, lname);
        stmt.setString(4, email);
        stmt.setString(5, institution);
        stmt.setString(6, office);
        stmt.setString(7, department);
        
        //System.out.println("Command to be executed: " + stmt2);
        stmt2.executeUpdate();
        //System.out.println("Command to be executed: " + stmt);
        stmt.executeUpdate();
        //System.out.println("-----INSERT finished-----");
     }
     catch(SQLException sqle)
     {
        System.out.println("SQL ERROR");
        System.out.println(" UNABLE TO CREATE ACCOUNT");
        System.out.println("Possible Causes: ");
        System.out.println(" 1 - Information was entered incorrectly.");
        System.out.println(" 2 - The ID you assigned is already in use.");
        //System.out.println("ERROR MESSAGE IS -> "+sqle);
        //sqle.printStackTrace();
        return(0);
     }
     catch(Exception e)
     {
        System.out.println("SQL ERROR");
        System.out.println(" UNABLE TO CREATE ACCOUNT");
        System.out.println("Possible Causes: ");
        System.out.println(" 1 - Information was entered incorrectly.");
        System.out.println(" 2 - The ID you assigned is already in use.");
        //System.out.println("Error occured in createFacultyAccount method");
        //System.out.println("ERROR MESSAGE is -> "+e);
        //e.printStackTrace();
        return(0);

     }
     System.out.println("Account Created Successfully!");
     return (rows);
  }

   
   /**
     * createStudentAccount - Creates account for student.
     
     * @param userID        - Primary key
     * @param username      - username
     * @param password      - password
     * @param fname         - first name
     * @param lname         - last name
     * @param email         - email
     * @param majorCode     - majorCode

     * @return the number of rows affected 
   */
   public int createStudentAccount(int userID, String username, String password, String fname, String lname, String email, int majorCode)
   {
      int rows = 0;

      //System.out.println("-----INSERT started-----");
      try
      {
         String sql2 = "INSERT INTO account VALUES (?,?,?,?)";

         PreparedStatement stmt2 = conn.prepareStatement(sql2);
         // bind values into the parameters
         stmt2.setInt(1, userID);
         stmt2.setString(2, username);
         stmt2.setString(3, password);
         stmt2.setString(4, "student");
         // prepared statement
         String sql = "INSERT INTO students VALUES (?,?,?,?,?)";

         PreparedStatement stmt = conn.prepareStatement(sql);
         // bind values into the parameters
         stmt.setInt(1, userID);
         stmt.setString(2, fname);
         stmt.setString(3, lname);
         stmt.setString(4, email);
         stmt.setInt(5, majorCode);
         
         //System.out.println("Command to be executed: " + stmt2);
         stmt2.executeUpdate();
         //System.out.println("Command to be executed: " + stmt);
         stmt.executeUpdate();
         //System.out.println("-----INSERT finished-----");
      }
      catch(SQLException sqle)
      {
        //System.out.println("SQL ERROR");
        System.out.println(" UNABLE TO CREATE ACCOUNT");
        System.out.println("Possible Causes: ");
        System.out.println(" 1 - Information was entered incorrectly.");
        System.out.println(" 2 - The ID you assigned is already in use.");
        //System.out.println("Error occured in createStudentAccount method");
         return(0);
      }
      catch(Exception e)
      {
         //System.out.println("SQL ERROR");
         System.out.println(" UNABLE TO CREATE ACCOUNT");
         System.out.println("Possible Causes: ");
         System.out.println(" 1 - Information was entered incorrectly.");
         System.out.println(" 2 - The ID you assigned is already in use.");
         //System.out.println("Error occured in createStudentAccount method");

         return(0);
      }
      System.out.println("Account Created Successfully!");
      return (rows);
   }
    
   /**
     * addInterest - Adds interest to a user's profile.
     *
     * @param userID    - ID of the user
     * @param interestID - ID of the interest
     * @return the number of rows affected
   */
   public int addInterest(int userID, int interestID) 
   {
       int rows = 0;
   
       //System.out.println("-----INSERT started-----");
       try 
       {
           String sql = "INSERT INTO user_interest_associations (userID, interestID) VALUES (?, ?)";
   
           PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, userID);
           pstmt.setInt(2, interestID);
   
           //System.out.println("Command to be executed: " + pstmt);
           rows = pstmt.executeUpdate();
           //System.out.println("-----INSERT finished-----");
       } 
       catch (SQLException sqle) 
       {
            System.out.println("UNABLE TO ADD INTEREST");
            System.out.println("Possible Causes: ");
            System.out.println(" 1 - Information was entered incorrectly.");
            System.out.println(" 2 - The interest ID you entered does not exist");
            System.out.println(" 3 - You already have this interest in your profile");
           //sqle.printStackTrace();
           return 0;
       } 
       catch (Exception e) 
       {
            System.out.println("UNABLE TO ADD INTEREST");
            System.out.println("Possible Causes: ");
            System.out.println(" 1 - Information was entered incorrectly.");
            System.out.println(" 2 - The interest ID you entered does not exist");
            System.out.println(" 3 - You already have this interest in your profile");
           //e.printStackTrace();
           return 0;
       }
       return rows;
   }
   
   /**
     * createInterest - Creates new interest
     * @param interestID - ID of the interest
     * @param domian - Domain of the interest
     * @param interest - The interest
     * @return the number of rows affected
   */
   public int createInterest(int interestID, String domain, String interest) 
   {
       int rows = 0;
   
       //System.out.println("-----INSERT started-----");
       try 
       {
           String sql = "INSERT INTO interests (interestID, domain, interest) VALUES (?, ?, ?)";
   
           PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, interestID);
           pstmt.setString(2, domain);
           pstmt.setString(3, interest);
   
           //System.out.println("Command to be executed: " + pstmt);
           rows = pstmt.executeUpdate();
           //System.out.println("-----INSERT finished-----");
       } 
       catch (SQLException sqle) 
       {
            System.out.println("UNABLE TO CREATE INTEREST");
            System.out.println("Possible Causes: ");
            System.out.println(" 1 - Information was entered incorrectly.");
            System.out.println(" 2 - The ID you assigned is already in use.");
           //sqle.printStackTrace();
           return 0;
       } 
       catch (Exception e) 
       {
            System.out.println("UNABLE TO CREATE INTEREST");
            System.out.println("Possible Causes: ");
            System.out.println(" 1 - Information was entered incorrectly.");
            System.out.println(" 2 - The ID you assigned is already in use.");
           //e.printStackTrace();
           return 0;
       }
       return rows;
   }
   
   /**
     * addAbstract - Adds abstract to a user's profile.
     *
     * @param userID    - ID of the user
     * @param abstractID - ID of the abstract
     * @return the number of rows affected
   */
   public int addAbstract(int userID, int abstractID) 
   {
       int rows = 0;
   
       //System.out.println("-----INSERT started-----");
       try 
       {
           String sql = "INSERT INTO faculty_abstract_associations (facultyID, abstractID) VALUES (?, ?)";
   
           PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, userID);
           pstmt.setInt(2, abstractID);
   
           //System.out.println("Command to be executed: " + pstmt);
           rows = pstmt.executeUpdate();
           //System.out.println("-----INSERT finished-----");
       } 
       catch (SQLException sqle) 
       {
            System.out.println("UNABLE TO ADD ABSTRACT");
            System.out.println("Possible Causes: ");
            System.out.println(" 1 - Information was entered incorrectly.");
            System.out.println(" 2 - The abstract ID you entered does not exist");
            System.out.println(" 3 - You already have this abstract in your profile");
           //sqle.printStackTrace();
           return 0;
       } 
       catch (Exception e) 
       {
            System.out.println("UNABLE TO ADD ABSTRACT");
            System.out.println("Possible Causes: ");
            System.out.println(" 1 - Information was entered incorrectly.");
            System.out.println(" 2 - The abstract ID you entered does not exist");
            System.out.println(" 3 - You already have this abstract in your profile");
           //e.printStackTrace();
           return 0;
       }
       return rows;
   }

   /**
     * createAbstract - Adds abstract to database.
     
     * @param abstractID   - ID of abstract
     * @param title        - Title of abstract
     * @param abstractTxt  - abstract text
     * @return the number of rows affected 
   */
   public int createAbstract(int abstractID, String title, String abstractTxt)
   {
      int rows = 0;

      //System.out.println("-----INSERT started-----");
      try
      {
         String sql2 = "INSERT INTO abstract VALUES (?,?,?)";

         PreparedStatement stmt2 = conn.prepareStatement(sql2);
         // bind values into the parameters
         stmt2.setInt(1, abstractID);
         stmt2.setString(2, title);
         stmt2.setString(3, abstractTxt);
         
         //System.out.println("Command to be executed: " + stmt2);
         rows = stmt2.executeUpdate();
         //System.out.println("-----INSERT finished-----");
      }
      catch(SQLException sqle)
      {

         System.out.println("UNABLE TO CREATE ABSTRACT");
         System.out.println("Possible Causes: ");
         System.out.println(" 1 - Information was entered incorrectly.");
         System.out.println(" 2 - The ID you assigned is already in use.");
         //sqle.printStackTrace();
         return(0);
      }
      catch(Exception e)
      {
         System.out.println("Error occured in addAbstract method");
         System.out.println("ERROR MESSAGE is -> "+e);
         //e.printStackTrace();
         return(0);
      }
      return (rows);
   }
   
   /**
     * login - logs user in.
     
     * @param username   - user's username
     * @param password   - user's password
     * @param userType   - user's type (student or faculty)
     * @return the number of rows affected
   */
   public int login(String username, String password, String userType)
   {
      int id = 0;
      //System.out.println("-----LOGIN started-----");
      try
      {
         ResultSet rs;
         String sql = "SELECT userID FROM account WHERE username = ? AND password = ? AND userType = ?";
         PreparedStatement stmt = conn.prepareStatement(sql);
         stmt.setString(1, username);
         stmt.setString(2, password);
         stmt.setString(3, userType);
         rs = stmt.executeQuery();
         
         rs.next();
         id = rs.getInt(1);

         //System.out.println("-----LOGIN finished-----");
      }
      catch(SQLException sqle)
      {
         //System.out.println("SQL ERROR");
         System.out.println("LOGIN FAILED!!!!");
         System.out.println("Invalid Username or Password");
         //System.out.println("ERROR MESSAGE IS -> "+sqle);
         //sqle.printStackTrace();
         return id;
      }
      catch(Exception e)
      {
         System.out.println("LOGIN FAILED!!!!");
         System.out.println("Invalid Username or Password");
         //e.printStackTrace();
         return id;
      }
      System.out.println("LOGIN SUCCESSFUL!!!!");
      
      return id;
   }
   
   /**
     * getAllIntrests - Gets all interests
     *
   */
   
   public void getAllIntrests()
   {
      //System.out.println("-----GETTING ALL INTRESTS-----");
      try
      {
         ResultSet rs;
         String sql = "SELECT interestID, interest FROM interests";
         PreparedStatement stmt = conn.prepareStatement(sql);
         rs = stmt.executeQuery();
         System.out.println("INTEREST LIST:");

         while(rs.next())
         {
            int iid = rs.getInt(1);
            String intrest = rs.getString(2);
            System.out.println(iid+" --> "+intrest);
            
         }

      }
      catch(SQLException sqle)
      {
         System.out.println("SQL ERROR");
         System.out.println("ERROR MESSAGE IS -> "+sqle);
         //sqle.printStackTrace();
      }
      catch(Exception e)
      {
         System.out.println("Error occured in getAllIntrests method");
         System.out.println("ERROR MESSAGE is -> "+e);
         //e.printStackTrace();
      }
   }
   
   /**
     * getAllAbstracts - Gets all abstracts in the system
     *
   */
   
   public void getAllAbstracts()
   {
      //System.out.println("-----GETTING ALL ABSTRACTS-----");
      try
      {
         ResultSet rs;
         String sql = "SELECT abstractID, title, abstract FROM abstract";
         PreparedStatement stmt = conn.prepareStatement(sql);
         rs = stmt.executeQuery();
         System.out.println("INTEREST LIST:");

         while(rs.next())
         {
            int aid = rs.getInt(1);
            String title = rs.getString(2);
            String _abstract = rs.getString(3);
            System.out.println("\n"+aid+"\n"+title+"\n\n"+_abstract);
            
         }

      }
      catch(SQLException sqle)
      {
         System.out.println("SQL ERROR");
        //System.out.println("ERROR MESSAGE IS -> "+sqle);
        //sqle.printStackTrace();
      }
      catch(Exception e)
      {
         System.out.println("Error occured in getAllAbstracts method");
         //System.out.println("ERROR MESSAGE is -> "+e);
         //e.printStackTrace();
      }
   }
   
   /**
     * getAllMajors - Gets all majors in the system.
   */
   
   public void getAllMajors()
   {
      try
      {
         ResultSet rs;
         String sql = "SELECT majorCode, major, majorLevel FROM majors";
         PreparedStatement stmt = conn.prepareStatement(sql);
         rs = stmt.executeQuery();
         System.out.println("\nMAJOR LIST:");

         while(rs.next())
         {
            int mid = rs.getInt(1);
            String major = rs.getString(2);
            String majorLevel = rs.getString(2);
            System.out.println(mid+" --> "+major+" ("+majorLevel+")");
            
         }

      }
      catch(SQLException sqle)
      {
         System.out.println("SQL ERROR");
        //System.out.println("ERROR MESSAGE IS -> "+sqle);
        //sqle.printStackTrace();
      }
      catch(Exception e)
      {
         System.out.println("Error occured in getAllMajors method");
         //System.out.println("ERROR MESSAGE is -> "+e);
         //e.printStackTrace();
      }
   }


   /**
     * getUserIntrests - Gets interests based on userID
     *
     * @param userID            - userID of user to get interests of.
   */
   public void getUserIntrests(int userID)
   {
      System.out.println("-----GETTING ALL INTRESTS-----");
      try
      {
         ResultSet rs;
         String sql = "SELECT interestID, interest FROM interests JOIN user_interest_associations USING(interestID) WHERE userID = ?";
         PreparedStatement stmt = conn.prepareStatement(sql);
         stmt.setInt(1, userID);
         rs = stmt.executeQuery();
         System.out.println("YOUR INTERESTS:");

         while(rs.next())
         {
            
            int iid = rs.getInt(1);
            String intrest = rs.getString(2);
            System.out.println(iid+" --> "+intrest);
            
         }

      }
      catch(SQLException sqle)
      {
         System.out.println("SQL ERROR");
         //System.out.println("ERROR MESSAGE IS -> "+sqle);
         //sqle.printStackTrace();
      }
      catch(Exception e)
      {
         System.out.println("LOGIN FAILED!!!!");
         System.out.println("Error occured in getUserAbstracts method");
         //System.out.println("ERROR MESSAGE is -> "+e);
         //e.printStackTrace();
      }
   }
   
   
   /**
     * getUserAbstracts - Gets faculty abstracts based on facultyID (userID)
     *
     * @param userID            - userID of user to get abstracts of.
   */
   public void getUserAbstracts(int userID)
   {
      System.out.println("-----GETTING ALL ABSTRACTS-----");
      try
      {
         ResultSet rs;
         String sql = "SELECT abstractID, title ,abstract FROM abstract JOIN faculty_abstract_associations USING(abstractID) WHERE facultyID = ?";
         PreparedStatement stmt = conn.prepareStatement(sql);
         stmt.setInt(1, userID);
         rs = stmt.executeQuery();
         System.out.println("YOUR ABSTRACTS:");

         while(rs.next())
         {
            
            int aid = rs.getInt(1);
            String title = rs.getString(2);
            String _abstract = rs.getString(3);
            System.out.println("\n"+aid+"\n"+title+"\n\n"+_abstract);
            
         }

      }
      catch(SQLException sqle)
      {
         System.out.println("OPERATION FAILED");
         System.out.println("Unable to get Abstracts");
         System.out.println("SQL ERROR");
         //System.out.println("ERROR MESSAGE IS -> "+sqle);
         //sqle.printStackTrace();
      }
      catch(Exception e)
      {
         System.out.println("OPERATION FAILED");
         System.out.println("Unable to get Abstracts");
         //System.out.println("Error occured in getUserAbstracts method");
         //System.out.println("ERROR MESSAGE is -> "+e);
         //e.printStackTrace();
      }
   }


   /**
     * getUserIntrests - Matches faculty with students based on userID (of student)
     *
     * @param userID - userID of student to match faculty for.
   */
   public void matchInterests(int userID)
   {
      try
      {
         ResultSet rs;
         //System.out.println(userID);
         String sql = "{CALL match_faculty_interest(?)}";
      
         CallableStatement stmt = conn.prepareCall(sql);
         stmt.setInt(1, userID);

         rs = stmt.executeQuery();
         boolean found = false;
         while(rs.next())
         {
            found = true;
            String fname = rs.getString(1);
            String lname = rs.getString(2);
            String email = rs.getString(3);
            String institution = rs.getString(4);
            String office = rs.getString(5);
            String department = rs.getString(6);
            String domain = rs.getString(7);
            String interest  = rs.getString(8);
            
            System.out.println("");
            System.out.println("================================================");
            System.out.println("Name: "+fname+" "+lname);
            System.out.println("Email: "+email);
            System.out.println("Institution: "+institution);
            System.out.println("Office: "+office);
            System.out.println("Department: "+department);
            System.out.println("Domain(s): "+domain);
            System.out.println("Interest(s): "+interest);
            System.out.println("================================================");
         }
         
         if(found == false)
         {
            System.out.println("No Matches found :(\n");
         }
      }
      catch(SQLException sqle)
      {
         System.out.println("SQL ERROR");
         System.out.println("MATCH FAILED!!!!");
         //System.out.println("ERROR MESSAGE IS -> "+sqle);
         //sqle.printStackTrace();

      }
      catch(Exception e)
      {
         System.out.println("MATCH FAILED!!!!");
         System.out.println("Error occured in  matchInterests method");
         //System.out.println("ERROR MESSAGE is -> "+e);
         //e.printStackTrace();

      }
   }
   
   /**
     * matchInterestsFaculty - Matches students with faculty based on userID (of faculty)
     *
     * @param userID - userID of faculty to match students for.
   */
   public void matchInterestsFaculty(int userID)
   {
      try
      {
         ResultSet rs;
         //System.out.println(userID);
         String sql = "{CALL match_student_interest(?)}";
      
         CallableStatement stmt = conn.prepareCall(sql);
         stmt.setInt(1, userID);

         rs = stmt.executeQuery();
         boolean found = false;
         while(rs.next())
         {
            found = true;
            String fname = rs.getString(1);
            String lname = rs.getString(2);
            String email = rs.getString(3);
            String domain = rs.getString(4);
            String interest  = rs.getString(5);
            
            System.out.println("");
            System.out.println("================================================");
            System.out.println("Name: "+fname+" "+lname);
            System.out.println("Email: "+email);
            System.out.println("Domain(s): "+domain);
            System.out.println("Interest(s): "+interest);
            System.out.println("================================================");
         }
         
         if(found == false)
         {
            System.out.println("No Matches found :(\n");
         }
      }
      catch(SQLException sqle)
      {
         System.out.println("SQL ERROR");
         System.out.println("MATCH FAILED!!!!");
         //System.out.println("ERROR MESSAGE IS -> "+sqle);
         //sqle.printStackTrace();

      }
      catch(Exception e)
      {
         System.out.println("MATCH FAILED!!!!");
         System.out.println("Error occured in  matchInterests method");
         //System.out.println("ERROR MESSAGE is -> "+e);
         //e.printStackTrace();

      }
   }
   
   /**
     * getFacultyAbstracts - Gets faculty abstracts.
     *
     * @param userID - userID of faculty.
   */
   public void getFacultyAbstracts(int userID)
   {
      try
      {
         ResultSet rs;
         //System.out.println(userID);
         String sql = "{CALL get_abstracts(?)}";
      
         CallableStatement stmt = conn.prepareCall(sql);
         stmt.setInt(1, userID);

         rs = stmt.executeQuery();
         boolean found = false;
         while(rs.next())
         {
            found = true;
            String title = rs.getString(1);
            String _abstract = rs.getString(2);
            
            System.out.println("");
            System.out.println("================================================");
            System.out.println("\n"+title+"\n\n"+_abstract);
            System.out.println("================================================");
         }
         
         if(found == false)
         {
            System.out.println("No Abstracts found :(\n");
         }
      }
      catch(SQLException sqle)
      {
         System.out.println("SQL ERROR");
         System.out.println("MATCH FAILED!!!!");
         //System.out.println("ERROR MESSAGE IS -> "+sqle);
         //sqle.printStackTrace();

      }
      catch(Exception e)
      {
         System.out.println("OPERATION FAILED!!!!");
         System.out.println("OPERATION FAILED");
         System.out.println("Unable to get Abstracts - Ensure that you entered the correct FacultyID");
         System.out.println("Error occured in  getFacultyAbstracts method");
         //System.out.println("ERROR MESSAGE is -> "+e);
         //e.printStackTrace();

      }
   }

   /**
     * matchAbstracts - Gets faculty matches for students based on abstracts.
     *
     * @param userID - userID of student.
   */
   public void matchAbstracts(int userID)
   {
      try
      {
         ResultSet rs;
         //System.out.println(userID);
         String sql = "{CALL match_faculty_abstract(?)}";
      
         CallableStatement stmt = conn.prepareCall(sql);
         stmt.setInt(1, userID);

         rs = stmt.executeQuery();
         boolean found = false;
         while(rs.next())
         {
            found = true;
            int fid = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            String email = rs.getString(4);
            String institution = rs.getString(5);
            String office = rs.getString(6);
            String department = rs.getString(7);
            String domain = rs.getString(8);
            String interest  = rs.getString(9);
            
            System.out.println("");
            System.out.println("================================================");
            System.out.println("Faculty ID: "+fid);
            System.out.println("Name: "+fname+" "+lname);
            System.out.println("Email: "+email);
            System.out.println("Institution: "+institution);
            System.out.println("Office: "+office);
            System.out.println("Department: "+department);
            System.out.println("Domain: "+domain);
            System.out.println("Interest: "+interest);
            System.out.println("================================================");
         }
         
         if(found == false)
         {
            System.out.println("No Matches found :(\n");
         }
      }
      catch(SQLException sqle)
      {
         System.out.println("SQL ERROR");
         System.out.println("MATCH FAILED!!!!");
         //System.out.println("ERROR MESSAGE IS -> "+sqle);
         //sqle.printStackTrace();

      }
      catch(Exception e)
      {
         System.out.println("MATCH FAILED!!!!");
         System.out.println("Error occured in matchAbstrats method");
         //System.out.println("ERROR MESSAGE is -> "+e);
         //e.printStackTrace();

      }
   }
   
}


