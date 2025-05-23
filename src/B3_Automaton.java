import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class B3_Automaton {
    private ArrayList<B3_State> states;
    private ArrayList<B3_State> finalStates;
    private ArrayList<B3_State> initialStates;
    private ArrayList<Character> alphabet;
    private ArrayList<B3_Transition> transitions;

    public B3_Automaton() {
        states = new ArrayList<>();
        finalStates = new ArrayList<>();
        initialStates = new ArrayList<>();
        alphabet = new ArrayList<>();
        transitions = new ArrayList<>();
    }

    public B3_Automaton(ArrayList<B3_State> states, ArrayList<B3_State> finalStates, ArrayList<B3_State> initialStates, ArrayList<Character> alphabet, ArrayList<B3_Transition> transitions) {
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


    public void setStates(ArrayList<B3_State> states) {
        this.states = states;
    }

    public void addState(B3_State state) {
        states.add(state);
    }

    public ArrayList<B3_State> getStates() {
        return states;
    }


    public void setTransitions(ArrayList<B3_Transition> transitions) {
        this.transitions = transitions;
    }

    public ArrayList<B3_Transition> getTransitions() {
        return transitions;
    }

    public void addTransition(B3_Transition transition) {
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


    public ArrayList<B3_State> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(ArrayList<B3_State> finalStates) {
        this.finalStates = finalStates;
    }

    public void addFinalState(B3_State state) {
        if (state.isFinal()) {
            finalStates.add(state);
        } else {
            System.out.println("Votre etat n'est pas final");
        }
    }

    public ArrayList<B3_State> getInitialStates() {
        return initialStates;
    }

    public void setInitialState(ArrayList<B3_State> initialStates) {
        this.initialStates = initialStates;
    }

    public void addInitialState(B3_State state) {
        if (state.isInitial()) {
            initialStates.add(state);
        } else {
            System.out.println("Votre etat n'est pas initial");
        }
    }

    // Vérifie si l'automate n'a qu'une entree et que cette entree n'a pas plus d'une transition avec le meme symbole
    public boolean isDeterminist() {
        if (initialStates.size() == 1 && hasUniqueTransitionsSymbol(initialStates.getFirst())) {
            System.out.println("L'automate est deterministe");
            return true;
        } else {
            if (initialStates.size() != 1 && hasUniqueTransitionsSymbol(initialStates.getFirst())) {
                System.out.println("L'automate n'est pas deterministe car il possède plus d'un état initial.");
            } else if (initialStates.size() == 1 && !hasUniqueTransitionsSymbol(initialStates.getFirst())) {
                System.out.println("L'automate n'est pas deterministe car il possède plus d'une transition avec le même symbole.");
            } else {
                System.out.println("L'automate n'est pas deterministe car il possède plus d'un état initial et il possède plus d'une transition avec le même symbole.");
            }
            return false;
        }
    }
    public boolean isDeterministTest() {
        if (initialStates.size() == 1 && hasUniqueTransitionsSymbol(initialStates.getFirst())) {
            return true;
        } else {
            return false;
        }
    }

    //vérifie s'il n'y a qu'une seule entrée et si oui, verifie que l'entrée ne reçoit pas de transition
    public boolean isStandard() {
        if (initialStates.size() == 1) {
            for (B3_Transition transition : transitions) {
                if (transition.getToState().equals(initialStates.getFirst())) {
                    System.out.println("L'etat initial recoit une transition donc l'automate n'est pas standard");
                    return false;
                }
            }
            System.out.println("L'automate est standard");
            return true;
        }
        System.out.println("L'automate n'est pas standard");
        return false;
    }
    public boolean isStandardTest() {
        if (initialStates.size() == 1) {
            for (B3_Transition transition : transitions) {
                if (transition.getToState().equals(initialStates.getFirst())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean hasUniqueTransitionsSymbol(B3_State state) {
        Set<Character> symbols = new HashSet<>();
        for (B3_Transition transition : transitions) {
            if (transition.getFromState().equals(state)) {
                if (symbols.contains(transition.getSymbol())) {
                    return false; // Il y a déjà une transition avec ce symbole
                } else {
                    symbols.add(transition.getSymbol());
                }
            }
        }
        return true;
    }

    public boolean hasTransition(B3_State state, char symbol) {
        // Vérifier s'il existe une transition depuis l'état actuel avec le symbole donné
        for (B3_Transition transition : this.getTransitions()) {
            if (transition.getFromState().equals(state) && transition.getSymbol() == symbol) {
                return true;
            }
        }
        // Sinon on retourne false
        return false;
    }

    public boolean isComplete() {
        for (B3_State state : states) {
            for (char symbol : alphabet) {
                if (!hasTransition(state, symbol)) {
                    System.out.println("L'automate n'est pas complet car il n'a pas de transitions pour chaque état avec chaque symbole.");
                    return false;
                }
            }
        }
        System.out.println("L'automate est complet.");
        return true;
    }
    public boolean isCompleteTest() {
        for (B3_State state : states) {
            for (char symbol : alphabet) {
                if (!hasTransition(state, symbol)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void whatIsIt(){
        this.isStandard();
        this.isDeterminist();
        this.isComplete();
    }

    public B3_Automaton completeAutomaton() {
        if(isCompleteTest()){
            System.out.println("L'automate est déjà complet.");
            return null;
        }else {
            // Création d'un nouvel automate qui sera l'automate complété
            B3_Automaton completedAutomaton = new B3_Automaton();

            // Copie des informations de l'automate à compléter dans le nouveau
            completedAutomaton.states = new ArrayList<>(this.states);
            completedAutomaton.finalStates = new ArrayList<>(this.finalStates);
            completedAutomaton.initialStates = new ArrayList<>(this.initialStates);
            completedAutomaton.alphabet = new ArrayList<>(this.alphabet);
            completedAutomaton.transitions = new ArrayList<>(this.transitions);

            // On crée un état poubelle
            B3_State trashState = new B3_State();
            trashState.setName("QP");
            completedAutomaton.addState(trashState);

            // On ajoute les transitions manquantes vers l'état poubelle
            for (B3_State currentState : completedAutomaton.states) {
                for (char symbol : completedAutomaton.alphabet) {
                    if (!completedAutomaton.hasTransition(currentState, symbol)) {
                        B3_Transition trashTransition = new B3_Transition(currentState, trashState, symbol);
                        completedAutomaton.transitions.add(trashTransition);
                    }
                }
            }
            System.out.println("Automate complété");
            return completedAutomaton;
        }
    }

    public B3_Automaton standardizeAutomaton() {
        B3_Automaton standardizedAutomaton= new B3_Automaton();
        if (this.isStandardTest()) {
            System.out.println("L'automate est déjà standard.");
            return null;
        } else {
            standardizedAutomaton = this.standardize();
            System.out.println("L'automate a été standardisé !");
            return standardizedAutomaton;
        }

    }

    private B3_Automaton standardize(){
        B3_Automaton standardizedAutomaton = new B3_Automaton();

        // Copie des états, transitions et alphabet de l'automate d'origine
        standardizedAutomaton.setStates(new ArrayList<>(states));
        standardizedAutomaton.setTransitions(new ArrayList<>(transitions));
        standardizedAutomaton.setAlphabet(new ArrayList<>(alphabet));
        standardizedAutomaton.setFinalStates(new ArrayList<>(finalStates));

        // Création de l'état initial standardisé
        B3_State newInitialState = new B3_State();
        newInitialState.setName("QI"); // Nom de l'état initial standardisé
        newInitialState.setInitial(true);
        standardizedAutomaton.addInitialState(newInitialState);
        standardizedAutomaton.addState(newInitialState);

        for (B3_Transition transitions : this.transitions){
            if(transitions.fromState().equals(initialStates.getFirst())){
                standardizedAutomaton.addTransition(new B3_Transition(newInitialState, transitions.getToState(), transitions.getSymbol()));
            }
        }
        return standardizedAutomaton;
    }

    // Fonction nécéssaire pour la méthode suivante
    public B3_State getStateByName(String name) {
        for (B3_State state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null; // Retourne null si aucun état avec ce nom n'est trouvé
    }

    public static B3_Automaton readFromFile(String filePath) {
        // Initialisation
        B3_Automaton automaton = new B3_Automaton();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int alphabetSize = Integer.parseInt(br.readLine());
            int numberOfStates = Integer.parseInt(br.readLine());

            //Ajouter les symboles
            for(int i=0;i<alphabetSize;i++){
                char symbol =(char) ('a'+i);
                automaton.addAlphabet(symbol);
            }

            // Lire et ajouter les états initiaux
            String[] initialStatesLine = br.readLine().split(" ");
            int numberOfInitialStates = Integer.parseInt(initialStatesLine[0]);
            for (int i = 1 ; i <= numberOfInitialStates; i++) {
                int initialStateNumber = Integer.parseInt(initialStatesLine[i]);
                B3_State initialState = new B3_State("Q" + initialStateNumber, true, false);
                automaton.addInitialState(initialState);
                automaton.addState(initialState);
            }

            // Lire et ajouter les états terminaux
            String[] finalStatesLine = br.readLine().split(" ");
            int numberOfFinalStates = Integer.parseInt(finalStatesLine[0]);
            for (int i = 1; i <= numberOfFinalStates; i++) {
                int finalStateNumber = Integer.parseInt(finalStatesLine[i]);
                B3_State finalState = new B3_State("Q" + finalStateNumber, false, true);
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

                B3_State fromState = automaton.getStateByName("Q" + fromStateName);
                if (fromState == null) {
                    fromState = new B3_State("Q" + fromStateName, false, false);
                    automaton.addState(fromState);
                }

                B3_State toState = automaton.getStateByName("Q" + toStateName);
                if (toState == null) {
                    toState = new B3_State("Q" + toStateName, false, false);
                    automaton.addState(toState);
                }

                B3_Transition transition = new B3_Transition(fromState, toState, symbol);
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
        for (B3_State state : states) {
            // Longueur du nom d'état
            columnWidths[0] = Math.max(columnWidths[0], state.getName().length() + 2); // +2 pour le centrage

            for (Character symbol : alphabet) {
                ArrayList<String> toStates = new ArrayList<>();
                for (B3_Transition transition : transitions) {
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
        for (B3_State state : states) {
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
                for (B3_Transition transition : transitions) {
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


    public B3_Automaton createComplementAutomaton() {
        if(isCompleteTest() && isDeterministTest()) {
            B3_Automaton complementAutomaton = new B3_Automaton();

            // Copie des états, transitions et alphabet de l'automate d'origine
            complementAutomaton.setStates(new ArrayList<>(states));
            complementAutomaton.setTransitions(new ArrayList<>(transitions));
            complementAutomaton.setAlphabet(new ArrayList<>(alphabet));

            // Copie des états initiaux de l'automate d'origine
            for (B3_State initialState : initialStates) {
                complementAutomaton.addInitialState(initialState);
            }

            // Inversion des états finaux et non finaux
            for (B3_State state : states) {
                if (finalStates.contains(state)) {
                    state.setFinal(false);
                } else {
                    state.setFinal(true);
                    complementAutomaton.finalStates.add(state);
                }
            }
            return complementAutomaton;
        }
        return null;
    }


    public B3_Automaton determinizeAutomaton() {
        if (this.isDeterminist()) {
            System.out.println("L'automate est déjà déterministe.");
            return this;
        }

        B3_Automaton determinized = new B3_Automaton();
        determinized.alphabet = new ArrayList<>(this.alphabet);
        //Structure de données pour mapper les ensembles d'états de l'automate d'origine à ceux du nouvel automate
        Map<Set<B3_State>, B3_State> stateMap = new HashMap<>();
        //file pour traiter les ensembles des états
        Queue<Set<B3_State>> toProcess = new LinkedList<>();
        Set<B3_State> initialSet = new HashSet<>(this.initialStates);
        B3_State newInitialState = new B3_State(createStateName(initialSet), true, checkFinal(initialSet));

        //Permet d'associer l'ensemble des états initiaux de l'automate d'origine avec le nouvel état initial
        stateMap.put(initialSet, newInitialState);
        determinized.addState(newInitialState);
        determinized.addInitialState(newInitialState);
        toProcess.add(initialSet);

        while (!toProcess.isEmpty()) {
            Set<B3_State> currentSet = toProcess.poll();
            //Récupère l'état correspondant dans le nouvel automate.
            B3_State currentState = stateMap.get(currentSet);

            //examinent chaque transition de l'automate d'origine pour trouver les états atteignables depuis l'ensemble d'états courant avec le symbole courant
            for (char symbol : this.alphabet) {
                Set<B3_State> newStateSet = new HashSet<>();
                for (B3_State s : currentSet) {
                    for (B3_Transition t : transitions) {
                        if (t.getFromState().equals(s) && t.getSymbol() == symbol) {
                            newStateSet.add(t.getToState());
                        }
                    }
                }
                if (!newStateSet.isEmpty()) {
                    B3_State newState = stateMap.computeIfAbsent(newStateSet, k -> {
                        B3_State state = new B3_State(createStateName(newStateSet), false, checkFinal(newStateSet));
                        determinized.addState(state);
                        return state;
                    });
                    determinized.addTransition(new B3_Transition(currentState, newState, symbol));
                    if (!stateMap.containsKey(newStateSet)) {
                        toProcess.add(newStateSet);
                    }
                }
            }
        }

        return determinized;
    }

    //Permet de créer un nom d'état en concaténant plusieurs noms d'états différents
    private String createStateName(Set<B3_State> states) {
        return states.stream().map(B3_State::getName).sorted().collect(Collectors.joining("_"));
    }

    //Vérifie si un état du set est terminal ou pas
    private boolean checkFinal(Set<B3_State> states) {
        for (B3_State s : states) {
            if (s.isFinal()) {
                return true;
            }
        }
        return false;
    }
}