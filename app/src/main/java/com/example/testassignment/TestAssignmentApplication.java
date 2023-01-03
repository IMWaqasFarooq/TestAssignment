package com.example.testassignment;

import android.app.Application;
import android.content.Context;

import com.example.testassignment.di.component.AppComponent;
import com.example.testassignment.di.component.DaggerAppComponent;
import com.example.testassignment.di.module.AppModule;

public class TestAssignmentApplication  extends Application {
    AppComponent appComponent;
    private static Application sApplication;


    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
        sApplication = this;


    }



    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
