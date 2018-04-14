package com.kkisiele.minesweeper;

public class BoardConfiguration {
    private BoardSize size;
    private MineRatio mineRatio;

    public BoardConfiguration(BoardSize size, MineRatio mineRatio) {
        this.size = size;
        this.mineRatio = mineRatio;
    }

    public BoardSize getSize() {
        return size;
    }

    public MineRatio getMineRatio() {
        return mineRatio;
    }
}