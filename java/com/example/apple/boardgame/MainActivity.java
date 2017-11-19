package com.example.apple.boardgame;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activeplayer = 0;
    boolean gameisactive = true;
    int gamestate[] = {2,2,2,2,2,2,2,2,2};
    int winningpositions[][] = {{0,1,2},
                                {3,4,5},
                                {6,7,8},
                                {0,3,6},
                                {1,4,7},
                                {2,5,8},
                                {0,4,8},
                                {2,4,6}};
    public void dropln(View view) {

        ImageView counter = (ImageView) view;

        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        //System.out.println(tappedcounter);

        if (gamestate[tappedcounter]==2 && gameisactive) {

            //System.out.println(gamestate[tappedcounter]);

            gamestate[tappedcounter]=activeplayer; /* 哪一個tag = activeplayer*/

            //System.out.println(gamestate[tappedcounter]);

            counter.setTranslationY(-1000f);

            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);

                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int winningposition[] : winningpositions){
                //System.out.println("length:" +winningposition.length);
                if(gamestate[winningposition[0]]== gamestate[winningposition[1]] && gamestate[winningposition[1]]== gamestate[winningposition[2]] && gamestate[winningposition[0]]!=2){
//                    System.out.println(winningposition[0]); 贏的位置
//                    System.out.println(winningposition[1]);
//                    System.out.println(winningposition[2]);
//
//                    System.out.println(gamestate[winningposition[0]]); 贏的顏色
//                    System.out.println(gamestate[winningposition[1]]);
//                    System.out.println(gamestate[winningposition[2]]);

                    gameisactive = false;

                    String winner = "Red";
                    if(gamestate[winningposition[0]]==0){
                        winner = "Yellow";
                    }

                    TextView winningMessage = (TextView) findViewById(R.id.winnermessage);
                    winningMessage.setText(winner + " has won");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);
                    layout.setVisibility(View.VISIBLE);
                }
                  else{
                    boolean gameisover = true;
                    for(int counterstate : gamestate ) {
                        if (counterstate == 2) gameisover = false;
                    }
                        if(gameisover){
                            TextView winningMessage = (TextView) findViewById(R.id.winnermessage);
                            winningMessage.setText(" It is tie.");
                            LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);
                            layout.setVisibility(View.VISIBLE);
                        }
                }
            }
        }
    }

    public void playagain(View view){

        gameisactive = true;

        LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);
        layout.setVisibility(View.INVISIBLE);

        activeplayer = 0;

        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i =0; i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
