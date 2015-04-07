package com.application.mongo.service;

import com.application.mongo.dao.MongoDao;
import org.di.framework.core.Inject;

public class MongoServiceImpl2 implements MongoService {

    private MongoDao dao;

    @Inject
    public MongoServiceImpl2(MongoDao dao) {
        this.dao = dao;
    }

    @Override
    public void save() {
        dao.save();
    }
}
