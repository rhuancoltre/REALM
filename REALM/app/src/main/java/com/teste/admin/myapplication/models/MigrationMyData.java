package com.teste.admin.myapplication.models;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by Admin on 06/06/2017.
 */

public class MigrationMyData implements RealmMigration {

    public static final Long VERSION = 1L;

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            schema.get("")
                    .addField("strings", String.class);
            oldVersion++;
        }
    }
}
