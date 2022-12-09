package eu.veldsoft.broker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the players.
 */
class Player {
    private final int money = 300;
    private List<Card> cards = new ArrayList<Card>();

    /**
     * Get cards in the start of the game.
     *
     * @param cards List of cards.
     */
    public void getInitialCards(List<Card> cards) {
        this.cards = cards;
    }
}
