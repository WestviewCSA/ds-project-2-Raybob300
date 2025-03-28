import java.util.LinkedList;
import java.util.Queue;

public class QueueSolver  {
	  private char[][] maze;
	    private int rows, cols;
	    private int startX, startY;
	    private static final char wall = '@';
	    private static final char open = '.';
	    private static final char path = '+';
	    private static final char start = 'W';
	    private static final char end = '$';
	
	public QueueSolver(char[][] beans) {
	    this.maze = beans;
        this.rows = maze.length;
        this.cols = maze[0].length;
        findStart();	}
	
	
	public void findStart() {
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

    public void findPath() {
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<int[]>();
        int[][][] parent = new int[rows][cols][2]; 

        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;   // if the square is visited it should be marked true

        while (!queue.isEmpty()) {// checks if empty
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            if (maze[x][y] == end) {
                markPath(parent, x, y);
                return;
            }

            for (int[] dir : directions) {
                int newX = x + dir[0], newY = y + dir[1];
                if (isValidMove(newX, newY, visited)) {
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                    parent[newX][newY] = new int[]{x, y}; // Store parent
                }
            }
        }
    }

    private void markPath(int[][][] parent, int endX, int endY) {
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
        return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] != wall && !visited[x][y]; // checks all 4 directions the wolvirene can go
    }

    public void printMaze() {
        for (char[] row : maze) {
            System.out.println(new String(row));
        }
    }
    
 
// getters and setters auto generated
	public char[][] getMaze() {
		return maze;
	}

	public void setMaze(char[][] maze) {
		this.maze = maze;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public static char getWall() {
		return wall;
	}

	public static char getOpen() {
		return open;
	}

	public static char getPath() {
		return path;
	}

	public static char getStart() {
		return start;
	}

	public static char getEnd() {
		return end;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}
	
	
	


}
   


