import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Automaton {
    private ArrayList<State> states;
    private ArrayList<State> finalStates;
    private ArrayList<State> initialStates;
    private ArrayList<Character> alphabet;
    private ArrayList<Transition> transitions;

    public Automaton() {
        states = new ArrayList<>();
        finalStates = new ArrayList<>();
        initialStates = new ArrayList<>();
        alphabet = new ArrayList<>();
        transitions = new ArrayList<>();
    }

    public Automaton(ArrayList<State> states, ArrayList<State> finalStates, ArrayList<State> initialStates, ArrayList<Character> alphabet, ArrayList<Transition> transitions) {
        states = new ArrayList<>();
        finalStates = new ArrayList<>();
        initialStates = new ArrayList<>();
        alphabet = new ArrayList<>();
        transitions = new ArrayList<>();
        this.states = states;
        this.finalStates = finalStates;
        this.initialStates = initialStates;
        this.alphabet = alphabet;
        this.transitions = transitions;
    }


    public void setStates(ArrayList<State> states) {
        this.states = states;
    }

    public void addState(State state) {
        states.add(state);
    }

    public ArrayList<State> getStates() {
        return states;
    }


    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public ArrayList<Character> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(ArrayList<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public void addAlphabet(Character letter) {
        alphabet.add(letter);
    }


    public ArrayList<State> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(ArrayList<State> finalStates) {
        this.finalStates = finalStates;
    }

    public void addFinalState(State state) {
        if (state.isFinal()) {
            finalStates.add(state);
        } else {
            System.out.println("Votre etat n'est pas final");
        }
    }

    public ArrayList<State> getInitialStates() {
        return initialStates;
    }

    public void setInitialState(ArrayList<State> initialStates) {
        this.initialStates = initialStates;
    }

    public void addInitialState(State state) {
        if (state.isInitial()) {
            initialStates.add(state);
        } else {
            System.out.println("Votre etat n'est pas initial");
        }
    }

    // Vérifie si l'automate n'a qu'une entree et que cette entree n'a pas plus d'une transition avec le meme symbole
    public boolean isDeterminist() {
        if (initialStates.size() == 1 && initialStates.getFirst().hasUniqueTransitionsSymbol()) {
            System.out.println("L'automate est deterministe");
            return true;
        } else {
            if (initialStates.size() != 1 && initialStates.getFirst().hasUniqueTransitionsSymbol()) {
                System.out.println("L'automate n'est pas deterministe car il possède plus d'un état initial.");
            } else if (initialStates.size() == 1 && !initialStates.getFirst().hasUniqueTransitionsSymbol()) {
                System.out.println("L'automate n'est pas deterministe car il possède plus d'une transition avec le même symbole.");
            } else {
                System.out.println("L'automate n'est pas deterministe car il possède plus d'un état initial et il possède plus d'une transition avec le même symbole.");
            }
            return false;
        }
    }

    //vérifie s'il n'y a qu'une seule entrée et si oui, verifie que l'entrée ne reçoit pas de transition
    public boolean isStandard() {
        if (initialStates.size() == 1) {
            for (Transition transition : transitions) {
                if (transition.getToState().equals(initialStates.getFirst())) {
                    System.out.println("L'etat initial recoit une transition donc il n'est pas standard");
                    return false;
                }
            }
            System.out.println("L'automate est standard");
            return true;
        }
        System.out.println("L'automate n'est pas standard");
        return false;
    }

    public boolean hasTransition(State state, char symbol) {
        // Vérifier s'il existe une transition depuis l'état actuel avec le symbole donné
        for (Transition transition : this.getTransitions()) {
            if (transition.getFromState().equals(state) && transition.getSymbol() == symbol) {
                return true;
            }
        }
        // S'il n'y a pas de transition pour le symbole donné, l'automate n'est pas complet
        return false;
    }

    public boolean isComplete() {
        for (State state : states) {
            for (char symbol : alphabet) {
                if (!hasTransition(state, symbol)) {
                    System.out.println("L'automate n'est pas complet.");
                    return false;
                }
            }
        }
        System.out.println("L'automate est complet.");
        return true;
    }

    public Automaton completeTransitions(Automaton automaton) {
        // On utilise un set de char afin d'éviter les doublons qui contient l'alphabet de l'automate
        Set<Character> alphabetSet = new HashSet<>(automaton.getAlphabet());
        //On parcourt toutes les transitions de chaque état de l'automate
        for (State state : states) {
            for (Transition transition : transitions) {
                if (transition.getFromState().equals(state)){
                    //On ne laisse que les lettres qui ne correspondent à aucune transition dans le set
                    alphabetSet.remove(transition.getSymbol());
                }
            }
            for (char symbol : alphabetSet) {
                State trashState = new State();
                trashState.setName("Poubelle");
                automaton.addState(trashState);
                automaton.addTransition(new Transition(state, trashState, symbol));
            }
            alphabetSet.clear();
        }
        return automaton;
    }

    public Automaton standardizeAutomaton() {
        if (this.isStandard()) {
            System.out.println("L'automate est déjà standard.");
        } else {
            Automaton standardizedAutomaton = new Automaton();
            standardizedAutomaton.setAlphabet(new ArrayList<>(this.getAlphabet()));
            standardizedAutomaton.setStates(new ArrayList<>(this.getStates()));

            standardizedAutomaton.setInitialState(new ArrayList<>(this.getInitialStates()));

            // Création d'un nouvel état initial s'il y a plusieurs états initiaux
            if (this.getInitialStates().size() != 1) {
                State newInitialState = new State();
                newInitialState.setName("newInitialState"); // Nom de l'état initial standardisé
                newInitialState.setInitial(true);
                standardizedAutomaton.getStates().add(newInitialState);
                standardizedAutomaton.getInitialStates().clear();
                standardizedAutomaton.getInitialStates().add(newInitialState);
            }

            // Copie des états
            for (State state : this.getStates()) {
                State newState = new State();
                newState.setName(state.getName());
                newState.setFinal(state.isFinal());
                standardizedAutomaton.getStates().add(newState);
            }

            completeTransitions(standardizedAutomaton);

            System.out.println("Automate standardisé !");
            return standardizedAutomaton;
        }
        return null;
    }
}


