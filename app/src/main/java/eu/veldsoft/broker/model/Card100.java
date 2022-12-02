package eu.veldsoft.broker.model;

/**
 * Represents card 100.
 */
class Card100 extends Card {
    /**
     * Reference to one of the companies.
     */
    private Company company = null;

    /**
     * Constructor with one of the companies as a reference.
     * @param company Compnay reference.
     */
    public Card100(Company company) {
        this.company = company;
    }
}
