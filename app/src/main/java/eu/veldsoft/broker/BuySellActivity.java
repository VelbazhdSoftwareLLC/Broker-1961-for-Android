package eu.veldsoft.broker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Buy or sell shares of the companies.
 */
public class BuySellActivity extends Activity {

    /**
     * Trading possibilities of the player - money, shares, prices.
     */
    private int possibilities[] = {};

    /**
     * Reference to the UI which shows the current cash, current shares, current price.
     */
    private EditText changes[] = new EditText[9];

    /**
     * Try to sell according to available shares.
     *
     * @param index The index of the company.
     */
    private void tryToSell(int index) {
        int money = Integer.valueOf(changes[0].getText().toString());
        int shares = Integer.valueOf(changes[index].getText().toString());
        int price = Integer.valueOf(changes[index + 4].getText().toString());

        /*
         * Not enough shares to sell.
         */
        if (shares <= 0) {
            return;
        }

        money += price;
        shares--;

        changes[0].setText("" + money);
        changes[index].setText("" + shares);
    }

    /**
     * Try to buy according to available money.
     *
     * @param index The index of the company.
     */
    private void tryToBuy(int index) {
        int money = Integer.valueOf(changes[0].getText().toString());
        int shares = Integer.valueOf(changes[index].getText().toString());
        int price = Integer.valueOf(changes[index + 4].getText().toString());

        /*
         * Not enough money to buy.
         */
        if (money < price) {
            return;
        }

        shares++;
        money -= price;

        changes[0].setText("" + money);
        changes[index].setText("" + shares);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell);

        /*
         * Reference to the current money UI.
         */
        changes[0] = (EditText) findViewById(R.id.availableCash);

        /*
         * References to the current shares UI.
         */
        changes[1] = (EditText) findViewById(R.id.aAmountNumber);
        changes[2] = (EditText) findViewById(R.id.bAmountNumber);
        changes[3] = (EditText) findViewById(R.id.cAmountNumber);
        changes[4] = (EditText) findViewById(R.id.dAmountNumber);

        /*
         * References to the current prices UI.
         */
        changes[5] = (EditText) findViewById(R.id.aCurrentPrice);
        changes[6] = (EditText) findViewById(R.id.bCurrentPrice);
        changes[7] = (EditText) findViewById(R.id.cCurrentPrice);
        changes[8] = (EditText) findViewById(R.id.dCurrentPrice);

        findViewById(R.id.aMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToSell(1);
            }
        });
        findViewById(R.id.bMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToSell(2);
            }
        });
        findViewById(R.id.cMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToSell(3);
            }
        });
        findViewById(R.id.dMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToSell(4);
            }
        });
        findViewById(R.id.aPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToBuy(1);
            }
        });
        findViewById(R.id.bPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToBuy(2);
            }
        });
        findViewById(R.id.cPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToBuy(3);
            }
        });
        findViewById(R.id.dPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToBuy(4);
            }
        });

        ((Button) findViewById(R.id.tradeFinish)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] shares = {0, 0, 0, 0};

                /*
                 * Calculate shares differences.
                 */
                for (int i = 0; i < shares.length; i++) {
                    shares[i] = Integer.valueOf(changes[i + 1].getText().toString()) - possibilities[i + 1];
                }

                setResult(Activity.RESULT_OK, (new Intent()).putExtra("buySellShares", shares));

                BuySellActivity.this.finish();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();

        possibilities = getIntent().getIntArrayExtra("possibilities");

        if (possibilities == null) {
            Toast.makeText(BuySellActivity.this, R.string.incorrect_trading_information_text, Toast.LENGTH_LONG).show();
            return;
        }

        /*
         * Player's money.
         */
        ((TextView) findViewById(R.id.availableCash)).setText("" + possibilities[0]);

        /*
         * Player's shares.
         */
        ((EditText) findViewById(R.id.aAmountNumber)).setText("" + possibilities[1]);
        ((EditText) findViewById(R.id.bAmountNumber)).setText("" + possibilities[2]);
        ((EditText) findViewById(R.id.cAmountNumber)).setText("" + possibilities[3]);
        ((EditText) findViewById(R.id.dAmountNumber)).setText("" + possibilities[4]);

        /*
         * Companies' prices.
         */
        ((TextView) findViewById(R.id.aCurrentPrice)).setText("" + possibilities[5]);
        ((TextView) findViewById(R.id.bCurrentPrice)).setText("" + possibilities[6]);
        ((TextView) findViewById(R.id.cCurrentPrice)).setText("" + possibilities[7]);
        ((TextView) findViewById(R.id.dCurrentPrice)).setText("" + possibilities[8]);
    }
}
