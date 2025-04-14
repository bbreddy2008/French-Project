import java.util.*;
import java.io.*;
import java.awt.*;

//The main program that activates all the programs
public class Main {

	//The array of puzzle objects that hold the andswers
	private static Puzzle[] puzzles;

	//The interface of the program that adds usability
	private static Interface myInterface;
	private static Scanner scanner = new Scanner(System.in);

	//Begins the program
    public static void main(String[] args) {

		//Creates all of the puzzle objects and reads in the txt file with the answers
		puzzles = new Puzzle[100];
		readFile();

		//Creates the interface
		myInterface = new Interface();
		
	}

		//Returned to Interface. Return values determine Green or Red coloring for rounded boxes.
	public static boolean[] checkAnswers(){

		//Array of booleans to determine which input is true
		boolean[] yOrN;

		//Gets the current puzzle
		int pez = myInterface.getPuzzle() - 1;

		//If the current puzzle is 50 or lower, it checks for 3 answers
		if (pez <= 50){
			yOrN = new boolean[3];

			for (int i = 0; i < 3; i++) {

				//If the answer in the input box is the same as the answer stored in the puzzle object, Return True
				if (myInterface.userAnswers()[i].equalsIgnoreCase(puzzles[pez].getCorrect()[i])) {
					yOrN[i] = true;

					//Otherwise return false
				} else {
					yOrN[i] = false;
				}
			}
		//If the current puzzle is 51 or higher, it checks for 4 answers
		} else {
			yOrN = new boolean[4];

			for (int i = 0; i < 4; i++) {

				//If the answer in the input box is the same as the answer stored in the puzzle object, Return True
				if (myInterface.userAnswers()[i].equalsIgnoreCase(puzzles[pez].getCorrect()[i])) {
					yOrN[i] = true;

					//Otherwise return false
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

			//Skips the first line since its just allignment information
			String skip = in.nextLine();

			//Used to determine puzzle number
			int counter = 1;

			//While below 100 puzzles
			while (counter <= 100){

				//For puzzle number 50 or lower
				if (counter <= 50){

					//Creates, reads, and splits up a 3 by 3 2D array with answers at the top
					String[][] storedValue = new String[3][3];

					String lineA = in.nextLine();
					String lineB = in.nextLine();
					String lineC = in.nextLine();

					String[] splitLineA = lineA.split(",");
					String[] splitLineB = lineB.split(",");
					String[] splitLineC = lineC.split(",");

					//Stores the inputs into the 2d Array
					for(int i = 0; i < storedValue.length; i++){
						storedValue[0][i] = splitLineA[i + 1];
						storedValue[1][i] = splitLineB[i];
						storedValue[2][i] = splitLineC[i];
					}

					//Sends the input 2D array and the puzzle number to make a new puzzle, then stores it in the array
					Puzzle puzz = new Puzzle(counter, storedValue);
					puzzles[counter-1] = puzz;

					//increases the counter to indicate new puzzle number
					counter++;

				//For puzzle numbers 51 and higher
				} else {

					//Creates, reads, and splits up a 4 by 4 2D array with answers at the top
					String[][] storedValue = new String[4][4];

					String lineA = in.nextLine();
					String lineB = in.nextLine();
					String lineC = in.nextLine();
					String lineD = in.nextLine();

					String[] splitLineA = lineA.split(",");
					String[] splitLineB = lineB.split(",");
					String[] splitLineC = lineC.split(",");
					String[] splitLineD = lineD.split(",");

					//Stores the inputs into the 2d Array
					for(int i = 0; i < storedValue.length; i++){
						storedValue[0][i] = splitLineA[i + 1];
						storedValue[1][i] = splitLineB[i];
						storedValue[2][i] = splitLineC[i];
						storedValue[3][i] = splitLineD[i];
					}

					//Sends the input 2D array and the puzzle number to make a new puzzle, then stores it in the array			
					Puzzle puzz = new Puzzle(counter, storedValue);
					puzzles[counter-1] = puzz;

					//increases the counter to indicate new puzzle number
					counter++;

				}
			}
			//Throws an error without this
			in.close();
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

	//If the Interface submit button is cliced, then it activates check answers
	public static boolean[] submitButtonClicked() {
		System.out.println("Submit button clicked!");
		return checkAnswers();
	}

	//Returns current puzzle data
	public static Puzzle givePuzzleData(){
		return puzzles[myInterface.getPuzzle()];
	}
}
