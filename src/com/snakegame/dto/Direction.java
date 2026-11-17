package com.snakegame.dto;

public enum Direction {

    LEFT(0, -1),
    RIGHT(0, 1),
    TOP(-1, 0),
    BOTTOM(1, 0);

    private final int dRow;
    private final int dCol;

    Direction(int dRow, int dCol) {
        this.dRow = dRow;
        this.dCol = dCol;
    }

    public int getDRow() {
        return dRow;
    }

    public int getDCol() {
        return dCol;
    }

    public static Direction fromChar(char c) {
        switch (c) {
            //Bug --> 1
            case 'L':
            case 'l':
                return LEFT;
            case 'R':
            case 'r':
                return RIGHT;
            case 'T':
            case 't':
                return TOP;
            case 'B':
            case 'b':
                return BOTTOM;
            default:
                return null;
        }
    }
}
