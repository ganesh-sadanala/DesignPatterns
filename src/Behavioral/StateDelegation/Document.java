package Behavioral.StateDelegation;

public class Document {
    private State state;

    public void render() {
        state.render();
    }

    public void publish() {
        state.publish();
    }

    public void changeState(State state) {
        this.state = state;
    }
}
