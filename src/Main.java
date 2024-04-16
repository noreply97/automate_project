import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        State state1 = new State("Initial", true,false);
        State state2 = new State("Final", false,true);

        Transition transition1 = new Transition(state1, state2, 'x');
        Transition transition2 = new Transition(state2, state1, 'y');

        Automaton test = new Automaton();

        state1.addTransition(transition1);
        state2.addTransition(transition2);

        test.addInitialStates(state1);
        test.addFinalState(state2);

        test.addTransition(transition1);
        test.addTransition(transition2);

        test.addStates(state1);
        test.addStates(state2);

        test.addAlphabet('a');
        test.addAlphabet('b');
        test.addAlphabet('c');

        test.isDeterminist();
        test.isStandart();

        test.displayAutomaton();

    }
}