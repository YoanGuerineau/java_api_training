package fr.lernejo.navy_battle.game.board;

import fr.lernejo.navy_battle.game.boats.Boat;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Ocean {

    private final Cell[][] board = new Cell[10][10];
    private final Set<Boat> boats = new HashSet<>();

    public Ocean() {
        this(Cell.states.UNKNOWN.toString());
    }

    public Ocean(String initialState) {
        for ( int row=0; row < 10; row++) {
            for ( int column=0; column < 10; column++ ) {
                board[row][column] = new Cell( this, new Position( row, column ), initialState );
            }
        }
    }

    public Cell getCellFromString( String targetCell ) {
        int column = getColumnInt( targetCell.toCharArray()[0] );
        int row = Integer.parseInt( targetCell.substring( 1, 1 ) );
        return this.getCell( new Position( row, column ) );
    }

    public Cell getCell(Position targetCell) {
        if ( ! cellExists(targetCell) ) {
            return null;
        }
        return board[targetCell.getRow()][targetCell.getCol()];
    }

    public boolean cellExists(Position targetCell) {
        return 0 <= targetCell.getRow() && targetCell.getRow() <= board.length && 0 <= targetCell.getCol() && targetCell.getCol() <= board[0].length;
    }

    public void hitBoat( Position targetCell ) {

    }

    public Boat getBoat( Position targetCell ) {
        return null;
    }

    public int aliveBoats() {
        return this.boats.stream().collect( Collectors.filtering(Boat::isAlive, Collectors.counting())).intValue();
    }

    private int getColumnInt(char columnChar) {
        return ( (int)Character.toUpperCase( columnChar ) % (int)'A');
    }

    private char getColumnChar(int columnInt) {
        return (char)(columnInt + (int)'A');
    }

}
