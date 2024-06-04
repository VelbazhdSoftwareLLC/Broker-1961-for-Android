package eu.veldsoft.broker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the deck of cards, available in the game.
 */
class Deck {
    /**
     * List of the 100 cards.
     */
    private static final List<Card> cards100 = new ArrayList<Card>();
    /**
     * List of the x2 cards.
     */
    private static final List<Card> cards2x = new ArrayList<Card>();
    /**
     * List of the 40/60 cards.
     */
    private static final List<Card> cards4060 = new ArrayList<Card>();
    /**
     * Index of the 100 cards.
     */
    private static int index100 = 0;
    /**
     * Index of the x2 cards.
     */
    private static int index2x = 0;
    /**
     * Index of the 40/60 cards.
     */
    private static int index4060 = 0;

    /**
     * Constructor of the deck with information for the companies.
     *
     * @param companies List of the companies.
     */
    Deck(List<Company> companies) {
        /*
         * Each company should have 3 cards of type 100.
         */
        cards100.add(new Card100("100_A_10_3", companies.get(0), companies));
        cards100.add(new Card100("100_A_10_3", companies.get(0), companies));
        cards100.add(new Card100("100_A_10_3", companies.get(0), companies));
        cards100.add(new Card100("100_B_10_3", companies.get(1), companies));
        cards100.add(new Card100("100_B_10_3", companies.get(1), companies));
        cards100.add(new Card100("100_B_10_3", companies.get(1), companies));
        cards100.add(new Card100("100_C_10_3", companies.get(2), companies));
        cards100.add(new Card100("100_C_10_3", companies.get(2), companies));
        cards100.add(new Card100("100_C_10_3", companies.get(2), companies));
        cards100.add(new Card100("100_D_10_3", companies.get(3), companies));
        cards100.add(new Card100("100_D_10_3", companies.get(3), companies));
        cards100.add(new Card100("100_D_10_3", companies.get(3), companies));

        /*
         * Each company should have 3 cards of type 2x and 3 cards of type x1/2.
         */
        cards2x.add(new Card2mul("2x_A_12_1", companies.get(0)));
        cards2x.add(new Card2mul("2x_A_12_1", companies.get(0)));
        cards2x.add(new Card2mul("2x_A_12_1", companies.get(0)));
        cards2x.add(new Card2mul("2x_B_12_1", companies.get(1)));
        cards2x.add(new Card2mul("2x_B_12_1", companies.get(1)));
        cards2x.add(new Card2mul("2x_B_12_1", companies.get(1)));
        cards2x.add(new Card2mul("2x_C_12_1", companies.get(2)));
        cards2x.add(new Card2mul("2x_C_12_1", companies.get(2)));
        cards2x.add(new Card2mul("2x_C_12_1", companies.get(2)));
        cards2x.add(new Card2mul("2x_D_12_1", companies.get(3)));
        cards2x.add(new Card2mul("2x_D_12_1", companies.get(3)));
        cards2x.add(new Card2mul("2x_D_12_1", companies.get(3)));
        cards2x.add(new Card2div("2x_1_12_A", companies.get(0)));
        cards2x.add(new Card2div("2x_1_12_A", companies.get(0)));
        cards2x.add(new Card2div("2x_1_12_A", companies.get(0)));
        cards2x.add(new Card2div("2x_1_12_B", companies.get(1)));
        cards2x.add(new Card2div("2x_1_12_B", companies.get(1)));
        cards2x.add(new Card2div("2x_1_12_B", companies.get(1)));
        cards2x.add(new Card2div("2x_1_12_C", companies.get(2)));
        cards2x.add(new Card2div("2x_1_12_C", companies.get(2)));
        cards2x.add(new Card2div("2x_1_12_C", companies.get(2)));
        cards2x.add(new Card2div("2x_1_12_D", companies.get(3)));
        cards2x.add(new Card2div("2x_1_12_D", companies.get(3)));
        cards2x.add(new Card2div("2x_1_12_D", companies.get(3)));

        /*
         * Each company should have 4 cards of type 60 and 4 cards of type 40.
         */
        cards4060.add(new Card60("60_A_30_1", companies.get(0)));
        cards4060.add(new Card60("60_A_30_1", companies.get(0)));
        cards4060.add(new Card60("60_A_30_1", companies.get(0)));
        cards4060.add(new Card60("60_A_30_1", companies.get(0)));
        cards4060.add(new Card60("60_B_30_1", companies.get(1)));
        cards4060.add(new Card60("60_B_30_1", companies.get(1)));
        cards4060.add(new Card60("60_B_30_1", companies.get(1)));
        cards4060.add(new Card60("60_B_30_1", companies.get(1)));
        cards4060.add(new Card60("60_C_30_1", companies.get(2)));
        cards4060.add(new Card60("60_C_30_1", companies.get(2)));
        cards4060.add(new Card60("60_C_30_1", companies.get(2)));
        cards4060.add(new Card60("60_C_30_1", companies.get(2)));
        cards4060.add(new Card60("60_D_30_1", companies.get(3)));
        cards4060.add(new Card60("60_D_30_1", companies.get(3)));
        cards4060.add(new Card60("60_D_30_1", companies.get(3)));
        cards4060.add(new Card60("60_D_30_1", companies.get(3)));
        cards4060.add(new Card40("40_1_50_A", companies.get(0)));
        cards4060.add(new Card40("40_1_50_A", companies.get(0)));
        cards4060.add(new Card40("40_1_50_A", companies.get(0)));
        cards4060.add(new Card40("40_1_50_A", companies.get(0)));
        cards4060.add(new Card40("40_1_50_B", companies.get(1)));
        cards4060.add(new Card40("40_1_50_B", companies.get(1)));
        cards4060.add(new Card40("40_1_50_B", companies.get(1)));
        cards4060.add(new Card40("40_1_50_B", companies.get(1)));
        cards4060.add(new Card40("40_1_50_C", companies.get(2)));
        cards4060.add(new Card40("40_1_50_C", companies.get(2)));
        cards4060.add(new Card40("40_1_50_C", companies.get(2)));
        cards4060.add(new Card40("40_1_50_C", companies.get(2)));
        cards4060.add(new Card40("40_1_50_D", companies.get(3)));
        cards4060.add(new Card40("40_1_50_D", companies.get(3)));
        cards4060.add(new Card40("40_1_50_D", companies.get(3)));
        cards4060.add(new Card40("40_1_50_D", companies.get(3)));
    }

    /**
     * Shuffles all cards.
     */
    void shuffle() {
        index100 = 0;
        index2x = 0;
        index4060 = 0;
        Collections.shuffle(cards100);
        Collections.shuffle(cards2x);
        Collections.shuffle(cards4060);
    }

    /**
     * Deal the cards.
     *
     * @return Result of the given cards.
     */
    List<Card> deal() {
        List<Card> cards = new ArrayList<Card>();

        /*
         * Each player has two cards of type 100.
         */
        for (int i = 0; i < 2; i++) {
            cards.add(cards100.get(index100));
            index100 = (index100 + 1) % cards100.size();
        }

        /*
         * Each player has three cards of type x2.
         */
        for (int i = 0; i < 3; i++) {
            cards.add(cards2x.get(index2x));
            index2x = (index2x + 1) % cards2x.size();
        }

        /*
         * Each player has five cards of type 40/60.
         */
        for (int i = 0; i < 5; i++) {
            cards.add(cards4060.get(index4060));
            index4060 = (index4060 + 1) % cards4060.size();
        }

        return cards;
    }
}
