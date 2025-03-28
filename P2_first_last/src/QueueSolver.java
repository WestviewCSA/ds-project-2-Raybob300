import java.util.LinkedList;
import java.util.Queue;

public class QueueSolver {
    private char[][] maze;
    private int rows, cols;
    private int startX, startY;
    private int portal1X = -1, portal1Y = -1, portal2X = -1, portal2Y = -1;
    private int wolverine1X = -1, wolverine1Y = -1, wolverine2X = -1, wolverine2Y = -1;

    public QueueSolver(char[][] inputMaze) {
        this.rows = inputMaze.length;
        this.cols = inputMaze[0].length;
        this.maze = new char[rows][cols];

        // Copy the maze into the internal representation and track positions of important characters
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.maze[i][j] = inputMaze[i][j];
                if (maze[i][j] == 'W') {
                    if (wolverine1X == -1) {
                        wolverine1X = i;
                        wolverine1Y = j;
                    } else {
                        wolverine2X = i;
                        wolverine2Y = j;
                    }
                } else if (maze[i][j] == '|') {
                    if (portal1X == -1) {
                        portal1X = i;
                        portal1Y = j;
                    } else {
                        portal2X = i;
                        portal2Y = j;
                    }
                }
            }
        }
        startX = wolverine1X;
        startY = wolverine1Y;
    }
  //finds path while looking for all options as . |

    public boolean findPath() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            // If we reached the goal, stop
            if (maze[x][y] == '$') return true;

            // Mark the current path with '+' except if it's a portal
            if (maze[x][y] != 'W' && maze[x][y] != '|') maze[x][y] = '+';

            // Check movement directions (up, down, left, right)
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (isValidMove(newX, newY)) {
                    queue.add(new int[]{newX, newY});
                }
            }

            // Handle portals: only teleport if we're standing on a portal
            if (maze[x][y] == '|') {
                maze[x][y] = '|'; // Mark the portal as part of the path
                if (x == portal1X && y == portal1Y) {
                    queue.add(new int[]{wolverine2X, wolverine2Y});
                } else if (x == portal2X && y == portal2Y) {
                    queue.add(new int[]{wolverine1X, wolverine1Y});
                }
            }
        }
        return false;
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols && 
               (maze[x][y] == '.' || maze[x][y] == '$' || maze[x][y] == '|');
    }

    public void printMaze() {
        for (char[] row : maze) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
