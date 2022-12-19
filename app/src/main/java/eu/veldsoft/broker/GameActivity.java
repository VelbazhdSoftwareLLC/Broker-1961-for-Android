package eu.veldsoft.broker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import eu.veldsoft.broker.model.Board;

/**
 * Main game screen.
 */
public class GameActivity extends AppCompatActivity {
    /**
     * The identifier for launching activity.
     */
    private static int LAUNCH_PLAYERS_LIST_ACTIVITY = 1;

    /**
     * Array of references to markes views.
     */
    private ImageView markers[] = new ImageView[4];

    /**
     * The link between view layer and object model is the instance of the Board class.
     */
    Board board = new Board();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /*
         * Get markers views references.
         */
        markers[0] = findViewById(R.id.aPullImageView);
        markers[1] = findViewById(R.id.bPullImageView);
        markers[2] = findViewById(R.id.cPullImageView);
        markers[3] = findViewById(R.id.dPullImageView);
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
            case R.id.help:
                startActivity(new Intent(GameActivity.this, HelpActivity.class));
                break;
            case R.id.about:
                startActivity(new Intent(GameActivity.this, AboutActivity.class));
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

        redraw();
    }

    /**
     * After change in the object model the user interface should be updated.
     */
    void redraw() {
        setTitle(board.playing().name() + " plays ...");

        int prices[] = board.prices();
        for (int i = 0; i < prices.length && i < markers.length; i++) {
            AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(170, 130, 200 + i * 170, -20 + (int) (prices[i] * 6.85));
            markers[i].setLayoutParams(layoutParams);
        }
    }
}