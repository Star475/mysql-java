package cardgame;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand = new ArrayList<>();
    private int score = 0;
    private String name;

    // Constructor
    public Player(String name) {
        this.name = name;
    }

    // Getters
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    // Methods
    public void describe() {
        System.out.println(name + "'s hand:");
        for (Card card : hand) {
            card.describe();
        }
    }

    public Card flip() {
        return hand.remove(0);
    }

    public void draw(Deck deck) {
        hand.add(deck.draw());
    }

    public void incrementScore() {
        score++;
    }
}
