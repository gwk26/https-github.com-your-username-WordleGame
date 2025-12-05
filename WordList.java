package com.wordle;

import java.io.*;
import java.util.*;

public class WordList {
    private final List<String> words = new ArrayList<>();

    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == 5) {
                    words.add(line.toUpperCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Word getRandomWord() {
        Random random = new Random();
        return new Word(words.get(random.nextInt(words.size())));
    }

    public List<String> getAllWords() {
        return Collections.unmodifiableList(words);
    }
}