package fr.lernejo.navy_battle.game.board;

public class Position {

    private int row;
    private int col;

    public Position( String cellName ) {
        this.row = Integer.parseInt( cellName.substring( 1, 1 ) );
        this.col = getColumnInt( cellName.toCharArray()[0] );
    }

    public Position( int row, int col ) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    private int getColumnInt(char columnChar) {
        return ( (int)Character.toUpperCase( columnChar ) % (int)'A');
    }

    public Position up() {
        return new Position( this.getRow(), this.getCol() + 1 );
    }

    public Position right() {
        return new Position( this.getRow() + 1, this.getCol() );
    }

    public Position down() {
        return new Position( this.getRow(), this.getCol() - 1 );
    }

    public Position left() {
        return new Position( this.getRow() - 1, this.getCol() );
    }
}
