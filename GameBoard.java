import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class GameBoard extends JPanel {
    private int gridWidth, gridHeight;
    private Player player;
    private Ghost ghost;
    private ArrayList<Point> pellets;
    private HashSet<Point> powerPellets;

    public GameBoard(int gridWidth, int gridHeight, Player player, Ghost ghost) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.player = player;
        this.ghost = ghost;
        this.pellets = generatePellets(10);
        this.powerPellets = generatePowerPellets(2);
        setPreferredSize(new Dimension(gridWidth * 25, gridHeight * 25));
    }

    private ArrayList<Point> generatePellets(int count) {
        ArrayList<Point> newPellets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * gridWidth);
            int y = (int) (Math.random() * gridHeight);
            newPellets.add(new Point(x, y));
        }
        return newPellets;
    }

    private HashSet<Point> generatePowerPellets(int count) {
        HashSet<Point> newPowerPellets = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * gridWidth);
            int y = (int) (Math.random() * gridHeight);
            newPowerPellets.add(new Point(x, y));
        }
        return newPowerPellets;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.GRAY);
        for (int i = 0; i <= gridWidth; i++) {
            g.drawLine(i * 25, 0, i * 25, getHeight());
        }
        for (int i = 0; i <= gridHeight; i++) {
            g.drawLine(0, i * 25, getWidth(), i * 25);
        }

        g.setColor(Color.YELLOW);
        g.fillOval(player.getX() * 25 + 5, player.getY() * 25 + 5, 15, 15);

        g.setColor(Color.RED);
        g.fillOval(ghost.getX() * 25 + 5, ghost.getY() * 25 + 5, 15, 15);

        g.setColor(Color.WHITE);
        for (Point pellet : pellets) {
            g.fillOval(pellet.x * 25 + 10, pellet.y * 25 + 10, 5, 5);
        }

        g.setColor(Color.BLUE);
        for (Point powerPellet : powerPellets) {
            g.fillOval(powerPellet.x * 25 + 8, powerPellet.y * 25 + 8, 10, 10);
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + player.getScore(), 10, 20);
        if (player.isPoweredUp()) {
            g.drawString("Powered Up!", 100, 20);
        }
    }

    public ArrayList<Point> getPellets() {
        return pellets;
    }

    public HashSet<Point> getPowerPellets() {
        return powerPellets;
    }
}
