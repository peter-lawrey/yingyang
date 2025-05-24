import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScoreManager {
    private static final String FILE_NAME = "scores.txt";

    public static void addScore(String winner, int whiteTiles, int blackTiles) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            out.println(time + " | Winner:" + winner + " | White:" + whiteTiles + " | Black:" + blackTiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readScores() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            sb.append("No scores recorded.");
        }
        return sb.toString();
    }
}
