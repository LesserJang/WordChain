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
        assertEquals(game.nextWord("무지개"), Game.SUEC);
        assertEquals("무지개", game.getLastWord());
    }
    @Test
    public void nextWord() {
        assertEquals(game.nextWord("?"), Game.NOT_WORD);
        assertEquals(game.nextWord("장사꾼"), Game.FIRST_HANBANG);
        assertEquals(game.nextWord("한"), Game.NOT_WORD);
        assertEquals(game.nextWord("낱말이아닌것"), Game.NOT_WORD);
        assertEquals(game.nextWord("띄 어 쓰 기"), Game.NOT_WORD);
        assertEquals(game.nextWord("기러기"), Game.SUEC);
        assertEquals(game.nextWord("기러기"), Game.OVERLAP);
        assertEquals(game.nextWord("기장"), Game.SUEC);
        assertEquals(game.nextWord("장기"), Game.SUEC);
        assertEquals(game.nextWord("기장"), Game.OVERLAP);
        assertEquals(game.nextWord("기러기"), Game.OVERLAP);
    }
    @Test
    public void checkEnd() {
        assertEquals(game.nextWord("무지개"), Game.SUEC);
        assertFalse(game.checkEnd());
        game = Game.newGame();
        assertEquals(game.nextWord("기장"), Game.SUEC);
        assertEquals(game.nextWord("장사꾼"), Game.SUEC);
        assertTrue(game.checkEnd());
    }

    @Test
    public void newGame() {
        assertEquals("첫 단어를 정해주세요.", game.getLastWord());
        assertFalse(game.checkEnd());
    }
}