package eu.veldsoft.broker.model;

/**
 * Represents the company information.
 */
class Company {
    /**
     * Default inital company value.
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
     * Constructor by compoany name only with default company value.
     *
     * @param name The name of the company.
     */
    public Company(String name) {
        this(name, DEFAULT_VALUE);
    }

    /**
     * Constructor with all parameters needed.
     *
     * @param name  The name of the company.
     * @param price The initial price of the company.
     */
    public Company(String name, int price) {
        this.price = price;
        this.name = name;
    }

    /**
     * Set new price.
     *
     * @param price New price value.
     */
    void price(int price) {
        //TODO Price setting should be controlled according ot the game rules.
        this.price = price;
    }

    /**
     * Get current price.
     *
     * @return Current price value.
     */
    int price() {
        return price;
    }
}
