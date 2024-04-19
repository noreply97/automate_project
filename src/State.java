import java.util.ArrayList;

public class State {
    private String name;
    private ArrayList<Transition> transitions;
    private boolean isInitial;
    private boolean isFinal;

    public State() {
    }

    public State(final String name,final boolean isInitial,final boolean isFinal) {
        transitions = new ArrayList<>();
        this.name = name;
        this.isInitial = isInitial;
        this.isFinal = isFinal;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isInitial() {
        return this.isInitial;
    }

    public void setInitial(final boolean initial) {
        this.isInitial = initial;
    }

    public boolean isFinal() {
        return this.isFinal;
    }

    public void setFinal(final boolean aFinal) {
        this.isFinal = aFinal;
    }

    public void addTransition(final Transition transition){
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
