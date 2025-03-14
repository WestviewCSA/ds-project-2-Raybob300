import java.io.*;
import java.util.*;

public class Maze {
    private char[][] maze;
    private int rows, cols;
    private int startX, startY;
    private static final char WALL = '@';
    private static final char OPEN = '.';
    private static final char PATH = '+';
    private static final char START = 'W';
    private static final char END = '$';

    public Maze(char[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
        findStart();
    }

    private void findStart() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == START) {
                    startX = i;
                    startY = j;
                    return;
                }
            }
        }
    }

    public void findPath() {
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        Map<int[], int[]> parentMap = new HashMap<>();

        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            if (maze[x][y] == END) {
                markPath(parentMap, current);
                return;
            }

            for (int[] dir : directions) {
                int newX = x + dir[0], newY = y + dir[1];
                if (isValidMove(newX, newY, visited)) {
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                    parentMap.put(new int[]{newX, newY}, current);
                }
            }
        }
    }

    private void markPath(Map<int[], int[]> parentMap, int[] end) {
        int[] current = end;
        while (parentMap.containsKey(current)) {
            int x = current[0], y = current[1];
            if (maze[x][y] != START && maze[x][y] != END) {
                maze[x][y] = PATH;
            }
            current = parentMap.get(current);
        }
    }

    private boolean isValidMove(int x, int y, boolean[][] visited) {
        return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] != WALL && !visited[x][y];
    }

    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (char[] row : maze) {
                writer.println(new String(row));
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
