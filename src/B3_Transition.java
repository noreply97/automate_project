public class B3_Transition {
    private B3_State fromState;
    private B3_State toState;
    private char symbol;

    public B3_Transition(final B3_State fromState,final B3_State toState,final char symbol) {
        this.fromState = fromState;
        this.toState = toState;
        this.symbol = symbol;
    }

    public B3_State getFromState() {
        return this.fromState;
    }

    public void setFromState(final B3_State fromState) {
        this.fromState = fromState;
    }

    public B3_State getToState() {
        return this.toState;
    }

    public B3_State fromState(){
        return this.fromState;
    }

    public void setToState(final B3_State toState) {
        this.toState = toState;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void setSymbol(final char symbol) {
        this.symbol = symbol;
    }

}
