package com.application.mongo.dao;

public class MongoDaoImpl implements MongoDao {


    @Override
    public void save() {
        System.out.println("Saving by using "+this.getClass().getSimpleName());
    }
}
