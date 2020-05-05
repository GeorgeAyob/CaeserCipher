import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 *  A TextField Demo
 */

public class Cipher extends Application
{
	
   // create textfield labels to control	
   private TextField t2 = new TextField();
   private TextField t1 = new TextField();
   private TextField t3 = new TextField();
   private TextField t4 = new TextField();
   private Label Label5 = new Label("Message");


   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }
   
   @Override
   public void start(Stage primaryStage)
   {
      // Create a Label control.
  Label Label1 = new Label("Output File:");
  Label Label2 = new Label("Message to Encrypt:");
  Label Label3 = new Label("Input File: ");
  Label Label4 = new Label("Decrypted Message:  ");
  
  // Create a Button control.
  Button b1 = new Button("Encrypt");
  Button b2 = new Button("Decrypt");
  
  // Register the event handler.
  b1.setOnAction(new ButtonClickHandler());
  b2.setOnAction(new ButtonClickHandler2());

  // Put the Label and TextField in an HBox with 10 pixels of spacing.
  HBox h1 = new HBox(10, Label1, t1, Label2, t2, b1);
  HBox h2 = new HBox(10, Label3, t3, Label4, t4, b2);
  h1.setPadding(new Insets(10));
  h2.setPadding(new Insets(10));

  
  // Put the HBox and Button in a VBox with 10 pixels of spacing.
  VBox root = new VBox(10, h1, h2, Label5);
  root.setPadding(new Insets(10));
  
  // Create a Scene with the VBox as its root node.
  Scene scene = new Scene(root);
  
  // Set the scene's alignment to center.
  root.setAlignment(Pos.CENTER);
  
  // Add the Scene to the Stage.
  primaryStage.setScene(scene);
  
  // Set the stage title.
  primaryStage.setTitle("TextField Demo");
  
  // Show the window.
  primaryStage.show();
   }
   
   /*
    * Make a Button Handler Class to make an action button is encrypt button is clicked
    * Program will encrypt code in message field
    */
   class ButtonClickHandler implements EventHandler<ActionEvent>
   {	
	   public void handle(ActionEvent event) {
		 //encrypt
	 	String en = "";
	 	String s = t2.getText();  //get the Text from the field
	 	for(int i = 0; i < s.length(); i++) {    //go through each char
	 		char c = s.charAt(i);
	 		int shifted = (int)c + 10;			//shift 10 places
	 		en+=(char)shifted;					//turn back into char and add to String
	 	}
	 	//write this to a file
	 	try {
	 		//Get the file output
	 		String outputLoc = t1.getText();   //get File name
	 		DataOutputStream output = new DataOutputStream(new FileOutputStream(outputLoc)); //create the File
	 		output.writeUTF(en);		//write String to file
	 		output.close();				//close file
	 		Label5.setText("Encrypted the message to " + outputLoc);	//show message in label
	 	} catch (Exception e) { Label5.setText("An error has occurred when saving to the file!"); } //error message

		 }

   }
   
   /*
    * Make a Button Handler Class to make an action button is decrypt button is clicked
    * Program will decrypt code in inputfile
    */
   class ButtonClickHandler2 implements EventHandler<ActionEvent>
   {
	   public void handle(ActionEvent event) {
		 //decrypt			
	 String de = "";			//String to return	
	 String jumbled = "";	//The jumbled file
	 
	 	try {
	 		DataInputStream input = new DataInputStream(new FileInputStream(t3.getText()));   //read in the file
	 		jumbled = input.readUTF();		//grab the String
	 		input.close();					//close the file
	 	} catch (Exception e) { Label5.setText("Something terrible has happened when reading file."); }  //error message
	 	
	 	for(int i = 0; i < jumbled.length(); i++) {   //go through all the characters
	 		char c = jumbled.charAt(i);
	 		int shifted = (int)c - 10;			//deshift it
	 		de+=(char)shifted;
	 	}
	 	
	 	t4.setText(de);    //show the decrypted message
	 	Label5.setText("Successfully decrypted the message!");   //show message
		 }


   }
}