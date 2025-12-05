package com.wordle;

public class WordleGame {
    private final Word hiddenWord;
    private final Player player;

    public WordleGame(Word hiddenWord) {
        this.hiddenWord = hiddenWord;
        this.player = new Player();
    }

    public Player getPlayer() {
        return player;
    }

    public String playTurn(String guess) {
        player.addGuess(guess);
        return hiddenWord.generateFeedback(guess);
    }

    public boolean isGameOver() {
        return hiddenWord.matches(player.getGuesses().isEmpty() ? "" : player.getGuesses().get(player.getGuesses().size() - 1))
                || !player.hasAttemptsLeft();
    }

    public boolean hasPlayerWon() {
        return !player.getGuesses().isEmpty() && hiddenWord.matches(player.getGuesses().get(player.getGuesses().size() - 1));
    }
}