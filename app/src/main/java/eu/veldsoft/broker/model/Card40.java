package eu.veldsoft.broker.model;

/**
 * Represents card 40.
 */
class Card40 extends Card {

    /**
     * Constructor with parameters.
     *
     * @param key     Key value used in some collections.
     * @param company Company which should down its price with 50.
     */
    public Card40(String key, Company company) {
        super(key);

        up.clear();

        down.clear();
        down.add(company);
    }

    /**
     * Selected company go down with 50 when selected company go up with 40.
     */
    @Override
    public void play() {
        for (Company u : up) {
            u.price(u.price() + 40);
        }

        for (Company d : down) {
            d.price(d.price() - 50);
        }
    }

    /**
     * Selection of a company to increase its price with 40.
     *
     * @param company Company to be changed by user desire.
     * @return True if the selection is valid, false otherwise.
     */
    @Override
    public boolean select(Company company) {
        if (down.contains(company) == true) {
            return false;
        }

        up.clear();
        up.add(company);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean needCompanySelection() {
        return true;
    }
}
