public class B3_Main {
    public static void main(String[] args) {
        B3_Automaton auto= new B3_Automaton();
        B3_Automaton auto1= new B3_Automaton();
        B3_Automaton auto2= new B3_Automaton();

        auto=B3_Automaton.readFromFile("src\\ichierTestAutomateReader.txt");
        auto.displayAutomaton();
        auto.isStandard();
        auto1 = auto.standardizeAutomaton();
        auto1.displayAutomaton();
        auto2 = auto1.completeAutomaton();
        auto2.displayAutomaton();
        auto2.isDeterminist();

    }
}