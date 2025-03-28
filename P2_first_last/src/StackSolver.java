import java.util.ArrayList;
import java.util.Stack;

public class StackSolver {
	//var decleration
    private char[][] maze;
    private int rows, cols;
    private int startX, startY;
    private static final char wall = '@';
    private static final char open = '.';
    private static final char path = '+';
    private static final char start = 'W';
    private static final char end = '$';

    public StackSolver(char[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
        findStart();
    }

    private void findStart() {//looks for the W
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == start) {
                    startX = i;
                    startY = j;
                    return;
                }
            }
        }
    }

    public void findPath() {// finds the parth and then mark using methods
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Stack<int[]> stack = new Stack<int[]>();
        int[][][] parent = new int[rows][cols][2];

        stack.push(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0], y = current[1];

            if (maze[x][y] == end) {
                markPath(parent, x, y);
                return;
            }

            for (int[] dir : directions) {
                int newX = x + dir[0], newY = y + dir[1];
                if (isValidMove(newX, newY, visited)) {
                    stack.push(new int[]{newX, newY});
                    visited[newX][newY] = true;
                    parent[newX][newY] = new int[]{x, y}; // Store parent
                }
            }
        }
    }

    private void markPath(int[][][] parent, int endX, int endY) {//mark path using this method
        int x = endX, y = endY;
        while (maze[x][y] != start) {
            int P1 = parent[x][y][0];
            int P2 = parent[x][y][1];

            if (maze[x][y] != end) {
                maze[x][y] = path;
            }

            x = P1;
            y = P2;
        }
    }

    private boolean isValidMove(int x, int y, boolean[][] visited) {
        return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] != wall && !visited[x][y];
    }

    public void printMaze() {
        for (char[] row : maze) {
            System.out.println(new String(row));
        }
    }

    
    
    
    
}


