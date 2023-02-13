package eu.veldsoft.broker.model;

/**
 * Represents the company information.
 */
class Company {
    /**
     * Default initial company value.
     */
    private static final int DEFAULT_VALUE = 100;

    /**
     * Name of the company.
     */
    private String name = "";

    /**
     * The price is used also as marker information.
     */
    private int price = DEFAULT_VALUE;

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
        this(name, DEFAULT_VALUE);
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

        //TODO Magic numbers should not be used.
        if (this.price < 10) {
            //TODO Take the penalty.
            dividend = -20;
            this.price = 10;
        }
        if (this.price > 250) {
            //TODO Give the dividend.
            dividend = this.price - 250;
            this.price = 250;
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
     * Set current divident;
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
        price = DEFAULT_VALUE;
    }
}
