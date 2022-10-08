package net.htlgkr.kohlbauers190178.tictactoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.htlgkr.kohlbauers190178.tictactoe.databinding.ActivityMainBinding;
import net.htlgkr.kohlbauers190178.tictactoe.databinding.FragmentTicTacToeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicTacToeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicTacToeFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TicTacToeFragment() {
        // Required empty public constructor
    }

    TicTacToeGame ticTacToeGame;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TicTacToeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TicTacToeFragment newInstance(String param1, String param2) {
        TicTacToeFragment fragment = new TicTacToeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    FragmentTicTacToeBinding binding;

    Button btnTL;
    Button btnTM;
    Button btnTR;

    Button btnML;
    Button btnMM;
    Button btnMR;

    Button btnBL;
    Button btnBM;
    Button btnBR;

    Button btnReStart;

    TextView txtViewEndInfo;

    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tic_tac_toe,container,false);

        linearLayout = view.findViewById(R.id.linearLayoutVertical);

        txtViewEndInfo = view.findViewById(R.id.txtVwEndInfo);

        btnTL = view.findViewById(R.id.btnTL);
        btnTM = view.findViewById(R.id.btnTM);
        btnTR = view.findViewById(R.id.btnTR);

        btnML = view.findViewById(R.id.btnML);
        btnMM = view.findViewById(R.id.btnMM);
        btnMR = view.findViewById(R.id.btnMR);

        btnBL = view.findViewById(R.id.btnBL);
        btnBM = view.findViewById(R.id.btnBM);
        btnBR = view.findViewById(R.id.btnBR);


        btnReStart = view.findViewById(R.id.btnRestart);
        btnReStart.setOnClickListener(this);


        btnTL.setOnClickListener(this);
        btnTM.setOnClickListener(this);
        btnTR.setOnClickListener(this);

        btnML.setOnClickListener(this);
        btnMM.setOnClickListener(this);
        btnMR.setOnClickListener(this);

        btnBL.setOnClickListener(this);
        btnBM.setOnClickListener(this);
        btnBR.setOnClickListener(this);
        //return inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        startGame();

        return view;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == btnReStart.getId()) {
            restartGame();
        } else {
            try {

                String realIDFromButton = view.getResources().getResourceEntryName(view.getId());

                //if (ticTacToeGame.isValidTurn(realIDFromButton)) {
                if (ticTacToeGame.isValidTurn(view.getId())) {

                    Button btn = (Button)view;

                    String infoForButton = ticTacToeGame.turnMade(btn.getId());
                    btn.setText(infoForButton);

                    if (ticTacToeGame.checkIfEnd()) {
                        String causeOfEnd = ticTacToeGame.getCauseOfEnd();
                        txtViewEndInfo.setText(causeOfEnd);
                        endGame();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void restartGame() {
        txtViewEndInfo.setText("");
        endGame();
        ticTacToeGame = new TicTacToeGame();
        startGame();
    }


    private void endGame() {
        ticTacToeGame.endGame();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            LinearLayout horizontal = (LinearLayout) linearLayout.getChildAt(i);
            for (int j = 0; j < horizontal.getChildCount(); j++) {
                Button button = (Button) horizontal.getChildAt(j);
                button.setEnabled(false);
            }
        }
    }

    public void startGame() {

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            LinearLayout horizontal = (LinearLayout) linearLayout.getChildAt(i);
            for (int j = 0; j < horizontal.getChildCount(); j++) {
                Button button = (Button) horizontal.getChildAt(j);
                button.setText("");
                button.setEnabled(true);
            }
        }
        
        ticTacToeGame = new TicTacToeGame();
        ticTacToeGame.startGame();
    }


}