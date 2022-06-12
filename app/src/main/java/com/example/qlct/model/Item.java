package com.example.qlct.model;

import android.text.Editable;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

//public class Item extends RealmObject implements Serializable {
public class Item extends RealmObject implements Serializable {

    private int id;
    private int type; //type=1: Item; type=2: Expensis;
    private String name;
    private String topic;
    private String time;
    private String note;
    private long amount;
    private String url;
    private boolean isChecked;
    private int month;
    private int year;

    public Item() {
    }

    public Item(int id, int type, String name, String topic, String time, String note, long amount, String url, int month, int year) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.topic = topic;
        this.time = time;
        this.note = note;
        this.amount = amount;
        this.url = url;
        this.isChecked = false;
        this.month = month;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                ", time='" + time + '\'' +
                ", note='" + note + '\'' +
                ", amount=" + amount +
                ", url='" + url + '\'' +
                ", isChecked=" + isChecked +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
