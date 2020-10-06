package com.example.tabatatimer;

import android.webkit.WebView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.security.auth.callback.Callback;

public class SavedStateViewModel extends ViewModel {
    TimerHandler mState;
    private int stage;


    public void saveState(TimerHandler savedStateHandle) {
        mState = savedStateHandle;
    }

    public TimerHandler getSate() {
        return mState;
    }

    public int getStage() {
        return stage;
    }
    public void setStage(int stage) {
        this.stage = stage;
    }
}

