package com.application.mongo.service;

public class MongoServiceImpl1 implements MongoService {

    @Override
    public void save() {
        System.out.println("Saving by using "+this.getClass().getSimpleName());
    }
}
