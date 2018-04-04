package com.kkisiele.kata04.football;

public abstract class Team {
    public abstract String name();
    public abstract int scoredGoals();
    public abstract int concededGoals();

    public int goalDifference() {
        return Math.abs(scoredGoals() - concededGoals());
    }
}