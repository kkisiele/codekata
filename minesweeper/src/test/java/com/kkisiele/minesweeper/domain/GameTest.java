package com.kkisiele.minesweeper.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**

 x 1 0
 1 1 0
 0 0 0

 */
public class GameTest {
    private Game game;

    @Before
    public void setup() {
        BoardConfiguration configuration = new BoardConfiguration(new BoardSize(3, 3), new MineRatio(0));
        game = new Game(configuration);
        game.placeMine(FieldLocation.of(0, 0));
        game.start();
    }

    @Test
    public void testBoardSetup() throws GameOverException {
        Assert.assertEquals(0, game.numberOfRevealedFields());
    }

    @Test
    public void testRevealMineField() {
        try {
            game.revealField(FieldLocation.of(0, 0));
            Assert.fail("mine field revealed");
        } catch (GameOverException ex) {
        }
    }

    @Test
    public void testRevealBlankFieldHavingAdjacentMines() throws GameOverException {
        game.revealField(FieldLocation.of(0, 1));
        Assert.assertTrue(game.isFieldRevealed(FieldLocation.of(0, 1)));
        Assert.assertEquals(1, game.numberOfRevealedFields());
    }

    @Test
    public void testNumberOfMines() throws GameOverException {
        Assert.assertEquals(1, game.numberOfNeighborhoodMines(FieldLocation.of(1, 1)));
        Assert.assertEquals(0, game.numberOfNeighborhoodMines(FieldLocation.of(2, 2)));
    }

    @Test
    public void testRevealBlankField() throws GameOverException {
        game.revealField(FieldLocation.of(2, 2));
        Assert.assertEquals(8, game.numberOfRevealedFields());
    }
}