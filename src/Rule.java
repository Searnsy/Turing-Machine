import java.util.ArrayList;

/**
 * Created by andre on 9/28/2016.
 */
public class Rule {
    private int preState;
    private String[] preCondition;
    private String[] postCondition;
    private char[] shift;
    private int postState;

    public Rule(int preState, String[] preCondition, String[] postCondition, char[] shift, int postState) {
        this.preState = preState;
        this.preCondition = preCondition;
        this.postCondition = postCondition;
        this.shift = shift;
        this.postState = postState;
    }

    public Rule() {
    }

    public boolean checkState(int state, ArrayList<Tape> tapes){
        boolean isState = true;
        if(preState != state){
            return false;
        }
        for(int l = 0; l < tapes.size(); l++){
            if(!(tapes.get(l).getCondition().equals(preCondition[l]))){
                isState = false;
            }
            if(preCondition[l].equals("*")){
                isState = true;
            }
            if(!isState){
                return false;
            }
        }
        return isState;
    }

    public int evaluateRule(ArrayList<Tape> tapes){
        for(int i = 0; i < tapes.size(); i++){
            tapes.get(i).changeCondition(postCondition[i]);
            if(shift[i] == 'L'){
                tapes.get(i).moveLeft();
            }
            else if(shift[i] == 'R'){
                tapes.get(i).moveRight();
            }
            System.out.println(tapes.get(i));
        }
        return postState;
    }

    public String toString(){
        String out = "(" + preState + ", " + "{";
        for(int i = 0; i < preCondition.length - 1; i++){
            out += preCondition[i] + ", ";
        }
        out += preCondition[preCondition.length -1] + "}, {";
        for(int l = 0; l < postCondition.length - 1; l++){
            out += postCondition[l] + ", ";
        }
        out += postCondition[postCondition.length -1] + "}, {";
        for(int m = 0; m < shift.length - 1; m++){
            out += shift[m] + ", ";
        }
        out += shift[shift.length -1] + "}, " + postState + ")";
        return out;
    }

    public String[] getPreCondition(){
        return preCondition;
    }
}
