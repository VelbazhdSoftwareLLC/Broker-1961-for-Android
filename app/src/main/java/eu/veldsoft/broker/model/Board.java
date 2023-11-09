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
                Share share = playing.buy(companies.get(i), shares[i]);
                if (share == null) {
                    success = false;
                } else {
                    transactions.add(new Transaction(Transaction.Type.BUY, Transaction.Time.PREORDER, round, share, playing));
                }
            }
            if (shares[i] < 0) {
                /*
                 * Try to sell.
                 */
                Share share = playing.sell(companies.get(i), -shares[i]);
                if (share == null) {
                    success = false;
                } else {
                    transactions.add(new Transaction(Transaction.Type.SELL, Transaction.Time.PREORDER, round, share, playing));
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
        boolean success = true;

        /*
         * Apply the key rule.
         */
        for (Transaction t : transactions) {
            /*
             * Only transactions of the current player are important.
             */
            if (t.player() != playing) {
                continue;
            }

            /*
             * Only transactions in the current round are important.
             */
            if (t.round() != round) {
                continue;
            }

            /*
             * Only pre-order transactions are important.
             */
            if (t.time() != Transaction.Time.PREORDER) {
                continue;
            }

            for (int i = 0; i < shares.length; i++) {
                if (shares[i] < 0 && t.type() == Transaction.Type.BUY && companies.get(i).price() > t.share().price()) {
                    //TODO Implement better reporting!
                    shares[i] = 0;
                }

                if (shares[i] > 0 && t.type() == Transaction.Type.SELL && companies.get(i).price() < t.share().price()) {
                    //TODO Implement better reporting!
                    shares[i] = 0;
                }
            }
        }

        for (int i = 0; i < shares.length; i++) {
            if (shares[i] > 0) {
                /*
                 * Try to buy.
                 */
                Share share = playing.buy(companies.get(i), shares[i]);
                if (share == null) {
                    success = false;
                } else {
                    transactions.add(new Transaction(Transaction.Type.BUY, Transaction.Time.POSTORDER, round, share, playing));
                }
            }
            if (shares[i] < 0) {
                /*
                 * Try to sell.
                 */
                Share share = playing.sell(companies.get(i), -shares[i]);
                if (share == null) {
                    success = false;
                } else {
                    transactions.add(new Transaction(Transaction.Type.SELL, Transaction.Time.POSTORDER, round, share, playing));
                }
            }
        }

        return success;
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
        int[] prices = new int[companies.size()];

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
     * Get finished game flag.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean finished() {
        return state == State.GAME_END;
    }

    /**
     * Get report of the end of the game.
     *
     * @return The report of the end of the game.
     */
    public String endReport() {
        if (state != State.GAME_END) {
            return "The game is in progress ...";
        }

        if (state == State.GAME_END) {
            String text = "";

            text += "The End of the Game Report";
            text += "\n";
            text += "\n";

            for (Player p : players) {
                text += p.report();
                text += "\n";
            }

            return text;
        }

        return "";
    }

    /**
     * Start new game or restart current game.
     *
     * @param playersNames List with the names of the players.
     * @return True if the game starts, false otherwise.
     */
    public boolean newGame(String[] playersNames) {
        if (playersNames.length < 2 || 6 < playersNames.length) {
            return false;
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

        return true;
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
            if (players.get(i).active()) {
                next = i;
                break;
            }
        }

        /*
         * Check the first half of the players.
         */
        if (next == -1) {
            for (int i = 0; i < current; i++) {
                if (players.get(i).active()) {
                    next = i;
                    break;
                }
            }
        }

        playing.update();

        /*
         * There is no more active players.v The game should finish.
         */
        if (next == -1) {
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
            /*
             * Adjust prices above 250 and below 10.
             */
            for (Company c : companies) {
                for (Player p : players) {
                    /*
                     * Give dividend.
                     */
                    if (c.dividend() > 0) {
                        p.dividend(c);
                    }

                    /*
                     * Take penalty.
                     */
                    if (c.dividend() < 0) {
                        p.penalty(c);
                    }
                }

                c.dividend(0);
            }

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
        /*
         * Update game state after successful trading.
         */
        if (state == State.PRE_ORDER) {
            if (preTrade(shares)) {
                state = State.CARD_PLAY;
                return true;
            }
        } else if (state == State.POST_ORDER) {
            if (postOrder(shares)) {
                state = State.TURN_END;
                return true;
            }
        }

        return false;
    }

    /**
     * All player sell all shares.
     */
    public void totalSale() {
        for (Player p : players) {
            for (Share s : p.shares()) {
                Share share = p.sell(s.company(), -s.amount());
                if (share != null) {
                    transactions.add(new Transaction(Transaction.Type.SELL, Transaction.Time.POSTORDER, round, share, p));
                }
            }
        }
    }

    /**
     * Game progress checker.
     *
     * @return True if the game is in progress, false otherwise.
     */
    public boolean gameInProgress() {
        return state != State.NONE;
    }

    /**
     * Calculate shortages of the players to pay penalties.
     *
     * @return Array with amount of money to pay for all players who have penalty to pay.
     */
    public int[] playersPenaltiesShortages() {
        int totalPenalty = 0;
        for (Company c : companies) {
            if (c.dividend() >= 0) {
                continue;
            }

            totalPenalty += (-c.dividend());
        }

        int shortages[] = new int[players.size()];
        for (int i = 0; i < shortages.length; i++) {
            shortages[i] = 0;

            /*
             * Inactive player are not calculated.
             */
            if (players.get(i).active() == false) {
                continue;
            }

            /*
             * Player with money are not interesting.
             */
            if (players.get(i).money() >= totalPenalty) {
                continue;
            }

            shortages[i] = totalPenalty - players.get(i).money();
        }

        return shortages;
    }

    /**
     * Player's portfolio.
     *
     * @param playerIndex Player's index.
     * @return Object array with first element the name of the player, player's shares and companies' prices.
     */
    public Object[] portfolio(int playerIndex) {
        Object result[] = {"", 0, 0, 0, 0, 0, 0, 0, 0};

        result[0] = players.get(playerIndex).name();

        for (Share s : players.get(playerIndex).shares()) {
            result[companies.indexOf(s.company()) + 1] = (Integer) result[companies.indexOf(s.company()) + 1] + s.amount();
        }

        result[5] = companies.get(0).price();
        result[6] = companies.get(1).price();
        result[7] = companies.get(2).price();
        result[8] = companies.get(3).price();

        return result;
    }

    /**
     * Return trading possibilities of the current player.
     *
     * @return Array of integer values money, shares, prices.
     */
    public int[] currentPlayerTradingPossibilities() {
        int possibilities[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};

        possibilities[0] = playing.money();

        for (Share s : playing.shares()) {
            possibilities[companies.indexOf(s.company()) + 1] = (Integer) possibilities[companies.indexOf(s.company()) + 1] + s.amount();
        }

        possibilities[5] = companies.get(0).price();
        possibilities[6] = companies.get(1).price();
        possibilities[7] = companies.get(2).price();
        possibilities[8] = companies.get(3).price();

        return possibilities;
    }
}
