import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
    
    //method to read in file 
    public static void readFile(){
        File inputFile = new File("murdle.txt");
        try {
			//Scans all words as long as there is a word next
			Scanner in = new Scanner(inputFile);
			while(in.hasNextLine()){
				//Add in other stuff
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}