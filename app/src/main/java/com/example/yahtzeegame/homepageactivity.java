package com.example.yahtzeegame;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yahtzeegame.backend.*;

import java.security.PrivateKey;
import java.util.ArrayList;

public class homepageactivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Button> diceButtons;
    private Button roll;
    private Game game = new Game();
    private String selectedButton;
    private Integer nkeptButtons = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepageactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        selectedButton = getIntent().getStringExtra(OpenAppActivity.selectedKey);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // initializing dices
        final Button dice1 = findViewById(R.id.dice1);
        final Button dice2 = findViewById(R.id.dice2);
        final Button dice3 = findViewById(R.id.dice3);
        final Button dice4 = findViewById(R.id.dice4);
        final Button dice5 = findViewById(R.id.dice5);

        game.addDiceButton(dice1);
        game.addDiceButton(dice2);
        game.addDiceButton(dice3);
        game.addDiceButton(dice4);
        game.addDiceButton(dice5);

        // initializing scoring buttons for player 1
        final Button p1AcesScoreButton = findViewById(R.id.p1__aces);
        final Button p1TwosScoreButton = findViewById(R.id.p1_twos);
        final Button p1ThreesScoreButton = findViewById(R.id.p1_threes);
        final Button p1FoursScoreButton = findViewById(R.id.p1_fours);
        final Button p1FivesScoreButton = findViewById(R.id.p1_fives);
        final Button p1SixesScoreButton = findViewById(R.id.p1_sixes);
        final Button P1ThreeOFaKindScoreButton = findViewById(R.id.p1_3_of_a_kind);
        final Button p1FourOfaKindScoreButton = findViewById(R.id.p1_4_of_a_kind);
        final Button p1smStraightScoreButton = findViewById(R.id.p1_sm_straight);
        final Button p1lgStraightScoreButton = findViewById(R.id.p1_lg_straight);
        final Button p1FullHouseScoreButton = findViewById(R.id.p1_fullhouse);
        final Button P1YahzteeScoreButton = findViewById(R.id.p1_yahtzee);
        final Button p1ChanceScoreButton = findViewById(R.id.p1_chance);

        // initializing scoring buttons for player 1
        final Button p2AcesScoreButton = findViewById(R.id.p2_aces);
        final Button p2TwosScoreButton = findViewById(R.id.p2_twos);
        final Button p2ThreesScoreButton = findViewById(R.id.p2_threes);
        final Button p2FoursScoreButton = findViewById(R.id.p2_fours);
        final Button p2FivesScoreButton = findViewById(R.id.p2_fives);
        final Button p2SixesScoreButton = findViewById(R.id.p2_sixes);
        final Button P2ThreeOFaKindScoreButton = findViewById(R.id.p2_3_of_a_kind);
        final Button p2FourOfaKindScoreButton = findViewById(R.id.p2_4_of_a_kind);
        final Button p2smStraightScoreButton = findViewById(R.id.p2_sm_straight);
        final Button p2lgStraightScoreButton = findViewById(R.id.p2_lg_straight);
        final Button p2FullHouseScoreButton = findViewById(R.id.p2_fullhouse);
        final Button P2YahzteeScoreButton = findViewById(R.id.p2_yahtzee);
        final Button p2ChanceScoreButton = findViewById(R.id.p2_chance);

        if (this.selectedButton.equals("Single Player")){
            game.initializePlayers(1);
            Player player1 = game.getPlayers().get(0);
            player1.addScoreButton(p1AcesScoreButton, ScoreCategory.Aces);
            player1.addScoreButton(p1TwosScoreButton, ScoreCategory.Twos);
            player1.addScoreButton(p1ThreesScoreButton, ScoreCategory.Threes);
            player1.addScoreButton(p1FoursScoreButton, ScoreCategory.Fours);
            player1.addScoreButton(p1FivesScoreButton, ScoreCategory.Fives);
            player1.addScoreButton(p1SixesScoreButton, ScoreCategory.Sixes);
            player1.addScoreButton(p1FullHouseScoreButton, ScoreCategory.Full_House);
            player1.addScoreButton(P1ThreeOFaKindScoreButton, ScoreCategory.Three_of_a_kind);
            player1.addScoreButton(p1FourOfaKindScoreButton, ScoreCategory.Four_of_a_kind);
            player1.addScoreButton(p1smStraightScoreButton, ScoreCategory.SM_Straight);
            player1.addScoreButton(p1lgStraightScoreButton, ScoreCategory.LG_Straight);
            player1.addScoreButton(p1ChanceScoreButton, ScoreCategory.Chance);
            player1.addScoreButton(P1YahzteeScoreButton, ScoreCategory.Yahtzee);

            TextView p1sumView = findViewById(R.id.p1_initial_sum);
            TextView p1bonusView = findViewById(R.id.p1_bonus_score);
            TextView p1totalScoreView = findViewById(R.id.p1_total_score);
            player1.setSumView(p1sumView);
            player1.setBonusView(p1bonusView);
            player1.setTotalScoreView(p1totalScoreView);

            Log.i(homepageactivity.class.getName(), "One player");
        }else{
            game.initializePlayers(2);
            Player player1 = game.getPlayers().get(0);
            Player player2 = game.getPlayers().get(1);

            TextView p1sumView = findViewById(R.id.p1_initial_sum);
            TextView p1bonusView = findViewById(R.id.p1_bonus_score);
            TextView p1totalScoreView = findViewById(R.id.p1_total_score);
            player1.setSumView(p1sumView);
            player1.setBonusView(p1bonusView);
            player1.setTotalScoreView(p1totalScoreView);

            player1.addScoreButton(p1AcesScoreButton, ScoreCategory.Aces);
            player1.addScoreButton(p1TwosScoreButton, ScoreCategory.Twos);
            player1.addScoreButton(p1ThreesScoreButton, ScoreCategory.Threes);
            player1.addScoreButton(p1FoursScoreButton, ScoreCategory.Fours);
            player1.addScoreButton(p1FivesScoreButton, ScoreCategory.Fives);
            player1.addScoreButton(p1SixesScoreButton, ScoreCategory.Sixes);
            player1.addScoreButton(p1FullHouseScoreButton, ScoreCategory.Full_House);
            player1.addScoreButton(P1ThreeOFaKindScoreButton, ScoreCategory.Three_of_a_kind);
            player1.addScoreButton(p1FourOfaKindScoreButton, ScoreCategory.Four_of_a_kind);
            player1.addScoreButton(p1smStraightScoreButton, ScoreCategory.SM_Straight);
            player1.addScoreButton(p1lgStraightScoreButton, ScoreCategory.LG_Straight);
            player1.addScoreButton(p1ChanceScoreButton, ScoreCategory.Chance);
            player1.addScoreButton(P1YahzteeScoreButton, ScoreCategory.Yahtzee);


            TextView p2sumView = findViewById(R.id.p2_initial_sum);
            TextView p2bonusView = findViewById(R.id.p2_bonus_score);
            TextView p2totalScoreView = findViewById(R.id.p2_total_score);
            player2.setSumView(p2sumView);
            player2.setBonusView(p2bonusView);
            player2.setTotalScoreView(p2totalScoreView);

            player2.addScoreButton(p2AcesScoreButton, ScoreCategory.Aces);
            player2.addScoreButton(p2TwosScoreButton, ScoreCategory.Twos);
            player2.addScoreButton(p2ThreesScoreButton, ScoreCategory.Threes);
            player2.addScoreButton(p2FoursScoreButton, ScoreCategory.Fours);
            player2.addScoreButton(p2FivesScoreButton, ScoreCategory.Fives);
            player2.addScoreButton(p2SixesScoreButton, ScoreCategory.Sixes);
            player2.addScoreButton(p2FullHouseScoreButton, ScoreCategory.Full_House);
            player2.addScoreButton(P2ThreeOFaKindScoreButton, ScoreCategory.Three_of_a_kind);
            player2.addScoreButton(p2FourOfaKindScoreButton, ScoreCategory.Four_of_a_kind);
            player2.addScoreButton(p2smStraightScoreButton, ScoreCategory.SM_Straight);
            player2.addScoreButton(p2lgStraightScoreButton, ScoreCategory.LG_Straight);
            player2.addScoreButton(p2ChanceScoreButton, ScoreCategory.Chance);
            player2.addScoreButton(P2YahzteeScoreButton, ScoreCategory.Yahtzee);
            Log.i(homepageactivity.class.getName(), "Two player");
        }

        roll = findViewById(R.id.rollButton);
        game.setInitialPlayer();
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nkeptButtons < 2) {
                    game.roll();
                    nkeptButtons += 1;
                }else {
                    displayToast("You have reached the maximum number of rolls. You have to choose the score now");
                    roll.setEnabled(false);
                }
            }
        });


        View.OnClickListener diceSlection = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!game.getDice((Button)v).isSelected()) {
                    game.getDice((Button)v).selectDice();
                }
            }
        };

        dice1.setOnClickListener(diceSlection);
        dice2.setOnClickListener(diceSlection);
        dice3.setOnClickListener(diceSlection);
        dice4.setOnClickListener(diceSlection);
        dice5.setOnClickListener(diceSlection);

//        game.setInitialPlayer();

        View.OnClickListener ScoreButtonSelected = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.resetAllDices();
                nkeptButtons = 0;
                game.resetAllDices();
                if(!game.getCurrntScoreButton((Button)v).isScored()){

                }
                game.getCurrntScoreButton((Button)v).markScored();
                game.switchPlayer();
                roll.setEnabled(true);
            }
        };

        p1AcesScoreButton.setOnClickListener(ScoreButtonSelected);
        p1TwosScoreButton.setOnClickListener(ScoreButtonSelected);
        p1ThreesScoreButton.setOnClickListener(ScoreButtonSelected);
        p1FoursScoreButton.setOnClickListener(ScoreButtonSelected);
        p1FivesScoreButton.setOnClickListener(ScoreButtonSelected);
        p1SixesScoreButton.setOnClickListener(ScoreButtonSelected);
        p1FullHouseScoreButton.setOnClickListener(ScoreButtonSelected);
        P1ThreeOFaKindScoreButton.setOnClickListener(ScoreButtonSelected);
        p1FourOfaKindScoreButton.setOnClickListener(ScoreButtonSelected);
        p1ChanceScoreButton.setOnClickListener(ScoreButtonSelected);
        p1smStraightScoreButton.setOnClickListener(ScoreButtonSelected);
        p1lgStraightScoreButton.setOnClickListener(ScoreButtonSelected);
        P1YahzteeScoreButton.setOnClickListener(ScoreButtonSelected);

        p2AcesScoreButton.setOnClickListener(ScoreButtonSelected);
        p2TwosScoreButton.setOnClickListener(ScoreButtonSelected);
        p2ThreesScoreButton.setOnClickListener(ScoreButtonSelected);
        p2FoursScoreButton.setOnClickListener(ScoreButtonSelected);
        p2FivesScoreButton.setOnClickListener(ScoreButtonSelected);
        p2SixesScoreButton.setOnClickListener(ScoreButtonSelected);
        p2FullHouseScoreButton.setOnClickListener(ScoreButtonSelected);
        P2ThreeOFaKindScoreButton.setOnClickListener(ScoreButtonSelected);
        p2FourOfaKindScoreButton.setOnClickListener(ScoreButtonSelected);
        p2ChanceScoreButton.setOnClickListener(ScoreButtonSelected);
        p2smStraightScoreButton.setOnClickListener(ScoreButtonSelected);
        p2lgStraightScoreButton.setOnClickListener(ScoreButtonSelected);
        P2YahzteeScoreButton.setOnClickListener(ScoreButtonSelected);


    }

    public void displayToast(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepageactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.single_player_mode) {
            game.deleteAllPlayers();
            game.initializePlayers(1);
//            game.addPlayer(new Player());
            // Handle the camera action
        } else if (id == R.id.two_player_mode) {
            game.deleteAllPlayers();
            game.initializePlayers(2);
//            game.addPlayer(new Player());
//            game.addPlayer(new Player());

        } else if (id == R.id.replay_game) {
            game.resetAllDices();
            game.restAllScoreButtons();
            roll.setEnabled(true);


        } else if (id == R.id.exit_app) {
            this.selectedButton = "";
            finish();
        }

//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

