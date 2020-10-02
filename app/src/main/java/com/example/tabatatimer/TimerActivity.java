package com.example.tabatatimer;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TimerActivity extends AppCompatActivity {
    TimerHandler handler;
    PendingIntent pi;
    final int TASK1_CODE = 1;

    public final static int STATUS_FINISH = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        WorkoutModel workout = (WorkoutModel) getIntent().getSerializableExtra("SEQUENCE");
        getWindow().getDecorView().setBackgroundColor(workout.getColor());


        TextView timer = (TextView) findViewById(R.id.timer);
        TextView cycleNumber = (TextView) findViewById(R.id.stage);
        ListView listView = (ListView) findViewById(R.id.stages);
        ImageButton lock = (ImageButton) findViewById(R.id.lockUnlock);
        ImageButton playPause = (ImageButton) findViewById(R.id.playPause);
        ImageButton nextStage = (ImageButton) findViewById(R.id.nextStage);
        ImageButton prevStage = (ImageButton) findViewById(R.id.prevStage);

        TextView holeTime = (TextView) findViewById(R.id.hole_time);
        pi = createPendingResult(TASK1_CODE, new Intent(this, TimerService.class), 0);
        handler = new TimerHandler(this, workout, timer, holeTime, cycleNumber,
                listView, lock, nextStage, prevStage, playPause, pi);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.stopTimer();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == STATUS_FINISH) {
            int result = data.getIntExtra("RESULT", 0);
            if (result == 100)
                handler.restartTimer();
            else if (result == 0) {
                handler.stopTimer();
            }
        }
    }

    public void lockUnlock(View view) {
        handler.lockUnlock();
    }

    public void playPause(View view) {
        handler.startStop();
    }

    public void prevStage(View view) {
        handler.prevStage();
    }

    public void nextStage(View view) {
        handler.nextStage();
    }
}
