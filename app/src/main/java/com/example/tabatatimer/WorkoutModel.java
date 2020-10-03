package com.example.tabatatimer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Entity(tableName = "workouts")
public class WorkoutModel implements Serializable {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "workout_name")
    private String workoutName;

    @ColumnInfo(name = "preparation_time")
    private int preparationTime;

    @ColumnInfo(name = "stretch_time")
    private int stretchTime;

    @ColumnInfo(name = "work_time")
    private int workTime;

    @ColumnInfo(name = "relax_time")
    private int relaxTime;

    @ColumnInfo(name = "cycles_number")
    private int cyclesNumber;

    @ColumnInfo(name = "sets_number")
    private int setsNumber;

    @ColumnInfo(name = "relax_after_set_time")
    private int relaxAfterSetTime;

    @ColumnInfo(name = "workout_order")
    @TypeConverters(TypeConverterStrings.class)
    private ArrayList<String> workoutOrder;

    @ColumnInfo(name = "workout_order_time")
    @TypeConverters(TypeConverterInteger.class)
    private ArrayList<Integer> workoutOrderTime;

    private String description;

    @ColumnInfo(name = "color")
    private int color;

    // If id is equal to 0, then this object doesn't saved in database
    public WorkoutModel createWorkoutModel(int id, String workout_name, int preparation_time, int stretch_time, int work_time, int relax_time, int cycles_number, int sets_number, int relax_after_set_time, String workout_order_value, String workout_order_time_value, int color) {
        this.id = id;
        this.workoutName = workout_name;
        this.preparationTime = preparation_time;
        this.stretchTime = stretch_time;
        this.workTime = work_time;
        this.relaxTime = relax_time;
        this.cyclesNumber = cycles_number;
        this.setsNumber = sets_number;
        this.relaxAfterSetTime = relax_after_set_time;
        this.color = color;

        if (workout_order_time_value.equals("") && workout_order_value.equals("")) {
            createWorkingOrder();
        }
        else {
            workoutOrder = new ArrayList<String>(Arrays.asList(Converter.convertStringToArray(workout_order_value)));
            workoutOrderTime = new ArrayList<Integer>(Arrays.asList(Converter.convertStringToIntegerArray(workout_order_time_value)));
        }

        this.description = createDescription();
        return this;
    }

    private void createWorkingOrder() {
        workoutOrder = new ArrayList<String>();
        workoutOrderTime = new ArrayList<Integer>();
        workoutOrder.add("Preparation");
        workoutOrderTime.add(preparationTime);
        workoutOrder.add("Stretch");
        workoutOrderTime.add(stretchTime);
        for (int i = 0; i < setsNumber; i++) {
            for (int j = 0; j < cyclesNumber; j++) {
                workoutOrder.add("Work");
                workoutOrderTime.add(workTime);
                workoutOrder.add("Rest");
                workoutOrderTime.add(relaxTime);

            }
            workoutOrder.add("SetsRest");
            workoutOrderTime.add(relaxAfterSetTime);
        }
    }

    public void updateWorkingOrder() {
        createWorkingOrder();
    }


//    public boolean saveWorkout(DatabaseHelper dbHelper) {
//        return dbHelper.insertData(this.workoutName, this.preparation_time, this.stretch_time,
//                this.work_time, this.relax_time, this.cycles_number, this.sets_number, this.relax_after_set_time, Converter.convertArrayToString(workout_order.toArray(new String[0])), Converter.convertIntArrayToString(workout_order_time.toArray(new Integer[0])), color);
//    }
//
//    public int deleteWorkout(DatabaseHelper dbHelper) {
//
//        return dbHelper.deleteData(String.valueOf(this.id));
//    }
//
//    public void updateWorkout(DatabaseHelper dbHelper) {
//        dbHelper.updateData(this.id, this.workout_name, this.preparation_time, this.stretch_time,
//                this.work_time, this.relax_time, this.cycles_number, this.sets_number, this.relax_after_set_time,
//                Converter.convertArrayToString(workout_order.toArray(new String[0])), Converter.convertIntArrayToString(workout_order_time.toArray(new Integer[0])), color);
//    }

    public void saveWorkout(WorkoutDatabase workoutDatabase) {
        workoutDatabase.workoutDao().insert(this);
    }

    public void deleteWorkout (WorkoutDatabase workoutDatabase) {
        workoutDatabase.workoutDao().delete(this);
    }

    public void updateWorkout(WorkoutDatabase workoutDatabase) {
        workoutDatabase.workoutDao().update(this);
    }

    private String createDescription() {
        int whole_time = 0;
        for (Integer time: workoutOrderTime) {
            whole_time += time;
        }
        if (MainActivity.language.equals("en")) {
            return "Whole training time: " + whole_time + "\nCycle number: " + cyclesNumber
                    + "\nSets number: " + setsNumber;
        }
        else if (MainActivity.language.equals("ru")) {
            return "Полное время тренировки: " + whole_time + "\nКоличество циклов: " + cyclesNumber
                    + "\nКоличество сетов: " + setsNumber;
        }
        return "";
    }

    public void updateDescription() {
        this.description = createDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getStretchTime() {
        return stretchTime;
    }

    public void setStretchTime(int stretchTime) {
        this.stretchTime = stretchTime;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public int getRelaxTime() {
        return relaxTime;
    }

    public void setRelaxTime(int relaxTime) {
        this.relaxTime = relaxTime;
    }

    public int getCyclesNumber() {
        return cyclesNumber;
    }

    public void setCyclesNumber(int cyclesNumber) {
        this.cyclesNumber = cyclesNumber;
    }

    public int getSetsNumber() {
        return setsNumber;
    }

    public void setSetsNumber(int setsNumber) {
        this.setsNumber = setsNumber;
    }

    public int getRelaxAfterSetTime() {
        return relaxAfterSetTime;
    }

    public void setRelaxAfterSetTime(int relaxAfterSetTime) {
        this.relaxAfterSetTime = relaxAfterSetTime;
    }

    public ArrayList<String> getWorkoutOrder() {
        return workoutOrder;
    }

    public void setWorkoutOrder(ArrayList<String> workoutOrder) {
        this.workoutOrder = workoutOrder;
    }

    public ArrayList<Integer> getWorkoutOrderTime() {
        return workoutOrderTime;
    }

    public void setWorkoutOrderTime(ArrayList<Integer> workoutOrderTime) {
        this.workoutOrderTime = workoutOrderTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
