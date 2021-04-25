
package danceteacherappfx;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
// RefundLessons Class
//
// methods:     
// start(Stage object, Student object)
// purchasePrivateLessons(Stage object, Student object)
// purchaseGroupLessons(Stage object, Student object)
// viewCart(Stage object, Student object)
// itemsInCart(Stage object, Student object)
//
// Class fields: totalPrivateLessons
//               totalGroupLessons
//               totalRefund  
//******************************************************************************//
public abstract class RefundLessons extends Application {
    
        static int totalPrivateLessons=0;
        static int totalGroupLessons=0;
        static int totalRefund=0;

//******************************************************************************//
// method:     start
// param:      Stage object, Student object
// purpose:    Drives the Refund Lessons Menu, also calls Student File Menu, 
//             and allows user to exit program
//******************************************************************************//          
    public static void start(Stage primaryStage, Student student) {
        
    // +++++++++++ PANES +++++++++++++++++ //    
        HBox pane = new HBox(10);
        VBox v = new VBox(15);
        
     // ++++++++++ NODES +++++++++++++++++ //    
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
        
        Label welcome = new Label("     Refund Lessons Menu     ");
        
        Button b1 = new Button("Refund Private Lessons");
        Button b2 = new Button("Refund Group Lessons");
        Button b3 = new Button("  View Refund / Checkout  ");
        Button b4 = new Button(" STUDENT FILE MENU ");
        Button b5 = new Button("QUIT");
        
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
        b5.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        
       // ++++++++ ACTION PROPERTIES +++++++ //
        //onClick - go to corresponding page
        b1.setOnAction(e->refundPrivateLessons(primaryStage, student));  
        b2.setOnAction(e->refundGroupLessons(primaryStage, student));       
        b3.setOnAction(e->viewCart(primaryStage, student));
       
        // onClick - If cart is not empty, go to itemsInCart protocol 
        b4.setOnAction(e->{
            if(totalRefund != 0 || totalPrivateLessons != 0 || totalGroupLessons != 0)
                itemsInCart(primaryStage, student);
            else
                StudentProfileMenu.start(primaryStage, student);
        });
         // onClick - If cart is not empty, go to itemsInCart protocol 
         b5.setOnAction(e->{
            if(totalRefund != 0 || totalPrivateLessons != 0 || totalGroupLessons != 0)
                itemsInCart(primaryStage, student);
            else
                System.exit(0);
        });
    
    // ++++++++ LAYOUT +++++++++++//      
        v.getChildren().addAll(welcome, b1, b2, b3, b4, b5);
        pane.getChildren().addAll(imageView,v);
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Dance Teacher App");
        primaryStage.setScene(scene);
        primaryStage.show();
    } // end method
    
 //****************************************************************************//
 // method:     refundPrivateLessons
 // param:      Stage object, Student object
 // purpose:    Prompt user for number of lessons, updates total cost of entered
 //             lesson qty and adds lessons to refund cart
 //****************************************************************************//   
    static void refundPrivateLessons(Stage primaryStage, Student student) {
        int price = 85;
        
    // +++++++++++ PANES +++++++++++++++++ //   
        HBox pane = new HBox(10);
        HBox a = new HBox(10);
        VBox v = new VBox(15);
        
    // ++++++++++ NODES +++++++++++++++++ //     
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
        
        Label welcome = new Label("  Refund Private Lessons   ");
        Label qty = new Label ("How many private lessons would you like to refund? Press enter to update price.");
        
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        
        Button cart = new Button("Add to Cart");
        
    // ++++++++ STYLE PROPERTIES ++++++++ //     
        pane.setStyle("-fx-background-color: black");
        pane.setPadding(new Insets(0,10,0,0));
        
        v.setAlignment(Pos.CENTER);
        a.setAlignment(Pos.CENTER);
        
        welcome.setFont(new Font("Garamond", 25));
        welcome.setTextFill(Color.web("#dcdcdc"));
        
        qty.setStyle(" -fx-font-size: 15");
        qty.setTextFill(Color.web("#808080"));
        
        cart.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        
    // ++++++++ ACTION PROPERTIES +++++++ //  
       // onKeystroke "enter" in lessonQty field, cost field will be updated. 
        tf1.setOnAction(e-> {
            Integer response = Integer.valueOf(tf1.getText());
            String cost = Integer.toString(response * price);
            try {tf2.setText("$" + cost); } catch (Exception ex) {
                Logger.getLogger(DanceTeacherAppFX.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
       // onClick - totalPrivateLessons and totalCost will be updated, and return
        // to Refund Lessons Menu  
        cart.setOnAction(e-> {
             totalPrivateLessons += Integer.valueOf(tf1.getText());
             totalRefund += Double.valueOf(tf1.getText()) * price;
             start(primaryStage, student);
         });
   
    // ++++++++ LAYOUT +++++++++++//     
        a.getChildren().addAll(tf1,tf2);       
        v.getChildren().addAll(welcome, qty,a,cart);      
        pane.getChildren().addAll(imageView, v);
          
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();   
    }//end method
    
 //****************************************************************************//
 // method:     refundGroupLessons
 // param:      Stage object, Student object
 // purpose:    Prompt user for number of lessons, updates total cost of entered
 //             lesson qty and adds lessons to refund cart
 //****************************************************************************//     
     static void refundGroupLessons(Stage primaryStage, Student student) {
         
        int price = 15;
        
     // +++++++++++ PANES +++++++++++++++++ // 
        HBox pane = new HBox(10);
        HBox a = new HBox(10);
        VBox v = new VBox(15);
        
     // ++++++++++ NODES +++++++++++++++++ // 
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
        
        Label welcome = new Label("  Refund Group Lessons   ");
        Label qty = new Label ("How many group lessons would you like to refund? Press enter to update price.");
        
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        
        Button cart = new Button("Add to Cart");
        
        // ++++++++ STYLE PROPERTIES ++++++++ //    
        pane.setStyle("-fx-background-color: black");
        pane.setPadding(new Insets(0,10,0,0));
        
        a.setAlignment(Pos.CENTER);
        v.setAlignment(Pos.CENTER);
        
        welcome.setFont(new Font("Garamond", 25));
        welcome.setTextFill(Color.web("#dcdcdc"));
        
        qty.setStyle(" -fx-font-size: 15");
        qty.setTextFill(Color.web("#808080"));
        
        cart.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        
      // ++++++++ ACTION PROPERTIES +++++++ //  
       // onKeystroke "enter" in lessonQty field, cost field will be updated. 
        tf1.setOnAction(e-> {
            Integer response = Integer.valueOf(tf1.getText());
            String cost = Integer.toString(response * price);
            try {tf2.setText("$" + cost); } catch (Exception ex) {
                Logger.getLogger(DanceTeacherAppFX.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
      // onClick - totalPrivateLessons and totalCost will be updated, and return
        // to Refund Lessons Menu   
        cart.setOnAction(e-> {
            totalGroupLessons += Integer.valueOf(tf1.getText());
            totalRefund += Integer.valueOf(tf1.getText()) * price;
            start(primaryStage, student);
         });
        
     // ++++++++ LAYOUT +++++++++++// 
        a.getChildren().addAll(tf1,tf2);
        v.getChildren().addAll(welcome, qty,a,cart);
        pane.getChildren().addAll(imageView, v);
        
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }// end method
    
 //*****************************************************************************//
 // method:     viewCart
 // param:      Stage object, Student object
 // purpose:    Shows User all items and total cost of lessons in cart. Allows user 
 //             to update student file, or empty cart and return to prior menu.
 //             Calls updateFile(String student.ID)
 // exceptions:  FileNotFound - will exit program if student ID does not exist.
 //*****************************************************************************//      
    static void viewCart(Stage primaryStage, Student student){
        
     // +++++++++++ PANES +++++++++++++++++ //    
        HBox pane = new HBox(10);
        HBox a = new HBox(10);
        VBox v = new VBox(15);
     
     // ++++++++++ NODES +++++++++++++++++ //    
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
        
        Label welcome = new Label("     REVIEW REFUND     ");
        Label pl = new Label(Integer.toString(totalPrivateLessons) + " Private Lessons");
        Label gl = new Label(Integer.toString(totalGroupLessons) + " Group Lessons");
        Label price = new Label("Total: $" + Integer.toString(totalRefund));
        
        Button update = new Button("Update " + student.firstName + "'s file?");
        Button cancel = new Button("Cancel");
     
     // ++++++++ STYLE PROPERTIES ++++++++ //    
        pane.setStyle("-fx-background-color: black");
        pane.setPadding(new Insets(0,10,0,0));
        
        a.setAlignment(Pos.CENTER);
        v.setAlignment(Pos.CENTER);

        welcome.setFont(new Font("Garamond", 25));
        welcome.setTextFill(Color.web("#dcdcdc"));
        
        pl.setStyle(" -fx-font-size: 15");
        pl.setTextFill(Color.web("#808080"));
       
        gl.setStyle(" -fx-font-size: 15");
        gl.setTextFill(Color.web("#808080"));
        
        price.setStyle(" -fx-font-size: 15");
        price.setTextFill(Color.web("#808080"));
        
        update.setStyle("-fx-background-color: black; -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        update.setTextFill(Color.web("#808080"));
        
        cancel.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        cancel.setTextFill(Color.web("#808080"));
       
       // ++++++++ ACTION PROPERTIES +++++++ //  
        //onClick - Update Student file, clear cart, return to Refund Lessons Menu
        update.setOnAction(e-> {
            student.totalPLPurchased -= totalPrivateLessons;
            student.privateLessonsRemaining -= totalPrivateLessons;
            student.totalGLPurchased -= totalGroupLessons;
            student.groupLessonsRemaining -= totalGroupLessons;
            student.totalSpent -= totalRefund;
            student.totalRefunded += totalRefund;
            
            totalGroupLessons = 0;
            totalPrivateLessons = 0;
            totalRefund = 0;
            try {
                student.updateFile(student.ID);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PurchaseLessons.class.getName()).log(Level.SEVERE, null, ex);
            }
            PurchaseLessons.start(primaryStage, student);
        });
      
       // onClick - clear cart, return to Refund Lessons Menu, student file NOT updated    
        cancel.setOnAction(e->{
            totalGroupLessons = 0;
            totalPrivateLessons = 0;
            totalRefund = 0;
            PurchaseLessons.start(primaryStage, student);
            });
        
      // ++++++++ LAYOUT +++++++++++//   
        a.getChildren().addAll(update,cancel);   
        v.getChildren().addAll(welcome, pl, gl, price, a);
        pane.getChildren().addAll(imageView, v);
        
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
                
    } // end method
   
  //*****************************************************************************//
  // method: itemsInCart
  // param: Stage object, Student object
  // purpose: this method is called when cart is not empty. It promts user to 
  //          either complete refund or remove all items from the cart
  //****************************************************************************//    
    static void itemsInCart(Stage primaryStage, Student student){
        
    // +++++++++++ PANES +++++++++++++++++ //    
        HBox pane = new HBox(10);
        HBox a = new HBox(10);
        VBox v = new VBox(15);
        
    // ++++++++++ NODES +++++++++++++++++ //     
        Image image = new Image("ballroom1.jpg");
        ImageView imageView = new ImageView(image);
        
        Label warning = new Label("***** WARNING *****");
        Label warning2 = new Label("There are items that need to be reviewed.");
        
        Button checkout = new Button("View Cart/Checkout");
        Button remove = new Button("Remove All Items");
        
     // ++++++++ STYLE PROPERTIES ++++++++ //    
        pane.setStyle("-fx-background-color: black");
        pane.setPadding(new Insets(0,10,0,0));
        
        v.setAlignment(Pos.CENTER);
        a.setAlignment(Pos.CENTER);
        
        warning.setFont(new Font("Garamond", 25));
        warning.setTextFill(Color.web("#dcdcdc"));
        warning.setAlignment(Pos.CENTER);
        
        warning2.setFont(new Font("Garamond", 18));
        warning2.setTextFill(Color.web("#dcdcdc"));
        warning2.setAlignment(Pos.CENTER);
        
        checkout.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        checkout.setTextFill(Color.web("#808080"));
       
        remove.setStyle("-fx-background-color: black;  -fx-border-width: 1 1 0 0 ; -fx-border-color: grey; -fx-font-size: 15");
        remove.setTextFill(Color.web("#808080"));
     
     // ++++++++ ACTION PROPERTIES +++++++ //
       //onClick - go to viewCart(primaryStage, student)    
        checkout.setOnAction(e-> viewCart(primaryStage, student));
      
     // onClick - clear cart, return to Purchase Lessons Menu, student file NOT updated   
        remove.setOnAction(e->{
            totalGroupLessons = 0;
            totalPrivateLessons = 0;
            totalRefund = 0;
            PurchaseLessons.start(primaryStage, student); });
      
     // ++++++++ LAYOUT +++++++++++//     
        a.getChildren().addAll(checkout, remove);
        v.getChildren().addAll(warning, warning2, a);
        pane.getChildren().addAll(imageView, v);
        
        
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();  
    }// end method
    public static void main(String[] args) {
        launch(args);
    }//end method 
}//end class
