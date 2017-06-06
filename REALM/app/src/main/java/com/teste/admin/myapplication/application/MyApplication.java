package com.teste.admin.myapplication.application;

import android.app.Application;

import com.teste.admin.myapplication.models.MigrationMyData;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Admin on 06/06/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .schemaVersion(MigrationMyData.VERSION)
                .migration(new MigrationMyData())
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
