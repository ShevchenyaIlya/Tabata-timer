package com.example.tabatatimer;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class TimerHandler implements Serializable {
    public WorkoutModel workout;
    public static long timeLeftInMilliseconds;
    public boolean timerRunning = true;
    public int stage = 0;
    int currentCycle = 0;
    public ArrayList<String> array;
    public Intent intent;
    private Context context;
    PendingIntent pi;

    public TimerHandler(Context context, WorkoutModel workout, PendingIntent pi, ArrayList<String> array) {
        this.context = context;
        this.workout = workout;
        this.array = array;
        this.pi = pi;

        timeLeftInMilliseconds = (workout.getWorkoutOrderTime().get(stage) + 1) * 1000;
        startTimer();
    }


    public void startTimer() {
        intent = new Intent(context, TimerService.class);
        intent.putExtra("TIME", timeLeftInMilliseconds).putExtra("PENDING_INTENT", pi);
        context.startService(intent);
        timerRunning = true;
    }

    public void stopTimer() {
        context.stopService(intent);
        timerRunning = false;
    }
}