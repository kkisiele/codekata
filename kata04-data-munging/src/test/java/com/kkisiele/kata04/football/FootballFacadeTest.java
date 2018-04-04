package com.kkisiele.kata04.football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootballFacadeTest {
    private FootballFacade facade;

    @Before
    public void setup() {
        facade = new FootballFacade();
    }

    @Test
    public void testFindTeamWithSmallestGoalDifference() {
        Assert.assertEquals("Aston_Villa", facade.findTeamWithSmallestGoalDifference());
    }
}