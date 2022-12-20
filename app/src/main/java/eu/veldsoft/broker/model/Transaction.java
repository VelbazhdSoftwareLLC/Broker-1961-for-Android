package eu.veldsoft.broker.model;

/**
 * It is a description for each buy/sell action.
 */
class Transaction {
    /**
     * Each transaction can be buy or sell.
     */
    enum Type {BUY, SELL}

    ;

    /**
     * The transaction can be done before card playing and after card playing.
     */
    enum Time {PREORDER, POSTORDER}

    ;

    /**
     * The type of the transaction.
     */
    private Type type = null;

    /**
     * Reference to the shares object.
     */
    private Share share = null;

    /**
     * When transaction is done - before or after card playing.
     */
    private Time time = null;

    /**
     * Reference to the player who did the transaction.
     */
    private Player player = null;

    /**
     * Constructor with all parameters.
     *
     * @param type   Is it a buy o sell transaction.
     * @param share  Reference to shares object.
     * @param time   Before or after card playing.
     * @param player The player who did the transaction.
     */
    public Transaction(Type type, Share share, Time time, Player player) {
        super();
        this.type = type;
        this.share = share;
        this.time = time;
        this.player = player;
    }

    /**
     * Get type of transaction.
     *
     * @return Type of the transaction.
     */
    public Type type() {
        return type;
    }

    /**
     * Get share of the transaction.
     *
     * @return Shares participating in the transaction.
     */
    public Share share() {
        return share;
    }

    /**
     * Get time of transaction.
     *
     * @return Time of the transaction - before or after card playing.
     */
    public Time time() {
        return time;
    }

    /**
     * Get reference to the player object.
     *
     * @return Reference to the player instance.
     */
    public Player player() {
        return player;
    }
}
