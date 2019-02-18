package com.example.yahtzeegame;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yahtzeegame.backend.*;

public class HomepageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button roll;
    private Game game = new Game();
    private String selectedButton;
    private Integer nRollTurns = 0;

    // initializing scoring buttons for player 1
    private Button p1AcesScoreButton;
    private Button p1TwosScoreButton;
    private Button p1ThreesScoreButton;
    private Button p1FoursScoreButton;
    private Button p1FivesScoreButton;
    private Button p1SixesScoreButton;
    private Button P1ThreeOFaKindScoreButton;
    private Button p1FourOfaKindScoreButton;
    private Button p1smStraightScoreButton;
    private Button p1lgStraightScoreButton;
    private Button p1FullHouseScoreButton;
    private Button P1YahzteeScoreButton;
    private Button p1ChanceScoreButton;

    private TextView p1sumView;
    private TextView p1bonusView;
    private TextView p1totalScoreView;

    // initializing scoring buttons for player 2
    private Button p2AcesScoreButton;
    private Button p2TwosScoreButton;
    private Button p2ThreesScoreButton;
    private Button p2FoursScoreButton;
    private Button p2FivesScoreButton;
    private Button p2SixesScoreButton;
    private Button P2ThreeOFaKindScoreButton;
    private Button p2FourOfaKindScoreButton;
    private Button p2smStraightScoreButton;
    private Button p2lgStraightScoreButton;
    private Button p2FullHouseScoreButton;
    private Button P2YahzteeScoreButton;
    private Button p2ChanceScoreButton;

    private TextView p2sumView;
    private TextView p2bonusView;
    private TextView p2totalScoreView;

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

        p1AcesScoreButton = findViewById(R.id.p1__aces);
        p1TwosScoreButton = findViewById(R.id.p1_twos);
        p1ThreesScoreButton = findViewById(R.id.p1_threes);
        p1FoursScoreButton = findViewById(R.id.p1_fours);
        p1FivesScoreButton = findViewById(R.id.p1_fives);
        p1SixesScoreButton = findViewById(R.id.p1_sixes);
        P1ThreeOFaKindScoreButton = findViewById(R.id.p1_3_of_a_kind);
        p1FourOfaKindScoreButton = findViewById(R.id.p1_4_of_a_kind);
        p1smStraightScoreButton = findViewById(R.id.p1_sm_straight);
        p1lgStraightScoreButton = findViewById(R.id.p1_lg_straight);
        p1FullHouseScoreButton = findViewById(R.id.p1_fullhouse);
        P1YahzteeScoreButton = findViewById(R.id.p1_yahtzee);
        p1ChanceScoreButton = findViewById(R.id.p1_chance);

        p1sumView = findViewById(R.id.p1_initial_sum);
        p1bonusView = findViewById(R.id.p1_bonus_score);
        p1totalScoreView = findViewById(R.id.p1_total_score);


        p2AcesScoreButton = findViewById(R.id.p2_aces);
        p2TwosScoreButton = findViewById(R.id.p2_twos);
        p2ThreesScoreButton = findViewById(R.id.p2_threes);
        p2FoursScoreButton = findViewById(R.id.p2_fours);
        p2FivesScoreButton = findViewById(R.id.p2_fives);
        p2SixesScoreButton = findViewById(R.id.p2_sixes);
        P2ThreeOFaKindScoreButton = findViewById(R.id.p2_3_of_a_kind);
        p2FourOfaKindScoreButton = findViewById(R.id.p2_4_of_a_kind);
        p2smStraightScoreButton = findViewById(R.id.p2_sm_straight);
        p2lgStraightScoreButton = findViewById(R.id.p2_lg_straight);
        p2FullHouseScoreButton = findViewById(R.id.p2_fullhouse);
        P2YahzteeScoreButton = findViewById(R.id.p2_yahtzee);
        p2ChanceScoreButton = findViewById(R.id.p2_chance);

        p2sumView = findViewById(R.id.p2_initial_sum);
        p2bonusView = findViewById(R.id.p2_bonus_score);
        p2totalScoreView = findViewById(R.id.p2_total_score);

        if (this.selectedButton.equals("Single Player")){
            game.initializePlayers(1);
            Player player1 = game.getPlayers().get(0);
            this.setPlayer1GuiButtons(player1);
            game.restAllScoreButtons();
            game.resetAllDices();
            this.nRollTurns = 0;
            game.setInitialPlayer();

        }else{
            game.initializePlayers(2);
            Player player1 = game.getPlayers().get(0);
            Player player2 = game.getPlayers().get(1);
            this.setPlayer1GuiButtons(player1);
            this.setPlayer2GuiButtons(player2);
            game.restAllScoreButtons();
            game.resetAllDices();
            this.nRollTurns = 0;
            game.setInitialPlayer();
        }

        roll = findViewById(R.id.rollButton);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nRollTurns < 2) {
                    game.roll();
                    nRollTurns += 1;
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
                }else{
                    game.getDice((Button)v).deselectDice();
                }
            }
        };

        dice1.setOnClickListener(diceSlection);
        dice2.setOnClickListener(diceSlection);
        dice3.setOnClickListener(diceSlection);
        dice4.setOnClickListener(diceSlection);
        dice5.setOnClickListener(diceSlection);

        View.OnClickListener ScoreButtonSelected = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.resetAllDices();
                nRollTurns = 0;
                game.resetAllDices();
                game.getCurrntScoreButton((Button)v).markScored();
                game.switchPlayer();
                roll.setEnabled(true);
                if (game.checkGameOver()){
                    displayToast("Game Over!!!");
                    roll.setEnabled(false);
                }
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


    public void setPlayer1GuiButtons(Player player1){
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

        player1.setSumView(p1sumView);
        player1.setBonusView(p1bonusView);
        player1.setTotalScoreView(p1totalScoreView);

        p2AcesScoreButton.setEnabled(false);
        p2TwosScoreButton.setEnabled(false);
        p2ThreesScoreButton.setEnabled(false);
        p2FoursScoreButton.setEnabled(false);
        p2FivesScoreButton.setEnabled(false);
        p2SixesScoreButton.setEnabled(false);
        p2FullHouseScoreButton.setEnabled(false);
        P2ThreeOFaKindScoreButton.setEnabled(false);
        p2FourOfaKindScoreButton.setEnabled(false);
        p2ChanceScoreButton.setEnabled(false);
        P2YahzteeScoreButton.setEnabled(false);
        p2lgStraightScoreButton.setEnabled(false);
        p2smStraightScoreButton.setEnabled(false);

        p2AcesScoreButton.setText("");
        p2TwosScoreButton.setText("");
        p2ThreesScoreButton.setText("");
        p2FoursScoreButton.setText("");
        p2FivesScoreButton.setText("");
        p2SixesScoreButton.setText("");
        p2FullHouseScoreButton.setText("");
        P2ThreeOFaKindScoreButton.setText("");
        p2FourOfaKindScoreButton.setText("");
        p2ChanceScoreButton.setText("");
        P2YahzteeScoreButton.setText("");
        p2lgStraightScoreButton.setText("");
        p2smStraightScoreButton.setText("");


        p2AcesScoreButton.setBackgroundColor(0xFFFFFFFF);
        p2TwosScoreButton.setBackgroundColor(0xFFFFFFFF);
        p2ThreesScoreButton.setBackgroundColor(0xFFFFFFFF);
        p2FoursScoreButton.setBackgroundColor(0xFFFFFFFF);
        p2FivesScoreButton.setBackgroundColor(0xFFFFFFFF);
        p2SixesScoreButton.setBackgroundColor(0xFFFFFFFF);
        p2FullHouseScoreButton.setBackgroundColor(0xFFFFFFFF);
        P2ThreeOFaKindScoreButton.setBackgroundColor(0xFFFFFFFF);
        p2FourOfaKindScoreButton.setBackgroundColor(0xFFFFFFFF);
        p2ChanceScoreButton.setBackgroundColor(0xFFFFFFFF);
        P2YahzteeScoreButton.setBackgroundColor(0xFFFFFFFF);
        p2lgStraightScoreButton.setBackgroundColor(0xFFFFFFFF);
        p2smStraightScoreButton.setBackgroundColor(0xFFFFFFFF);

        p2sumView.setText("");
        p2bonusView.setText("");
        p2totalScoreView.setText("");

    }

    public void setPlayer2GuiButtons(Player player2){
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

        player2.setSumView(p2sumView);
        player2.setBonusView(p2bonusView);
        player2.setTotalScoreView(p2totalScoreView);


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
            Player player1 = game.getPlayers().get(0);
            this.setPlayer1GuiButtons(player1);
            game.restAllScoreButtons();
            game.resetAllDices();
            this.nRollTurns = 0;
            game.setInitialPlayer();

        } else if (id == R.id.two_player_mode) {
            game.deleteAllPlayers();
            game.initializePlayers(2);
            Player player1 = game.getPlayers().get(0);
            Player player2 = game.getPlayers().get(1);
            this.setPlayer1GuiButtons(player1);
            this.setPlayer2GuiButtons(player2);
            game.restAllScoreButtons();
            game.resetAllDices();
            this.nRollTurns = 0;
            game.setInitialPlayer();


        } else if (id == R.id.replay_game) {
            game.resetAllDices();
            game.restAllScoreButtons();
            roll.setEnabled(true);
            this.nRollTurns = 0;
            game.setInitialPlayer();

        } else if (id == R.id.exit_app) {
            this.selectedButton = "";
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

