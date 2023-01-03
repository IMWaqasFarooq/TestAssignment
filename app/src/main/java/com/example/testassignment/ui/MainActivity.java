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

import com.example.testassignment.R;
import com.example.testassignment.ui.base.BaseActivity;
import com.example.testassignment.ui.home.model.RateModel;
import com.example.testassignment.ui.home.presenter.HomePresenter;
import com.example.testassignment.ui.home.view.IHomeView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IHomeView {

    @BindView( R.id.edit_txt_usd)
    EditText editTextUsd;

    @BindView( R.id.txt_other)
    TextView textOther;

    @BindView( R.id.currency_spinner)
    Spinner currencySpinner;

    HomePresenter presenter;

    Map<String, Double> rates;
    String selectedCurrency;
    List<String> currencyList;
    double amount, currencyRate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter= new HomePresenter(this);
        showLoader();
        presenter.getRateList();



        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selectedCurrency = currencyList.get(position);
                currencyRate = rates.get(selectedCurrency);
                double totalAMount = amount*currencyRate;
                textOther.setText(String.valueOf(totalAMount));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        editTextUsd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>0){
                    amount = Double.parseDouble(charSequence.toString());
                    double totalAMount = amount*currencyRate;
                    textOther.setText(String.valueOf(totalAMount));
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    @Override
    public void onResultRates(String message ,  RateModel rateModel) {

        hideLoader();


        if (message.equalsIgnoreCase("success")) {

            rates = rateModel.rates;

            currencyList = new ArrayList<>(rates.keySet());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currencyList);
            currencySpinner.setAdapter(adapter);


        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

}