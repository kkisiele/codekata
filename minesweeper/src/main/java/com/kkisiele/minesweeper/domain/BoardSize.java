package com.kkisiele.minesweeper.domain;

public final class BoardSize {
    private final int rows;
    private final int columns;

    public BoardSize(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int rows() {
        return rows;
    }

    public int columns() {
        return columns;
    }
}