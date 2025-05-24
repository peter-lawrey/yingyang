import javax.swing.*;
import java.awt.*;

public class StartMenu extends JFrame {

    public StartMenu() {
        setTitle("Ying Yang");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JButton startButton = new JButton("Start Game");
        JButton scoreButton = new JButton("High Scores");
        JButton exitButton = new JButton("Exit");

        add(startButton);
        add(scoreButton);
        add(exitButton);

        startButton.addActionListener(e -> new Interface());

        scoreButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this, ScoreManager.readScores(),
                        "High Scores", JOptionPane.INFORMATION_MESSAGE));

        exitButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        new StartMenu().setVisible(true);
    }
}
