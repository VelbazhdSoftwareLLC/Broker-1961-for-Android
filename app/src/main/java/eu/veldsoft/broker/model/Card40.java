package eu.veldsoft.broker.model;

/**
 * Represents card 40.
 */
class Card40 extends Card {

    /**
     * Constructor with parameters.
     * @param company Company which should down its price with 50.
     */
    public Card40(Company company) {
        super();

        up.clear();

        down.clear();
        down.add(company);
    }

    /**
     * Selected company go down with 50 when selected company go up with 40.
     */
    @Override
    public void play() {
        for(Company u : up) {
            u.price( u.price() + 40 );
        }

        for(Company d : down) {
            d.price( d.price() - 50 );
        }
    }

    /**
     * Selection of a company to increase its price with 40.
     * @param company Company to be changed by user desire.
     */
    @Override
    public void select(Company company) {
        up.clear();
        up.add(company);
    }
}
