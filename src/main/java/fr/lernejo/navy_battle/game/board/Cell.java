package fr.lernejo.navy_battle.game.board;

import java.util.concurrent.atomic.AtomicReference;

public class Cell {

    public enum states {
        UNKNOWN("unknown"), EMPTY("empty"), OCCUPIED("occupied"), HIT("hit"), SUNK("sunk");
        private final String text;
        states(final String text) {
            this.text = text;
        }
        public String toString() {
            return this.text;
        }
    }

    private final AtomicReference<String> currentState = new AtomicReference<>();
    private final Ocean container;

    public Cell(Ocean container) {
        this(container, states.UNKNOWN.toString());
    }

    public Cell(Ocean container, String initialState) {
        this.container = container;
        this.setCurrentState(initialState);
    }

    public String getCurrentState() {
        return currentState.get();
    }

    public void setCurrentState(String newState) {
        this.currentState.set(newState);
    }
}
