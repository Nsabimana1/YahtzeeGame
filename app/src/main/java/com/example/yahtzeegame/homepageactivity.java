package com.example.yahtzeegame;

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

import com.example.yahtzeegame.backend.*;

import java.util.ArrayList;

public class homepageactivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Button> diceButtons;
    private Button roll;

    private Game game = new Game();
    private String selectedButton = OpenAppActivity.selectedbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepageactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final Button dice1 = findViewById(R.id.dice1);
        final Button dice2 = findViewById(R.id.dice2);
        final Button dice3 = findViewById(R.id.dice3);
        final Button dice4 = findViewById(R.id.dice4);
        final Button dice5 = findViewById(R.id.dice5);
        final Button dice6 = findViewById(R.id.dice6);


        game.addDiceButton(dice1);
        game.addDiceButton(dice2);
        game.addDiceButton(dice3);
        game.addDiceButton(dice4);
        game.addDiceButton(dice5);
        game.addDiceButton(dice6);

        if (this.selectedButton == "Single Player"){
            game.addPlayer(new Player());
        }else{
            game.addPlayer(new Player());
            game.addPlayer(new Player());
        }

        roll = findViewById(R.id.rollButton);
        game.startGame();
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.roll();
            }
        });

        dice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!game.getDice(dice1).isSelected()) {
                    game.getDice(dice1).selectDice();
                }
            }
        });



        dice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!game.getDice(dice2).isSelected()) {
                    game.getDice(dice2).selectDice();
                }
            }
        });

        dice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!game.getDice(dice3).isSelected()) {
                    game.getDice(dice3).selectDice();
                }
            }
        });


        dice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!game.getDice(dice4).isSelected()) {
                    game.getDice(dice4).selectDice();
                }
            }
        });


        dice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!game.getDice(dice5).isSelected()) {
                    game.getDice(dice5).selectDice();
                }
            }
        });

        dice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!game.getDice(dice6).isSelected()) {
                    game.getDice(dice6).selectDice();
                }
            }
        });

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
            game.addPlayer(new Player());
            // Handle the camera action
        } else if (id == R.id.two_player_mode) {
            game.deleteAllPlayers();
            game.addPlayer(new Player());
            game.addPlayer(new Player());

        } else if (id == R.id.replay_game) {

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

