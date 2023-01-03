package com.example.testassignment.di.component;


import com.example.testassignment.TestAssignmentApplication;
import com.example.testassignment.di.module.AppModule;
import com.example.testassignment.ui.base.BaseActivity;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject (TestAssignmentApplication application);

    void inject (BaseActivity baseActivity);


}
