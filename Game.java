import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {
    private static final int GRID_WIDTH = 20;
    private static final int GRID_HEIGHT = 20;
    private Timer timer;
    private Player player;
    private Ghost ghost;
    private GameBoard gameBoard;

    public Game() {
        initializeGame();
    }

    private void initializeGame() {
        player = new Player(GRID_WIDTH / 2, GRID_HEIGHT / 2);
        ghost = new Ghost(0, 0, true);
        gameBoard = new GameBoard(GRID_WIDTH, GRID_HEIGHT, player, ghost);

        JFrame frame = new JFrame("Pac-Man");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameBoard);
        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handlePlayerInput(e);
            }
        });

        startGameLoop();
    }

    private void handlePlayerInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> player.move(0, -1, GRID_WIDTH, GRID_HEIGHT);
            case KeyEvent.VK_DOWN -> player.move(0, 1, GRID_WIDTH, GRID_HEIGHT);
            case KeyEvent.VK_LEFT -> player.move(-1, 0, GRID_WIDTH, GRID_HEIGHT);
            case KeyEvent.VK_RIGHT -> player.move(1, 0, GRID_WIDTH, GRID_HEIGHT);
        }
    }

    private void startGameLoop() {
        timer = new Timer(200, e -> {
            Point playerPos = new Point(player.getX(), player.getY());
            Point ghostPos = new Point(ghost.getX(), ghost.getY());

            // Check if ghost caught Pac-Man
            if (playerPos.equals(ghostPos)) {
                if (player.isPoweredUp()) {
                    return;  // Pac-Man is invincible, ghost can't catch him
                } else {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Game Over! Caught you, honey ;)\nFinal Score: " + player.getScore());
                    System.exit(0); // Exit the game
                }
            }

            // Power pellet collection
            if (gameBoard.getPowerPellets().contains(playerPos)) {
                player.activatePowerUp();
                gameBoard.getPowerPellets().remove(playerPos);
            }

            // Pellet collection
            gameBoard.getPellets().removeIf(pellet -> {
                if (pellet.equals(playerPos)) {
                    player.collectPellet();
                    return true;
                }
                return false;
            });

            // Ghost movement
            if (player.isPoweredUp()) {
                ghost.fleeFrom(player.getX(), player.getY(), GRID_WIDTH, GRID_HEIGHT);
            } else {
                ghost.moveAI(player.getX(), player.getY(), GRID_WIDTH, GRID_HEIGHT);
            }

            // Reduce power duration
            player.reducePowerDuration();

            // Repaint game
            gameBoard.repaint();
        });
        timer.start();
    }

    public static void main(String[] args) {
        new Game();
    }
}
