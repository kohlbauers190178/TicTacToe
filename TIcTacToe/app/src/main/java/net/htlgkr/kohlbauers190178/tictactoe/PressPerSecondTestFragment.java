package net.htlgkr.kohlbauers190178.tictactoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PressPerSecondTestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PressPerSecondTestFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PressPerSecondTestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReactionTesterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PressPerSecondTestFragment newInstance(String param1, String param2) {
        PressPerSecondTestFragment fragment = new PressPerSecondTestFragment();
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


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double dps = 0.00;
                dps = amountPressed / ((System.currentTimeMillis() - timeStarted)/1000);


                txtViewPPS.setText(String.valueOf(dps).substring(0,3)+ "pps");
            }
        }, 1000, 500);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_presspersecondtest, container, false);

        txtViewPPS = view.findViewById(R.id.txtViewPPS);

        btnStartReactionTest = view.findViewById(R.id.btnStartReactionTest);
        btnReaction = view.findViewById(R.id.btnReaction);

        btnStartReactionTest.setOnClickListener(this);
        btnReaction.setOnClickListener(this);

        return view;
    }

    double timeStarted;

    volatile double amountPressed = 0;

    TextView txtViewPPS;

    Button btnStartReactionTest;
    Button btnReaction;

    Timer timer = new Timer();

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnStartReactionTest) {
            startReactionTest();
        }
        if (view.getId() == R.id.btnReaction) {
            calculatePress();
        }
    }

    private void calculatePress() {
        synchronized (this) {
            amountPressed++;
        }
    }

    private void startReactionTest() {
        btnStartReactionTest.setVisibility(View.INVISIBLE);
        btnReaction.setVisibility(View.VISIBLE);

        timeStarted = System.currentTimeMillis();

    }
}