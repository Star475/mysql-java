package cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    // Constructor to populate deck
    public Deck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] names = {
            "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", 
            "Nine", "Ten", "Jack", "Queen", "King", "Ace"
        };

        for (String suit : suits) {
            for (int i = 0; i < names.length; i++) {
                String name = names[i] + " of " + suit;
                cards.add(new Card(i + 2, name)); // value 2 to 14
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.remove(0);
    }
}

