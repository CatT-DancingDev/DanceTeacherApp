
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**************************************************************** //
 * StudentProfileMenu Class
 * Methods:
 * Start(Stage object)
 * bDayMsg(Student object)
 /*****************************************************************/
public abstract class StudentProfileMenu extends Application {
    
//**********************************************************************//
// method:    start
// param:     Stage object, Student object
// purpose:   Drives the student profile menu. Calls all menu classes.
//***********************************************************************//    
    public static void start(Stage primaryStage, Student student) {
    // +++++++++++ PANES +++++++++++++++++ //
        HBox pane = new HBox(10);
        VBox v = new VBox(15); 
    
    // ++++++++++ NODES +++++++++++++++++ //     
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
        
        Label welcome = new Label("     Student File Menu     "); 
        
        Button b1 = new Button("Purchase Lessons");
        Button b2 = new Button("Update Attendance" );
        Button b3 = new Button("Refund Lessons" );
        Button b4 = new Button("Print Information");
        Button b5 = new Button("QUIT");
        
      // ++++++++ STYLE PROPERTIES ++++++++ //    
        pane.setStyle("-fx-background-color: black");
        pane.setPadding(new Insets(0,10,0,0));
      
        
        welcome.setFont(new Font("Garamond", 25));
        welcome.setTextFill(Color.web("#dcdcdc"));
        
        b1.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        b2.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        b3.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        b4.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        b5.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        
       
      // ++++++++ ACTION PROPERTIES +++++++ // 
        //Each button takes you to the corresponding page
        b1.setOnAction(e->PurchaseLessons.start(primaryStage, student));
        b2.setOnAction(e->UpdateAttendance.start(primaryStage, student));
        b3.setOnAction(e->RefundLessons.start(primaryStage, student));
        b4.setOnAction(e->PrintInfo.start(primaryStage, student));
        b5.setOnAction(e->System.exit(0));
       
     // ++++++++ LAYOUT +++++++++++// 
        v.getChildren().addAll(welcome, b2, b4, b1, b3, b5);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20,20,20,20));
        pane.getChildren().addAll(imageView, v);
        
        Scene scene = new Scene(pane);
        scene.getStylesheets().add("fxml.css");
        primaryStage.setTitle("Dance Teacher App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
   
   //**************************************************************************//
   // method:      bdayMsg
   // param:       Student object
   // purpose:     Shows a new window with a birthday reminder. Window closes on
   //              button click.
   //**************************************************************************//
    public static void bdayMsg(Student student){
    
    // +++++++++++ PANES +++++++++++++++++ //
        StackPane bday = new StackPane();
        VBox v = new VBox(10);
     
    // ++++++++++ NODES +++++++++++++++++ //     
        Image image = new Image("birthday.jpg");
        ImageView imageView = new ImageView(image);
        
        Label label = new Label("It's " + student.firstName + "'s birthday today!");
        Label label2 = new Label("Don't forget to wish them a Happy Birthday.");
        
        Button b = new Button("Got it!");
        
    // ++++++++ STYLE PROPERTIES ++++++++ // 
        bday.setStyle("-fx-background-color: white");
        
    // ++++++++ LAYOUT +++++++++++//  
        v.getChildren().addAll(imageView, label, label2, b);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20,20,20,20));
        bday.getChildren().add(v);
        
        Scene scene = new Scene(bday);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    // ++++++++ ACTION PROPERTIES +++++++ //    
        b.setOnAction(e-> stage.hide());             
    } // end method

    public static void main(String[] args) {
        launch(args);
    }// end method    
}// end class
