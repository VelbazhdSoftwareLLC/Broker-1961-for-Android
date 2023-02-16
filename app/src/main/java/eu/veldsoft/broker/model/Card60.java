package eu.veldsoft.broker.model;

/**
 * Represents card 60.
 */
class Card60 extends Card {

    /**
     * Constructor with parameters.
     *
     * @param key     Key value used in some collections.
     * @param company Company which should rise its price with 60.
     */
    Card60(String key, Company company) {
        super(key);

        up.clear();
        up.add(company);

        down.clear();
    }

    /**
     * Selected company go up with 60 when selected company go down with 30.
     */
    @Override
    void play() {
        for (Company u : up) {
            u.price(u.price() + 60);
        }

        for (Company d : down) {
            d.price(d.price() - 30);
        }
    }

    /**
     * Selection of a company to decrease its price with 30.
     *
     * @param company Company to be changed by user desire.
     * @return True if the selection is valid, false otherwise.
     */
    @Override
    boolean select(Company company) {
        if (up.contains(company)) {
            return false;
        }

        down.clear();
        down.add(company);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean needCompanySelection() {
        return true;
    }
}
