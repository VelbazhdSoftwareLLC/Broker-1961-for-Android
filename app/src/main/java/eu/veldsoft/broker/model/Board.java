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
    enum State {
        NONE(""), PRE_ORDER("pre order"), CARD_PLAY("play card"), POST_ORDER("post order"), TURN_END(""), GAME_END("");
        /**
         * Text of the state;
         */
        private String text;

        private State(String text) {
            this.text = text;
        }

        /**
         * Get the text of the state.
         *
         * @return Text of the state.
         */
        public String text() {
            return text;
        }
    }

    /**
     * List of companies.
     */
    private List<Company> companies = new ArrayList<Company>();

    /**
     * Deck with the cards.
     */
    private Deck deck = null;

    /**
     * List of players.
     */
    private List<Player> players = new ArrayList<Player>();

    /**
     * The player who is playing at the moment.
     */
    private Player playing = null;

    /**
     * The state of the board.
     */
    private State state = State.NONE;

    /**
     * The game goes in turns.
     */
    private int round = 0;

    /**
     * All transactions done in the game.
     */
    private List<Transaction> transactions = new ArrayList<Transaction>();

    /**
     * List of cards played during the gameplay.
     */
    private List<Card> cards = new ArrayList<Card>();

    /**
     * Try to trade shares before card play.
     *
     * @param shares Shares to sell (negative numbers) or to buy (positive numbers).
     * @return True if the trading was successful, false otherwise.
     */
    private boolean preTrade(int[] shares) {
        boolean success = true;

        for (int i = 0; i < shares.length; i++) {
            if (shares[i] > 0) {
                /*
                 * Try to buy.
                 */
                if (playing.buy(companies.get(i), shares[i]) == null) {
                    success = false;
                }
            }
            if (shares[i] < 0) {
                /*
                 * Try to sell.
                 */
                if (playing.sell(companies.get(i), -shares[i]) == null) {
                    success = false;
                }
            }
        }

        return success;
    }

    /**
     * Try to trade shares after card play.
     *
     * @param shares Shares to sell (negative numbers) or to buy (positive numbers).
     * @return True if the trading was successful, false otherwise.
     */
    private boolean postOrder(int[] shares) {
        return false;
    }

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
    public String currentPlayerInfo() {
        return playing.name() + " (round " + round + " - " + state.text() + ")";
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
        players.clear();
        for (String name : playersNames) {
            players.add(new Player(name));
        }

        /*
         * Reset initial price.
         */
        for (Company c : companies) {
            c.reset();
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
        state = State.PRE_ORDER;

        /*
         * In the real life counting usually starts from one, not from zero.
         */
        round = 1;
    }

    /**
     * Do things needed in the end of the turn.
     *
     * @return True for successful turn end, false otherwise.
     */
    public boolean endTurn() {
        /*
         * The card is played only if it is time to be played.
         */
        if (state != State.TURN_END && state != State.POST_ORDER) {
            return false;
        }

        /*
         * The index of the current playing player.
         */
        int current = players.indexOf(playing);

        /*
         * The index of the next playing player.
         */
        int next = -1;

        /*
         * Check the second half of the players.
         */
        for (int i = current + 1; i < players.size(); i++) {
            if (players.get(i).active() == true) {
                next = i;
                break;
            }
        }

        /*
         * Check the first half of the players.
         */
        if (next == -1) {
            for (int i = 0; i < current; i++) {
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
            state = State.PRE_ORDER;

            /*
             * Next round in the game.
             */
            if (next < current) {
                round++;
            }
        }

        return true;
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

    /**
     * Try to play a selected card.
     *
     * @param cardIndex    Index of the card in the player's list of cards.
     * @param companyIndex Index of a selected company to change price.
     * @return True if the card playing was successful, false otherwise.
     */
    public boolean play(int cardIndex, int companyIndex) {
        /*
         * The card is played only if it is time to be played.
         */
        if (state != State.CARD_PLAY && state != State.PRE_ORDER) {
            return false;
        }

        Company company = null;
        if (0 <= companyIndex && companyIndex < companies.size()) {
            company = companies.get(companyIndex);
        }

        /*
         * Keep the played card.
         */
        Card card = playing.play(cardIndex, company);
        if (card != null) {
            cards.add(card);
            state = State.POST_ORDER;
        }

        return true;
    }

    /**
     * Try to trade shares.
     *
     * @param shares Shares to sell (negative numbers) or to buy (positive numbers).
     * @return True if the trading was successful, false otherwise.
     */
    public boolean trade(int[] shares) {
        //TODO Checking of valid trade.

        /*
         * Update game state after successful trading.
         */
        if (state == State.PRE_ORDER) {
            if (preTrade(shares) == true) {
                state = State.CARD_PLAY;
                return true;
            }
        } else if (state == State.POST_ORDER) {
            if (postOrder(shares) == true) {
                state = State.TURN_END;
                return true;
            }
        }

        return false;
    }
}
