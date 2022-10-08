package net.htlgkr.kohlbauers190178.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToeGame {

    private char currentTurn;

    List<Integer> turnsX = new ArrayList<>();
    List<Integer> turnsO = new ArrayList<>();


    public void startGame() {
        Random random = new Random();
        boolean boolTurn = random.nextBoolean();
        if (boolTurn) {
            currentTurn = 'X';
        } else {
            currentTurn = 'O';
        }
    }

    public String turnMade(int buttonID) {

        String returnValue=null;
        if (currentTurn == 'X') {
            currentTurn = 'O';
            returnValue = "X";
            turnsX.add(buttonID);
        } else if (currentTurn == 'O') {
            currentTurn = 'X';
            returnValue = "O";
            turnsO.add(buttonID);
        }

        return returnValue;
    }

    public boolean checkIfEnd() {
        return checkTurnsFromPlayer(turnsX) || checkTurnsFromPlayer(turnsO) || isDraw();
    }

    private boolean isDraw() {
        return !((turnsX.size() + turnsO.size()) < 9);
    }

    public boolean checkTurnsFromPlayer(List<Integer> turnsList) {

        int topLeft = R.id.btnTL;
        int topMid = R.id.btnTM;
        int topRight = R.id.btnTR;

        int midLeft = R.id.btnML;
        int midMid = R.id.btnMM;
        int midRight = R.id.btnMR;

        int bottomLeft = R.id.btnBL;
        int bottomMid = R.id.btnBM;
        int bottomRight = R.id.btnBR;

        //first row
        if (turnsList.contains(topLeft) && turnsList.contains(topMid) && turnsList.contains(topRight)) {
            return true;
        }

        //second Row
        if (turnsList.contains(midLeft) && turnsList.contains(midMid) && turnsList.contains(midRight)) {
            return true;
        }

        //third row
        if (turnsList.contains(bottomLeft) && turnsList.contains(bottomMid) && turnsList.contains(bottomRight)) {
            return true;
        }

        //first column
        if (turnsList.contains(topLeft) && turnsList.contains(midLeft) && turnsList.contains(bottomLeft)) {
            return true;
        }

        //second column
        if (turnsList.contains(topMid) && turnsList.contains(midMid) && turnsList.contains(bottomMid)) {
            return true;
        }

        //third column
        if (turnsList.contains(topRight) && turnsList.contains(midRight) && turnsList.contains(bottomRight)) {
            return true;
        }

        //diagonal topLeft to bottomRight
        if (turnsList.contains(topLeft) && turnsList.contains(midMid) && turnsList.contains(bottomRight)) {
            return true;
        }

        //diagonal bottomLeft to topRight
        if (turnsList.contains(bottomLeft) && turnsList.contains(midMid) && turnsList.contains(topRight)) {
            return true;
        }

        return false;
    }

    public String getCauseOfEnd() {

        if (checkTurnsFromPlayer(turnsX)) {
            return "X won";
        } else if (checkTurnsFromPlayer(turnsO)) {
            return "O won";
        } else if (isDraw()) {
            return "draw";
        }
        return null;
    }

    public void endGame() {
        //maybe for the future
    }

    public boolean isValidTurn(int idFromButton) {
        return !turnsX.contains(idFromButton) && !turnsO.contains(idFromButton);
    }
}
