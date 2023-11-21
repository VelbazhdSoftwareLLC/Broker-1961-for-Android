package eu.veldsoft.broker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Lobby menu screen.
 */
public class MenuActivity extends Activity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*
         * Open new game screen.
         */
        findViewById(R.id.single_game).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                        startActivity(intent);
                    }
                }
        );

        /*
         *  Start host screen for multiplayer game.
         */
        findViewById(R.id.join_game).setVisibility(View.INVISIBLE);
        findViewById(R.id.join_game).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MenuActivity.this, JoinActivity.class);
                        startActivity(intent);
                    }
                }
        );

        /*
         *  Start host screen for multiplayer game.
         */
        findViewById(R.id.host_game).setVisibility(View.INVISIBLE);
        findViewById(R.id.host_game).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MenuActivity.this, HostActivity.class);
                        startActivity(intent);
                    }
                }
        );

        /*
         *  Application exit.
         */
        findViewById(R.id.exit_game).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MenuActivity.this.finish();
                    }
                }
        );
    }
}
