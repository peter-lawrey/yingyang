import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Interface extends JFrame {

    // Level data. Each level is an array of obstacles defined as
    // {x, y, width, height} in tile units.
    private static final int[][][] LEVELS = new int[][][] {
            // Level 0 : two small blocks in the middle
            {
                    {8, 8, 2, 2},
                    {11, 8, 2, 2}
            },
            // Level 1 : vertical walls in the centre
            {
                    {10, 4, 1, 6},
                    {10, 12, 1, 6}
            }
    };

    protected static int frameHeight = 637;
    protected static int frameWidth = 637;
    private int tileSize = 30;

    public Interface(int level) {

        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("Ying Yang");
        int[][] obstacles = LEVELS[level % LEVELS.length];
        add(new Core(frameHeight / tileSize, frameWidth / tileSize, tileSize, obstacles));
        setVisible(true);

    }

    public static void main(String[] args) {
        int level = 0;
        if (args.length > 0) {
            try {
                level = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {
            }
        }
        new Interface(level);
    }

}

class ColoredShape {
    
    private Shape shape;
    private Color color;

    public ColoredShape(Shape shape, Color color) {
        
        this.shape = shape;
        this.color = color;

    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

class Ball {
    
    private Ellipse2D.Double shape;
    private Color color;
    private int vx;
    private int vy;

    public Ball(int x, int y, int diameter, Color color, int vx, int vy) {
        
        this.shape = new Ellipse2D.Double(x, y, diameter, diameter);
        this.color = color;
        this.vx = vx;
        this.vy = vy;

    }

    public Ellipse2D.Double getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getX() {
        return (int) shape.x;
    }

    public void setX(int x) {
        shape.x = x;
    }

    public int getY() {
        return (int) shape.y;
    }

    public void setY(int y) {
        shape.y = y;
    }

}

class Obstacle {

    private Shape shape;

    public Obstacle(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }
}
