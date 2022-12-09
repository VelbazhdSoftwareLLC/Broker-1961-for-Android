package eu.veldsoft.broker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It is a card played in the game.
 */
abstract class Card {
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
    public abstract void play();

    /**
     * The player can select only one company.
     *
     * @param company Company to be changed by user desire.
     */
    public abstract void select(Company company);
}
