public class Transition {
    private State fromState;
    private State toState;
    private char symbol;

    public Transition(final State fromState,final State toState,final char symbol) {
        this.fromState = fromState;
        this.toState = toState;
        this.symbol = symbol;
    }

    public State getFromState() {
        return this.fromState;
    }

    public void setFromState(final State fromState) {
        this.fromState = fromState;
    }

    public State getToState() {
        return this.toState;
    }

    public State fromState(){
        return this.fromState;
    }

    public void setToState(final State toState) {
        this.toState = toState;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void setSymbol(final char symbol) {
        this.symbol = symbol;
    }

}
