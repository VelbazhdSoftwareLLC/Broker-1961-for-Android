package eu.veldsoft.broker.model;

/**
 * Represents card 2x of second type.
 */
class Card2div extends Card {

    /**
     * Constructor with parameters.
     *
     * @param key     Key value used in some collections.
     * @param company Company which should half its price.
     */
    Card2div(String key, Company company) {
        super(key);

        up.clear();

        down.clear();
        down.add(company);
    }

    /**
     * Selected company go half down when selected company go double its price.
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
     * Selection of a company to double its price.
     *
     * @param company Company to be changed by user desire.
     * @return True if the selection is valid, false otherwise.
     */
    @Override
    boolean select(Company company) {
        if (down.contains(company)) {
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
    boolean needCompanySelection() {
        return true;
    }
}
