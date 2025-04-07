import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

	}
    
    //method to read in file 
    public static void readFile(){
        File inputFile = new File("murdle.txt");
        try {
			//Scans all words as long as there is a word next
			Scanner in = new Scanner(inputFile);
			int counter = 1;
			while(in.hasNextLine()){
				if(counter >= 50){
					String lineA = in.nextLine();
					String lineB = in.nextLine();
					String lineC = in.nextLine();
					String[] splitLineA = lineA.split(",");
					String[] splitLineB = lineB.split(",");
					String[] splitLineC = lineC.split(",");
				}else{
					String lineA = in.nextLine();
					String lineB = in.nextLine();
					String lineC = in.nextLine();
					String lineD = in.nextLine();
					String[] splitLineA = lineA.split(",");
					String[] splitLineB = lineB.split(",");
					String[] splitLineC = lineC.split(",");
					String[] splitLineD = lineD.split(",");
				}
			}
			in.close();
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}