package com.kkisiele.datamunging.football;

public interface Team {
    String name();
    int scoredGoals();
    int concededGoals();

    default int goalDifference() {
        return Math.abs(scoredGoals() - concededGoals());
    }
}