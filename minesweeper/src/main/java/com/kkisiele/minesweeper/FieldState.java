package com.kkisiele.minesweeper;

abstract class FieldState {
    protected final Field field;

    public FieldState(Field field) {
        this.field = field;
    }

    public abstract boolean isRevealed();
    public abstract void reveal() throws GameOverException;
    public abstract boolean canPlaceMine();

    public static class Hidden extends FieldState {
        public Hidden(Field field) {
            super(field);
        }

        @Override
        public boolean isRevealed() {
            return false;
        }

        @Override
        public void reveal() throws GameOverException {
            if(field.hasMine()) {
                throw new GameOverException();
            }
            field.changeState(new Revealed(field));
        }

        @Override
        public boolean canPlaceMine() {
            return !field.hasMine();
        }
    }

    public static class Revealed extends FieldState {
        public Revealed(Field field) {
            super(field);
        }

        @Override
        public boolean isRevealed() {
            return true;
        }

        @Override
        public void reveal() throws GameOverException {
            throw new IllegalStateException("Field is already revealed");
        }

        @Override
        public boolean canPlaceMine() {
            return false;
        }
    }
}