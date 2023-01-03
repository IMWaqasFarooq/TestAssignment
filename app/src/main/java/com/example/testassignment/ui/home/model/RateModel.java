package com.example.testassignment.ui.home.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RateModel implements Serializable{

    @SerializedName("result")
    public String result;

    @SerializedName("rates")
    public Map<String, Double> rates;

//    public static class Rates implements Serializable {
//
////        @SerializedName("USD")
////        public double usd;
////
////        @SerializedName("AED")
////        public double aed;
////
////        @SerializedName("ARS")
////        public double ars;
////
////        @SerializedName("AUD")
////        public double aud;
////
////        @SerializedName("CAD")
////        public double cad;
////
////        @SerializedName("CHF")
////        public double chf;
////
////        @SerializedName("EUR")
////        public double eur;
////
////        @SerializedName("GBP")
////        public double gbp;
////
////        @SerializedName("HKD")
////        public double hkd;
//
//
//    }

}
