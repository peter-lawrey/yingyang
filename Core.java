import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.lang.Math;

class Core extends JPanel {

    //Adjusting the starting points of balls and other variables
    protected int xw, xb;
    protected int yw, yb;
    private int vx = 10, vy = 3;
    private int bounds;
    private int rows, columns, tileSize;
    private ArrayList<ColoredShape> tiles;
    private ArrayList<Ball> balls;

    private Timer animationTimer;
    private Timer gameTimer;
    private int whiteScore = 0;
    private int blackScore = 0;
    private static final int GAME_DURATION = 30000; // 30 seconds

    public Core(int rows, int columns, int tileSize) {
        
        this.rows = rows;
        this.columns = columns;
        this.tileSize = tileSize;
        this.bounds = tileSize / 2;
        this.tiles = new ArrayList<>();
        this.balls = new ArrayList<>();

        //Randomizing starting point and speed values
        xw = (int) Math.floor(Math.random() *(((Interface.frameWidth / 2) - tileSize*2) - tileSize*2 + 1) + tileSize*2);
        xb = (int) Math.floor(Math.random() *((Interface.frameWidth - tileSize) - (Interface.frameWidth / 2 + tileSize) + 1) + (Interface.frameWidth / 2 + tileSize));
        yw = (int) Math.floor(Math.random() *((Interface.frameHeight - tileSize*2) - tileSize*2 + 1) + tileSize*2);
        yb = (int) Math.floor(Math.random() *((Interface.frameHeight - tileSize*2) - tileSize*2 + 1) + tileSize*2);
        vx = (Math.random() < 0.5) ? vx : -1*vx;
        vy = (Math.random() < 0.5) ? vy : -1*vy;

        // Creating tiles
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                
                int x = j * tileSize;
                int y = i * tileSize;
                Shape rect = new Rectangle(x, y, tileSize, tileSize);
                Color color = (j < columns / 2) ? Color.BLACK : Color.WHITE;
                tiles.add(new ColoredShape(rect, color));

            }
        }

        // Creating balls
        balls.add(new Ball(xw, yw, bounds, Color.WHITE, vx, vy));
        balls.add(new Ball(xb, yb, bounds, Color.BLACK, vx, vy));

        // Animation part
        animationTimer = new Timer(10, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                moveBalls();
                checkCollision();
                repaint();
            }

        });

        animationTimer.start();

        gameTimer = new Timer(GAME_DURATION, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGame();
            }
        });
        gameTimer.setRepeats(false);
        gameTimer.start();
    }

    //Moving balls
    private void moveBalls() {
        for (Ball ball : balls) {
            
            ball.setX(ball.getX() + ball.getVx());
            ball.setY(ball.getY() + ball.getVy());
    
            // Bouncing back from walls
            if (ball.getX() > getWidth() - ball.getShape().width || ball.getX() < 0) {
                ball.setVx(-ball.getVx());
            }
            if (ball.getY() > getHeight() - ball.getShape().height || ball.getY() < 0) {
                ball.setVy(-ball.getVy());
            }

        }
    }

    private void checkCollision() {
        
        for (Ball ball : balls) {
            
            for (ColoredShape tile : tiles) {
                
                if (ball.getShape().getBounds().intersects(tile.getShape().getBounds())) {
                    
                    if (ball.getColor().equals(tile.getColor())) {

                        if (ball.getColor().equals(Color.WHITE)) {
                            whiteScore++;
                        } else {
                            blackScore++;
                        }
                        tile.setColor(ball.getColor().equals(Color.WHITE) ? Color.BLACK : Color.WHITE);
                        
                        // Calculating the angle of incidence
                        double tileCenterX = tile.getShape().getBounds2D().getCenterX();
                        double tileCenterY = tile.getShape().getBounds2D().getCenterY();
                        double ballCenterX = ball.getShape().getCenterX();
                        double ballCenterY = ball.getShape().getCenterY();

                        double angle = Math.atan2(ballCenterY - tileCenterY, ballCenterX - tileCenterX);

                        // Adjusting velocity based on angle of incidence
                        if (Math.abs(angle) < Math.PI / 4 || Math.abs(angle) > 3 * Math.PI / 4) {
                            // Adjusting x velocity
                            ball.setVx(-ball.getVx());
                        } else {
                            // Adjusting y velocity
                            ball.setVy(-ball.getVy());
                        }

                    }
                    
                }
            }
        }
    }

    private void endGame() {
        animationTimer.stop();
        gameTimer.stop();

        int whiteTiles = 0;
        int blackTiles = 0;
        for (ColoredShape tile : tiles) {
            if (tile.getColor().equals(Color.WHITE)) {
                whiteTiles++;
            } else {
                blackTiles++;
            }
        }

        String winner;
        if (whiteTiles > blackTiles) {
            winner = "White";
        } else if (blackTiles > whiteTiles) {
            winner = "Black";
        } else {
            winner = "Draw";
        }

        ScoreManager.addScore(winner, whiteTiles, blackTiles);

        JOptionPane.showMessageDialog(this,
                "Game Over! Winner: " + winner + "\nWhite: " + whiteTiles + " Black: " + blackTiles);
        SwingUtilities.getWindowAncestor(this).dispose();
    }

    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Drawing tiles
        for (ColoredShape tile : tiles) {
            g2d.setColor(tile.getColor());
            g2d.fill(tile.getShape());
        }

        // Drawing balls
        for (Ball ball : balls) {
            g2d.setColor(ball.getColor());
            g2d.fill(ball.getShape());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(columns * tileSize, rows * tileSize);
    }

}