package org.forestpark.scorekeeperapp;

import static org.forestpark.scorekeeperapp.R.id.ResetBtn;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Spinner;
import android.widget.TextView;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView usTitle;
    TextView team1Name;
    TextView team2Name;
    TextView team1Score;
    TextView team2Score;
    EditText team1EditName;
    EditText team2EditName;
    Button team1ScoreBtn1;
    Button team1ScoreBtn2;
    Button team1ScoreBtn3;
    Button team2ScoreBtn1;
    Button team2ScoreBtn2;
    Button team2ScoreBtn3;
    Switch posNegSwitch;
    Button resetBtn;
    Spinner sportSel;
    View mainScreen;
    boolean pNSW = false;
    
    MediaPlayer pointUp;
    MediaPlayer pointDown3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usTitle = (TextView) findViewById(R.id.USTitle);
        team1Name = (TextView) findViewById(R.id.Team1Name);
        team2Name = (TextView) findViewById(R.id.Team2Name);
        team1Score = (TextView) findViewById(R.id.Team1Score);
        team2Score = (TextView) findViewById(R.id.Team2Score);
        team1EditName = (EditText) findViewById(R.id.Team1EditName);
        team2EditName = (EditText) findViewById(R.id.Team2EditName);
        team1ScoreBtn1 = (Button) findViewById(R.id.Team1ScoreBtn1);
        team1ScoreBtn2 = (Button) findViewById(R.id.Team1ScoreBtn2);
        team1ScoreBtn3 = (Button) findViewById(R.id.Team1ScoreBtn3);
        team2ScoreBtn1 = (Button) findViewById(R.id.Team2ScoreBtn1);
        team2ScoreBtn2 = (Button) findViewById(R.id.Team2ScoreBtn2);
        team2ScoreBtn3 = (Button) findViewById(R.id.Team2ScoreBtn3);
        resetBtn = (Button) findViewById(R.id.ResetBtn);
        posNegSwitch = (Switch) findViewById(R.id.PosNegSwitch);
        sportSel = (Spinner) findViewById(R.id.SportSel);
        mainScreen = (View) findViewById(R.id.MainScreen);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.Sports, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sportSel.setAdapter(adapter);

        sportSel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (sportSel.getSelectedItem().toString().equals("Basketball")){
                    usTitle.setText("Basketball");
                    mainScreen.setBackgroundResource(R.drawable.bb_bg50);
                } if (sportSel.getSelectedItem().toString().equals("Football")){
                    usTitle.setText("Football");
                    mainScreen.setBackgroundResource(R.drawable.fb_bg50);
                } if (sportSel.getSelectedItem().toString().equals("Baseball")){
                    usTitle.setText("Baseball");
                    mainScreen.setBackgroundResource(R.drawable.bsb_bg50);
                } if (sportSel.getSelectedItem().toString().equals("Soccer")){
                    usTitle.setText("Soccer");
                    mainScreen.setBackgroundResource(R.drawable.s_bg50);
                } if (sportSel.getSelectedItem().toString().equals("Select a Sport...")) {
                    usTitle.setText("Universal Scoreboard");
                    mainScreen.setBackgroundResource(R.drawable.blk_bg);
                }
        }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        team1EditName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (team1EditName.getText().toString().equals("")) {
                    String teamName = team1EditName.getText().toString();
                    teamName = "Team 1";
                    team1Name.setText(teamName);
                } if (!team1EditName.getText().toString().equals("")){
                    String teamName = team1EditName.getText().toString();
                    team1Name.setText(teamName);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        team2EditName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (team2EditName.getText().toString().equals("")) {
                    String teamName = team2EditName.getText().toString();
                    teamName = "Team 2";
                    team2Name.setText(teamName);
                } if (!team2EditName.getText().toString().equals("")){
                    String teamName = team2EditName.getText().toString();
                    team2Name.setText(teamName);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        posNegSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (posNegSwitch.isChecked()) {
                    pNSW = true;
                    team1ScoreBtn1.setText("-1");
                    team1ScoreBtn2.setText("-2");
                    team1ScoreBtn3.setText("-3");
                    team2ScoreBtn1.setText("-1");
                    team2ScoreBtn2.setText("-2");
                    team2ScoreBtn3.setText("-3");
                } else {
                    pNSW = false;
                    team1ScoreBtn1.setText("+1");
                    team1ScoreBtn2.setText("+2");
                    team1ScoreBtn3.setText("+3");
                    team2ScoreBtn1.setText("+1");
                    team2ScoreBtn2.setText("+2");
                    team2ScoreBtn3.setText("+3");
                }
            }
        });

        team1ScoreBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (team1ScoreBtn1.getText().toString().equals("-1")) {
                    String t1Score = team1Score.getText().toString();
                    int score1 = Integer.parseInt(t1Score);
                    score1 -= 1;
                    if (score1 < 0) {
                        score1 = 0;
                    }
                    if (score1 >= 100) {
                        team1Score.setTextSize(100);
                        team1Score.setPadding(0,100,0,0);
                    } if (score1 < 100) {
                        team1Score.setTextSize(150);
                        team1Score.setPadding(0,0,0,0);
                    }
                    String team1Sc = Integer.toString(score1);
                    team1Score.setText(team1Sc);
                    pointDown3 = MediaPlayer.create(MainActivity.this, R.raw.pointdown3);
                    pointDown3.start();
                } if (team1ScoreBtn1.getText().toString().equals("+1")) {
                    String t1Score = team1Score.getText().toString();
                    int score1 = Integer.parseInt(t1Score);
                    score1 += 1;
                    if (score1 >= 100) {
                        team1Score.setTextSize(100);
                        team1Score.setPadding(0,100,0,0);
                    } if (score1 < 100) {
                        team1Score.setTextSize(150);
                        team1Score.setPadding(0,0,0,0);
                    }
                    String team1Sc = Integer.toString(score1);
                    team1Score.setText(team1Sc);
                    pointUp = MediaPlayer.create(MainActivity.this, R.raw.pointup);
                    pointUp.start();
                }
            }
        });

        team1ScoreBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (team1ScoreBtn2.getText().toString().equals("-2")) {
                    String t1Score = team1Score.getText().toString();
                    int score1 = Integer.parseInt(t1Score);
                    score1 -= 2;
                    if (score1 < 0) {
                        score1 = 0;
                    }
                    if (score1 >= 100) {
                        team1Score.setTextSize(100);
                        team1Score.setPadding(0,100,0,0);
                    } if (score1 < 100) {
                        team1Score.setTextSize(150);
                        team1Score.setPadding(0,0,0,0);
                    }
                    String team1Sc = Integer.toString(score1);
                    team1Score.setText(team1Sc);
                    pointDown3 = MediaPlayer.create(MainActivity.this, R.raw.pointdown3);
                    pointDown3.start();
                } if (team1ScoreBtn2.getText().toString().equals("+2")) {
                    String t1Score = team1Score.getText().toString();
                    int score1 = Integer.parseInt(t1Score);
                    score1 += 2;
                    if (score1 >= 100) {
                        team1Score.setTextSize(100);
                        team1Score.setPadding(0,100,0,0);
                    } if (score1 < 100) {
                        team1Score.setTextSize(150);
                        team1Score.setPadding(0,0,0,0);
                    }
                    String team1Sc = Integer.toString(score1);
                    team1Score.setText(team1Sc);
                    pointUp = MediaPlayer.create(MainActivity.this, R.raw.pointup);
                    pointUp.start();
                }
            }
        });

        team1ScoreBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (team1ScoreBtn3.getText().toString().equals("-3")) {
                    String t1Score = team1Score.getText().toString();
                    int score1 = Integer.parseInt(t1Score);
                    score1 -= 3;
                    if (score1 < 0) {
                        score1 = 0;
                    }
                    if (score1 >= 100) {
                        team1Score.setTextSize(100);
                        team1Score.setPadding(0,100,0,0);
                    } if (score1 < 100) {
                        team1Score.setTextSize(150);
                        team1Score.setPadding(0,0,0,0);
                    }
                    String team1Sc = Integer.toString(score1);
                    team1Score.setText(team1Sc);
                    pointDown3 = MediaPlayer.create(MainActivity.this, R.raw.pointdown3);
                    pointDown3.start();
                } if (team1ScoreBtn3.getText().toString().equals("+3")) {
                    String t1Score = team1Score.getText().toString();
                    int score1 = Integer.parseInt(t1Score);
                    score1 += 3;
                    if (score1 >= 100) {
                        team1Score.setTextSize(100);
                        team1Score.setPadding(0,100,0,0);
                    } if (score1 < 100) {
                        team1Score.setTextSize(150);
                        team1Score.setPadding(0,0,0,0);
                    }
                    String team1Sc = Integer.toString(score1);
                    team1Score.setText(team1Sc);
                    pointUp = MediaPlayer.create(MainActivity.this, R.raw.pointup);
                    pointUp.start();
                }
            }
        });

        team2ScoreBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (team2ScoreBtn1.getText().toString().equals("-1")) {
                    String t2Score = team2Score.getText().toString();
                    int score2 = Integer.parseInt(t2Score);
                    score2 -= 1;
                    if (score2 < 0) {
                        score2 = 0;
                    }
                    if (score2 >= 100) {
                        team2Score.setTextSize(100);
                        team2Score.setPadding(0,100,0,0);
                    } else {
                        team2Score.setTextSize(150);
                        team2Score.setPadding(0,0,0,0);
                    }
                    String team2Sc = Integer.toString(score2);
                    team2Score.setText(team2Sc);
                    pointDown3 = MediaPlayer.create(MainActivity.this, R.raw.pointdown3);
                    pointDown3.start();
                } if (team2ScoreBtn1.getText().toString().equals("+1")) {
                    String t2Score = team2Score.getText().toString();
                    int score2 = Integer.parseInt(t2Score);
                    score2 += 1;
                    if (score2 >= 100) {
                        team2Score.setTextSize(100);
                        team2Score.setPadding(0,100,0,0);
                    } else {
                        team2Score.setTextSize(150);
                        team2Score.setPadding(0,0,0,0);
                    }
                    String team2Sc = Integer.toString(score2);
                    team2Score.setText(team2Sc);
                    pointUp = MediaPlayer.create(MainActivity.this, R.raw.pointup);
                    pointUp.start();
                }
            }
        });

        team2ScoreBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (team2ScoreBtn2.getText().toString().equals("-2")) {
                    String t2Score = team2Score.getText().toString();
                    int score2 = Integer.parseInt(t2Score);
                    score2 -= 2;
                    if (score2 < 0) {
                        score2 = 0;
                    }
                    if (score2 >= 100) {
                        team2Score.setTextSize(100);
                        team2Score.setPadding(0,100,0,0);
                    } else {
                        team2Score.setTextSize(150);
                        team2Score.setPadding(0,0,0,0);
                    }
                    String team2Sc = Integer.toString(score2);
                    team2Score.setText(team2Sc);
                    pointDown3 = MediaPlayer.create(MainActivity.this, R.raw.pointdown3);
                    pointDown3.start();
                } if (team2ScoreBtn2.getText().toString().equals("+2")) {
                    String t2Score = team2Score.getText().toString();
                    int score2 = Integer.parseInt(t2Score);
                    score2 += 2;
                    if (score2 >= 100) {
                        team2Score.setTextSize(100);
                        team2Score.setPadding(0,100,0,0);
                    } else {
                        team2Score.setTextSize(150);
                        team2Score.setPadding(0,0,0,0);
                    }
                    String team2Sc = Integer.toString(score2);
                    team2Score.setText(team2Sc);
                    pointUp = MediaPlayer.create(MainActivity.this, R.raw.pointup);
                    pointUp.start();
                }
            }
        });

        team2ScoreBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (team2ScoreBtn3.getText().toString().equals("-3")) {
                    String t2Score = team2Score.getText().toString();
                    int score2 = Integer.parseInt(t2Score);
                    score2 -= 3;
                    if (score2 < 0) {
                        score2 = 0;
                    }
                    String team2Sc = Integer.toString(score2);
                    team2Score.setText(team2Sc);
                    pointDown3 = MediaPlayer.create(MainActivity.this, R.raw.pointdown3);
                    pointDown3.start();
                } if (team2ScoreBtn3.getText().toString().equals("+3")) {
                    String t2Score = team2Score.getText().toString();
                    int score2 = Integer.parseInt(t2Score);
                    score2 += 3;
                    if (score2 >= 100) {
                        team2Score.setTextSize(100);
                        team2Score.setPadding(0,100,0,0);
                    } else {
                        team2Score.setTextSize(150);
                        team2Score.setPadding(0,0,0,0);
                    }
                    String team2Sc = Integer.toString(score2);
                    team2Score.setText(team2Sc);
                    pointUp = MediaPlayer.create(MainActivity.this, R.raw.pointup);
                    pointUp.start();
                }
            }
        });

    resetBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String score1 = "0";
            team1Score.setText(score1);
            team2Score.setText(score1);
            MediaPlayer resetSE = MediaPlayer.create(MainActivity.this, R.raw.resetse);
            resetSE.start();

        }
    });
}
}