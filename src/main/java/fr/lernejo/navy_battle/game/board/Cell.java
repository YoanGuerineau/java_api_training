package fr.lernejo.navy_battle.game.board;

import fr.lernejo.navy_battle.game.boats.Boat;
import fr.lernejo.navy_battle.transactions.JSONFire;

import java.util.concurrent.atomic.AtomicReference;

public class Cell {

    // Empty will be used to define enemy's board
    public enum states {
        EMPTY("empty"), OCCUPIED("occupied"), MISS("miss"), HIT("hit"), SUNK("sunk");
        private final String text;
        states(final String text) {
            this.text = text;
        }
        public String toString() {
            return this.text;
        }
    }

    private final Ocean container;
    private final Position position;
    private final AtomicReference<String> currentState = new AtomicReference<>();

    public Cell(Ocean container, Position cellPosition ) {
        this( container, cellPosition, states.EMPTY.toString() );
    }

    public Cell(Ocean container,Position cellPosition , String initialState) {
        this.container = container;
        this.position = cellPosition;
        this.setCurrentState(initialState);
    }

    public Position getPosition() {
        return this.position;
    }

    public String getCurrentState() {
        return currentState.get();
    }

    public void setCurrentState(String newState) {
        this.currentState.set(newState);
    }

    public boolean isOccupied() {
        return this.getCurrentState().equals(states.OCCUPIED.toString());
    }

    public boolean isEmpty() {
        return this.getCurrentState().equals(states.EMPTY.toString());
    }

    public JSONFire hit() {
        JSONFire consequence;
        if ( this.isOccupied() ) {
            consequence = this.hitOccupied();
        } else if ( this.isEmpty() ) {
            consequence = this.hitEmpty();
        } else {
            // Here we manage the cells that were already played ( they are either in miss, hit or sunk state )
            consequence = new JSONFire( this.getCurrentState(), true );
        }
        return consequence;
    }

    private JSONFire hitOccupied() {
        Boat targetBoat = this.container.findBoat(this.getPosition());
        this.setCurrentState(states.HIT.toString());
        if ( ! targetBoat.isAlive() ) {
            for (Cell cell : targetBoat.getPosition()) {
                cell.setCurrentState(states.SUNK.toString());
            }
            return new JSONFire(JSONFire.possibilities.SUNK.toString(), this.container.aliveBoats() > 0);
        }
        return new JSONFire(JSONFire.possibilities.HIT.toString(), true);
    }

    private JSONFire hitEmpty() {
        this.setCurrentState(states.MISS.toString());
        return new JSONFire( JSONFire.possibilities.MISS.toString(), true );
    }

    public Cell up() {
        return this.container.getCell(this.getPosition().up());
    }

    public Cell right() {
        return this.container.getCell(this.getPosition().right());
    }

    public Cell down() {
        return this.container.getCell(this.getPosition().down());
    }

    public Cell left() {
        return this.container.getCell(this.getPosition().left());
    }
}
