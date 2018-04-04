package com.kkisiele.minesweeper.domain;

public final class MineRatio {
    public static final double MIN_VALUE = 0;
    public static final double MAX_VALUE = 0.5;

    private final double value;

    public MineRatio(double value) {
        if(value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException("Incorrect ratio [" + value + "]");
        }
        this.value = value;
    }

    public int numberOfMines(Board board) {
        return (int) (board.numberOfFields() * value);
    }
}