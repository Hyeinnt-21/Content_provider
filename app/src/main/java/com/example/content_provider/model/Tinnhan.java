package com.example.content_provider.model;

import java.io.Serializable;

public class Tinnhan implements Serializable {
    private String number, time, body;

    public Tinnhan(String number, String time, String body) {
        this.number = number;
        this.time = time;
        this.body = body;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
