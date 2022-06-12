package com.example.qlct.app;

import android.app.Application;

import io.realm.Realm;

public class RealmApplication extends Application {

    private static RealmApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);
    }

    public static RealmApplication getInstance() {
        return instance;
    }


}
