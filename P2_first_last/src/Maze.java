import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
	private Tile[][] maze;
	
	public char translate() {
    	

    }
    public static void read(String fileName) {
        File file = new File(fileName);

        try {
			Scanner scanner = new Scanner(file);
			System.out.println("Is this in Coordinate form Yes(1)/NO(2)?");
			if(scanner.nextInt()==1) {
				System.out.println("you suck");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
	
}
