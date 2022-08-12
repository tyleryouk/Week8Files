/** Tyler Youk Lab13 Painter Class */

import javafx.application.Application; 
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class Painter extends Application {
  
  double startingPosX,startingPosY;
  public static void main(String[] args){
    launch(args);}
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Drawing");
    BorderPane bp = new BorderPane();
    Canvas canvas = new Canvas(600,600);
    final GraphicsContext gc = canvas.getGraphicsContext2D();
    
    Ellipse circle = new Ellipse();
    circle.setStrokeWidth(1.0);
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.TRANSPARENT);
    
    /** When a user presses the mouse on canvas */
    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        startingPosX = e.getX();
        startingPosY = e.getY(); 
        circle.setCenterX(startingPosX);
        circle.setCenterY(startingPosY);
        /** Next time when you click somewhere else in the canvas to draw another circle, 
          * then it will blink the previous draw circle until you drag from that point */
        circle.setRadiusX(0);
        circle.setRadiusY(0);
        bp.getChildren().add(circle);
      }
    });
    
    /** second the user continues to draw a circle on canvas */
    canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        /** X and Y continuously changing while dragging the mouse */
        circle.setCenterX((e.getX() + startingPosX) / 2);
        circle.setCenterY((e.getY() + startingPosY) / 2);
        /** line 64,65 if not given then while drawing the circle, you won't be able to see how computer is drawing */
        circle.setRadiusX(Math.abs((e.getX() - startingPosX) / 2));
        circle.setRadiusY(Math.abs((e.getY() - startingPosY) / 2)); }});
    
    /**Final circle will be drawn once mouse is release */
    canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        bp.getChildren().remove(circle);
        double width=0.0,height=0.0;
        
        /** Condition where you do not draw the circle perfectly 
          * width and height need to be same
          * by mistake if you drag a lit bit longer then the width should be increased to match the height */
        if(Math.abs(e.getX() - startingPosX) <= Math.abs(e.getY() - startingPosY)){
          width = Math.abs(e.getY() - startingPosY);
          height = Math.abs(e.getY() - startingPosY);}  
        /** by mistake if you drag a lit bit wider then the height should be increased to match the width*/
        if(Math.abs(e.getX() - startingPosX) > Math.abs(e.getY() - startingPosY)){
          width = Math.abs(e.getX() - startingPosX);
          height = Math.abs(e.getX() - startingPosX);}
        
        /** Uncomment following lines if you want to draw an ellipse irrespective of the fact that it is not a circle */
        //width = Math.abs(e.getX() - startingPosX);
        //height = Math.abs(e.getY() - startingPosY);
        gc.setStroke(Color.BLACK);
        gc.fillOval(Math.min(startingPosX, e.getX()), Math.min(startingPosY, e.getY()), width, height);
      }
    });
    bp.setCenter(canvas);
    primaryStage.setScene(new Scene(bp));
    primaryStage.show();
  }
}
