package eu.veldsoft.broker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It is a card played in the game.
 */
abstract class Card {
    /**
     * Key value of the card used in some collections.
     */
    private String key = "";

    /**
     * List of all companies which will go up.
     */
    protected List<Company> up = new ArrayList<Company>();

    /**
     * List of all companies which will go down.
     */
    protected List<Company> down = new ArrayList<Company>();

    /**
     * When card is played the prize of the shares changes.
     */
    abstract void play();

    /**
     * The player can select only one company.
     *
     * @param company Company to be changed by user desire.
     * @return True if the selection is valid, false otherwise.
     */
    abstract boolean select(Company company);

    /**
     * Need selection by the player for a particular company.
     *
     * @return True if the company selection is needed, false otherwise.
     */
    abstract boolean needCompanySelection();

    /**
     * Constructor with card key.
     *
     * @param key Key value used in some collections.
     */
    Card(String key) {
        this.key = key;
    }

    /**
     * Get card key.
     *
     * @return Card key.
     */
    String key() {
        return key;
    }
}
