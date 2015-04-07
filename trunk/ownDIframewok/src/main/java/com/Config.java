package com;

import com.application.mongo.dao.MongoDao;
import com.application.mongo.dao.MongoDaoImpl;
import com.application.mongo.service.MongoService;
import com.application.mongo.service.MongoServiceImpl2;
import com.application.mysql.service.MySqlService;
import com.application.mysql.service.MySqlServiceImpl;

import static org.di.framework.core.Mapper.createMapping;

public class Config {

    public void configure() {
        createMapping(MongoDao.class, MongoDaoImpl.class);
        createMapping(MongoService.class, MongoServiceImpl2.class);
        createMapping(MySqlService.class, MySqlServiceImpl.class);
    }
}
