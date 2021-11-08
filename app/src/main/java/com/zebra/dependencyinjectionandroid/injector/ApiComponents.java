package com.zebra.dependencyinjectionandroid.injector;

import com.zebra.dependencyinjectionandroid.activities.MainActivity;
import com.zebra.dependencyinjectionandroid.modules.ApiClientModule;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {ApiClientModule.class})
public interface ApiComponents {
    void injectMain(MainActivity mainActivity);
}
