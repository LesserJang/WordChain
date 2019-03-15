package com.jih10157.WordChain;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    public static Game game;
    @Before
    public void initGame() {
        game = Game.newGame();
    }
    @Test
    public void getLastWord() {
        assertTrue(game.nextWord("무지개"));
        assertEquals("무지개", game.getLastWord());
    }

    @Test
    public void checkEnd() {
        assertTrue(game.nextWord("무지개"));
        assertFalse(game.checkEnd());
        game = Game.newGame();
        assertTrue(game.nextWord("기장"));
        assertTrue(game.nextWord("장사꾼"));
        assertTrue(game.checkEnd());
    }

    @Test
    public void newGame() {
        assertEquals("첫 단어를 정해주세요.", game.getLastWord());
        assertFalse(game.checkEnd());
    }
}