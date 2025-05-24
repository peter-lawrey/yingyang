import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Interface extends JFrame {

    protected static int frameHeight;
    protected static int frameWidth;
    private int tileSize;

    public Interface(int rows, int columns, int tileSize) {

        this.tileSize = tileSize;
        frameHeight = rows * tileSize;
        frameWidth = columns * tileSize;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("Ying Yang");
        add(new Core(rows, columns, tileSize));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new StartMenu();
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
