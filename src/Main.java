import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        State state1 = new State("Initial", true,false);
        State state2 = new State("Final", false,true);

        Transition transition1 = new Transition(state1, state2, 'a');
        Transition transition2 = new Transition(state1, state2, 'b');
        Transition transition3 = new Transition(state1, state2, 'c');

        Transition transition4 = new Transition(state2, state1, 'a');
        Transition transition5 = new Transition(state2, state1, 'b');
        Transition transition6 = new Transition(state2, state1, 'c');


        Automaton test = new Automaton();
        Automaton test2 = new Automaton();


        state1.addTransition(transition1);
        state1.addTransition(transition2);
        //state1.addTransition(transition3);

        state2.addTransition(transition4);
        state2.addTransition(transition5);
        state2.addTransition(transition6);


        test.addInitialState(state1);
        test.addFinalState(state2);

        test.addTransition(transition1);
        test.addTransition(transition2);
        //test.addTransition(transition3);
        test.addTransition(transition4);
        test.addTransition(transition5);
        test.addTransition(transition6);


        test.addState(state1);
        test.addState(state2);

        test.addAlphabet('a');
        test.addAlphabet('b');
        test.addAlphabet('c');

        test.isDeterminist();
        test.isStandard();
        test.isComplete();

        test2 = test.standardizeAutomaton();
        test2.isStandard();
        test2.isComplete();

    }
}