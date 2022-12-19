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
    private Share sahre = null;

    /**
     * When transaction is done - before or after card playing.
     */
    private Time time = null;

    /**
     * Reference to the player who did the transaction.
     */
    private Player player = null;

    //TODO Create constructor with all parameters.

    //TODO Create all getters. Do not crate setters!
}
