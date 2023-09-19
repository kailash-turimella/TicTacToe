package com.example.k2.tictactoe;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1: red, 2: empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;
    boolean gameActive = true;

    public void dropIn(View view) {

        ImageView box = (ImageView) view;

        int tappedBox = Integer.parseInt(box.getTag().toString());

        if (gameState[tappedBox] == 2 && gameActive) {
            gameState[tappedBox] = activePlayer;
            box.animate().alpha(0);

            if (activePlayer == 0) {

                box.setImageResource(R.drawable.o);
                activePlayer = 1;

            } else {

                box.setImageResource(R.drawable.x);
                activePlayer = 0;

            }

            box.animate().alpha(1).setDuration(2000);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    // Somone has won!

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {

                        winner = "Player 2";

                    } else {

                        winner = "Player 1";

                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);

                }

            }
        }else{
            Toast.makeText(MainActivity.this, "Box already filled", Toast.LENGTH_SHORT).show();
        }
    }

    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView box = (ImageView) gridLayout.getChildAt(i);
            box.setImageDrawable(null);

        }

        for (int i=0; i<9; i++) {

            gameState[i] = 2;

        }
        activePlayer = 0;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
