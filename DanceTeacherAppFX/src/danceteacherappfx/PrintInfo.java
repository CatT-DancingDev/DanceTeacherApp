/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danceteacherappfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//******************************************************************************//
// PrintInfo Class
//
// methods:     
// start(Stage object, Student object)
//******************************************************************************//
public abstract class PrintInfo extends Application {

//******************************************************************************//
// method:     start
// param:      Stage object, Student object
// purpose:    Output all Student information fields. Call Student File Menu
//******************************************************************************//    
    public static void start(Stage primaryStage, Student student) {
    
    // +++++++++++ PANES +++++++++++++++++ //    
        HBox pane = new HBox(40);
        VBox v = new VBox(8); 
     
    // ++++++++++ NODES +++++++++++++++++ //     
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
      
      
        Label welcome = new Label("     Student Information     ");
        Label name = new Label(student.lastName + " , " + student.firstName);
        Label id = new Label("Student ID: " + student.ID);
        Label bday = new Label("DOB: " + student.dob);
        Label year = new Label("Year Enrolled: " + student.startYear);
        Label totalPL = new Label("Total Private Lessons Purchased: " + student.totalPLPurchased);       
        Label plUsed = new Label("Total Private Lessons Used: " + student.privateLessonsUsed); 
        Label plRem = new Label("Private Lessons Remaining " + student.privateLessonsRemaining);        
        Label totalGL = new Label("Total Group Lessons Purchased: " + student.totalGLPurchased);
        Label glUsed = new Label("Total Group Lessons Used: " + student.groupLessonsUsed);
        Label glRem = new Label("Group Lessons Remaining " + student.groupLessonsRemaining);  
        Label lastPL = new Label("Date of last Private lesson " + student.lastPL);
        Label lastGL = new Label("Date of last Group lesson " + student.lastGL);
        Label spent = new Label("Total Spent on lessons: $" + Integer.toString(student.totalSpent));
        Label refund = new Label("Total Refunded: $" + Integer.toString(student.totalRefunded)); 
        
        Button menu = new Button("STUDENT FILE MENU ");
       
    // ++++++++ STYLE PROPERTIES ++++++++ //    
        pane.setStyle("-fx-background-color: black");
        pane.setPadding(new Insets(0,10,0,0));
        
        v.setPadding(new Insets(10, 40,10,0));
        
        welcome.setFont(new Font("Garamond", 25));
        welcome.setTextFill(Color.web("#dcdcdc")); 
        
        name.setTextFill(Color.web("#808080"));
        id.setTextFill(Color.web("#808080"));
        bday.setTextFill(Color.web("#808080")); 
        year.setTextFill(Color.web("#808080"));
        totalPL.setTextFill(Color.web("#808080"));
        plUsed.setTextFill(Color.web("#808080"));
        plRem.setTextFill(Color.web("#808080"));
        totalGL.setTextFill(Color.web("#808080"));
        glUsed.setTextFill(Color.web("#808080"));
        glRem.setTextFill(Color.web("#808080"));
        lastPL.setTextFill(Color.web("#808080"));
        lastGL.setTextFill(Color.web("#808080"));
        spent.setTextFill(Color.web("#808080"));
        refund.setTextFill(Color.web("#808080"));
        
        menu.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        menu.setAlignment(Pos.BOTTOM_CENTER);  
      
     // ++++++++ ACTION PROPERTIES ++++++++ //   
        menu.setOnAction(e-> StudentProfileMenu.start(primaryStage, student));
      
     // ++++++++ LAYOUT +++++++++++//   
        v.getChildren().addAll(welcome, name,id,bday,year,totalPL,plUsed,plRem,totalGL,glUsed,glRem,lastPL,lastGL,spent,refund,menu);
        pane.getChildren().addAll(imageView,v);
        
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    } // end method
    public static void main(String[] args) {
        launch(args);
    } // end method
} // end class
