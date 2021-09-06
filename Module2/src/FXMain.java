/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author joann
 */
public class FXMain extends Application {
    String strToDo = "";
    String toDoDate ="";
    String toDoDesc ="";  
     String strResults=""; 
    ArrayList<ToDo> toDoList;
     
    
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        StackPane root = new StackPane();
        Label lbl1 = new Label("To Do Item");
        lbl1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        TextField txt = new TextField();
        txt.setPrefWidth(400);   
        
        Label lbl2 = new Label("Due Date (mm/dd/yyyy)");
        lbl2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        TextField txtDate = new TextField();
        
        //txtDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("mm/dd/yyyy")));
        txtDate.setPrefWidth(50);   
        
        Label lbl3 = new Label("Description");
        lbl3.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        TextField txtDesc = new TextField();
        txtDesc.setPrefWidth(100);   

        
        ComboBox<ToDo> ddl = new ComboBox();
        ddl.setPrefWidth(200);     
        
        Button btn1 = new Button();
        btn1.setText("Add");
        btn1.setPrefWidth(100);
        Label lblResults = new Label();
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {
        
        
 
        
            @Override
            public void handle(ActionEvent event) {
                strResults = "";
                strToDo = txt.getText();
                toDoDate = txtDate.getText();
                toDoDesc = txtDesc.getText();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Improper input");
                Validate valid = new Validate();
                String strError1 = valid.checkLength(strToDo);
                String strError2 = valid.checkValid(toDoDate);
                String strError3 = valid.checkLength(toDoDesc);

                if(strToDo !=null)
                        strToDo=strToDo.trim();
                    //check there is something in the text box
                    if(strError1.length()>0){
                        alert.setContentText(strError1) ;
                        alert.showAndWait();}
                    //check that the date is valid
                    else if(strError2.length()>0){
                        alert.setContentText(strError2);
                        alert.showAndWait();}
                else{
                   
 
                    Label lblToDo = new Label("To Do Items");
                    grid.add(lblToDo,0,7);
                    lblToDo.setFont(Font.font("Arial", FontWeight.BOLD, 16)); 
                    ToDo td = new ToDo(strToDo,toDoDate,toDoDesc);
//                    td.setList(toDoList);
                    td.addToDo();
                    
                    String ddList = "";
                    toDoList = td.getToDoList();
                    strResults = lblResults.getText();
                    for(ToDo toDo : toDoList){
                        strResults +=  System.lineSeparator() + toDo.Date() + System.lineSeparator();
                        strResults += toDo.Name() + System.lineSeparator();
                        strResults += toDo.Description() + System.lineSeparator()   ;
                        
                    }
                     

                    lblResults.setText(strResults);
                    lblResults.setWrapText(true);
                    lblResults.setPrefWidth(400);
                    
                    ddl.getItems().addAll(toDoList);   
                     //grid.add(ddl, 1, 6);  
            }
            }
        });
        
        
        Button btn2 = new Button();
        btn2.setText("Delete");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                    ToDo td = ddl.getSelectionModel().getSelectedItem();
                    td.DeleteToDo();
                    
                    //strResults = lblResults.getText();
                    toDoList = td.getToDoList();
                    if(toDoList!=null || toDoList.size()>0){
                    for(ToDo toDo : toDoList){
                        strResults +=  System.lineSeparator() + toDo.Date() + System.lineSeparator();
                        strResults += toDo.Name() + System.lineSeparator();
                        strResults += toDo.Description() + System.lineSeparator()   ;
                        
                    }
                    
                    lblResults.setText(strResults);
                    lblResults.setWrapText(true);
                    lblResults.setPrefWidth(400);
                    ddl.getItems().addAll(toDoList);
                    }
                    


            }
            
        });

        
        grid.add(lbl1, 0, 0);              
        grid.add(txt, 0, 1,3,1);
        grid.add(lbl2, 0, 2);      
        grid.add(txtDate, 0, 3,3,1);
        grid.add(lbl3, 0, 4);      
        grid.add(txtDesc, 0, 5,3,1);   
        grid.add(ddl, 1, 6);        
        grid.add(btn1, 0, 6);
        grid.add(btn2, 2, 6);
        grid.add(lblResults, 0, 8,2,1);
        
        
        Scene scene = new Scene(grid, 500, 600);
        
        primaryStage.setTitle("To Do List application.");
        primaryStage.setScene(scene);
        primaryStage.show();
    


    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
