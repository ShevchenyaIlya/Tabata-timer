package com.example.tabatatimer;

public class WorkoutModel {
    private int id;
    private String workout_name;
    private int preparation_time;
    private int stretch_time;
    private int work_count;
    private int work_time;
    private int relax_count;
    private int relax_time;
    private int cycles_number;
    private int sets_number;
    private int relax_after_set_time;

    // If id is equal to 0, then this object doesn't saved in database
    public WorkoutModel(int id, String workout_name, int preparation_time, int stretch_time, int work_count, int work_time, int relax_count, int relax_time, int cycles_number, int sets_number, int relax_after_set_time) {
        this.id = id;
        this.workout_name = workout_name;
        this.preparation_time = preparation_time;
        this.stretch_time = stretch_time;
        this.work_count = work_count;
        this.work_time = work_time;
        this.relax_count = relax_count;
        this.relax_time = relax_time;
        this.cycles_number = cycles_number;
        this.sets_number = sets_number;
        this.relax_after_set_time = relax_after_set_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean saveWorkout(DatabaseHelper dbHelper) {
        return dbHelper.insertData(this.workout_name, this.preparation_time, this.stretch_time,
                this.work_count, this.work_time, this.relax_count, this.relax_time,
                this.cycles_number, this.sets_number, this.relax_after_set_time);
    }

    public int deleteWorkout(DatabaseHelper dbHelper) {

        return dbHelper.deleteData(String.valueOf(this.id));
    }

    public void updateWorkout(DatabaseHelper dbHelper) {
        dbHelper.updateData(this.id, this.workout_name, this.preparation_time, this.stretch_time,
                this.work_count, this.work_time, this.relax_count, this.relax_time,
                this.cycles_number, this.sets_number, this.relax_after_set_time);
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

    public int getWork_count() {
        return work_count;
    }

    public void setWork_count(int work_count) {
        this.work_count = work_count;
    }

    public int getWork_time() {
        return work_time;
    }

    public void setWork_time(int work_time) {
        this.work_time = work_time;
    }

    public int getRelax_count() {
        return relax_count;
    }

    public void setRelax_count(int relax_count) {
        this.relax_count = relax_count;
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
