package com.example.testassignment.ui.home.view_model;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testassignment.ui.home.model.RateModel;
import com.example.testassignment.ui.home.repository.HomeRepository;

public class HomeViewModel extends ViewModel {

    private HomeRepository homeRepository;

    private LiveData<RateModel> rateModelLiveData;

    public MutableLiveData<String> textUsd = new MutableLiveData<>();
    public MutableLiveData<String> textOthers = new MutableLiveData<>();




    public HomeViewModel(){
        homeRepository = new HomeRepository();
        rateModelLiveData = homeRepository.getCurrencyRates();
    }

    public LiveData<RateModel> getRates(){
        return rateModelLiveData;
    }

    public LiveData<String> getTextUsd(){
        return textUsd;
    }

    public LiveData<String> getTextOthers(){
        return textOthers;
    }

//    public void onClick(View view) {
//
//        LoginUser loginUser = new LoginUser(EmailAddress.getValue(), Password.getValue());
//
//        userMutableLiveData.setValue(loginUser);
//
//    }


}
