package eu.veldsoft.broker.model;

/**
 * Represents card 2x of first type.
 */
class Card2mul extends Card {

    /**
     * Constructor with parameters.
     *
     * @param key     Key value used in some collections.
     * @param company Company which should double its price.
     */
    Card2mul(String key, Company company) {
        super(key);

        up.clear();
        up.add(company);

        down.clear();
    }

    /**
     * Selected company go up double when selected company go twice down.
     */
    @Override
    void play() {
        for (Company u : up) {
            u.price(u.price() * 2);
        }

        for (Company d : down) {
            //TODO Check division according to the game's rule.
            d.price(d.price() / 2);
        }
    }

    /**
     * Selection of a company to half its price.
     *
     * @param company Company to be changed by user desire.
     * @return True if the selection is valid, false otherwise.
     */
    @Override
    boolean select(Company company) {
        if (up.contains(company) == true) {
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
