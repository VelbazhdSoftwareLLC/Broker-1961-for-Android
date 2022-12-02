package eu.veldsoft.broker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents all objects available in the game.
 */
class Board {
    /**
     * List of companies.
     */
    List<Company> companies = new ArrayList<Company>();

    /**
     * Deck with the cards.
     */
    Deck deck = null;

    /**
     * Number of active players.
     */
    int numberOfPlayers = 0;

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

        /*
         * The maximum number of players is 6.
         */
        for(int i=0; i<6; i++) {
            players.add(new Player());
        }
    }

    /**
     * Start new game or restart current game.
     */
    public void newGame(int numberOfPlayers) {
        if(numberOfPlayers < 2 && 6 < numberOfPlayers) {
            throw new RuntimeException("Incorrect number of players!");
        }
        this.numberOfPlayers = numberOfPlayers;

        deck.shuffle();
        for(int p=0; p<numberOfPlayers; p++) {
            players.get(p).getInitialCards( deck.deal() );
        }
    }
}
