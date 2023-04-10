package eu.veldsoft.broker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import eu.veldsoft.broker.model.Board;

/**
 * Main game screen.
 */
public class GameActivity extends AppCompatActivity {
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
     * Array of references to markes views.
     */
    private static final ImageView[] MARKERS_IMAGES = new ImageView[4];

    /**
     * Map of the card key and card image.
     */
    static final Map<String, Integer> CARDS_IMAGES = new HashMap<String, Integer>();

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
         * Get markers views references.
         */
        MARKERS_IMAGES[0] = findViewById(R.id.aPullImageView);
        MARKERS_IMAGES[1] = findViewById(R.id.bPullImageView);
        MARKERS_IMAGES[2] = findViewById(R.id.cPullImageView);
        MARKERS_IMAGES[3] = findViewById(R.id.dPullImageView);

        /*
         * Estimating scaling factors.
         */
        ((ImageView) findViewById(R.id.boardImageView)).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                xScale = (float) ((ImageView) findViewById(R.id.boardImageView)).getWidth() / BitmapFactory.decodeResource(getResources(), R.drawable.board).getWidth();
                yScale = (float) ((ImageView) findViewById(R.id.boardImageView)).getHeight() / BitmapFactory.decodeResource(getResources(), R.drawable.board).getHeight();
            }
        });

        /*
         * Scale makers according to board size.
         */
        for (ImageView maker : MARKERS_IMAGES) {
            maker.setScaleX(xScale);
            maker.setScaleY(yScale);
        }
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
                if(board != null) {
                    getSharedPreferences("GameSaveLoadPreferences", MODE_PRIVATE).edit().putString("game_state", (new Gson()).toJson(board)).apply();
                }
                break;
            case R.id.load_game:
                board = (new Gson()).fromJson(getSharedPreferences("GameSaveLoadPreferences", MODE_PRIVATE).getString("game_state", ""), Board.class);
                redraw();
                break;
            case R.id.buy_sell:
                startActivityForResult(new Intent(GameActivity.this, BuySellActivity.class), LAUNCH_BUY_SELL_ACTIVITY);
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
            board.newGame(names.toArray(new String[0]));
        }

        if (requestCode == LAUNCH_PLAY_CARD_ACTIVITY) {
            /*
             * Try to play a card.
             */
            if (!board.play(data.getIntExtra("cardIndex", -1), data.getIntExtra("companyIndex", -1))) {
                Toast.makeText(GameActivity.this, R.string.card_is_not_played_text, Toast.LENGTH_LONG).show();
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
        setTitle(board.currentPlayerInfo());

        int[] prices = board.prices();
        for (int i = 0; i < prices.length && i < MARKERS_IMAGES.length; i++) {
            AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(
                    MARKERS_IMAGES[i].getWidth(),
                    MARKERS_IMAGES[i].getHeight(),
                    Math.round((176 + i * 190) * xScale),
                    Math.round((2 + prices[i] * 7.47F) * yScale)
            );
            MARKERS_IMAGES[i].setLayoutParams(layoutParams);
            MARKERS_IMAGES[i].setVisibility(View.VISIBLE);
        }
    }
}
