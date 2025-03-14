import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {

    public static void main(String[] args) {
    	
    	// 
        System.out.println("p2");
        readMap("Test02");
      //need to translate the txt to a 2d array and make it the parameter  Maze maze = new Maze(maze);

    }


    public static void readMap(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            
            // Read and extract first row (metadata)
            int numRows = scanner.nextInt();
            int numCols = scanner.nextInt();
            int numRooms = scanner.nextInt();
            scanner.nextLine(); // Move to next line after integers
            
            // Read and print the map (excluding the first row)
            int rowIndex = 0;
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                
                if (rowIndex < numRows) { // Ignore junk lines beyond expected rows
                    for (int i = 0; i < numCols && i < row.length(); i++) {
                        System.out.print(row.charAt(i));
                    }
                    System.out.println(); // Newline for next row
                    rowIndex++;
                }
            }
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    
    }
	
}
