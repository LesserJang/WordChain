package com.jih10157.WordChain;

import java.io.IOException;

public class Game {
    private static WordManager wordManager;
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
    }

    public String getLastWord() { return lastword; }
    public boolean nextWord(String str) {
        if(wordManager.isWord(str)) return false;
        if(str.equalsIgnoreCase("첫 단어를 정해주세요.")||!wordManager.isHanbangWord(str)) {
            lastword = str;
            return true;
        }
        if(wordManager.lastandfirst(lastword, str)) {
            lastword = str;
            if (wordManager.isHanbangWord(str)) end = true;
            return true;
        }
        return false;
    }
    public boolean checkEnd() {
        return end;
    }
    public static Game newGame() {
        return new Game();
    }
}
