package gui;

import logic.Food;
import logic.Grid;
import logic.Point;
import logic.Snake;
import gui.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static logic.Grid.SIZE;

import java.io.File;
import java.util.Scanner;

/**
* @author Natnael Assefa and Anand Blum
* @date 05.25.17
*/
public class Painter {


    public static void paint(Grid grid, GraphicsContext gc) {
    	
   
    	//The Grid
        gc.setFill(Grid.COLOR);
        gc.fillRect(0, 0, grid.getWidth(), grid.getHeight());

        // Now the Food
        gc.setFill(Food.COLOR);
        paintPoint(grid.getFood().getPoint(), gc);


        // Now the snake
        Snake snake = grid.getSnake();
        gc.setFill(Snake.COLOR);
        snake.getPoints().forEach(point -> paintPoint(point, gc));
        gc.setFill(Snake.HEAD);
        paintPoint(snake.getHead(),gc);
        if(snake.getHead().getY() == 720 || snake.getHead().getX()==0 || snake.getHead().getX()==1028|| snake.getHead().getY()==0){
        	snake.setSafe(false);
        }
        if (!snake.isSafe()) {
            gc.setFill(Snake.DEAD);
            paintPoint(snake.getHead(), gc);
        }

        // The score
        gc.setFill(Color.BEIGE);
        gc.fillText("Score : " + 100 * snake.getPoints().size(), 10, 710);
    }

    private static void paintPoint(Point point, GraphicsContext gc) {
        gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
    }

    public static void paintResetMessage(GraphicsContext gc, Snake snake) {
        gc.setFill(Color.AQUAMARINE);
        gc.fillText("Hit Return to play again", 10, 25);
        
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(30));
        gc.fillText("Score: " + snake.getPoints().size()*100, 450, 310);
        gc.setFont(Font.font(14));
        
    }
}
