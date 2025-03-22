import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        // Ask for the file name
        System.out.print("Enter the maze file name: ");
        String fileName = userInput.nextLine();

        // Read the map and store it in a 2D char array
        char[][] maze = readMap(fileName);

        if (maze != null) {
            Maze mazeSolver = new Maze(maze);
            mazeSolver.findPath();
            mazeSolver.printMaze(); // Print the solved maze
        }

        userInput.close();
    }

    public static char[][] readMap(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            // Read the metadata
            int numRows = scanner.nextInt();
            int numCols = scanner.nextInt();
            int numRooms = scanner.nextInt(); // Currently unused but read for completeness
            scanner.nextLine(); // Move to the next line

            // Initialize the maze array
            char[][] maze = new char[numRows][numCols];

            // Read the maze data
            for (int i = 0; i < numRows; i++) {
                String row = scanner.nextLine();
                for (int j = 0; j < numCols; j++) {
                    maze[i][j] = row.charAt(j);
                }
            }

            scanner.close();
            return maze;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return null;
    }
}
