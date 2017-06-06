package com.teste.admin.myapplication.models;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Admin on 06/06/2017.
 */

public class Carro extends RealmObject {

    @PrimaryKey()
    private Long id;
    private String name;
    private String color;
    private String strings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStrings() {
        return strings;
    }

    public void setStrings(String strings) {
        this.strings = strings;
    }

    public static Long autoIncrementId(){
        Long key = 1L;
        Realm realm = Realm.getDefaultInstance();
        try {
            key = realm.where(Carro.class).max("id").longValue() + 1;
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return key;
    }

}
