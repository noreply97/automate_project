public class Main {
    public static void main(String[] args) {
        Automaton auto= new Automaton();
        Automaton auto1= new Automaton();
        auto=Automaton.readFromFile("src\\ichierTestAutomateReader.txt");
        auto.displayAutomaton();
        auto.isStandard();
    }
}