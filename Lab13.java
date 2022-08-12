/** Tyler Youk Lab13 */

//package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Lab13 extends Application {
        /** A clickable button */
        private Button button;
        int clickCount = 0;

        /** Overrides the start method of Application to create the GUI with one button in the center of the main window.
         * @param primaryStage the JavaFX main window */
        public void start(Stage primaryStage) {
                button = new Button("Click count: "+clickCount);
                BorderPane pane = new BorderPane(); // create a 5 region layout for the window
                pane.setCenter(button); // add the button to the middle
                Scene scene = new Scene(pane); // Create a "scene" that contains this border area
                primaryStage.setTitle("Button Lab GUI");
                primaryStage.setScene(scene); // Add the "scene" to the main window
                primaryStage.show(); // Display the window
                button.setOnAction(actionEvent -> {
                        clickCount++;
                        button.setText("Click count: "+clickCount);
                });
        }

        public static void main(String[] args) {
                Application.launch(args);
        }

}



