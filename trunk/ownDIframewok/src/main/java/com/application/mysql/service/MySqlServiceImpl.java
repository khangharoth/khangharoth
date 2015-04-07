package com.application.mysql.service;

public class MySqlServiceImpl implements MySqlService {
    @Override
    public void save() {
        System.out.println("Saving by using "+this.getClass().getSimpleName());
    }
}
