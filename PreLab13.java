import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;  
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PreLab13 extends Application { // extending application interface

    @Override
    public void start(Stage primaryStage) throws Exception{ // overriding start method of javafx

        primaryStage.setTitle("Button Test");  // title

        Button b1 = new Button("Button1"); // creating button 1

        b1.setOnAction(new EventHandler<ActionEvent>() { // anonymous event handler for b1
            @Override
            public void handle(ActionEvent event) {
                System.out.println("You Click Button 1");
            }
        });
        Button b2 = new Button("Button2"); // creating button 2
        MyEvent ev1 = new MyEvent();
        b2.setOnAction(ev1.ev); // button 2 EventHandler
        VBox vBox = new VBox(5); // layout vBox
        vBox.setSpacing(10); // add space to component
        vBox.getChildren().addAll(b1,b2); // add button to b1 and b2
        primaryStage.setScene(new Scene(vBox, 300, 275)); // Scene build
        primaryStage.show(); // show
    }

    private class MyEvent{ // nested event
       EventHandler ev =  new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {System.out.println("You click Button 2");}
       };
    }

    public static void main(String[] args) {
        launch(args);
    }
}