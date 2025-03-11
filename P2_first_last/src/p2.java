import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("p2");
		readMap("Test01");
	}

	
	public static void readMap(String fileName) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			
			int rowIndex=0;
			while(scanner.hasNextLine()) {
				String row = scanner.nextLine();
				for(int i =0; i<numCols&& i<row.length(); i++) {
					char el = row.charAt(i);
					Tile obj = new Tile(rowIndex, i, el );
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		
	}
	
}
