package com.wordle;

public class Word {
    private final String value;

    public Word(String value) {
        this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }

    public boolean matches(String guess) {
        return value.equalsIgnoreCase(guess);
    }

    public String generateFeedback(String guess) {
        guess = guess.toUpperCase();
        StringBuilder feedback = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char guessChar = guess.charAt(i);
            if (value.charAt(i) == guessChar) {
                feedback.append("G");
            } else if (value.contains(String.valueOf(guessChar))) {
                feedback.append("Y");
            } else {
                feedback.append("X");
            }
        }
        return feedback.toString();
    }
}