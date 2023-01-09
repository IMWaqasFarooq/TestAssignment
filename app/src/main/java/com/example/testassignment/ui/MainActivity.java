package com.example.testassignment.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.testassignment.R;
import com.example.testassignment.databinding.ActivityMainBinding;
import com.example.testassignment.ui.base.BaseActivity;
import com.example.testassignment.ui.home.model.RateModel;

import com.example.testassignment.ui.home.view_model.HomeViewModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    Map<String, Double> rates;
    String selectedCurrency;
    List<String> currencyList;
    double amount, currencyRate;

    HomeViewModel homeViewModel;
    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel =  new ViewModelProvider(this).get(HomeViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setLifecycleOwner(this);

        binding.setHomeViewModel(homeViewModel);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);

        showLoader();
//        presenter.getRateList();


        homeViewModel.getRates().observe(this, rateModel -> {
            if(rateModel != null){
                hideLoader();
                rates = rateModel.rates;

                currencyList = new ArrayList<>(rates.keySet());

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currencyList);
                binding.currencySpinner.setAdapter(adapter);
            }else{
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
            }

        });

//        homeViewModel.getTextUsd().observe(this, textUsd -> {
//
//            homeViewModel.textOthers.setValue(textUsd);
//
//        });




        binding.currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selectedCurrency = currencyList.get(position);
                currencyRate = rates.get(selectedCurrency);
                double totalAMount = amount*currencyRate;
                homeViewModel.textOthers.setValue(String.valueOf(totalAMount));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

         binding.editTxtUsd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>0){
                    amount = Double.parseDouble(charSequence.toString());
                    double totalAMount = amount*currencyRate;
                    homeViewModel.textOthers.setValue(String.valueOf(totalAMount));
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

}