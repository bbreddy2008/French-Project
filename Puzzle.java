import java.util.*;

public class Puzzle {
    //puzzle id number
    private int id;
    
    //arrays to hold each type of answer
    private String[] qui;
    private String[] quoi;
    private String[] ou;
    private String[] pourquoi;

    //constructor for a puzzle 
    public Puzzle(int numb, String[][] arrays){  
        id = numb;
        int size;
        // sets size of array dependent on id
        if (id <= 50){ 
            size = 3;
        }  
        else {
            size = 4;
        }
        
       // creates question arrays
        qui = new String[size];
        quoi = new String[size];
        ou = new String[size];
        
       // creates pourquoi only if size is 4
        if (size == 4) {
            pourquoi = new String[size];
        }
            
        // fills arrays with question arrays
        for (int i = 0; i < size; i++) {
            qui[i]= arrays[i][0];
            quoi[i] = arrays[i][1];
            ou[i] = arrays[i][2];
            if (size == 4) {
                pourquoi[i] = arrays[i][3];
            }
        }
    }

    //returns puzzle id
    public int puzzleID(){
        return id;
    }

    // only the first value
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

    public String[] getOptions(String type) { // returns the options for a given type
        if (type.toLowerCase().equals("qui")) {
            return Arrays.copyOf(qui, qui.length);
        }
        if (type.toLowerCase().equals("quoi")) {
            return Arrays.copyOf(quoi, quoi.length);
        }
        if (type.toLowerCase().equals("ou")) {
            return Arrays.copyOf(ou, ou.length);
        }
        if (type.toLowerCase().equals("pourquoi")) {
            if (pourquoi != null) {
                return Arrays.copyOf(pourquoi, pourquoi.length);
            } else {
                return new String[0];
            }
        }
        return new String[0]; // returns empty array of options
    }

    // prints all the correct puzzle answers; all located at index 1
    public String print(){
        return qui[1] + " " + quoi[1] + " " + ou[1] + " " + pourquoi[1];
    }

}
