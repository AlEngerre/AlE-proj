package com.example.ale_proj;

import java.io.Serializable;

public class Rewards implements Serializable {
    private  long id;
    private String user;
    private int record;
    public Rewards(long id, String user, int record){
        this.id = id;
        this.user = user;
        this.record = record;
    }
    public long getId(){
        return id;
    }
    public String getUsers(){
        return user;
    }

    public int getRecord(){
        return record;
    }
}
