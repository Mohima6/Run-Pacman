import java.awt.Point;

public class Ghost {
    private int x, y;
    private boolean isAI;

    public Ghost(int x, int y, boolean isAI) {
        this.x = x;
        this.y = y;
        this.isAI = isAI;
    }

    public void moveAI(int pacX, int pacY, int gridWidth, int gridHeight) {
        // AI behavior: Ghost chases Pac-Man
        if (pacX < x) x--;
        else if (pacX > x) x++;

        if (pacY < y) y--;
        else if (pacY > y) y++;

        // Ensure ghost stays within bounds
        x = Math.max(0, Math.min(x, gridWidth - 1));
        y = Math.max(0, Math.min(y, gridHeight - 1));
    }

    public void fleeFrom(int pacX, int pacY, int gridWidth, int gridHeight) {
        // Power-up mode: Ghost runs away from Pac-Man
        if (pacX < x) x++;
        else if (pacX > x) x--;

        if (pacY < y) y++;
        else if (pacY > y) y--;

        // Ensure ghost stays within bounds
        x = Math.max(0, Math.min(x, gridWidth - 1));
        y = Math.max(0, Math.min(y, gridHeight - 1));
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
