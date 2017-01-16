package com.example.administrator.giftelves.enties;

/**
 *
 * Created by Administrator on 2017/1/11.
 */

public class User {
    private int id;
    private String name;
    private String pass;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", pass=" + pass + "]";
    }
    public User(int id, String name, String pass) {
        super();
        this.id = id;
        this.name = name;
        this.pass = pass;
    }
    public User() {
        super();
    }
}

