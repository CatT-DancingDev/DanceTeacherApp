/*******************************************************************************************************
 * Catherine Trujillo
 * CSC 240-C40
 * 4/29/20
 * Term Project
 * 
 * Program Title: Dance Teacher Application
 *  
 * Purpose:  This GUI program will allow dance teachers to keep track of information about their clients.
 * The program will allow teachers to create and update student files, sell or refund lessons, 
 * monitor attendance, keep track of money spent, alert the teacher if it is student's birthday,
 * and print out all of the students information.
 * 
 * Advanced Concepts Used:
 * Methods; Classes and Objects; javaFX; panes; nodes; controls; HBox and VBox;
 * File creation/modification; Exception Handling; Use of *this reference; Action events;
 ****************************************************************************************************/
package danceteacherappfx;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//******************************************************************************//
// MAIN CLASS - DanceTeacherAppFX
// Methods: start, newScene, existingScene, createFile
//******************************************************************************//
public class DanceTeacherAppFX extends Application {
    
    TextField field1 = new TextField();
    TextField field2 = new TextField();
    TextField field3 = new TextField();
    TextField field4 = new TextField("--/--/----");
  //****************************************************************************//
  // method: start
  // param:  Stage Object
  //
  //Purpose: Shows Main Start Page with options to create a new file, update an
  //         an existing one, or quit 
  //****************************************************************************//  
    
    public void start(Stage primaryStage) {
        
       // +++++++++++ PANES +++++++++++++++++ //
        HBox pane = new HBox(10);
        VBox v = new VBox(15);
        
       // ++++++++++ NODES +++++++++++++++++ // 
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
        
        Label welcome = new Label("Welcome to the Dance Teacher App");
        
        Button createNew = new Button("Create New Student Profile");
        Button updateExisting = new Button("Update Existing Student File");
        Button quit = new Button("Quit");
        
       // ++++++++ STYLE PROPERTIES ++++++++ // 
        pane.setStyle("-fx-background-color: black");
        pane.setPadding(new Insets(0,10,0,0));
      
       
        welcome.setFont(new Font("Garamond", 30));
        welcome.setTextFill(Color.web("#dcdcdc"));
        
        
        createNew.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");       
        updateExisting.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        quit.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        
       // ++++++++ ACTION PROPERTIES +++++++ // 
        updateExisting.setOnAction(e-> primaryStage.setScene(existingScene(primaryStage)));
        createNew.setOnAction(e-> primaryStage.setScene(newScene(primaryStage)));
        quit.setOnAction(e-> System.exit(0));
        
       // ++++++++ LAYOUT +++++++++++//
        v.getChildren().addAll(welcome,updateExisting,createNew,quit);
        v.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(imageView, v);
        
        Scene scene = new Scene(pane);
        scene.getStylesheets().add("fxml.css");
        primaryStage.setTitle("Dance Teacher App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }//end method
    
    //**************************************************************************//
    // method:   newScene
    // param:    Stage object
    // purpose:  This method will prompt user for new student ID, first name, 
    //           last name, and date of birth. It will ensure date is entered in
    //           correct format. If so it will call the createFile(Stage object)
    //           method.
    // exceptions: If a file already exists with input ID, the program will exit.
    //**************************************************************************//
    public Scene newScene(Stage stage){
      // +++++++++++ PANES +++++++++++++++++ //
        HBox pane = new HBox(10);
        HBox a = new HBox(10);
        HBox b = new HBox(10);
        HBox c = new HBox(10);
        HBox d = new HBox(10);
        VBox v = new VBox(15); 
     
      // ++++++++++ NODES +++++++++++++++++ //   
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
        
        Label welcome = new Label("Welcome to the Dance Teacher App");
        Label getID = new Label("New Student ID:");
        Label getFName = new Label("First name:         ");
        Label getLName = new Label("Last name:         ");
        Label bday = new Label("Date of birth:      ");
        
        Button enter = new Button("Enter");
        
     // ++++++++ STYLE PROPERTIES ++++++++ //     
        pane.setStyle("-fx-background-color: black");
        pane.setPadding(new Insets(0,10,0,0));
      
        welcome.setFont(new Font("Garamond", 30));
        welcome.setTextFill(Color.web("#dcdcdc"));
        
        enter.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
     
     // ++++++++ ACTION PROPERTIES +++++++ // 
        // Tests for proper entry of date format on enter key from textfield.
        // If date is entered correctly, a new student file is created by calling
        // createFile(stage).
        field4.setOnAction(e->{
            char test1 = field4.getText().charAt(2);
            char test2 = field4.getText().charAt(5);
            if(field4.getText().equals("--/--/----"))
                newScene(stage);    
            else if(test1 != '/' || test2 != '/')
                field4.setText("--/--/----");     
            else
            try {
                createFile(stage);
            } catch (IOException ex) {
                Logger.getLogger(DanceTeacherAppFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // Tests for proper entry date format with enter button. If entered correctly, 
        // a new student file is created by callinf createFile(stage) 
        enter.setOnAction(e->{
            char test1 = field4.getText().charAt(2);
            char test2 = field4.getText().charAt(5);
            if(field4.getText().equals("--/--/----"))
                newScene(stage);    
            else if(test1 != '/' || test2 != '/')
                field4.setText("--/--/----");     
            else
            try {
                createFile(stage);
            } catch (IOException ex) {
                Logger.getLogger(DanceTeacherAppFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
       // ++++++++ LAYOUT +++++++++++// 
        a.getChildren().addAll(getID, field1);
        b.getChildren().addAll(getFName, field2);
        c.getChildren().addAll(getLName, field3);
        d.getChildren().addAll(bday, field4);
        
        v.getChildren().addAll(welcome, a, b, c, d, enter);
        v.setAlignment(Pos.CENTER); 
        a.setAlignment(Pos.CENTER);
        b.setAlignment(Pos.CENTER);
        c.setAlignment(Pos.CENTER);
        d.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(imageView, v);
        Scene scene = new Scene(pane);
       
       return scene; 
    }//end method
    
   //***************************************************************************//
   // method:    existingScene
   // param:     Stage object
   // purpose:   This method will prompt the user for the exisitng student's ID.
   //            It will call the constructor student(String ID) and bDayCheck()
   //            methods before going to student profile menu.
   // exception: If a file with the input ID does not exist, the program will exit.
   //***************************************************************************//
    public Scene existingScene(Stage stage){
    // +++++++++++ PANES +++++++++++++++++ //  
        HBox pane = new HBox(10);
        VBox v = new VBox(15); 
    
    // ++++++++++ NODES +++++++++++++++++ //     
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
        
        Label welcome = new Label("Welcome to the Dance Teacher App");
        Label getID = new Label("Student ID:");
        
        Button enter = new Button("Enter");
        
    // ++++++++ STYLE PROPERTIES ++++++++ //    
        pane.setStyle("-fx-background-color: black");
        pane.setPadding(new Insets(0,10,0,0));
       
        welcome.setFont(new Font("Garamond", 30));
        welcome.setTextFill(Color.web("#dcdcdc"));
        
        enter.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        
    // ++++++++ ACTION PROPERTIES +++++++ // 
        // import existing student using Student ID. Either enter key or button will work. 
        field1.setOnAction(e-> {
            String response = field1.getText();
            try {
                 Student student = new Student(response);                 
                 StudentProfileMenu.start(stage, student);
                 student.bdayCheck();
            } catch (Exception ex) {
                Logger.getLogger(DanceTeacherAppFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        enter.setOnAction(e-> {
            String response = field1.getText();
            try {
                 Student student = new Student(response);
                 StudentProfileMenu.start(stage, student);
                student.bdayCheck();
            } catch (Exception ex) {
                Logger.getLogger(DanceTeacherAppFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
       // ++++++++ LAYOUT +++++++++++// 
        v.getChildren().addAll(welcome, getID, field1, enter);
        v.setAlignment(Pos.CENTER); 
        pane.getChildren().addAll(imageView,v);
       
        
       Scene scene = new Scene(pane);
       return scene;
    
    }//end method
    
    //**************************************************************************//
    // method:    creatFile
    // param:     Stage object
    // purpose:   no-arg Student constructor called. Will get text from input fields
    //            to set student's ID, first name, last name and birthday. It will
    //            update student file using writeNewFile(String ID), perform 
    //            bdayCheck(), and go to student profile menu.
    //**************************************************************************//
    private void createFile(Stage stage) throws IOException{
        Student student = new Student();
        String id = field1.getText();
        String fName = field2.getText();
        String lName = field3.getText();
        String dob = field4.getText();
        student.ID = id;
        student.firstName = fName;
        student.lastName = lName;
        student.dob = dob;
        student.writeNewFile(id);
        System.out.println("File Created");
        StudentProfileMenu.start(stage, student);
        student.bdayCheck();
           
    }
    
    public static void main(String[] args) {
        launch(args);
    }//end method    
}//end class
