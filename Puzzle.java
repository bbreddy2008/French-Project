import java.util.ArrayList;
import java.util.*;

public class Puzzle {
    //puzzle id number
    private int id;
    
    //Strings of each asnwer type 
    private String qui_answer;
    private String quoi_answer;
    private String ou_answer;
    private String pourquoi_answer;
    
    //arrays to hold each type of answer
    private String[] qui;
    private String[] quoi;
    private String[] ou;
    private String[] pourquoi;

    //constructor for a puzzle 
    public Puzzle(int numb, String[][] arrays){  
        id = numb;
        //if the id number is less than 50...
        if (id <= 50){ // creates arrays of size 3
            qui = new String[3];
            quoi = new String[3];
            ou = new String[3];

            // fills array with answers
            for (int i = 0; i < 3; i++){
                qui[i] = arrays[i][0];
                quoi[i] = arrays[i][1];
                ou[i] = arrays[i][2];
            }

        } else { //if the id number is 51 and above 
            // creates array of size 4
            qui = new String[4];
            quoi = new String[4];
            ou = new String[4];
            pourquoi = new String[4];

            // fills array with answers
            for (int i = 0; i < 4; i++){
                qui[i] = arrays[i][0];
                quoi[i] = arrays[i][1];
                ou[i] = arrays[i][2];
                pourquoi[i] = arrays[i][3];
            }


        }
    }

    //returns puzzle id
    public int puzzleID(){
        return id;
    }

    // Only the first value
    // returns the correct answers for checking
    public String[] getCorrect(){
        if (id <= 50){
            String[] send = {qui[0], quoi[0], ou[0]};
            return send;
        } else {
            String[] send = {qui[0], quoi[0], ou[0], pourquoi[0]};
            return send;
        }
    }

    //Accesses all of the possible option answers for the puzzle 
    public String[] getOptions(String[] array){
        String[] options = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            options[i] = array[i];
        }
        return options;
    }

    //prints all the correct puzzle answers; all located at index 1
    public String print(){
        return qui[1] + " " + quoi[1] + " " + ou[1] + " " + pourquoi[1];
    }

}
