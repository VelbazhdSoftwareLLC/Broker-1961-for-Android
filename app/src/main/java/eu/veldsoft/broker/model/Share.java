package eu.veldsoft.broker.model;

/**
 * Represents shares hold by the players.
 */
class Share {
    /**
     * Reference to the company for which the shares are.
     */
    private Company company;

    /**
     * The amount of shares.
     */
    private int amount;

    /**
     * The price of buy/sell.
     */
    private int price;

    /**
     * Constructor with all parameters.
     *
     * @param company Reference to the particular company.
     * @param amount  The amount of initial shares.
     * @param price The price of buy/sell.
     */
    Share(Company company, int amount, int price) {
        this.company = company;
        this.amount = amount;
        this.price = price;
    }

    /**
     * Constructor for liking shares with a company.
     *
     * @param company Reference to the particular company.
     */
    Share(Company company) {
        this(company, 0, 0);
    }

    /**
     * Get company for this shares.
     *
     * @return Reference to the company instance.
     */
    Company company() {
        return company;
    }

    /**
     * Set the amount of shares for the associated company.
     *
     * @param amount Number of shares.
     */
    void amount(int amount) {
        this.amount = amount;
    }

    /**
     * Get the amount of shares for the associated compnay.
     *
     * @return Number of shares.
     */
    int amount() {
        return amount;
    }

    /**
     * Get the price of buy or sell.
     *
     * @return The value of a single share.
     */
    int price() {
        return price;
    }
}
