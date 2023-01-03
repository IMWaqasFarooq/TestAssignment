package com.example.testassignment.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testassignment.TestAssignmentApplication;
import com.example.testassignment.utils.constants.KeysConstants;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    public final TestAssignmentApplication application;

    public AppModule(TestAssignmentApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesApplicationContext(){
        return application;
    }


    @Provides
    @Singleton
    SharedPreferences providesSharedPreference(Context app){
        return app.getSharedPreferences(KeysConstants.key_PREF_NAME , Context.MODE_PRIVATE);
    }

//    @Provides
//    @Singleton
//    SharedPreferences providesSharedPreferencesApp(Application application) {
//        return PreferenceManager.getDefaultSharedPreferences(application);
//    }
}




