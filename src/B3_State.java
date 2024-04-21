import java.util.ArrayList;

public class B3_State {
    private String name;
    //private ArrayList<B3_Transition> transitions;
    private boolean isInitial;
    private boolean isFinal;

    public B3_State(){

    }
    public B3_State(String name, boolean isInitial, boolean isFinal) {
        //transitions = new ArrayList<>();
        this.name = name;
        this.isInitial = isInitial;
        this.isFinal = isFinal;
    }

       public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInitial() {
        return isInitial;
    }

    public void setInitial(boolean initial) {
        isInitial = initial;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public void setTransitions(ArrayList<B3_Transition> transitions) {
        //this.transitions = transitions;
    }

    public void addTransition(B3_Transition transition){
        //transitions.add(transition);
    }

    /*
    //Verifie que l'etat n'a pas plus d'une transition avec le meme symbole
    public boolean hasUniqueTransitionsSymbol() {
        for (B3_Transition t1 : transitions) {
            for (B3_Transition t2 : transitions) {
                if (t1 != t2 && t1.getSymbol() == t2.getSymbol()) {
                    return false;
                }
            }
        }
        return true;
    }*/
}
