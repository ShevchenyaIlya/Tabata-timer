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
    private static TextView timer;
    private TextView cycleNumber;
    private WorkoutModel workout;
    private ListView listView;
    private ImageButton playPause;
    private ImageButton lock;
    private ImageButton nextStage;
    private ImageButton prevStage;


    private static long timeLeftInMilliseconds;
    private boolean timerRunning = true;
    private boolean lockedButtons = false;
    private int stage = 0;
    int currentCycle = 0;
    private ArrayList<String> array;
    private Intent intent;
    Context context;
    private static MediaPlayer sound;
    PendingIntent pi;

    public TimerHandler(Context context, WorkoutModel workout, TextView timer, TextView holeTime,
                        TextView cycleNumber, ListView listView, ImageButton lock, ImageButton nextStage,
                        ImageButton prevStage, ImageButton playPause, PendingIntent pi) {
        this.context = context;
        this.workout = workout;
        this.timer = timer;
        this.cycleNumber = cycleNumber;
        this.listView = listView;
        this.lock = lock;
        this.nextStage = nextStage;
        this.prevStage = prevStage;
        this.playPause = playPause;
        this.pi = pi;

        sound = MediaPlayer.create(this.context, R.raw.censor_beep);
        timer.setText(workout.getWorkout_order_time().get(stage).toString());
        int cycle_value = workout.getCycles_number() * workout.getSets_number();
        cycleNumber.setText(String.valueOf(currentCycle) + "/" + cycle_value);
        array = new ArrayList<String>();
        int stages_number = workout.getWorkout_order().size();
        for (int i = 0; i < stages_number; i++)
        {
            array.add(String.valueOf(i + 1) + ". " + workout.getWorkout_order().get(i));
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(arrayAdapter);
        listView.setBackgroundColor(workout.getColor());
        int time = 0;
        for (Integer seconds: workout.getWorkout_order_time()) {
            time += seconds;
        }
        int min = time / 60;
        int seconds = time - min * 60;
        holeTime.setText(String.valueOf(min) + ":" + seconds);

        timeLeftInMilliseconds = (workout.getWorkout_order_time().get(stage) + 1) * 1000;
        startTimer();
    }

    public static void playSound() {
        sound.start();
    }

    public static void setText(long time) {
        timeLeftInMilliseconds = time;
        timer.setText(String.valueOf((timeLeftInMilliseconds / 1000) - 1));
    }

    public void restartTimer() {
        stopTimer();
        stage++;
        if (stage != workout.getWorkout_order_time().size()) {
            array.remove(0);
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array);
            listView.setAdapter(arrayAdapter);
            if (workout.getWorkout_order().get(stage).equals("Work")) {
                updateStageTextView();
            }
            timeLeftInMilliseconds = (workout.getWorkout_order_time().get(stage) + 1) * 1000;
            startTimer();
        }
        else {
            stopTimer();
            lock.setClickable(false);
            lock.setEnabled(false);
            disableButtons();
        }
    }

    public void startStop() {
        if (timerRunning) {
            stopTimer();
            playPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            timerRunning = false;
        }
        else {
            startTimer();
            playPause.setImageResource(R.drawable.pause);
            timerRunning = true;
        }
    }

    public void updateStageTextView() {
        int cycle_value = workout.getCycles_number() * workout.getSets_number();
        currentCycle++;
        if (currentCycle < cycle_value + 1) {
            cycleNumber.setText(String.valueOf(currentCycle) + "/" + cycle_value);
        }
    }

    public  void startTimer() {
        intent = new Intent(context, TimerService.class);
        intent.putExtra("TIME", timeLeftInMilliseconds).putExtra("PENDING_INTENT", pi);
        context.startService(intent);
        timerRunning = true;
    }

    public void stopTimer() {
//        countDownTimer.cancel();
        context.stopService(intent);
        timerRunning = false;
    }

    private void enableButtons() {
        playPause.setClickable(true);
        playPause.setEnabled(true);
        prevStage.setClickable(true);
        prevStage.setEnabled(true);
        nextStage.setClickable(true);
        nextStage.setEnabled(true);
    }

    private void disableButtons() {
        playPause.setClickable(false);
        playPause.setEnabled(false);
        prevStage.setClickable(false);
        prevStage.setEnabled(false);
        nextStage.setClickable(false);
        nextStage.setEnabled(false);
    }

    public void lockUnlock() {
        if (lockedButtons) {
            lock.setImageResource(R.drawable.ic_baseline_lock_open_24);
            enableButtons();
            lockedButtons = false;
        }
        else {
            lock.setImageResource(R.drawable.ic_baseline_lock_24);
            disableButtons();
            lockedButtons = true;
        }
    }

    public void playPause() {
        startStop();
    }

    public void prevStage() {
        if (stage != 0) {
            stage--;
            stopTimer();
            array = new ArrayList<String>();
            int stages_number = workout.getWorkout_order().size();
            for (int i = stage; i < stages_number; i++)
            {
                array.add(String.valueOf(i + 1) + ". " + workout.getWorkout_order().get(i));
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array);
            listView.setAdapter(arrayAdapter);

            if (workout.getWorkout_order().get(stage + 1).equals("Work")) {
                int cycle_value = workout.getCycles_number() * workout.getSets_number();
                currentCycle--;
                if (currentCycle < cycle_value + 1) {
                    cycleNumber.setText(String.valueOf(currentCycle) + "/" + cycle_value);
                }
            }
            timeLeftInMilliseconds = (workout.getWorkout_order_time().get(stage) + 1) * 1000;
            startTimer();
        }
    }

    public void nextStage() {
        if(stage < workout.getWorkout_order().size() - 1){
            restartTimer();
        }
        else if (stage == workout.getWorkout_order().size() - 1) {
            stopTimer();
            stage++;
            timer.setText("0");
            lock.setClickable(false);
            lock.setEnabled(false);
            disableButtons();
        }
    }
}
