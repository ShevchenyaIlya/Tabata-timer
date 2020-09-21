package com.example.tabatatimer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkoutModel implements Serializable {
    private int id;
    private String workout_name;
    private int preparation_time;
    private int stretch_time;
    private int work_time;
    private int relax_time;
    private int cycles_number;
    private int sets_number;
    private int relax_after_set_time;
    private ArrayList<String> workout_order;
    private ArrayList<Integer> workout_order_time;
    private String description;



    // If id is equal to 0, then this object doesn't saved in database
    public WorkoutModel(int id, String workout_name, int preparation_time, int stretch_time, int work_time, int relax_time, int cycles_number, int sets_number, int relax_after_set_time, String workout_order_value, String workout_order_time_value) {
        this.id = id;
        this.workout_name = workout_name;
        this.preparation_time = preparation_time;
        this.stretch_time = stretch_time;
        this.work_time = work_time;
        this.relax_time = relax_time;
        this.cycles_number = cycles_number;
        this.sets_number = sets_number;
        this.relax_after_set_time = relax_after_set_time;

        if (workout_order_time_value.equals("") && workout_order_value.equals("")) {
            createWorkingOrder();
        }
        else {
            workout_order = new ArrayList<String>(Arrays.asList(Converter.convertStringToArray(workout_order_value)));
            workout_order_time = new ArrayList<Integer>(Arrays.asList(Converter.convertStringToIntegerArray(workout_order_time_value)));
        }

        this.description = createDescription();
    }

    private void createWorkingOrder() {
        workout_order = new ArrayList<String>();
        workout_order_time = new ArrayList<Integer>();
        workout_order.add("Preparation");
        workout_order_time.add(preparation_time);
        workout_order.add("Stretch");
        workout_order_time.add(stretch_time);
        for (int i = 0; i < sets_number; i++) {
            for (int j = 0; j < cycles_number; j++) {
                workout_order.add("Work");
                workout_order_time.add(work_time);
                workout_order.add("Rest");
                workout_order_time.add(relax_time);

            }
            workout_order.add("SetsRest");
            workout_order_time.add(relax_after_set_time);
        }
    }

    public void updateWorkingOrder() {
        createWorkingOrder();
    }

    public ArrayList<String> getWorkout_order() {
        return workout_order;
    }

    public void setWorkout_order(ArrayList<String> workout_order) {
        this.workout_order = workout_order;
    }

    public ArrayList<Integer> getWorkout_order_time() {
        return workout_order_time;
    }

    public void setWorkout_order_time(ArrayList<Integer> workout_order_time) {
        this.workout_order_time = workout_order_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean saveWorkout(DatabaseHelper dbHelper) {
        return dbHelper.insertData(this.workout_name, this.preparation_time, this.stretch_time,
                this.work_time, this.relax_time, this.cycles_number, this.sets_number, this.relax_after_set_time, Converter.convertArrayToString(workout_order.toArray(new String[0])), Converter.convertIntArrayToString(workout_order_time.toArray(new Integer[0])));
    }

    public int deleteWorkout(DatabaseHelper dbHelper) {

        return dbHelper.deleteData(String.valueOf(this.id));
    }

    public void updateWorkout(DatabaseHelper dbHelper) {
        dbHelper.updateData(this.id, this.workout_name, this.preparation_time, this.stretch_time,
                this.work_time, this.relax_time, this.cycles_number, this.sets_number, this.relax_after_set_time,
                Converter.convertArrayToString(workout_order.toArray(new String[0])), Converter.convertIntArrayToString(workout_order_time.toArray(new Integer[0])));
    }

    private String createDescription() {
        int whole_time = 0;
        for (Integer time: workout_order_time) {
            whole_time += time;
        }
        return "Whole training time: " + whole_time + "\nCycle number: " + cycles_number
                + "\nSets number: " + sets_number;
    }

    public void updateDescription() {
        this.description = createDescription();
    }
    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public int getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(int preparation_time) {
        this.preparation_time = preparation_time;
    }

    public int getStretch_time() {
        return stretch_time;
    }

    public void setStretch_time(int stretch_time) {
        this.stretch_time = stretch_time;
    }

    public int getWork_time() {
        return work_time;
    }

    public void setWork_time(int work_time) {
        this.work_time = work_time;
    }

    public int getRelax_time() {
        return relax_time;
    }

    public void setRelax_time(int relax_time) {
        this.relax_time = relax_time;
    }

    public int getCycles_number() {
        return cycles_number;
    }

    public void setCycles_number(int cycles_number) {
        this.cycles_number = cycles_number;
    }

    public int getSets_number() {
        return sets_number;
    }

    public void setSets_number(int sets_number) {
        this.sets_number = sets_number;
    }

    public int getRelax_after_set_time() {
        return relax_after_set_time;
    }

    public void setRelax_after_set_time(int relax_after_set_time) {
        this.relax_after_set_time = relax_after_set_time;
    }
}
