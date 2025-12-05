package com.wordle;

import java.util.*;

public class Player {
    private final List<String> guesses = new ArrayList<>();
    private int attempts = 0;
    private final int maxAttempts = 6;

    public void addGuess(String guess) {
        guesses.add(guess.toUpperCase());
        attempts++;
    }

    public List<String> getGuesses() {
        return Collections.unmodifiableList(guesses);
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean hasAttemptsLeft() {
        return attempts < maxAttempts;
    }
}