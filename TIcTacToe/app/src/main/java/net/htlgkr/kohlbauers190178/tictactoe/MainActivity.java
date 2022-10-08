package net.htlgkr.kohlbauers190178.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button btnClicked = findViewById(view.getId());
        if (btnClicked.getId() == R.id.btnStart) {
            btnClicked.setVisibility(View.GONE);
            startGame();
        }
    }



    public void startGame() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.constraintLayout, TicTacToeFragment.newInstance("",""), "TicTacToeFragment")
                .commit();
    }
}