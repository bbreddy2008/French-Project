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

    //constructor 
    public Puzzle(int numb, String[][] arrays){  
        id = numb;
        if (id <= 50){
            qui = new String[3];
            quoi = new String[3];
            ou = new String[3];

            for (int i = 0; i < 3; i++){
                qui[i] = arrays[i][0];
                quoi[i] = arrays[i][1];
                ou[i] = arrays[i][2];
            }

        } else {
            qui = new String[4];
            quoi = new String[4];
            ou = new String[4];
            pourquoi = new String[4];

            for (int i = 0; i < 4; i++){
                qui[i] = arrays[i][0];
                quoi[i] = arrays[i][1];
                ou[i] = arrays[i][2];
                pourquoi[i] = arrays[i][3];
            }


        }
    }

    //methods
    public int puzzleID(){
        return id;
    }

    //Only the first value
    // puzzles[5].getCorrect()
    public String[] getCorrect(){
        if (id <= 50){
            String[] send = {qui[0], quoi[0], ou[0]};
            return send;
        } else {
            String[] send = {qui[0], quoi[0], ou[0], pourquoi[0]};
            return send;
        }
    }

    //All options
    public String[] getOptions(String[] array){
        String[] options = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            options[i] = array[i];
        }
        return options;
    }

    public String print(){
        return qui[1] + " " + quoi[1] + " " + ou[1] + " " + pourquoi[1];
    }

}
