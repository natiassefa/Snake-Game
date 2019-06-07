package logic;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * The class that encapsulates the star of the show. Basically it stores it's current
 * state and stuff like position, velocity, length and so on and so forth.
 *
 *
 * @author Natnael Assefa and Anand Blum
 * @date 05.25.17
 */
public class Snake {
	public static final Color COLOR = Color.WHITE;
	public static final Color DEAD = Color.RED;
	 public static final Color HEAD = Color.CORNFLOWERBLUE;
	
	private Grid grid;
	private int length;
	private boolean safe;
	private List<Point> points;
	private Point head;
	private int xVelocity;
	private int yVelocity;

	/**
	 * The constructor the snake. It takes the initial point, for the head and the Grid
	 * that it lives (and dies) in.
	 *
	 * @param initialPoint The {@link Point} to the put the snake's head on.
	 */
	public Snake(Grid grid, Point initialPoint) {
		length = 1;
		points = new LinkedList<>();
		points.add(initialPoint);
		head = initialPoint;
		safe = true;
		this.grid = grid;
		xVelocity = 0;
		yVelocity = 0;
	}

	/**
	 * This method is called after food has been consumed. It increases the length of the
	 * snake by one.
	 *
	 * @param point The Point where the food was and the new location for the head.
	 */
	private void growTo(Point point) {
		length+=10;
		checkAndAdd(point);
	}
	public void setSafe(boolean b){
		safe = b;
	}
	/**
	 * Called during every update. It gets rid of the oldest point and adds the given point.
	 *
	 * @param point The new Point to add.
	 */
	private void shiftTo(Point point) {
		// The head goes to the new location
		checkAndAdd(point);
		// The last/oldest position is dropped
		points.remove(0);
	}

	/**
	 * Checks for an intersection and marks the "safe" flag accordingly.
	 *
	 * @param point The new Point to move to.
	 */
	private void checkAndAdd(Point point) {
		point = grid.wrap(point);
		safe &= !points.contains(point);
		points.add(point);
		head = point;
	}

	/**
	 * @return The points occupied by the snake.
	 */
	public List<Point> getPoints() {
		return points;
	}

	/**
	 * @return {@code true} if the Snake hasn't run into itself yet.
	 */
	public boolean isSafe() {
		return safe || length == 1;
	}

	/**
	 * @return The location of the head of the Snake.
	 */
	public Point getHead() {
		return head;
	}

	private boolean isStill() {
		return xVelocity == 0 & yVelocity == 0;
	}

	/**
	 * Make the snake move one square in it's current direction.
	 */
	public void move() {
		if (!isStill()) {
			shiftTo(head.translate(xVelocity, yVelocity));
		}
	}

	/**
	 * Make the snake extend/grow to the square where it's headed.
	 */
	public void extend() {
		if (!isStill()) {
			growTo(head.translate(xVelocity, yVelocity));
		}
	}

	public void setUp() {
		if (yVelocity == 1 && length > 1) return;
		xVelocity = 0;
		yVelocity = -1;
	}

	public void setDown() {
		if (yVelocity == -1 && length > 1) return;
		xVelocity = 0;
		yVelocity = 1;
	}

	public void setLeft() {
		if (xVelocity == 1 && length > 1) return;
		xVelocity = -1;
		yVelocity = 0;
	}

	public void setRight() {
		if (xVelocity == -1 && length > 1) return;
		xVelocity = 1;
		yVelocity = 0;
	}
}
