package eu.veldsoft.broker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.veldsoft.broker.model.Board;

/**
 * Main game screen.
 */
public class GameActivity extends Activity {
    /**
     * The identifier for launching activity.
     */
    private static final int LAUNCH_PLAYERS_LIST_ACTIVITY = 1;

    /**
     * The identifier for launching activity.
     */
    private static final int LAUNCH_PLAY_CARD_ACTIVITY = 2;

    /**
     * The identifier for launching activity.
     */
    private static final int LAUNCH_BUY_SELL_ACTIVITY = 3;

    /**
     * The identifier for launching activity.
     */
    private static final int LAUNCH_URGENT_SALE_ACTIVITY = 4;

    /**
     * Board view references.
     */
    private static ImageView BOARD_IMAGE = null;

    /**
     * Array of references to markers views.
     */
    private static final ImageView[] MARKERS_IMAGES = new ImageView[4];

    /**
     * Map of the card key and card image.
     */
    static final Map<String, Integer> CARDS_IMAGES = new HashMap<String, Integer>();

    /**
     * Map of the company, price and image.
     */
    static final Map PRICES_IMAGES[] = {new HashMap<Integer, Integer>(), new HashMap<Integer, Integer>(), new HashMap<Integer, Integer>(), new HashMap<Integer, Integer>()};

    /**
     * Scaling in X.
     */
    private static float xScale = 1;

    /**
     * Scaling in Y.
     */
    private static float yScale = 1;

    /**
     * The link between view layer and object model is the instance of the Board class. It is static because it will be needed in other activities.
     */
    private static Board board = new Board();

    /**
     * Get board reference.
     *
     * @return The board reference.
     */
    static Board board() {
        return board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /*
         * Map card keys to card image resource identifiers.
         */
        if (CARDS_IMAGES.size() == 0) {
            CARDS_IMAGES.put("100_A_10_3", R.drawable.a100);
            CARDS_IMAGES.put("100_B_10_3", R.drawable.b100);
            CARDS_IMAGES.put("100_C_10_3", R.drawable.c100);
            CARDS_IMAGES.put("100_D_10_3", R.drawable.d100);
            CARDS_IMAGES.put("2x_1_12_A", R.drawable.a2div);
            CARDS_IMAGES.put("2x_1_12_B", R.drawable.b2div);
            CARDS_IMAGES.put("2x_1_12_C", R.drawable.c2div);
            CARDS_IMAGES.put("2x_1_12_D", R.drawable.d2div);
            CARDS_IMAGES.put("2x_A_12_1", R.drawable.a2x);
            CARDS_IMAGES.put("2x_B_12_1", R.drawable.b2x);
            CARDS_IMAGES.put("2x_C_12_1", R.drawable.c2x);
            CARDS_IMAGES.put("2x_D_12_1", R.drawable.d2x);
            CARDS_IMAGES.put("40_1_50_A", R.drawable.a50);
            CARDS_IMAGES.put("40_1_50_B", R.drawable.b50);
            CARDS_IMAGES.put("40_1_50_C", R.drawable.c50);
            CARDS_IMAGES.put("40_1_50_D", R.drawable.d50);
            CARDS_IMAGES.put("60_A_30_1", R.drawable.a60);
            CARDS_IMAGES.put("60_B_30_1", R.drawable.b60);
            CARDS_IMAGES.put("60_C_30_1", R.drawable.c60);
            CARDS_IMAGES.put("60_D_30_1", R.drawable.d60);
        }

        /*
         * Map company index and company price to price image resource identifiers.
         */
        if (PRICES_IMAGES[0].size() == 0) {
            PRICES_IMAGES[0].put(10, R.drawable.a010marker);
            PRICES_IMAGES[0].put(20, R.drawable.a020marker);
            PRICES_IMAGES[0].put(30, R.drawable.a030marker);
            PRICES_IMAGES[0].put(40, R.drawable.a040marker);
            PRICES_IMAGES[0].put(50, R.drawable.a050marker);
            PRICES_IMAGES[0].put(60, R.drawable.a060marker);
            PRICES_IMAGES[0].put(70, R.drawable.a070marker);
            PRICES_IMAGES[0].put(80, R.drawable.a080marker);
            PRICES_IMAGES[0].put(90, R.drawable.a090marker);
            PRICES_IMAGES[0].put(100, R.drawable.a100marker);
            PRICES_IMAGES[0].put(110, R.drawable.a110marker);
            PRICES_IMAGES[0].put(120, R.drawable.a120marker);
            PRICES_IMAGES[0].put(130, R.drawable.a130marker);
            PRICES_IMAGES[0].put(140, R.drawable.a140marker);
            PRICES_IMAGES[0].put(150, R.drawable.a150marker);
            PRICES_IMAGES[0].put(160, R.drawable.a160marker);
            PRICES_IMAGES[0].put(170, R.drawable.a170marker);
            PRICES_IMAGES[0].put(180, R.drawable.a180marker);
            PRICES_IMAGES[0].put(190, R.drawable.a190marker);
            PRICES_IMAGES[0].put(200, R.drawable.a200marker);
            PRICES_IMAGES[0].put(210, R.drawable.a210marker);
            PRICES_IMAGES[0].put(220, R.drawable.a220marker);
            PRICES_IMAGES[0].put(230, R.drawable.a230marker);
            PRICES_IMAGES[0].put(240, R.drawable.a240marker);
            PRICES_IMAGES[0].put(250, R.drawable.a250marker);
        }
        if (PRICES_IMAGES[1].size() == 0) {
            PRICES_IMAGES[1].put(10, R.drawable.b010marker);
            PRICES_IMAGES[1].put(20, R.drawable.b020marker);
            PRICES_IMAGES[1].put(30, R.drawable.b030marker);
            PRICES_IMAGES[1].put(40, R.drawable.b040marker);
            PRICES_IMAGES[1].put(50, R.drawable.b050marker);
            PRICES_IMAGES[1].put(60, R.drawable.b060marker);
            PRICES_IMAGES[1].put(70, R.drawable.b070marker);
            PRICES_IMAGES[1].put(80, R.drawable.b080marker);
            PRICES_IMAGES[1].put(90, R.drawable.b090marker);
            PRICES_IMAGES[1].put(100, R.drawable.b100marker);
            PRICES_IMAGES[1].put(110, R.drawable.b110marker);
            PRICES_IMAGES[1].put(120, R.drawable.b120marker);
            PRICES_IMAGES[1].put(130, R.drawable.b130marker);
            PRICES_IMAGES[1].put(140, R.drawable.b140marker);
            PRICES_IMAGES[1].put(150, R.drawable.b150marker);
            PRICES_IMAGES[1].put(160, R.drawable.b160marker);
            PRICES_IMAGES[1].put(170, R.drawable.b170marker);
            PRICES_IMAGES[1].put(180, R.drawable.b180marker);
            PRICES_IMAGES[1].put(190, R.drawable.b190marker);
            PRICES_IMAGES[1].put(200, R.drawable.b200marker);
            PRICES_IMAGES[1].put(210, R.drawable.b210marker);
            PRICES_IMAGES[1].put(220, R.drawable.b220marker);
            PRICES_IMAGES[1].put(230, R.drawable.b230marker);
            PRICES_IMAGES[1].put(240, R.drawable.b240marker);
            PRICES_IMAGES[1].put(250, R.drawable.b250marker);
        }
        if (PRICES_IMAGES[2].size() == 0) {
            PRICES_IMAGES[2].put(10, R.drawable.c010marker);
            PRICES_IMAGES[2].put(20, R.drawable.c020marker);
            PRICES_IMAGES[2].put(30, R.drawable.c030marker);
            PRICES_IMAGES[2].put(40, R.drawable.c040marker);
            PRICES_IMAGES[2].put(50, R.drawable.c050marker);
            PRICES_IMAGES[2].put(60, R.drawable.c060marker);
            PRICES_IMAGES[2].put(70, R.drawable.c070marker);
            PRICES_IMAGES[2].put(80, R.drawable.c080marker);
            PRICES_IMAGES[2].put(90, R.drawable.c090marker);
            PRICES_IMAGES[2].put(100, R.drawable.c100marker);
            PRICES_IMAGES[2].put(110, R.drawable.c110marker);
            PRICES_IMAGES[2].put(120, R.drawable.c120marker);
            PRICES_IMAGES[2].put(130, R.drawable.c130marker);
            PRICES_IMAGES[2].put(140, R.drawable.c140marker);
            PRICES_IMAGES[2].put(150, R.drawable.c150marker);
            PRICES_IMAGES[2].put(160, R.drawable.c160marker);
            PRICES_IMAGES[2].put(170, R.drawable.c170marker);
            PRICES_IMAGES[2].put(180, R.drawable.c180marker);
            PRICES_IMAGES[2].put(190, R.drawable.c190marker);
            PRICES_IMAGES[2].put(200, R.drawable.c200marker);
            PRICES_IMAGES[2].put(210, R.drawable.c210marker);
            PRICES_IMAGES[2].put(220, R.drawable.c220marker);
            PRICES_IMAGES[2].put(230, R.drawable.c230marker);
            PRICES_IMAGES[2].put(240, R.drawable.c240marker);
            PRICES_IMAGES[2].put(250, R.drawable.c250marker);
        }
        if (PRICES_IMAGES[3].size() == 0) {
            PRICES_IMAGES[3].put(10, R.drawable.d010marker);
            PRICES_IMAGES[3].put(20, R.drawable.d020marker);
            PRICES_IMAGES[3].put(30, R.drawable.d030marker);
            PRICES_IMAGES[3].put(40, R.drawable.d040marker);
            PRICES_IMAGES[3].put(50, R.drawable.d050marker);
            PRICES_IMAGES[3].put(60, R.drawable.d060marker);
            PRICES_IMAGES[3].put(70, R.drawable.d070marker);
            PRICES_IMAGES[3].put(80, R.drawable.d080marker);
            PRICES_IMAGES[3].put(90, R.drawable.d090marker);
            PRICES_IMAGES[3].put(100, R.drawable.d100marker);
            PRICES_IMAGES[3].put(110, R.drawable.d110marker);
            PRICES_IMAGES[3].put(120, R.drawable.d120marker);
            PRICES_IMAGES[3].put(130, R.drawable.d130marker);
            PRICES_IMAGES[3].put(140, R.drawable.d140marker);
            PRICES_IMAGES[3].put(150, R.drawable.d150marker);
            PRICES_IMAGES[3].put(160, R.drawable.d160marker);
            PRICES_IMAGES[3].put(170, R.drawable.d170marker);
            PRICES_IMAGES[3].put(180, R.drawable.d180marker);
            PRICES_IMAGES[3].put(190, R.drawable.d190marker);
            PRICES_IMAGES[3].put(200, R.drawable.d200marker);
            PRICES_IMAGES[3].put(210, R.drawable.d210marker);
            PRICES_IMAGES[3].put(220, R.drawable.d220marker);
            PRICES_IMAGES[3].put(230, R.drawable.d230marker);
            PRICES_IMAGES[3].put(240, R.drawable.d240marker);
            PRICES_IMAGES[3].put(250, R.drawable.d250marker);
        }

        /*
         * Get board view reference.
         */
        BOARD_IMAGE = findViewById(R.id.boardImageView);
        BOARD_IMAGE.setVisibility(View.INVISIBLE);

        /*
         * Get markers views references.
         */
        MARKERS_IMAGES[0] = findViewById(R.id.aPullImageView);
        MARKERS_IMAGES[1] = findViewById(R.id.bPullImageView);
        MARKERS_IMAGES[2] = findViewById(R.id.cPullImageView);
        MARKERS_IMAGES[3] = findViewById(R.id.dPullImageView);
        for (View view : MARKERS_IMAGES) {
            view.setVisibility(View.INVISIBLE);
        }

        redraw();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                startActivityForResult(new Intent(GameActivity.this, NumberOfPlayersActivity.class), LAUNCH_PLAYERS_LIST_ACTIVITY);
                break;
            case R.id.save_game:
                if (board != null) {
                    getSharedPreferences("GameSaveLoadPreferences", MODE_PRIVATE).edit().putString("game_state", (new Gson()).toJson(board)).apply();
                }
                break;
            case R.id.load_game:
                //TODO Deserialization should be fixed and improved.
                board = (new Gson()).fromJson(getSharedPreferences("GameSaveLoadPreferences", MODE_PRIVATE).getString("game_state", ""), Board.class);
                redraw();
                break;
            case R.id.buy_sell:
                startActivityForResult((new Intent(GameActivity.this, BuySellActivity.class)).putExtra("possibilities", board.currentPlayerTradingPossibilities()), LAUNCH_BUY_SELL_ACTIVITY);
                break;
            case R.id.play_card:
                startActivityForResult((new Intent(GameActivity.this, PlayerCardsActivity.class)).putExtra("keys", board.currentPlayerCardsKyes()), LAUNCH_PLAY_CARD_ACTIVITY);
                break;
            case R.id.end_turn:
                if (!board.endTurn()) {
                    Toast.makeText(GameActivity.this, R.string.turn_is_not_ending_text, Toast.LENGTH_LONG).show();
                }
                if (board.finished()) {
                    Toast.makeText(GameActivity.this, R.string.game_finished_text, Toast.LENGTH_LONG).show();
                }
                redraw();
                break;
            case R.id.player_report:
                startActivity(new Intent(GameActivity.this, PlayerReportActivity.class).putExtra("report", board.currentPlayerReport()));
                break;
            case R.id.played_cards:
                startActivity((new Intent(GameActivity.this, PlayedCardsActivity.class)).putExtra("keys", board.playedCardsKyes()));
                break;
            case R.id.end_report:
                board.totalSale();
                startActivity(new Intent(GameActivity.this, EndReportActivity.class).putExtra("report", board.endReport()));
                break;
            case R.id.help:
                startActivity(new Intent(GameActivity.this, HelpActivity.class));
                break;
            case R.id.about:
                startActivity(new Intent(GameActivity.this, AboutActivity.class));
                break;
            case R.id.exitt:
                GameActivity.this.finish();
                break;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*
         * Do not handle other results than OK.
         */
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == LAUNCH_PLAYERS_LIST_ACTIVITY) {
            List<String> names = new ArrayList<String>();

            if (data.getBooleanExtra("player1Enabled", false)) {
                names.add(data.getCharSequenceExtra("player1Name").toString());
            }
            if (data.getBooleanExtra("player2Enabled", false)) {
                names.add(data.getCharSequenceExtra("player2Name").toString());
            }
            if (data.getBooleanExtra("player3Enabled", false)) {
                names.add(data.getCharSequenceExtra("player3Name").toString());
            }
            if (data.getBooleanExtra("player4Enabled", false)) {
                names.add(data.getCharSequenceExtra("player4Name").toString());
            }
            if (data.getBooleanExtra("player5Enabled", false)) {
                names.add(data.getCharSequenceExtra("player5Name").toString());
            }
            if (data.getBooleanExtra("player6Enabled", false)) {
                names.add(data.getCharSequenceExtra("player6Name").toString());
            }

            /*
             * Convert the list of names to array of names.
             */
            if (board.newGame(names.toArray(new String[0])) == false) {
                Toast.makeText(GameActivity.this, R.string.game_not_started_text, Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == LAUNCH_PLAY_CARD_ACTIVITY) {
            /*
             * Try to play a card.
             */
            if (!board.play(data.getIntExtra("cardIndex", -1), data.getIntExtra("companyIndex", -1))) {
                Toast.makeText(GameActivity.this, R.string.card_is_not_played_text, Toast.LENGTH_LONG).show();
            } else {
                /*
                 * Calculate money shortage of the player for paying penalties.
                 */
                int shortages[] = board.playersPenaltiesShortages();
                for (int i = 0; i < shortages.length; i++) {
                    /*
                     * The shortage is a positive number.
                     */
                    if (shortages[i] <= 0) {
                        continue;
                    }

                    int prices[] = {0, 0, 0, 0};
                    int quantities[] = {0, 0, 0, 0};
                    Object portfolio[] = board.portfolio(i);
                    String name = (String) portfolio[0];
                    quantities[0] = (Integer) portfolio[1];
                    quantities[1] = (Integer) portfolio[2];
                    quantities[2] = (Integer) portfolio[3];
                    quantities[3] = (Integer) portfolio[4];
                    prices[0] = (Integer) portfolio[5];
                    prices[1] = (Integer) portfolio[6];
                    prices[2] = (Integer) portfolio[7];
                    prices[3] = (Integer) portfolio[8];

                    //TODO Show activity for shares sell for each targeted player.
                    startActivityForResult(new Intent(GameActivity.this, UrgentSaleActivity.class).putExtra("playerIndex", i).putExtra("name", name).putExtra("shortage", shortages[i]).putExtra("quantities", quantities).putExtra("prices", prices), LAUNCH_URGENT_SALE_ACTIVITY);
                }
            }
        }

        if (requestCode == LAUNCH_BUY_SELL_ACTIVITY) {
            int[] shares = data.getIntArrayExtra("buySellShares");

            /*
             * Try to trade shares.
             */
            if (!board.trade(shares)) {
                Toast.makeText(GameActivity.this, R.string.trading_is_not_done_text, Toast.LENGTH_LONG).show();
            }
        }

        redraw();
    }

    /**
     * After change in the object model the user interface should be updated.
     */
    void redraw() {
        if (board.gameInProgress() == false) {
            BOARD_IMAGE.setVisibility(View.INVISIBLE);
            for (View view : MARKERS_IMAGES) {
                view.setVisibility(View.INVISIBLE);
            }
            this.setTitle(getString(R.string.start_new_game_text));
            return;
        } else {
            BOARD_IMAGE.setVisibility(View.VISIBLE);
            for (View view : MARKERS_IMAGES) {
                view.setVisibility(View.VISIBLE);
            }
        }

        setTitle(board.currentPlayerInfo());

        int[] prices = board.prices();
        for (int i = 0; i < prices.length && i < MARKERS_IMAGES.length; i++) {
            MARKERS_IMAGES[i].setImageResource((Integer) PRICES_IMAGES[i].get(prices[i]));
        }
    }
}
