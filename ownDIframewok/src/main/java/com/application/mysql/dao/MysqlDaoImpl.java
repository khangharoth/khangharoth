package com.application.mysql.dao;

public class MysqlDaoImpl implements MysqlDao {
    @Override
    public void save() {
        System.out.println("Saving by using "+this.getClass().getSimpleName());
    }
}
