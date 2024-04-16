import javax.swing.*;
import java.util.ArrayList;

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

    public void addStates(State state) {
        states.add(state);
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public void addFinalState(State state) {
        if (state.isFinal()) {
            finalStates.add(state);
        } else {
            System.out.println("Votre etat n'est pas final");
        }
    }

    public void addInitialStates(State state) {
        if (state.isInitial()) {
            initialStates.add(state);
        } else {
            System.out.println("Votre etat n'est pas initial");
        }
    }

    public void addAlphabet(Character letter) {
        alphabet.add(letter);
    }

    // Vérifie si l'automate n'a qu'une entree et que cette entree n'a pas plus d'une transition avec le meme symbole
    public boolean isDeterminist() {
        if (initialStates.size() == 1 && initialStates.getFirst().hasUniqueTransitionsSymbol()) {
            System.out.println("L'automate est deterministe");
            return true;
        } else {
            System.out.println("L'automate n'est pas deterministe");
            return false;
        }
    }

    //vérifie s'il n'y a qu'une seule entrée et si oui, verifie que l'entrée ne reçoit pas de transition
    public boolean isStandart() {
        if (initialStates.size() == 1) {
            for (Transition transition : transitions) {
                if (transition.getToState().equals(initialStates.getFirst())) {
                    System.out.println("L'etat initial recoit une transition");
                    System.out.println("L'automate n'est pas standart");
                    return false;
                }
            }
            System.out.println("L'automate est standart");
            return true;
        }
        System.out.println("L'automate n'est pas standart");
        return false;
    }

    public void displayAutomaton(){
        Object[][] transitionTable = new Object[1+states.size()][2+alphabet.size()];

        transitionTable[0][0] = "Type";
        transitionTable[0][1] = "Etat";
        for (int j = 0; j < alphabet.size(); j++) {
            transitionTable[0][j+2] = alphabet.get(j);
        }

        for (int i = 0; i < states.size(); i++){
            if(states.get(i).isInitial()){
                transitionTable[i+1][0] = 'E';
            }else if(states.get(i).isFinal()){
                transitionTable[i+1][0] = 'S';
            }else{
                transitionTable[i+1][0] = " ";
            }
            transitionTable[i+1][1] = states.get(i).getName();
        }

        for (int i = 0; i < states.size(); i++){
            for (int j = 0; j < alphabet.size(); j++){
                transitionTable[i+1][j+2] = states.get(i)
            }
        }


        String[] entetes = {"Type", "Etat", "a", "b", "c"};
        JTable table = new JTable(transitionTable, entetes);

        // Afficher le tableau dans une fenêtre
        JFrame frame = new JFrame();
        frame.add(table);
        frame.setSize(1200, 400);
        frame.setVisible(true);

    }

}
