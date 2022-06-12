package com.example.qlct.model;

public class Mess {
    public String bank;
    public int type;
    public String amount;

    public Mess() {
    }

    public Mess(String bank, int type, String amount) {
        this.bank = bank;
        this.type = type;
        this.amount = amount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
