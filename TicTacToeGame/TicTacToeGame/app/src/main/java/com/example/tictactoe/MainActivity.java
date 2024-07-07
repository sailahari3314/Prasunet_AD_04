
package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int currentPlayer = 1; // 1 for X, 2 for O
    private int[] gameState = new int[9]; // 0 for unplayed, 1 for X, 2 for O
    private int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, 
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
            {0, 4, 8}, {2, 4, 6}
    };

    private boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetGame();
    }

    public void dropIn(View view) {
        Button counter = (Button) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 0 && gameActive) {
            gameState[tappedCounter] = currentPlayer;
            counter.setText(currentPlayer == 1 ? "X" : "O");
            currentPlayer = currentPlayer == 1 ? 2 : 1;

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 0) {
                    gameActive = false;
                    String winner = gameState[winningPosition[0]] == 1 ? "X" : "O";
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won!");
                    return;
                }
            }

            boolean gameDraw = true;
            for (int state : gameState) {
                if (state == 0) {
                    gameDraw = false;
                    break;
                }
            }

            if (gameDraw) {
                gameActive = false;
                TextView winnerTextView = findViewById(R.id.winnerTextView);
                winnerTextView.setText("It's a draw!");
            }
        }
    }

    public void resetGame(View view) {
        resetGame();
    }

    private void resetGame() {
        currentPlayer = 1;
        gameActive = true;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 0;
        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button counter = (Button) gridLayout.getChildAt(i);
            counter.setText("");
        }

        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText("");
    }
}
