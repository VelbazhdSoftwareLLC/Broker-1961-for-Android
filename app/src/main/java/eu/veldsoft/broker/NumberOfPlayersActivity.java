package eu.veldsoft.broker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Number of players ane their names.
 */
public class NumberOfPlayersActivity extends AppCompatActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
        editor.putBoolean("player1Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer1)).isChecked());
        editor.putString("player1Name", ((EditText) findViewById(R.id.textNamePlayer1)).getText().toString());
        editor.putBoolean("player2Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer2)).isChecked());
        editor.putString("player2Name", ((EditText) findViewById(R.id.textNamePlayer2)).getText().toString());
        editor.putBoolean("player3Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer3)).isChecked());
        editor.putString("player3Name", ((EditText) findViewById(R.id.textNamePlayer3)).getText().toString());
        editor.putBoolean("player4Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer4)).isChecked());
        editor.putString("player4Name", ((EditText) findViewById(R.id.textNamePlayer4)).getText().toString());
        editor.putBoolean("player5Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer5)).isChecked());
        editor.putString("player5Name", ((EditText) findViewById(R.id.textNamePlayer5)).getText().toString());
        editor.putBoolean("player6Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer6)).isChecked());
        editor.putString("player6Name", ((EditText) findViewById(R.id.textNamePlayer6)).getText().toString());
        editor.apply();
    }
}