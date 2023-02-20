package com.example.businessmanagement;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> text = new MutableLiveData<>();

    public void setText(String input) {
        text.setValue(input);
    }

    public void setText1(String input) {
        text.setValue(input);
    }

    public MutableLiveData<String> getText() {
        return text;
    }
    public MutableLiveData<String> getText1() {
        return text;
    }

}