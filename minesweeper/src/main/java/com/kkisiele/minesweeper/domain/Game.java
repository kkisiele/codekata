package com.kkisiele.minesweeper.domain;

import java.util.Set;

public class Game {
    private Board board;

    public Game(BoardConfiguration configuration) {
        this.board = new Board(configuration);
    }

    public void start() {
    }

    void placeMine(FieldLocation location) {
        board.placeMine(location);
    }

    public void revealField(FieldLocation location) throws GameOverException {
        if(board.isFieldRevealed(location)) {
            return;
        }

        board.revealField(location);
        int mines = board.numberOfNeighborhoodMines(location);
        if (mines == 0) {
            revealFields(board.neighborhoodLocations(location));
        }
    }

    private void revealFields(Set<FieldLocation> locations) throws GameOverException {
        for(FieldLocation location : locations) {
            revealField(location);
        }
    }

    public int numberOfRevealedFields() {
        return board.numberOfRevealedFields();
    }

    public boolean isFieldRevealed(FieldLocation location) {
        return board.isFieldRevealed(location);
    }

    public int numberOfNeighborhoodMines(FieldLocation location) {
        return board.numberOfNeighborhoodMines(location);
    }
}