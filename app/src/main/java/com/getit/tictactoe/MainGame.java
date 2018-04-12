package com.getit.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainGame extends AppCompatActivity {
    ArrayList<Button> buttonGroup;
    TextView Xscore;
    TextView Oscore;
    boolean isAi;
    int xo;
    int oo;
    TextView turn;
    boolean turnx;
    ArrayList<Integer> xPositions;
    Random rand;
    ArrayList<Integer> oPositions;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        buttonGroup = new ArrayList<>();
        buttonGroup.add((Button) findViewById(R.id.button1));
        buttonGroup.add((Button) findViewById(R.id.button2));
        buttonGroup.add((Button) findViewById(R.id.button3));
        buttonGroup.add((Button) findViewById(R.id.button4));
        buttonGroup.add((Button) findViewById(R.id.button5));
        buttonGroup.add((Button) findViewById(R.id.button6));
        buttonGroup.add((Button) findViewById(R.id.button7));
        buttonGroup.add((Button) findViewById(R.id.button8));
        buttonGroup.add((Button) findViewById(R.id.button9));
        Xscore = findViewById(R.id.Xscore);
        Oscore = findViewById(R.id.Oscore);
        turn = findViewById(R.id.Turn);
        turnx = true;
        xo = oo = 0;
        isAi = false;
        rand = new Random();
        xPositions = new ArrayList<>();
        oPositions = new ArrayList<>();
        try {
            if (getIntent().getStringExtra("isAi").equals("isAi")) {
                isAi = true;
                turnx = true;
            }
        }catch (Exception e){
            Log.i("ai","he nhe ai");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        turnx = true;
    }

    public void buttonClick(View v) {
        Log.i("userID","id is "+v.getId());
        switch(v.getId()){
            case R.id.button1:
                if(buttonGroup.get(0).getText().toString().equals(" ")){
                    if(turnx) {
                        buttonGroup.get(0).setText("X");
                    }else{
                        buttonGroup.get(0).setText("O");
                    }
                }
                break;
            case R.id.button2:
                if(buttonGroup.get(1).getText().toString().equals(" ")){
                    if(turnx) {
                        buttonGroup.get(1).setText("X");
                    }else{
                        buttonGroup.get(1).setText("O");
                    }
                }
                break;
            case R.id.button3:
                if(buttonGroup.get(2).getText().toString().equals(" ")){
                    if(turnx) {
                        buttonGroup.get(2).setText("X");
                    }else{
                        buttonGroup.get(2).setText("O");
                    }
                }
                break;
            case R.id.button4:
                if(buttonGroup.get(3).getText().toString().equals(" ")){
                    if(turnx) {
                        buttonGroup.get(3).setText("X");
                    }else{
                        buttonGroup.get(3).setText("O");
                    }
                }
                break;
            case R.id.button5:
                if(buttonGroup.get(4).getText().toString().equals(" ")){
                    if(turnx) {
                        buttonGroup.get(4).setText("X");

                    }else{
                        buttonGroup.get(4).setText("O");
                    }
                }
                break;
            case R.id.button6:
                if(buttonGroup.get(5).getText().toString().equals(" ")){
                    if(turnx) {
                        buttonGroup.get(5).setText("X");

                    }else{
                        buttonGroup.get(5).setText("O");
                    }
                }
                break;
            case R.id.button7:
                if(buttonGroup.get(6).getText().toString().equals(" ")){
                    if(turnx) {
                        buttonGroup.get(6).setText("X");
                    }else{
                        buttonGroup.get(6).setText("O");
                    }
                }
                break;
            case R.id.button8:
                if(buttonGroup.get(7).getText().toString().equals(" ")){
                    if(turnx) {
                        buttonGroup.get(7).setText("X");
                    }else{
                        buttonGroup.get(7).setText("O");
                    }
                }
                break;
            case R.id.button9:
                if(buttonGroup.get(8).getText().toString().equals(" ")){
                    if(turnx) {
                        buttonGroup.get(8).setText("X");
                    }else{
                        buttonGroup.get(8).setText("O");
                    }
                }
                break;
        }

        if (winningLogic()) {
            if (turnx) {
                xo += 1;
                Xscore.setText(Integer.toString(xo));
                if (isAi){
                    turnx = true;
                    turn.setText("Turn X");
                }else {
                    turnx = false;
                    turn.setText("Turn O");
                }
            }else {
                oo += 1;
                Oscore.setText(Integer.toString(oo));
                turnx = true;
                turn.setText("Turn X");
            }
            for (Button a:buttonGroup){
                a.setText(" ");
            }
        }
        if (isAi && !isFilled() && turnx && !isEmpty()) {
            int id = minimax(true);
            Log.i("aibutton"," "+id);
            Button AiButton;
            if (id != 0){
                AiButton = findViewById(id);
            }else{
                ArrayList<Integer> valid = new ArrayList<>();
                for (Button a:buttonGroup){
                    if (a.getText().toString().equals(" ")){
                        valid.add(a.getId());
                    }
                    //valid.get(rand.nextInt(valid.size()));
                }
                AiButton = findViewById(valid.get(rand.nextInt(valid.size())));
            }

            AiButton.setText("O");
            Log.i("Ai",winningLogic()+" result");
            turnx = false;
            if (winningLogic()) {
                oo += 1;
                Oscore.setText(Integer.toString(oo));
                for (Button a:buttonGroup){
                    a.setText(" ");
                }
            }
            turnx = true;
            turn.setText("Turn X");
        }
        if (isFilled()) {
            xo += 1;
            Xscore.setText(Integer.toString(xo));
            oo += 1;
            Oscore.setText(Integer.toString(oo));
            for (Button a:buttonGroup){
                a.setText(" ");
            }
            turnx = !turnx;
            if (turnx) {
                turn.setText("Turn X");
            }else {
                turn.setText("Turn O");
            }
            if (isAi) {
                turn.setText("Turn X");
                turnx = true;
            }
        }else {
            if (!isAi) {
                turnx = !turnx;
                if (turnx){
                    turn.setText("Turn X");
                }else{
                    turn.setText("Turn O");
                }
            }
        }
        if (isAi){
            turnx = true;
            turn.setText("Turn X");
        }

    }

    public boolean isFilled() {
        for(Button a:buttonGroup) {
            if (a.getText().toString().equals(" ")){
                return false;
            }
        }
        return true;

    }
    public boolean isEmpty() {
        for(Button a:buttonGroup) {
            if (a.getText().toString().equals(" ")){
                continue;
            }
            return false;
        }
        return true;
    }
    public boolean winningLogic() {
        if (turnx) {
            if (buttonGroup.get(0).getText().toString().equals("X") && buttonGroup.get(1).getText().toString().equals("X") && buttonGroup.get(2).getText().toString().equals("X")){
                return true;
            }else if(buttonGroup.get(3).getText().toString().equals("X") && buttonGroup.get(4).getText().toString().equals("X") && buttonGroup.get(5).getText().toString().equals("X")){
                return true;
            }else if(buttonGroup.get(6).getText().toString().equals("X") && buttonGroup.get(7).getText().toString().equals("X") && buttonGroup.get(8).getText().toString().equals("X")){
                return true;
            }else if(buttonGroup.get(0).getText().toString().equals("X") && buttonGroup.get(3).getText().toString().equals("X") && buttonGroup.get(6).getText().toString().equals("X")){
                return true;
            }else if(buttonGroup.get(1).getText().toString().equals("X") && buttonGroup.get(4).getText().toString().equals("X") && buttonGroup.get(7).getText().toString().equals("X")){
                return true;
            }else if(buttonGroup.get(2).getText().toString().equals("X") && buttonGroup.get(5).getText().toString().equals("X") && buttonGroup.get(8).getText().toString().equals("X")){
                return true;
            }else if(buttonGroup.get(0).getText().toString().equals("X") && buttonGroup.get(4).getText().toString().equals("X") && buttonGroup.get(8).getText().toString().equals("X")){
                return true;
            }else if(buttonGroup.get(2).getText().toString().equals("X") && buttonGroup.get(4).getText().toString().equals("X") && buttonGroup.get(6).getText().toString().equals("X")){
                return true;
            }else {
                return false;
            }
        }
        if(!turnx){
            if (buttonGroup.get(0).getText().toString().equals("O") && buttonGroup.get(1).getText().toString().equals("O") && buttonGroup.get(2).getText().toString().equals("O")){
                return true;
            }else if(buttonGroup.get(3).getText().toString().equals("O") && buttonGroup.get(4).getText().toString().equals("O") && buttonGroup.get(5).getText().toString().equals("O")){
                return true;
            }else if(buttonGroup.get(6).getText().toString().equals("O") && buttonGroup.get(7).getText().toString().equals("O") && buttonGroup.get(8).getText().toString().equals("O")){
                return true;
            }else if(buttonGroup.get(0).getText().toString().equals("O") && buttonGroup.get(3).getText().toString().equals("O") && buttonGroup.get(6).getText().toString().equals("O")){
                return true;
            }else if(buttonGroup.get(1).getText().toString().equals("O") && buttonGroup.get(4).getText().toString().equals("O") && buttonGroup.get(7).getText().toString().equals("O")){
                return true;
            }else if(buttonGroup.get(2).getText().toString().equals("O") && buttonGroup.get(5).getText().toString().equals("O") && buttonGroup.get(8).getText().toString().equals("O")){
                return true;
            }else if(buttonGroup.get(0).getText().toString().equals("O") && buttonGroup.get(4).getText().toString().equals("O") && buttonGroup.get(8).getText().toString().equals("O")){
                return true;
            }else if(buttonGroup.get(2).getText().toString().equals("O") && buttonGroup.get(4).getText().toString().equals("O") && buttonGroup.get(6).getText().toString().equals("O")){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }
    public int AiScore() {
        if (turnx) {
            if (buttonGroup.get(0).getText().toString().equals("X") && buttonGroup.get(1).getText().toString().equals("X") && buttonGroup.get(2).getText().toString().equals("X")){
                return -10;
            }else if(buttonGroup.get(3).getText().toString().equals("X") && buttonGroup.get(4).getText().toString().equals("X") && buttonGroup.get(5).getText().toString().equals("X")){
                return -10;
            }else if(buttonGroup.get(6).getText().toString().equals("X") && buttonGroup.get(7).getText().toString().equals("X") && buttonGroup.get(8).getText().toString().equals("X")){
                return -10;
            }else if(buttonGroup.get(0).getText().toString().equals("X") && buttonGroup.get(3).getText().toString().equals("X") && buttonGroup.get(6).getText().toString().equals("X")){
                return -10;
            }else if(buttonGroup.get(1).getText().toString().equals("X") && buttonGroup.get(4).getText().toString().equals("X") && buttonGroup.get(7).getText().toString().equals("X")){
                return -10;
            }else if(buttonGroup.get(2).getText().toString().equals("X") && buttonGroup.get(5).getText().toString().equals("X") && buttonGroup.get(8).getText().toString().equals("X")){
                return -10;
            }else if(buttonGroup.get(0).getText().toString().equals("X") && buttonGroup.get(4).getText().toString().equals("X") && buttonGroup.get(8).getText().toString().equals("X")){
                return -10;
            }else if(buttonGroup.get(2).getText().toString().equals("X") && buttonGroup.get(4).getText().toString().equals("X") && buttonGroup.get(6).getText().toString().equals("X")){
                return -10;
            }
        }
        if(!turnx){
            if (buttonGroup.get(0).getText().toString().equals("O") && buttonGroup.get(1).getText().toString().equals("O") && buttonGroup.get(2).getText().toString().equals("O")){
                return 10;
            }else if(buttonGroup.get(3).getText().toString().equals("O") && buttonGroup.get(4).getText().toString().equals("O") && buttonGroup.get(5).getText().toString().equals("O")){
                return 10;
            }else if(buttonGroup.get(6).getText().toString().equals("O") && buttonGroup.get(7).getText().toString().equals("O") && buttonGroup.get(8).getText().toString().equals("O")){
                return 10;
            }else if(buttonGroup.get(0).getText().toString().equals("O") && buttonGroup.get(3).getText().toString().equals("O") && buttonGroup.get(6).getText().toString().equals("O")){
                return 10;
            }else if(buttonGroup.get(1).getText().toString().equals("O") && buttonGroup.get(4).getText().toString().equals("O") && buttonGroup.get(7).getText().toString().equals("O")){
                return 10;
            }else if(buttonGroup.get(2).getText().toString().equals("O") && buttonGroup.get(5).getText().toString().equals("O") && buttonGroup.get(8).getText().toString().equals("O")){
                return 10;
            }else if(buttonGroup.get(0).getText().toString().equals("O") && buttonGroup.get(4).getText().toString().equals("O") && buttonGroup.get(8).getText().toString().equals("O")){
                return 10;
            }else if(buttonGroup.get(2).getText().toString().equals("O") && buttonGroup.get(4).getText().toString().equals("O") && buttonGroup.get(6).getText().toString().equals("O")){
                return 10;
            }
        }
        return 0;
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
    public int minimax(boolean isMax) {

        if (buttonGroup.get(4).getText().toString().equals(" ")){
            return buttonGroup.get(4).getId();
        }
        for (Button a: buttonGroup){
            if (a.getText().toString().equals(" ")){
                a.setText("O");
                turnx=!turnx;
                if (winningLogic()){
                    a.setText(" ");
                    turnx=!turnx;
                    return a.getId();
                }
                a.setText(" ");
                turnx=!turnx;
            }
        }
        for (Button a: buttonGroup){
            if (a.getText().toString().equals(" ")){
                a.setText("X");
//                turnx=!turnx;
                if (winningLogic()){
                    a.setText(" ");
  //                  turnx=!turnx;
                    return a.getId();
                }
                a.setText(" ");
    //            turnx=!turnx;
            }
        }
        if (isMax){
            for (Button a: buttonGroup){
                if (a.getText().toString().equals(" ")){
                    a.setText("O");
                    turnx=!turnx;
                    int id = minimax(!isMax);

                    if (id != 0){
                        a.setText(" ");
                        turnx=!turnx;
                        return a.getId();
                    }
                    a.setText(" ");
                    turnx=!turnx;
                }
            }
        }else {
            for (Button a: buttonGroup){
                if (a.getText().toString().equals(" ")){
                    a.setText("X");
                    turnx=!turnx;
                    int id = minimax(!isMax);
                    if (id == 0){
                        a.setText(" ");
                        turnx=!turnx;
                        return a.getId();
                    }
                    a.setText(" ");
                    turnx=!turnx;
                }
            }
        }
        ArrayList<Integer> valid = new ArrayList<>();
        for (Button a:buttonGroup){
            if (a.getText().toString().equals(" ")){
                valid.add(a.getId());
            }
            //valid.get(rand.nextInt(valid.size()));
        }
        return 0;
    }

}
