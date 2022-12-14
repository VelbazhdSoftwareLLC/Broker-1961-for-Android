package eu.veldsoft.broker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents all objects available in the game.
 */
public class Board {
    /**
     * Data structure for the state of the board.
     */
    enum State {NONE, PRE_ORDER, CARD_PLAY, POST_ORDER, GAME_END}

    ;

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
     * The player who is playing at the moment.
     */
    Player playing = null;

    /**
     * The state of the board.
     */
    State state = State.NONE;

    /**
     * All transactions done in the game.
     */
    List<Transaction> transactions = new ArrayList<Transaction>();

    /**
     * List of cards played during the gameplay.
     */
    List<Card> cards = new ArrayList<Card>();

    /**
     * Board constructor without parameters.
     */
    public Board() {
        companies.add(new Company("A"));
        companies.add(new Company("B"));
        companies.add(new Company("C"));
        companies.add(new Company("D"));

        deck = new Deck(companies);
    }

    /**
     * Get the player on turn.
     *
     * @return Currently playing player.
     */
    public Player playing() {
        return playing;
    }

    /**
     * Get prices of all companies.
     *
     * @return Array with companies' prices.
     */
    public int[] prices() {
        int prices[] = new int[companies.size()];

        int i = 0;
        for (Company c : companies) {
            prices[i++] = c.price();
        }

        return prices;
    }

    /**
     * List of played cards keys getter.
     *
     * @return Array with cards keys.
     */
    public String[] playedCardsKyes() {
        List<String> keys = new ArrayList<String>();

        for (Card c : cards) {
            keys.add(c.key());
        }

        return keys.toArray(new String[0]);
    }

    /**
     * List of current player cards keys getter.
     *
     * @return Array with cards keys.
     */
    public String[] currentPlayerCardsKyes() {
        if (playing == null) {
            return new String[0];
        }

        List<String> keys = new ArrayList<String>();

        for (Card c : playing.cards()) {
            keys.add(c.key());
        }

        return keys.toArray(new String[0]);
    }

    /**
     * Get name of the current playing player.
     *
     * @return The name of the player.
     */
    public String currentPlayerName() {
        return playing.name();
    }

    /**
     * Get report of the current playing player.
     *
     * @return The report of the player.
     */
    public String currentPlayerReport() {
        return playing.report();
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

        /*
         * The first player plays after start of the game.
         */
        playing = players.get(0);

        /*
         * Clear transactions of previous games.
         */
        transactions.clear();

        /*
         * Clear cards played in previous games.
         */
        cards.clear();

        /*
         * The first playing player can buy or sell.
         */
        state = State.POST_ORDER;
    }

    /**
     * Do things needed in the end of the turn.
     */
    public void endTurn() {
        int next = -1;
        int index = players.indexOf(playing);

        /*
         * Check the second half of the players.
         */
        for (int i = index + 1; i < players.size(); i++) {
            if (players.get(i).active() == true) {
                next = i;
                break;
            }
        }

        /*
         * Check the first half of the players.
         */
        if (next == -1) {
            for (int i = 0; i < index; i++) {
                if (players.get(i).active() == true) {
                    next = i;
                    break;
                }
            }
        }

        /*
         * There is no more active players.v The game should finish.
         */
        if (next == -1) {
            //TODO Report the end of the game.
            state = State.GAME_END;
        } else {
            playing = players.get(next);
        }
    }

    /**
     * Check selected by index card in the hand of the current player for company selection need.
     *
     * @param card Company index in the list of cards.
     * @return True if company selection is needed, false otherwise.
     */
    public boolean needCompanySelection(int card) {
        return playing.cards().get(card).needCompanySelection();
    }
}
