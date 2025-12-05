package com.wordle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private final JTextField guessField = new JTextField(5);
    private final JTextArea feedbackArea = new JTextArea(10, 20);
    private WordleGame game;

    public GUI() {
        setTitle("Wordle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        WordList wordList = new WordList();
        wordList.loadFromFile("src/main/resources/words.txt");
        game = new WordleGame(wordList.getRandomWord());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter your 5-letter guess: "));
        topPanel.add(guessField);

        JButton submitButton = new JButton("Submit");
        topPanel.add(submitButton);
        add(topPanel, BorderLayout.NORTH);

        feedbackArea.setEditable(false);
        add(new JScrollPane(feedbackArea), BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = guessField.getText();
                if (guess.length() != 5) {
                    JOptionPane.showMessageDialog(null, "Please enter a 5-letter word!");
                    return;
                }

                String feedback = game.playTurn(guess);
                feedbackArea.append(guess.toUpperCase() + " -> " + feedback + "\n");
                guessField.setText("");

                if (game.hasPlayerWon()) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the word!");
                    restartGame(wordList);
                } else if (game.isGameOver()) {
                    JOptionPane.showMessageDialog(null, "Game Over!");
                    restartGame(wordList);
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void restartGame(WordList wordList) {
        game = new WordleGame(wordList.getRandomWord());
        feedbackArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}