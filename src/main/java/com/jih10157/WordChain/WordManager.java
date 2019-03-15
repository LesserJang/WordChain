package com.jih10157.WordChain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class WordManager {
    Set<String> wordlist;
    Set<String> hanbangwordList;
    WordManager(Set<String> wordlist) throws IOException {
        this.wordlist = wordlist;
        this.hanbangwordList = loadHanbangWord();
    }
    boolean isWord(String string) {
        return wordlist.contains(string);
    }
    boolean isHanbangWord(String word) {
        return hanbangwordList.contains(word);
    }
    boolean lastandfirst(String str1, String str2) {
        return str1.charAt(str1.length()-1)==str2.charAt(0)||douem(str1.charAt(str1.length()-1))==str2.charAt(0);
    }
    private Set<String> loadHanbangWord() throws IOException {
        File file = new File("Data", "HanbangWordDb.txt");
        Path path = file.toPath();
        if(file.exists()) {
            return new HashSet<>(Files.readAllLines(path));
        }
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if (!file.exists()) file.createNewFile();
        StringBuilder sb = new StringBuilder();
        String nl = System.getProperty("line.separator");
        Set<String> set = new HashSet<>();
        Loop1 : for(String word:wordlist) {
            for(String str:wordlist) {
                if(lastandfirst(word, str)) {
                    continue Loop1;
                }
            }
            set.add(word);
            sb.append(word).append(nl);
        }
        Files.write(path, sb.toString().getBytes());
        return set;
    }
    private char douem(char c) {
        switch((c - '가') / 28){
            // 녀, 뇨, 뉴, 니
            case 48: case 54:
            case 59: case 62:
                c += 5292;
                break;
            // 랴, 려, 례, 료, 류, 리
            case 107: case 111:
            case 112: case 117:
            case 122: case 125:
                c += 3528;
                break;
            // 라, 래, 로, 뢰, 루, 르
            case 105: case 106:
            case 113: case 116:
            case 118: case 123:
                c -= 1764;
                break;
            default:
                break;
        }
        return c;
    }
}
