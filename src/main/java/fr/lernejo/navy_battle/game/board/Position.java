package fr.lernejo.navy_battle.game.board;

import java.util.concurrent.atomic.AtomicReference;

public class Position {

    public enum directions {
        UP("up"), RIGHT("right"), DOWN("down"), LEFT("left");
        private final String text;
        directions(final String text) {
            this.text = text;
        }
        public String toString() {
            return this.text;
        }
    }

    private final AtomicReference<Integer> row = new AtomicReference<>();
    private final AtomicReference<Integer> col = new AtomicReference<>();

    public Position( String cellName ) {
        this.row.set( Integer.parseInt( String.valueOf( cellName.charAt(1) ) ) );
        this.col.set( getColumnInt( cellName.toCharArray()[0] ) );
    }

    public Position( int row, int col ) {
        this.row.set(row);
        this.col.set(col);
    }

    public int getRow() {
        return this.row.get();
    }

    public void setRow(int row) {
        this.row.set(row);
    }

    public int getCol() {
        return this.col.get();
    }

    public void setCol(int col) {
        this.col.set(col);
    }

    private int getColumnInt(char columnChar) {
        return ( (int)Character.toUpperCase( columnChar ) % (int)'A');
    }

    public Position up() {
        return new Position( this.getRow() - 1 , this.getCol() );
    }

    public Position right() {
        return new Position( this.getRow(), this.getCol() + 1 );
    }

    public Position down() {
        return new Position( this.getRow() + 1, this.getCol() );
    }

    public Position left() {
        return new Position( this.getRow(), this.getCol() - 1 );
    }
}
