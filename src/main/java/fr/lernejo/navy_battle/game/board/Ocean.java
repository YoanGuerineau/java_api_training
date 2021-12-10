package fr.lernejo.navy_battle.game.board;

import java.util.HashMap;
import java.util.Map;

public class Ocean {

    private final Cell[][] board = new Cell[10][10];
    private final HashMap<Character, Integer> columnMap = (HashMap<Character, Integer>) Map.ofEntries(
        Map.entry( 'a', 0 ),
        Map.entry( 'b', 1 ),
        Map.entry( 'c', 2 ),
        Map.entry( 'd', 3 ),
        Map.entry( 'e', 4 ),
        Map.entry( 'f', 5 ),
        Map.entry( 'g', 6 ),
        Map.entry( 'h', 7 ),
        Map.entry( 'i', 8 ),
        Map.entry( 'j', 9 )
    );

    public Ocean() {
        this(Cell.states.UNKNOWN.toString());
    }

    public Ocean(String initialState) {
        for ( int row=0; row < 10; row++) {
            for ( int col=0; col < 10; col++ ) {
                board[row][col] = new Cell( this, initialState );
            }
        }
    }

    public Cell getCell(String targetCell) {
        int column = getColumn(targetCell.toUpperCase().toCharArray()[0]);
        int row = Integer.parseInt(targetCell.substring(1,1));
        return board[row][column];
    }

    private int getColumn(char columnLetter) {
        return this.columnMap.get(columnLetter);
    }

}
