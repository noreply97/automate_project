public class B3_Main {
    public static void main(String[] args) {

        B3_Automaton auto1= new B3_Automaton();
        B3_Automaton auto2= new B3_Automaton();
        B3_Automaton auto3= new B3_Automaton();
        B3_Automaton auto4= new B3_Automaton();

        auto1 = B3_Automaton.readFromFile("src/Automates/B3_06.txt");
        auto1.displayAutomaton();
        auto1.whatIsIt();

        auto2 = auto1.determinizeAutomaton();
        auto3 = auto2.completeAutomaton();
        auto3.displayAutomaton();
        auto3.whatIsIt();


    }
}