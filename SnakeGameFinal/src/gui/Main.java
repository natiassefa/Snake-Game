package gui;

import logic.GameLoop;
import logic.Grid;
import logic.Snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
* @author Natnael Assefa and Anand Blum
* @date 05.25.17
*/
public class Main extends Application {

	
    private static final int WIDTH = 1028;
    private static final int HEIGHT = 720  ;

    private  static final  Map<String, ArrayList<Integer>> scores=   new HashMap<String , ArrayList<Integer>>();
    private GameLoop loop;
    private Grid grid;
    private GraphicsContext context;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        context = canvas.getGraphicsContext2D();
     
        canvas.setFocusTraversable(true);
      

        List<Integer> score = new ArrayList<Integer>();
      
        canvas.setOnKeyPressed(e -> {
            Snake snake = grid.getSnake();
            if (loop.isKeyPressed()) {
                return;
            }
            switch (e.getCode()) {
                case UP:
                    snake.setUp();
                    break;
                case DOWN:
                    snake.setDown();
                    break;
                case LEFT:
                    snake.setLeft();
                    break;
                case RIGHT:
                    snake.setRight();
                    break;
                case ENTER:
                
                    if (loop.isPaused()) {
                        reset();
                        (new Thread(loop)).start();
                    }
                               
            }
        });
        
    
        
      reset();

        root.getChildren().addAll(canvas);

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        
        primaryStage.setOnCloseRequest(e ->{
        	 Snake snake = grid.getSnake();
        	score.add(snake.getPoints().size()*100);
        	System.out.println(score);
        	int max =getMax(score);
        
        	System.out.println(scores);
        System.exit(0);});
        
        primaryStage.setScene(scene);
        primaryStage.show();

        (new Thread(loop)).start();
    }

    private void reset() {
        grid = new Grid(WIDTH, HEIGHT);
        loop = new GameLoop(grid, context);
        Painter.paint(grid, context);
    }
    public int getMax(List <Integer> scores){
    	int max = Integer.MIN_VALUE;
    	for(int i = 0; i< scores.size(); i++){
    		if(scores.get(i)> max){
    			max= scores.get(i);
    		}
    	}
    	return max;
    }
    

}
