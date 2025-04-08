import java.util.*;
import java.io.*;

public class Main {
	private static Puzzle[] puzzles;
	private static Scanner scanner = new Scanner(System.in);
	private static String qui_answer;
    private static String quoi_answer;
    private static String ou_answer;
    private static String pourquoi_answer;
    public static void main(String[] args) {
		puzzles = new Puzzle[100];
		readFile();
		System.out.println("Enter the id of puzzle.");
		int n = scanner.nextInt();
		getInput(n);
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
					System.out.println(lineA);
					String lineB = in.nextLine();
					System.out.println(lineB);
					String lineC = in.nextLine();
					System.out.println(lineC);
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
					System.out.println(lineA);
					String lineB = in.nextLine();
					System.out.println(lineB);
					String lineC = in.nextLine();
					System.out.println(lineC);
					String lineD = in.nextLine();
					System.out.println(lineD);
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

	//Input from the interface
    public static void getInput(int id){
        if (id <= 50){
            qui_answer= scanner.nextLine();
            quoi_answer= scanner.nextLine();
            ou_answer= scanner.nextLine();
            pourquoi_answer = null;
        } else {
            qui_answer= scanner.nextLine();
            quoi_answer= scanner.nextLine();
            ou_answer= scanner.nextLine();
            pourquoi_answer = scanner.nextLine();
        }
    }
}