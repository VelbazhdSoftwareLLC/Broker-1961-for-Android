package eu.veldsoft.broker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Get player's active status.
     *
     * @return True if the player is still active, false otherwise.
     */
    public boolean active() {
        return active;
    }

    /**
     * Get player's name.
     *
     * @return The name of the player.
     */
    public String name() {
        return name;
    }

    /**
     * Get list of cards hold by the player.
     *
     * @return List of the cards reference.
     */
    public List<Card> cards() {
        return cards;
    }

    /**
     * Get cards in the start of the game.
     *
     * @param cards List of cards.
     */
    public void getInitialCards(List<Card> cards) {
        this.cards = cards;
    }

    public String report() {
        String text = "";

        text += name;
        text += "\n";
        text += "=== === ===";
        text += "\n";
        text += "Money:";
        text += "\t";
        text += money;
        text += "\n";
        text += "=== === ===";
        text += "\n";

        if(shares.size() > 0) {
            Map<String, Integer> portfolio = new HashMap<String,Integer>();
            for(Share s : shares) {
                String name = s.company().name();
                int amount = s.amount();

                if(portfolio.containsKey(name) == false) {
                    portfolio.put(name,amount);
                } else {
                    portfolio.put(name, portfolio.get(name)+amount);
                }
            }

            for(String name : portfolio.keySet()) {
                text += name;
                text += ":";
                text += "\t";
                text += portfolio.get(name);
                text += "\n";
            }
            text += "=== === ===";
            text += "\n";
        }

        return text;
    }
}
