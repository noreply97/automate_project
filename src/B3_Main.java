public class B3_Main {
    public static void main(String[] args) {

        B3_Automaton auto1= new B3_Automaton();
        B3_Automaton auto2= new B3_Automaton();
        B3_Automaton auto3= new B3_Automaton();
        B3_Automaton auto4= new B3_Automaton();

        auto1 = B3_Automaton.readFromFile("src/Automates/B3_1.7txt");
        auto1.displayAutomaton();
        auto1.whatIsIt();
        auto2 = auto1.standardizeAutomaton();
        auto2.displayAutomaton();
        auto3 = auto2.completeAutomaton();
        auto3.displayAutomaton();
        auto3.whatIsIt();
        auto4 = auto3.determinizeAutomaton();
        auto4.displayAutomaton();




    }
}