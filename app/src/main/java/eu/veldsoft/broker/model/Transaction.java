package eu.veldsoft.broker.model;

/**
 * It is a description for each buy/sell action.
 */
class Transaction {
    /**
     * The type of the transaction.
     */
    private Type type = null;
    /**
     * When transaction is done - before or after card playing.
     */
    private Time time = null;
    /**
     * The round of the game when the transaction is done.
     */
    private int round = 0;
    /**
     * Reference to the shares object.
     */
    private Share share = null;
    /**
     * Reference to the player who did the transaction.
     */
    private Player player = null;

    /**
     * Constructor with all parameters.
     *
     * @param type   Is it a buy o sell transaction.
     * @param time   Before or after card playing.
     * @param round  The round of the game.
     * @param share  Reference to shares object.
     * @param player The player who did the transaction.
     */
    Transaction(Type type, Time time, int round, Share share, Player player) {
        super();
        this.type = type;
        this.time = time;
        this.round = round;
        this.share = share;
        this.player = player;
    }

    /**
     * Get type of transaction.
     *
     * @return Type of the transaction.
     */
    Type type() {
        return type;
    }

    /**
     * Get time of transaction.
     *
     * @return Time of the transaction - before or after card playing.
     */
    Time time() {
        return time;
    }

    /**
     * Get round of the game.
     *
     * @return The round of the game when the transaction happened.
     */
    int round() {
        return round;
    }

    /**
     * Get share of the transaction.
     *
     * @return Shares participating in the transaction.
     */
    Share share() {
        return share;
    }

    /**
     * Get reference to the player object.
     *
     * @return Reference to the player instance.
     */
    Player player() {
        return player;
    }

    /**
     * Each transaction can be buy or sell.
     */
    enum Type {BUY, SELL}

    /**
     * The transaction can be done before card playing and after card playing.
     */
    enum Time {PREORDER, POSTORDER, URGENT, FINAL}
}
