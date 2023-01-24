package eu.veldsoft.broker.model;

import java.util.List;

/**
 * Represents card 100.
 */
class Card100 extends Card {
    /**
     * Constructor with one of the companies as a reference.
     *
     * @param key       Key value used in some collections.
     * @param company   Compnay reference.
     * @param companies List of all companies.
     */
    Card100(String key, Company company, List<Company> companies) {
        super(key);

        up.clear();
        up.add(company);

        down.clear();
        for (Company c : companies) {
            /*
             * Up companies are not part of the down companies.
             */
            if (c == company) {
                continue;
            }

            down.add(c);
        }
    }

    /**
     * Selected company go up with 100 when all the others go down.
     */
    @Override
    void play() {
        for (Company u : up) {
            u.price(u.price() + 100);
        }

        for (Company d : down) {
            d.price(d.price() - 10);
        }
    }

    /**
     * Card 100 does not give the user chance to select companies.
     *
     * @param company Company to be changed by user desire.
     * @return True if the selection is valid, false otherwise.
     */
    @Override
    boolean select(Company company) {
        /* In this card player does not select. */
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean needCompanySelection() {
        return false;
    }
}
