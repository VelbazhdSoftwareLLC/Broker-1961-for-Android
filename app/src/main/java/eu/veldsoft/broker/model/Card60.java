package eu.veldsoft.broker.model;

/**
 * Represents card 60.
 */
class Card60 extends Card {

    /**
     * Constructor with parameters.
     * @param company Company which should rise its price with 60.
     */
    public Card60(Company company) {
        super();

        up.clear();
        up.add(company);

        down.clear();
    }

    /**
     * Selected company go up with 60 when selected company go down with 30.
     */
    @Override
    public void play() {
        for(Company u : up) {
            u.price( u.price() + 60 );
        }

        for(Company d : down) {
            d.price( d.price() - 30 );
        }
    }

    /**
     * Selection of a company to decrease its price with 30.
     * @param company Company to be changed by user desire.
     */
    @Override
    public void select(Company company) {
        down.clear();
        down.add(company);
    }
}
