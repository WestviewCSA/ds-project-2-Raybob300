import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class p2 {
    public static void main(String[] args) {
    	


        Scanner userInput = new Scanner(System.in);
 System.out.print("Enter the maze file name: ");
        
        String fileName = userInput.nextLine();
        
        System.out.print("Is this coordinate-based? (Yes/No): ");
        String isCoordinateBased = userInput.nextLine();
        // Read the map and store it in a 2D char array
        char[][] maze = null;
        if (isCoordinateBased.equals("Yes")) {
            // Read the coordinate-based format and convert to normal format
           maze = convertCoordToNormal(fileName);
        } else {
            // Read the normal text-based maze
            maze = readMap(fileName);
        }
        // Read the map and store it in a 2D char array
        System.out.print("Do you want to solve as a Queue, Stack, or Optimal: ");
        String Type = userInput.nextLine();
        
        
        
        
        
        //Queue Solver call
        if(Type.equals("Queue")) {
            long startTime = System.nanoTime();
           // in nanoseconds
        	 if (maze != null) {
                 QueueSolver mazeSolver = new QueueSolver(maze);
                 mazeSolver.findPath();
                 mazeSolver.printMaze(); // Print the solved maze
                 long endTime = System.nanoTime();
                 long duration = (endTime - startTime);  
                 System.out.println("Execution time: " + duration + " nanoseconds");

        	 }
        }
       
        else if(Type.equals("Stack")) {
            long startTime = System.nanoTime();
           // in nanoseconds
        	 if (maze != null) {
                 StackSolver mazeSolver = new StackSolver(maze);
                 mazeSolver.findPath();
                 mazeSolver.printMaze(); // Print the solved maze
                 long endTime = System.nanoTime();
                 long duration = (endTime - startTime);  
                 System.out.println("Execution time: " + duration + " nanoseconds");

        	 }
        }
        else if (Type.equals("Optimal")) {
            long startTime = System.nanoTime();
           // in nanoseconds
        	 if (maze != null) {
                 QueueSolver mazeSolver = new QueueSolver(maze);
                 mazeSolver.findPath();
                 mazeSolver.printMaze(); // Print the solved maze
                 long endTime = System.nanoTime();
                 long duration = (endTime - startTime);  
                 System.out.println("Execution time: " + duration + " nanoseconds");

        	 }
        } 	 
        else {
          		 System.out.println("Spell correctly");
          	     userInput = new Scanner(System.in);
          	  System.out.print("Enter the maze file name: ");
          	         
          	          fileName = userInput.nextLine();
          	         // Read the map and store it in a 2D char array
          	          maze = readMap(fileName);
          	         System.out.print("Do you want to solve as a Queue, Stack, or Optimal: ");
          	          Type = userInput.nextLine();
          	         
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
    
    // translate the coord ver to a 2D array
    public static char[][] convertCoordToNormal(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int numRows = scanner.nextInt();
            int numCols = scanner.nextInt();
            int numRooms = scanner.nextInt();  
            scanner.nextLine(); 

            // Initialize the normal maze with empty spaces ('.')
            char[][] normalMaze = new char[numRows][numCols];
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    normalMaze[i][j] = '.'; 
                }
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" "); 
                if (parts.length >= 4) { 
                    String marker = parts[0]; 
                    int x = Integer.parseInt(parts[1]); 
                    int y = Integer.parseInt(parts[2]); 

                    if (x >= 0 && x < numRows && y >= 0 && y < numCols) { 
                        if (marker.equals("W")) {
                            normalMaze[x][y] = 'W'; 
                        } else if (marker.equals("@")) {
                            normalMaze[x][y] = '@'; 
                        } else if (marker.equals("$")) {
                            normalMaze[x][y] = '$'; 
                        }
                    }
                }
            }

            scanner.close();
            return normalMaze;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return null;
    }



}
