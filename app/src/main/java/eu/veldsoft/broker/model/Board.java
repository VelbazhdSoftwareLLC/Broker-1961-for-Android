package eu.veldsoft.broker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents all objects available in the game.
 */
public class Board {
    /**
     * List of companies.
     */
    List<Company> companies = new ArrayList<Company>();

    /**
     * Deck with the cards.
     */
    Deck deck = null;

    /**
     * List of players.
     */
    List<Player> players = new ArrayList<Player>();

    /**
     * Board constructor without parameters.
     */
    public Board() {
        companies.add(new Company("A"));
        companies.add(new Company("B"));
        companies.add(new Company("C"));
        companies.add(new Company("D"));

        Deck deck = new Deck(companies);
    }

    /**
     * Start new game or restart current game.
     *
     * @param playersNames List with the names of the players.
     */
    public void newGame(String playersNames[]) {
        if (playersNames.length < 2 && 6 < playersNames.length) {
            throw new RuntimeException("Incorrect number of players!");
        }

        /*
         * The maximum number of players is 6.
         */
        for (String name : playersNames) {
            players.add(new Player(name));
        }

        /*
         * Deal cards to the players.
         */
        deck.shuffle();
        for (Player p : players) {
            p.getInitialCards(deck.deal());
        }

        //TODO It is better each player to be able to be in each order.
        Collections.shuffle(players);
    }
}
