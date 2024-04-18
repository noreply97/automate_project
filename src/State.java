import java.util.ArrayList;

public class State {
    private String name;
    private ArrayList<Transition> transitions;
    private boolean isInitial;
    private boolean isFinal;

    public State() {
    }

    public State(String name, boolean isInitial, boolean isFinal) {
        transitions = new ArrayList<>();
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

    public void addTransition(Transition transition){
        transitions.add(transition);
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    //Verifie que l'etat n'a pas plus d'une transition avec le meme symbole
    public boolean hasUniqueTransitionsSymbol() {
        for (Transition t1 : transitions) {
            for (Transition t2 : transitions) {
                if (t1 != t2 && t1.getSymbol() == t2.getSymbol()) {
                    return false;
                }
            }
        }
        return true;
    }
}
