import javax.swing.*;
import java.awt.*;

public class StartMenu extends JFrame {

    private JTextField rowsField;
    private JTextField columnsField;
    private JTextField tileSizeField;

    public StartMenu() {
        setTitle("Ying Yang - Start Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Rows:"));
        rowsField = new JTextField("20");
        add(rowsField);

        add(new JLabel("Columns:"));
        columnsField = new JTextField("20");
        add(columnsField);

        add(new JLabel("Tile Size:"));
        tileSizeField = new JTextField("30");
        add(tileSizeField);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> startGame());
        add(startButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startGame() {
        try {
            int rows = Integer.parseInt(rowsField.getText());
            int columns = Integer.parseInt(columnsField.getText());
            int tileSize = Integer.parseInt(tileSizeField.getText());
            new Interface(rows, columns, tileSize);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
