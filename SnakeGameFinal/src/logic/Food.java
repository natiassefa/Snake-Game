package logic;

import javafx.scene.paint.Color;

/**
 * Food class that will be the food for the snake
 *
 * @author Natnael Assefa and Anand Blum
 * @version 2016.12.17
 */
public class Food {
    public static final Color COLOR = Color.BISQUE;

    private Point point;

    Food(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}