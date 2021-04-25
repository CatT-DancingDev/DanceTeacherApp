/*************************************************************
 * Student Class
 * Methods:
 * Default Constructor
 * Constructor with Parameters
 * bdayCheck()
 * writeNewFile(String x)
 * updateFile(String x)
 *****************************************************************/
package danceteacherappfx;
import java.io.*;
import java.util.*;
import java.time.LocalDate; 
import java.text.SimpleDateFormat;  
import java.util.Date;  


public class Student {
    String ID = "";
    String firstName = "";
    String lastName = "";
    String dob = "";
    String lastPL = "";
    String lastGL = "";
    String startYear = "";
    int totalPLPurchased = 0;
    int privateLessonsUsed = 0;
    int privateLessonsRemaining = 0;
    int totalGLPurchased = 0;
    int groupLessonsUsed = 0;
    int groupLessonsRemaining =0;
    int totalSpent = 0;
    int totalRefunded = 0;
//******************************************************************************//
// Method:    bdayCheck
// Args:      no-args
// Purpose:   If the month and day of student's dob matches current month and day
//            output birthday reminder.
//******************************************************************************//
    public void bdayCheck(){
      // Create formatted current date string 
        Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
        String strDate = formatter.format(date);
      
      // Check if month and day match student month and day  
        String bd = strDate.substring(0, 5);
        String bday = this.dob.substring(0, 5);
        if(bd.equals(bday))
            StudentProfileMenu.bdayMsg(this);  
    }
//******************************************************************************//
// Method:    writeNewFile
// Args:      String Student ID
// Purpose:   Check if Student ID available. Create new file with student info.
//******************************************************************************//
    void writeNewFile(String x)throws java.io.IOException{
      // File name = student ID passed to this method.   
        String fileName = x + ".txt";    
        java.io.File newFile = new java.io.File(fileName);
      // Check if file already exists. If so, exit program
        if(newFile.exists()){
            System.out.println("File already exists with that ID. Exiting program.");
            System.exit(2);
        }
      // Write student data to file
        java.io.PrintWriter output = new java.io.PrintWriter(newFile);
        output.println(this.ID);
        output.println(this.firstName);
        output.println(this.lastName);
        output.println(this.dob);
        output.println(this.lastPL);
        output.println(this.lastGL);
        output.println(this.startYear);
        output.println(this.totalPLPurchased);
        output.println(this.privateLessonsUsed);
        output.println(this.privateLessonsRemaining);
        output.println(this.totalGLPurchased);
        output.println(this.groupLessonsUsed);
        output.println(this.groupLessonsRemaining);
        output.println(this.totalSpent);
        output.println(this.totalRefunded);
        output.close();
    }
//******************************************************************************//
// Method:    updateFile
// Args:      String Student ID
// Purpose:   Check if Student ID exist. Update file with student info. File will
//            be updated when purchasing or refunding lessons, and for each 
//            lesson attended.
//******************************************************************************//    
    void updateFile(String x) throws FileNotFoundException{
      // File name = student ID passed to this method.   
        String fileName = x + ".txt";
        PrintWriter output = new PrintWriter(fileName);
      
     // Write student data to file
        output.println(this.ID);
        output.println(this.firstName);
        output.println(this.lastName);
        output.println(this.dob);
        output.println(this.lastPL);
        output.println(this.lastGL);
        output.println(this.startYear);
        output.println(this.totalPLPurchased);
        output.println(this.privateLessonsUsed);
        output.println(this.privateLessonsRemaining);
        output.println(this.totalGLPurchased);
        output.println(this.groupLessonsUsed);
        output.println(this.groupLessonsRemaining);
        output.println(this.totalSpent);
        output.println(this.totalRefunded);
        output.close();
    }
    
//*******************************************************************************//
// Default Constructor
// Args:    no-args
// Purpose: for use in creating a new student file
// Result:  student object is created with user-input for Student ID, dob, 
//          first Name, and last name. All other fields set to appropriate default
//          values.
//*******************************************************************************//
    Student() throws IOException{
       Scanner input = new Scanner(System.in);
       
       System.out.println("Please enter the new Student ID.");
       this.ID = "";
       this.firstName = "";
       this.lastName = "";      
     
    
        this.dob = "00/00/0000";
       
       
     //Set remaining fields to default. Set start year to current year.  
       this.lastPL = "00/00/0000";
       this.lastGL = "00/00/0000";
       
       LocalDate currentDate = LocalDate.now();
       this.startYear =  String.valueOf(currentDate.getYear());
       
       this.totalPLPurchased = 0;
       this.privateLessonsUsed = 0;
       this.privateLessonsRemaining = 0;
       this.totalGLPurchased = 0;
       this.groupLessonsUsed = 0;
       this.groupLessonsRemaining = 0;
       this.totalSpent = 0;
       this.totalRefunded = 0;
      
    } 
//*******************************************************************************//
// Constructor with Paramters
// Args:    String student ID
// Purpose: for use in updating existing student file
// Result:  student object is created and fields are set by reading from exisitng 
//          student file.
//*******************************************************************************//    
    Student(String x) throws Exception {
        // Argument passes the studentID as the file name. 
        String fileName = x + ".txt";
        
        // Check if file exists. If not, exit.
        java.io.File sourceFile = new java.io.File(fileName);
        if(!sourceFile.exists()){
            System.out.println("Source file does not exist.");
            System.exit(1);
        }
        
        Scanner input = new Scanner(sourceFile);
        
        // set fields in student object by reading contents of student file.
        while(input.hasNext()){
        this.ID = input.nextLine();
        this.firstName = input.nextLine();
        this.lastName = input.nextLine();
        this.dob = input.nextLine();
        this.lastPL = input.nextLine();
        this.lastGL = input.nextLine();
        this.startYear = input.nextLine();
        this.totalPLPurchased = input.nextInt();
        this.privateLessonsUsed = input.nextInt();
        this.privateLessonsRemaining = input.nextInt();
        this.totalGLPurchased = input.nextInt();
        this.groupLessonsUsed = input.nextInt();
        this.groupLessonsRemaining = input.nextInt();
        this.totalSpent = input.nextInt();
        this.totalRefunded = input.nextInt();
        }
        input.close();
        
        // Alert User that file has been imported.
        System.out.println(this.firstName + " " + this.lastName +"'s file has been imported.");
        
       
    }                
} // end student class

