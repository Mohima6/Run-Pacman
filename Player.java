import java.awt.Point;

public class Player {
    private int x, y;
    private int score;
    private boolean poweredUp;
    private int powerDuration;  // Duration for the power-up

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.score = 0;
        this.poweredUp = false;
        this.powerDuration = 0;  // No power-up at the start
    }

    public void move(int dx, int dy, int gridWidth, int gridHeight) {
        x += dx;
        y += dy;
        // Keep the player within grid boundaries
        x = Math.max(0, Math.min(x, gridWidth - 1));
        y = Math.max(0, Math.min(y, gridHeight - 1));
    }

    public void collectPellet() {
        score += 10; // Increase score for each pellet collected
    }

    public void activatePowerUp() {
        poweredUp = true;
        powerDuration = 10;  // Power-up lasts for 10 moves
    }

    public void reducePowerDuration() {
        if (poweredUp && powerDuration > 0) {
            powerDuration--;
            if (powerDuration == 0) {
                poweredUp = false;  // Power-up ends when duration is 0
            }
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getScore() { return score; }
    public boolean isPoweredUp() { return poweredUp; }
}
