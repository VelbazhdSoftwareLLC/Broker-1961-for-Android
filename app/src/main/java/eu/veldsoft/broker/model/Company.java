package eu.veldsoft.broker.model;

/**
 * Represents the company information.
 */
class Company {
    /**
     * Default initial company value.
     */
    private static final int INITIAL_VALUE = 100;

    /**
     * The lowest company value.
     */
    private static final int LOWEST_VALUE = 10;

    /**
     * The highest company value.
     */
    private static final int HIGHEST_VALUE = 250;

    /**
     * Penalty value for price crash.
     */
    private static final int PENALTY_VALUE = -20;

    /**
     * Name of the company.
     */
    private String name = "";

    /**
     * The price is used also as marker information.
     */
    private int price = INITIAL_VALUE;

    /**
     * When the price goes above the upper limit, the dividend is given.
     */
    private int dividend = 0;

    /**
     * Constructor by company name only with default company value.
     *
     * @param name The name of the company.
     */
    Company(String name) {
        this(name, INITIAL_VALUE);
    }

    /**
     * Constructor with all parameters needed.
     *
     * @param name  The name of the company.
     * @param price The initial price of the company.
     */
    Company(String name, int price) {
        this.price = price;
        this.name = name;
    }

    /**
     * Set new price.
     *
     * @param price New price value.
     */
    void price(int price) {
        this.price = price;

        /*
         * Price setting should be controlled according ot the game rules.
         */
        this.price /= 10;
        this.price *= 10;

        if (this.price < LOWEST_VALUE) {
            //TODO Take the penalty.
            dividend = PENALTY_VALUE;
            this.price = LOWEST_VALUE;
        }
        if (this.price > HIGHEST_VALUE) {
            //TODO Give the dividend.
            dividend = this.price - HIGHEST_VALUE;
            this.price = HIGHEST_VALUE;
        }
    }

    /**
     * Get the name of the company.
     *
     * @return Company's name.
     */
    String name() {
        return name;
    }

    /**
     * Get current price.
     *
     * @return Current price value.
     */
    int price() {
        return price;
    }

    /**
     * Set current dividend;
     *
     * @param dividend The value of the dividend.
     */
    void dividend(int dividend) {
        this.dividend = dividend;
    }

    /**
     * Get current dividend.
     *
     * @return Current company dividend.
     */
    int dividend() {
        return dividend;
    }

    /**
     * Reset company state for each new game.
     */
    void reset() {
        price = INITIAL_VALUE;
    }
}
