
package danceteacherappfx;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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

//*******************************************************************************//
// UpdateAttendance Class
//
// methods:
// start(Stage object, Student object)
// plAttended(Stage object, Student object)
// glAttended(Stage object, Student object)
//*******************************************************************************// 
public abstract class UpdateAttendance extends Application {
    
//******************************************************************************//
// method:     start
// param:      Stage object, Student object    
// purpose:    drives the Update Attendance menu. Also allows user to go to 
//             Student File Menu or exit program
//******************************************************************************//    
    public static void start(Stage primaryStage, Student student) {
        
    // +++++++++++ PANES +++++++++++++++++ //    
        HBox pane = new HBox(10);
        VBox v = new VBox(15); 
        
    // ++++++++++ NODES +++++++++++++++++ //     
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
      
        Label welcome = new Label("     Update Attendance     ");
        
        Button b1 = new Button("Private Lesson");
        Button b2 = new Button("Group Lesson");
        Button b3 = new Button("STUDENT FILE MENU");
        Button b4 = new Button("QUIT");
        
      // ++++++++ STYLE PROPERTIES ++++++++ //    
        pane.setStyle("-fx-background-color: black");
        pane.setPadding(new Insets(0,10,0,0));
        
        v.setAlignment(Pos.CENTER);
        
        welcome.setFont(new Font("Garamond", 25));
        welcome.setTextFill(Color.web("#dcdcdc"));
        
        b1.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        b2.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        b3.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        b4.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
      
      // ++++++++ ACTION PROPERTIES +++++++ //
        // onClick - go to corresponding page
        b1.setOnAction(e-> plAttended(primaryStage, student));
        b2.setOnAction(e-> glAttended(primaryStage, student));
        b3.setOnAction(e-> StudentProfileMenu.start(primaryStage, student));
        b4.setOnAction(e-> System.exit(0));
        
        v.getChildren().addAll(welcome, b1, b2, b3, b4);
        pane.getChildren().addAll(imageView, v);
         
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Dance Teacher App");
        primaryStage.setScene(scene);
        primaryStage.show();
    } // end method
 
//********************************************************************************//
// method:      plAttended
// param:       Stage object, Student object
// purpose:     Allows user to enter date of private lesson attended and update 
//              student file, or return to Update Attendance menu. Calls 
//              updateFile(String student.ID)
// exception:   FileNotFound - if file name does not exist, will exit program
//*******************************************************************************//    
    static void plAttended(Stage primaryStage, Student student){
        
    // +++++++++++ PANES +++++++++++++++++ //    
        HBox pane = new HBox(10);
        HBox a = new HBox(10);
        VBox v = new VBox(15);
        
     // ++++++++++ NODES +++++++++++++++++ //    
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
        
        Label welcome = new Label("     PRIVATE LESSON ATTENDANCE     ");
        Label date = new Label("Enter date: ");
        
        TextField field = new TextField("--/--/----");
        
        Button submit = new Button("Submit");
        Button quit = new Button("Previous Menu");
        
     // ++++++++ STYLE PROPERTIES ++++++++ //     
        pane.setStyle("-fx-background-color: black");
        pane.setPadding(new Insets(0,10,0,0));
        
        v.setAlignment(Pos.CENTER);
        a.setAlignment(Pos.CENTER);
        
        welcome.setFont(new Font("Garamond", 25));
        welcome.setTextFill(Color.web("#dcdcdc"));   
       
        date.setStyle(" -fx-font-size: 15");
        date.setTextFill(Color.web("#808080"));
        
        submit.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        quit.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        
      // ++++++++ ACTION PROPERTIES +++++++ //
        // onKeystroke "Enter" - Ensure correct date format, update student 
        // file, return to Attendance Menu 
        field.setOnAction(e->{
            char test1 = field.getText().charAt(2);
            char test2 = field.getText().charAt(5);
            if(field.getText().equals("--/--/----"))
                field.setText("--/--/----");
            else if(test1 != '/' || test2 != '/')              
                plAttended(primaryStage, student);
            else{
                student.lastGL = field.getText();
                student.groupLessonsRemaining -= 1;
                student.groupLessonsUsed += 1;
            try {
                student.updateFile(student.ID);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UpdateAttendance.class.getName()).log(Level.SEVERE, null, ex);
            }
            start(primaryStage, student);}
        });
        // onClick - Ensure correct date format, update student 
        // file, return to Attendance Menu 
        submit.setOnAction(e->{
            char test1 = field.getText().charAt(2);
            char test2 = field.getText().charAt(5);
            if(field.getText().equals("--/--/----"))
                field.setText("--/--/----");
            else if(test1 != '/' || test2 != '/')              
                plAttended(primaryStage, student);
            else{
                student.lastGL = field.getText();
                student.groupLessonsRemaining -= 1;
                student.groupLessonsUsed += 1;
            try {
                student.updateFile(student.ID);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UpdateAttendance.class.getName()).log(Level.SEVERE, null, ex);
            }
            start(primaryStage, student);}
        });
        // onClick - return to Attendance Menu
         quit.setOnAction(e->start(primaryStage, student));
       
         a.getChildren().addAll(submit ,quit);
         v.getChildren().addAll(welcome, date, field, a);
         pane.getChildren().addAll(imageView, v);
         
         Scene scene = new Scene(pane);
         primaryStage.setScene(scene);
         primaryStage.show();
    } // end method
    
//********************************************************************************//
// method:      glAttended
// param:       Stage object, Student object
// purpose:     Allows user to enter date of group lesson attended and update 
//              student file, or return to Update Attendance menu. Calls 
//              updateFile(String student.ID)
// exception:   FileNotFound - if file name does not exist, will exit program
//*******************************************************************************//     
     static void glAttended(Stage primaryStage, Student student){
    
    // +++++++++++ PANES +++++++++++++++++ //     
        HBox pane = new HBox(10);
        HBox a = new HBox(10);
        VBox v = new VBox(15);
        
    // ++++++++++ NODES +++++++++++++++++ //     
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
        
        Label welcome = new Label("     GROUP LESSON ATTENDANCE     ");
        Label date = new Label("Enter date: ");
        
        TextField field = new TextField("--/--/----");
        
        Button submit = new Button("Submit");
        Button quit = new Button("Previous Menu");
        
     // ++++++++ STYLE PROPERTIES ++++++++ //     
        pane.setStyle("-fx-background-color: black");  
        pane.setPadding(new Insets(0,10,0,0));
        
        v.setAlignment(Pos.CENTER);
        a.setAlignment(Pos.CENTER);
        
        welcome.setFont(new Font("Garamond", 25));
        welcome.setTextFill(Color.web("#dcdcdc"));   
 
        date.setStyle(" -fx-font-size: 15");
        date.setTextFill(Color.web("#808080"));
        
        submit.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        quit.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        
       // ++++++++ ACTION PROPERTIES +++++++ //
        // onKeystroke "Enter" - Ensure correct date format, update student 
        // file, return to Attendance Menu  
        field.setOnAction(e->{
            char test1 = field.getText().charAt(2);
            char test2 = field.getText().charAt(5);
            if(field.getText().equals("--/--/----"))
                field.setText("--/--/----");
            else if(test1 != '/' || test2 != '/')              
                plAttended(primaryStage, student);
            else{
                student.lastGL = field.getText();
                student.groupLessonsRemaining -= 1;
                student.groupLessonsUsed += 1;
            try {
                student.updateFile(student.ID);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UpdateAttendance.class.getName()).log(Level.SEVERE, null, ex);
            }
            start(primaryStage, student);}
        });
        // onClick - Ensure correct date format, update student 
        // file, return to Attendance Menu  
        submit.setOnAction(e->{
            char test1 = field.getText().charAt(2);
            char test2 = field.getText().charAt(5);
            if(field.getText().equals("--/--/----"))
                field.setText("--/--/----");
            else if(test1 != '/' || test2 != '/')              
                plAttended(primaryStage, student);
            else{
                student.lastGL = field.getText();
                student.groupLessonsRemaining -= 1;
                student.groupLessonsUsed += 1;
            try {
                student.updateFile(student.ID);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UpdateAttendance.class.getName()).log(Level.SEVERE, null, ex);
            }
            start(primaryStage, student);}
        });
        
        // onClick - return to Attendance Menu
        quit.setOnAction(e->start(primaryStage, student));
      
     // ++++++++ LAYOUT +++++++++++//    
        v.getChildren().addAll(welcome, date, field, a);
        a.getChildren().addAll(submit ,quit);
        pane.getChildren().addAll(imageView, v);
         
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }// end method
    public static void main(String[] args) {
        launch(args);
    }//end method   
}// end class
