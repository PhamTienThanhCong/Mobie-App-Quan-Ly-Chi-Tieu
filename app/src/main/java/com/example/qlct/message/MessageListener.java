package com.example.qlct.message;

import com.example.qlct.model.Mess;

public interface MessageListener {

//    void messageReceived(Mess message);
    void messageReceived(String sender, String body);
}
