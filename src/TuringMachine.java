import java.util.ArrayList;

/**
 * Created by andre on 9/28/2016.
 */
public class TuringMachine {
    private int state;
    private ArrayList<Tape> tapes;
    private ArrayList<Rule> Rules;

    public TuringMachine(ArrayList<Rule> rules, String defaul) {
        state = 0;
        tapes = new ArrayList<>();
        int numTapes = rules.get(0).getPreCondition().length;
        for(int i = 0; i < numTapes; i++){
            Tape tape = new Tape(defaul);
            tapes.add(tape);
        }
        Rules = rules;
    }

    public TuringMachine(ArrayList<Rule> rules, ArrayList<Tape> feedTapes, String defaul) {
        this.tapes = tapes;
        int numTapes = rules.get(0).getPreCondition().length;
        tapes = feedTapes;
        for(int i = tapes.size(); i < numTapes; i++){
            Tape tape = new Tape(defaul);
            tapes.add(tape);
        }
        Rules = rules;
    }

    public Rule checkRules(){
        for(Rule rule : Rules){
            if(rule.checkState(state, tapes)){
                System.out.println(rule);
                return rule;
            }
        }
        System.out.println("Failed to find rule for current state.");
        return new Rule();
    }

    public void nextStep(){
        state = checkRules().evaluateRule(tapes);

    }

    public void runMachine(){
        for(Rule r : Rules){
            System.out.println(r);
        }
        while(state != -1){
            nextStep();
        }
    }
}
