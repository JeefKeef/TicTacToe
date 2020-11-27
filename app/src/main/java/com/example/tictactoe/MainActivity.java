//Learning android development
//Created a tic-tac-toe game
//source: https://www.udemy.com/course/the-complete-android-oreo-developer-course/learn/lecture/8339404#overview

//added features
/*
- Created Random starting player(X or O) instead of initial start of 0.



 */

package com.example.tictactoe;
import java.util.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0: O, 1: X, 2: empty state

    //Array that keep tracks of the players position, initially starts as 2 for empty.
    int[] game_state = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //Winning positions if the current player have three in a row.
    int[][] winning_positions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    //current player
    Random rand = new Random();
    int current_player = rand.nextInt(2);

    //Current state of the game
    boolean active_game = true;

    public void click(View view) {
        ImageView emblem = (ImageView) view;
        //Takes the tag(position) and set it up as the current player position, then update the current player into the array
        int player_position = Integer.parseInt(emblem.getTag().toString());

        //Checks if the game state is empty, if so, go ahead, this prevents the position of the player to switch between X or O.
        //Also checks if the game is active or not, if active go ahead, if not, prevent anymore clicks.
        if(game_state[player_position] == 2 && active_game) {
            game_state[player_position] = current_player;

            //Checks if the current player is O or X, then draws the O or X and switches to the other player turn.
            if (current_player == 0) {
                emblem.setImageResource(R.drawable.o);
                current_player = 1;
            } else {
                emblem.setImageResource(R.drawable.x);
                current_player = 0;
            }
            //Loops through the winning_position array and checks if the game states have the player winning position
            for (int[] winning_position : winning_positions) {
                if (game_state[winning_position[0]] == game_state[winning_position[1]] && game_state[winning_position[1]] == game_state[winning_position[2]] && game_state[winning_position[0]] != 2) {// 2 makes sure the position isnt empty

                    //Checks the current player and display them as the winner!
                    winner();
                }
            }
        }
    }

    //Checks the current player and display them as the winner!
    public void winner() {
        String winner = "";
        if (current_player == 1) {
            winner = "O";
        } else {
            winner = "X";
        }
        //Sets active game to false to prevent further clicking.
        active_game = false;

        //Shows the winner and play again button.
        Button play_again = (Button) findViewById(R.id.tryAgainButton);
        TextView winner_prompt = (TextView) findViewById(R.id.winnerTextView);
        winner_prompt.setText(winner + " has won!");
        winner_prompt.setVisibility(View.VISIBLE);
        play_again.setVisibility(View.VISIBLE);
    }


    //Play game again function that restarts the game by resetting the board.
    public void playAgain(View view) {
        //Hides the winner and play again button.
        Button play_again = (Button) findViewById(R.id.tryAgainButton);
        TextView winner_prompt = (TextView) findViewById(R.id.winnerTextView);
        winner_prompt.setVisibility(View.INVISIBLE);
        play_again.setVisibility(View.INVISIBLE);

        //Loops through the grid to clear the images(X and O).
        clearGrid();

        //Loops through game_states and reset to 2(empty).
        resetGameState();

        //Sets new current player
        current_player = rand.nextInt(2);
        //sets active game to true
        active_game = true;
    }

    //Loops through game_states and reset to 2(empty).
    public void resetGameState() {
        for(int i = 0; i < game_state.length; ++i) {
            game_state[i] = 2;
        }
    }

    //Loops through the grid to clear the images(X and O).
    public void clearGrid() {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); ++i) {
            ImageView image = (ImageView) gridLayout.getChildAt(i);
            image.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}