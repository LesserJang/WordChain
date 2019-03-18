package com.jih10157.WordChain;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Game {
    public final static short NOT_WORD = 1;
    public final static short SUEC = 0;
    public final static short FIRST_HANBANG = 2;
    public final static short NOT_EQUALS = 3;
    public final static short OVERLAP = 4;
    private static WordManager wordManager;
    private Set<String> wordSet;
    static {
        try {
            wordManager = new WordManager(new DBCrawling().load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String lastword;
    private boolean end;
    private Game() {
        lastword = "첫 단어를 정해주세요.";
        end = false;
        wordSet = new HashSet<>();
    }

    public String getLastWord() { return lastword; }
    public short nextWord(String str) {
        if(!wordManager.isWord(str)) return NOT_WORD;
        if(lastword.equalsIgnoreCase("첫 단어를 정해주세요.")) {
            if(wordManager.isHanbangWord(str)) return FIRST_HANBANG;
            lastword = str;
            wordSet.add(str);
            return SUEC;
        }
        if(wordManager.lastandfirst(lastword, str)) {
            if(wordSet.contains(str)) return OVERLAP;
            lastword = str;
            if (wordManager.isHanbangWord(str)) end = true;
            wordSet.add(str);
            return SUEC;
        }
        return NOT_EQUALS;
    }
    public boolean checkEnd() {
        return end;
    }
    public static Game newGame() {
        return new Game();
    }
}
