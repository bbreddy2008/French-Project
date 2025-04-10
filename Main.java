import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {
	private static Puzzle[] puzzles;
	private static Interface myInterface;
	private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
		puzzles = new Puzzle[100];
		readFile();
		myInterface = new Interface();
		

		// Interface myInterface = new Interface();
		// while (true) {
		// 	System.out.println("" + myInterface.userAnswers()[0]);
		// }
	}

		//Returned to Interface. Return values determine Green or Red coloring for rounded boxes.
	public static boolean[] checkAnswers(){
		boolean[] yOrN;
		int pez = myInterface.getPuzzle();
		if (pez <= 50){
			yOrN = new boolean[3];
			for (int i = 0; i < 3; i++) {
				if (myInterface.userAnswers()[i].equalsIgnoreCase(puzzles[pez].getCorrect()[i])) {
					yOrN[i] = true;
				} else {
					yOrN[i] = false;
				}
			}
		} else {
			yOrN = new boolean[4];
			for (int i = 0; i < 4; i++) {
				if (myInterface.userAnswers()[i].equalsIgnoreCase(puzzles[pez].getCorrect()[i])) {
					yOrN[i] = true;
				} else {
					yOrN[i] = false;
				}
			}
		}
		return yOrN;
		
	}
    
    //method to read in file 
    public static void readFile(){
        File inputFile = new File("murdle.txt");
        try {
			//Scans all words as long as there is a word next
			Scanner in = new Scanner(inputFile);
			String skip = in.nextLine();
			int counter = 1;
			while (counter <= 100){
				if (counter <= 50){

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

					Puzzle puzz = new Puzzle(counter, storedValue);
					puzzles[counter-1] = puzz;
					counter++;

				} else {

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

					Puzzle puzz = new Puzzle(counter, storedValue);
					puzzles[counter-1] = puzz;
					counter++;

				}
			}
			in.close();
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

	public static boolean[] submitButtonClicked() {
		System.out.println("Submit button clicked!");
		return checkAnswers();
	}

	public static Puzzle givePuzzleData(){
		return puzzles[myInterface.getPuzzle()];
	}
}