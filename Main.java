import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
		readFile();
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

					String[][] storedValue = new String[3][3];
					String lineA = in.nextLine();
					String lineB = in.nextLine();
					String lineC = in.nextLine();
					String[] splitLineA = lineA.split(",");
					String[] splitLineB = lineB.split(",");
					String[] splitLineC = lineC.split(",");

					for(int i = 0; i < storedValue.length; i++){
						storedValue[0][i] = splitLineA[i + 1];
						storedValue[1][i] = splitLineB[i];
						storedValue[2][i] = splitLineC[i];
					}

					Puzzle puzz = new Puzzle(counter);
					for(int i = 0; i < storedValue.length; i++){
						puzz.addQui(storedValue[i][0]);
						puzz.addQuoi(storedValue[i][1]);
						puzz.addOu(storedValue[i][2]);
					}

				}else{

					String[][] storedValue = new String[4][4];
					String lineA = in.nextLine();
					String lineB = in.nextLine();
					String lineC = in.nextLine();
					String lineD = in.nextLine();
					String[] splitLineA = lineA.split(",");
					String[] splitLineB = lineB.split(",");
					String[] splitLineC = lineC.split(",");
					String[] splitLineD = lineD.split(",");

					for(int i = 0; i < storedValue.length; i++){
						storedValue[0][i] = splitLineA[i + 1];
						storedValue[1][i] = splitLineB[i];
						storedValue[2][i] = splitLineC[i];
						storedValue[3][i] = splitLineD[i];
					}

					Puzzle puzz = new Puzzle(counter);
					for(int i = 0; i < storedValue.length; i++){
						puzz.addQui(storedValue[i][0]);
						puzz.addQuoi(storedValue[i][1]);
						puzz.addOu(storedValue[i][2]);
						puzz.addPoruoi(storedValue[i][3]);
					}

				}
			}
			in.close();
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}