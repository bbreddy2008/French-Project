import java.util.ArrayList;

public class Puzzle {
    private int id;
    //arrayLists to hold each type of answer
    private ArrayList<String> qui = new ArrayList<String>();
    private ArrayList<String> quoi= new ArrayList<String>();
    private ArrayList<String> ou = new ArrayList<String>();
    private ArrayList<String> pourquoi = new ArrayList<String>(); 

    //constructor 
    public Puzzle(int numb){  
        id = numb;
    }

    //methods 
    public int puzzleID(){
        return id;
    }

    //Only the first value
    public String getCorrect(){

    }

    //All options
    public String getOptions(){

    }

    //Input from the interface
    public String getInput(){

    }

    //Adds options for Qui
    public void addQui(String name){

    }

    //Adds options for Quoi
    public void addQuoi(String item){

    }

    //Adds options for Ou
    public void addOu(String place){

    }

    //Adds options for Pourquoi
    public void addPoruoi(String reason){

    }
}
