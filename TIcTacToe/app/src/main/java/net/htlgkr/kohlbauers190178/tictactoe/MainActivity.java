package net.htlgkr.kohlbauers190178.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TicTacToeGame ticTacToeGame = new TicTacToeGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClicked(View view) {
        Button btnClicked = findViewById(view.getId());

        if (btnClicked.getId() == R.id.btnStart) {
            startGame();
        } else if (btnClicked.getId() == R.id.btnRestart) {
            restartGame();
        } else {
            try {

                String realIDFromButton = btnClicked.getResources().getResourceEntryName(view.getId());



                //if (ticTacToeGame.isValidTurn(realIDFromButton)) {
                if (ticTacToeGame.isValidTurn(btnClicked.getId())) {

                    String infoForButton = ticTacToeGame.turnMade(btnClicked.getId());
                    btnClicked.setText(infoForButton);

                    if (ticTacToeGame.checkIfEnd()) {
                        String causeOfEnd = ticTacToeGame.getCauseOfEnd();
                        TextView txtView = findViewById(R.id.txtVwEndInfo);
                        txtView.setText(causeOfEnd);

                        endGame();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void restartGame() {
        TextView txtView = findViewById(R.id.txtVwEndInfo);
        txtView.setText("");
        endGame();
        ticTacToeGame = new TicTacToeGame();
        startGame();
    }


    private void endGame() {
        ticTacToeGame.endGame();
        LinearLayout layout = findViewById(R.id.linearLayoutVertical);
        for (int i = 0; i < layout.getChildCount(); i++) {
            LinearLayout horizontal = (LinearLayout) layout.getChildAt(i);
            for (int j = 0; j < horizontal.getChildCount(); j++) {
                Button button = (Button) horizontal.getChildAt(j);
                button.setEnabled(false);
            }
        }
    }

    public void startGame() {
        Button startButton = findViewById(R.id.btnStart);
        startButton.setVisibility(View.INVISIBLE);

        LinearLayout layout = findViewById(R.id.linearLayoutVertical);
        layout.setVisibility(View.VISIBLE);

        for (int i = 0; i < layout.getChildCount(); i++) {
            LinearLayout horizontal = (LinearLayout) layout.getChildAt(i);
            for (int j = 0; j < horizontal.getChildCount(); j++) {
                Button button = (Button) horizontal.getChildAt(j);
                button.setText("");
                button.setEnabled(true);
            }
        }
        Button restartButton = findViewById(R.id.btnRestart);
        restartButton.setVisibility(View.VISIBLE);
        ticTacToeGame.startGame();
    }
}