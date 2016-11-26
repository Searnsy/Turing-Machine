import java.util.ArrayList;

/**
 * Created by andre on 9/28/2016.
 */
public class Tape {
    private ArrayList<String> tape;
    private String defaul;
    private int pos;

    public Tape(String defaul) {
        this.defaul = defaul;
        tape = new ArrayList<>();
        tape.add(defaul);
        pos = 0;
    }

    public Tape(ArrayList<String> tape, String defaul, int pos){
        this.tape = tape;
        this.defaul = defaul;
        this.pos = pos;
    }

    public String getCondition(){
        return tape.get(pos);
    }

    public ArrayList<String> getTape(){
        return tape;
    }

    public void addFront(){
        tape.add(0, defaul);
    }

    public void addEnd(){
        tape.add(defaul);
    }

    public void moveLeft(){
        pos -= 1;
        if(pos < 0){
            tape.add(0, defaul);
            pos = 0;
        }
    }

    public void moveRight(){
        pos += 1;
        if(pos > tape.size() - 1){
            tape.add(defaul);
        }
    }

    public void changeCondition(String condition){
        if(!condition.equals("*")) {
            tape.set(pos, condition);
        }
    }

    public String toString(){
        String out = "";
        for(int i = 0; i < tape.size(); i++){
            if (i == pos) {
                out += "!";
            }
            out += tape.get(i).toString() + " | ";
        }
        return out;
    }
}
