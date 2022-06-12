package com.example.qlct.realm;

import android.os.AsyncTask;

import com.example.qlct.model.Item;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class RealmController {

    public Realm realm;

    public RealmController() {
        if (realm == null)
            realm = Realm.getDefaultInstance();

//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
//
//        realm = Realm.getInstance(config);

    }

    public void addItem(Item item) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Item realmItem = realm.createObject(Item.class);
        realmItem.setId(item.getId());
        realmItem.setType(item.getType());
        realmItem.setName(item.getName());
        realmItem.setTopic(item.getTopic());
        realmItem.setTime(item.getTime());
        realmItem.setNote(item.getNote());
        realmItem.setAmount(item.getAmount());
        realmItem.setUrl(item.getUrl());
        realmItem.setChecked(item.isChecked());
        realmItem.setMonth(item.getMonth());
        realmItem.setYear(item.getYear());
        realm.commitTransaction();
    }

    public void deleteItem(Item item) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Item> results = realm.where(Item.class).equalTo("id", item.getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public void updateItem(Item item) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Item realmItem = realm.where(Item.class).equalTo("id", item.getId()).findFirst();
        realmItem.setId(item.getId());
        realmItem.setType(item.getType());
        realmItem.setName(item.getName());
        realmItem.setTopic(item.getTopic());
        realmItem.setTime(item.getTime());
        realmItem.setNote(item.getNote());
        realmItem.setAmount(item.getAmount());
        realmItem.setUrl(item.getUrl());
        realmItem.setChecked(item.isChecked());
        realmItem.setMonth(item.getMonth());
        realmItem.setYear(item.getYear());
        realm.commitTransaction();
    }

    public RealmResults<Item> getItem(int type) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Item> results = realm.where(Item.class).equalTo("type", type).findAll();
        realm.commitTransaction();
        return results;
    }

    public RealmResults<Item> getItemMonth(int type, int month) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Item> results = realm.where(Item.class).equalTo("type", type).equalTo("month", month).findAll();
        realm.commitTransaction();
        return results;
    }

    public RealmResults<Item> getItemYear(int type, int year) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Item> results = realm.where(Item.class).equalTo("type", type).equalTo("year", year).findAll();
        realm.commitTransaction();
        return results;
    }

    public RealmResults<Item> getItemYearMonth(int type, int year, int month) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Item> results = realm.where(Item.class).equalTo("type", type).equalTo("year", year).equalTo("month", month).findAll();
        realm.commitTransaction();
        return results;
    }

    public Item getItem(String id) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Item item = realm.where(Item.class).equalTo("id", id).findFirst();
        realm.commitTransaction();
        return item;
    }


}
