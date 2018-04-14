package com.kkisiele.minesweeper;

class Field {
    private boolean mine;
    private FieldState state;

    public Field() {
        this.state = new FieldState.Hidden(this);
    }

    void changeState(FieldState state) {
        this.state = state;
    }

    public void placeMine() {
        if(!canPlaceMine()) {
            throw new IllegalStateException("Cannot place mine on revealed field");
        }

        mine = true;
    }

    public boolean canPlaceMine() {
        return state.canPlaceMine();
    }

    public boolean isRevealed() {
        return state.isRevealed();
    }

    public boolean hasMine() {
        return mine;
    }

    public void reveal() throws GameOverException {
        state.reveal();
    }
}