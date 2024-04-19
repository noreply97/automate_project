import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
                if (transition.getFromState().equals(state)) {
                    //On ne laisse que les lettres qui ne correspondent à aucune transition dans le set
                    alphabetSet.remove(transition.getSymbol());
                }
            }
            for (char symbol : alphabetSet) {
                State trashState = new State();
                trashState.setName("P");
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
                newInitialState.setName("NQ0"); // Nom de l'état initial standardisé
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

    public Automaton determinizeAutomaton() {
        Automaton determinizedAutomaton = new Automaton();

    }

    /* Fonction nécéssaire pour la méthode suivante*/
    public State getStateByName(String name) {
        for (State state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null; // Retourne null si aucun état avec ce nom n'est trouvé
    }

    public static Automaton readFromFile(String filePath) {
        // Initialisation
        Automaton automaton = new Automaton();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int alphabetSize = Integer.parseInt(br.readLine());
            int numberOfStates = Integer.parseInt(br.readLine());

            //Ajouter les symboles
            for (int i = 0; i < alphabetSize; i++) {
                char symbol = (char) ('a' + i);
                automaton.addAlphabet(symbol);
            }

            // Lire et ajouter les états initiaux
            String[] initialStatesLine = br.readLine().split(" ");
            int numberOfInitialStates = Integer.parseInt(initialStatesLine[0]);
            for (int i = 1 ; i <= numberOfInitialStates; i++) {
                int initialStateNumber = Integer.parseInt(initialStatesLine[i]);
                State initialState = new State("Q" + initialStateNumber, true, false);
                automaton.addInitialState(initialState);
                automaton.addState(initialState);
            }

            // Lire et ajouter les états terminaux
            String[] finalStatesLine = br.readLine().split(" ");
            int numberOfFinalStates = Integer.parseInt(finalStatesLine[0]);
            for (int i = 1; i <= numberOfFinalStates; i++) {
                int finalStateNumber = Integer.parseInt(finalStatesLine[i]);
                State finalState = new State("Q" + finalStateNumber, false, true);
                automaton.addFinalState(finalState);
                automaton.addState(finalState);
            }

            // Lire et ajouter les transitions
            int numberOfTransitions = Integer.parseInt(br.readLine());
            for (int i = 0; i < numberOfTransitions; i++) {
                String transitionLine = br.readLine().trim();
                char fromStateName = transitionLine.charAt(0);
                char symbol = transitionLine.charAt(1);
                char toStateName = transitionLine.charAt(2);

                State fromState = automaton.getStateByName("Q" + fromStateName);
                if (fromState == null) {
                    fromState = new State("Q" + fromStateName, false, false);
                    automaton.addState(fromState);
                }

                State toState = automaton.getStateByName("Q" + toStateName);
                if (toState == null) {
                    toState = new State("Q" + toStateName, false, false);
                    automaton.addState(toState);
                }

                Transition transition = new Transition(fromState, toState, symbol);
                automaton.addTransition(transition);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return automaton;
    }

    public void displayAutomaton() {
        // Calcul des largeurs de colonnes basées sur le contenu
        int[] columnWidths = new int[this.alphabet.size() + 2]; // +2 pour les colonnes des états et des états initiaux/finaux
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = 1; // Largeur minimale
        }

        // Parcours des transitions pour trouver la longueur maximale pour chaque colonne
        for (State state : states) {
            // Longueur du nom d'état
            columnWidths[0] = Math.max(columnWidths[0], state.getName().length() + 2); // +2 pour le centrage

            for (Character symbol : alphabet) {
                ArrayList<String> toStates = new ArrayList<>();
                for (Transition transition : transitions) {
                    if (transition.fromState().equals(state) && transition.getSymbol() == symbol) {
                        toStates.add(transition.getToState().getName());
                    }
                }
                int maxLength = toStates.stream().mapToInt(String::length).max().orElse(0);
                columnWidths[alphabet.indexOf(symbol) + 1] = Math.max(columnWidths[alphabet.indexOf(symbol) + 1], maxLength + 2); // +2 pour le centrage
            }
        }

        // Affichage du tableau avec les colonnes alignées
        // Affichage de la première ligne avec les symboles de l'alphabet
        System.out.print(center("State",columnWidths[0]) + "|");
        System.out.print(" I/F | ");
        for (Character symbol : alphabet) {
            System.out.print(center((" " + symbol + " "), columnWidths[alphabet.indexOf(symbol) + 1]) + "|");
        }
        System.out.println();

        // Affichage des transitions pour chaque état
        for (State state : states) {
            System.out.print("   "+state.getName()+center("",columnWidths[0]) + "|");
            // Indiquer si l'état est initial ou final
            if (initialStates.contains(state)) {
                System.out.print(center(" I ", columnWidths[alphabet.size() + 1]) + "|");
            } else if (finalStates.contains(state)) {
                System.out.print(center(" F ",columnWidths[alphabet.size() + 1]) + "|");
            } else {
                System.out.print(center("   ",columnWidths[alphabet.size() + 1]) + "|");
            }
            // Affichage des transitions pour chaque symbole de l'alphabet
            for (Character symbol : alphabet) {
                ArrayList<String> toStates = new ArrayList<>();
                for (Transition transition : transitions) {
                    if (transition.fromState().equals(state) && transition.getSymbol() == symbol) {
                        toStates.add(transition.getToState().getName());
                    }
                }
                if (!toStates.isEmpty()) {
                    System.out.print(center((" " + String.join(",", toStates) + " "), columnWidths[alphabet.indexOf(symbol) + 1]) + "|");
                } else {
                    System.out.print(center("  - ", columnWidths[alphabet.indexOf(symbol) + 1]) + "|");
                }
            }
            System.out.println();
        }
    }

    // Méthode pour centrer une chaîne dans un espace donné
    public static String center(String s, int length) {
        return String.format("%-" + length + "s%s%-" + length + "s", "", s, "");
    }


public Automaton createComplementAutomaton() {
    Automaton complementAutomaton = new Automaton();

    // Copie des états, transitions et alphabet de l'automate d'origine
    complementAutomaton.setStates(new ArrayList<>(states));
    complementAutomaton.setTransitions(new ArrayList<>(transitions));
    complementAutomaton.setAlphabet(new ArrayList<>(alphabet));

    // Copie des états initiaux de l'automate d'origine
    for (State initialState : initialStates) {
        complementAutomaton.addInitialState(initialState);
    }

    // Inversion des états finaux et non finaux
    for (State state : states) {
        if (finalStates.contains(state)) {
            state.setFinal(false);
        } else {
            state.setFinal(true);
            complementAutomaton.finalStates.add(state);
        }
    }

    return complementAutomaton;
}
}