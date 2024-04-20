public class Main {
    public static void main(String[] args) {
        Automaton auto= new Automaton();
        Automaton auto1= new Automaton();
        Automaton auto2= new Automaton();

        auto=Automaton.readFromFile("src\\ichierTestAutomateReader.txt");
        auto.displayAutomaton();
        auto.isStandard();
        auto1 = auto.standardizeAutomaton();
        auto1.displayAutomaton();
        auto2 = auto1.completeAutomaton();
        auto2.displayAutomaton();

    }
}