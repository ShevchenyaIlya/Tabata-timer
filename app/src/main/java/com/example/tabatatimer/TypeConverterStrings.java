package com.example.tabatatimer;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TypeConverterStrings {

    @TypeConverter
    public static ArrayList<String> restoreList(String listOfString) {
        return new Gson().fromJson(listOfString, new TypeToken<ArrayList<String>>() {}.getType());
    }

    @TypeConverter
    public static String saveList(ArrayList<String> listOfString) {
        return new Gson().toJson(listOfString);
    }
}

