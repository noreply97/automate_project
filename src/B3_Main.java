public class B3_Main {
    public static void main(String[] args) {
        /*
        B3_Automaton auto= new B3_Automaton();
        B3_Automaton auto1= new B3_Automaton();
        B3_Automaton auto2= new B3_Automaton();

        auto=B3_Automaton.readFromFile("src\\ichierTestAutomateReader.txt");
        auto.displayAutomaton();
        auto1 = auto.standardizeAutomaton();
        auto1.isDeterminist();
        auto1.determinizeAutomaton();
        auto1.displayAutomaton();
        auto1.isDeterminist();
        */
        B3_Automaton test = new B3_Automaton();
        B3_Automaton test2 = new B3_Automaton();

        B3_State state1 = new B3_State("Q0", true, false);
        B3_State state2 = new B3_State("Q1", false, true);
        B3_State state3 = new B3_State("Q2", false, true);

        test.addAlphabet('a');
        test.addAlphabet('b');

        test.addState(state1);
        test.addState(state2);
        test.addState(state3);

        test.addInitialState(state1);
        test.addFinalState(state2);
        test.addFinalState(state3);

        B3_Transition transition1 = new B3_Transition(state1, state2, 'a');
        B3_Transition transition2 = new B3_Transition(state1, state3, 'a');

        test.addTransition(transition1);
        test.addTransition(transition2);

       test.displayAutomaton();
       test.whatIsIt();
       test2 = test.determinizeAutomaton();
       test2.displayAutomaton();
       test2.isDeterminist();

    }
}