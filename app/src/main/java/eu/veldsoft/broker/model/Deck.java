package eu.veldsoft.broker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the deck of cards, available in the game.
 */
class Deck {
    private static int index100 = 0;
    private static List<Card> cards100 = new ArrayList<Card>();

    private static int index2x = 0;
    private static List<Card> cards2x = new ArrayList<Card>();

    private static int index4060 = 0;
    private static List<Card> cards4060 = new ArrayList<Card>();

    static{
        //TODO Add 68 cards.
    }

    /**
     * Shuffles all cards.
     */
    static void shuffle() {
        index100 = 0;
        index2x = 0;
        index4060 = 0;
        Collections.shuffle(cards100);
        Collections.shuffle(cards2x);
        Collections.shuffle(cards4060);
    }

    static List<Card> deal() {
        List<Card> cards = new ArrayList<Card>();

        /*
         * Each player has two cards of type 100.
         */
        for(int i=0; i<2; i++) {
            cards.add(cards100.get(index100));
            index100 = (index100 + 1) % cards100.size();
        }

        /*
         * Each player has two cards of type x2.
         */
        for(int i=0; i<3; i++) {
            cards.add(cards2x.get(index2x));
            index2x = (index2x + 1) % cards2x.size();
        }

        /*
         * Each player has two cards of type 40/60.
         */
        for(int i=0; i<5; i++) {
            cards.add(cards4060.get(index4060));
            index4060 = (index4060 + 1) % cards4060.size();
        }

        return cards;
    }
}
