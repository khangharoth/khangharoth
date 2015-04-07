package com;


import com.application.mongo.service.MongoService;
import com.application.mysql.service.MySqlService;
import org.di.framework.core.Inject;

public class TheService {
    private MongoService mongoService;
    private MySqlService mySqlService;

    @Inject
    public TheService(MongoService mongoService, MySqlService mySqlService) {
        this.mongoService = mongoService;
        this.mySqlService = mySqlService;
    }

    public void save() {
        mongoService.save();
        mySqlService.save();
    }
}
