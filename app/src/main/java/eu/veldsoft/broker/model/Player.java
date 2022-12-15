package eu.veldsoft.broker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the players.
 */
class Player {

    /**
     * Flag for an active player.
     */
    private boolean active = true;

    /**
     * The name of the player.
     */
    private String name = "";

    /**
     * The amount of money which the player has.
     */
    private final int money = 300;

    /**
     * List of card for the player.
     */
    private List<Card> cards = new ArrayList<Card>();

    /**
     * List of the shares which the player has.
     */
    private List<Share> shares = new ArrayList<Share>();

    /**
     * Constructor with name of the player.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        super();
        this.name = name;
    }

    /**
     * Get cards in the start of the game.
     *
     * @param cards List of cards.
     */
    public void getInitialCards(List<Card> cards) {
        this.cards = cards;
    }
}
