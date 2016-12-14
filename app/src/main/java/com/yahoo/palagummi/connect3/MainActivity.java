package com.yahoo.palagummi.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0: Yellow and 1: Red
    int activePlayer = 0;

    // 2 means empty grid
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // "android:tag" is helpful to recognize which cell in the grid is pressed so as to change the gameState

    // keep track of game
    boolean gameIsActive = true;

    // Winning position in the form of array of arrays
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},
                                {0,3,6},{1,4,7},{2,5,8},
                                {0,4,8},{2,4,6}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view; // need not search for the id because it is what the user tapped on

        int tappedCounter = Integer.parseInt(counter.getTag().toString()); // info of which cell in grid is pressed

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer; // cell is occupied by activePlayer

            counter.setTranslationY(-1000f); // initially move it off the screen without any animation
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow); // set the image of 'yellow' player
                activePlayer = 1;
            } else if (activePlayer == 1) {
                counter.setImageResource(R.drawable.red); // set the image of 'red' player
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300); // animate it back to the screen
        }

        for(int[] winningPosition : winningPositions) {


            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2) {
                //System.out.println(gameState[winningPosition[0]]);

                //set gameIsActive to false
                gameIsActive = false;
                // make the layout appear
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout); // get control of the layout
                layout.setVisibility(View.VISIBLE);

                String winner = "Yellow";
                if(gameState[winningPosition[0]] == 1)
                    winner = "Red";
                // show the winner
                TextView winnerText = (TextView) findViewById(R.id.winnerText);
                winnerText.setText(winner + " has won!");
            } else { // game is drawn
                boolean gameIsOver = true;
                for (int counterState : gameState) {
                    if (counterState == 2) {
                        gameIsOver = false;
                    }
                }
                if (gameIsOver) {
                    TextView winnerText = (TextView) findViewById(R.id.winnerText);
                    winnerText.setText("It's a draw!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout); // get control of the layout
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        // set gameIsActive to true
        gameIsActive = true;

        // make the layout disappear
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout); // get control of the layout
        layout.setVisibility(View.INVISIBLE);

        // set the game screen to defaults
        activePlayer = 0;
        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }

        // make the gridLayout visible as new
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0); // reset the images back to nothing
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
