package com;

import org.di.framework.core.DIContext;
import org.di.framework.core.DIContextFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        DIContext DIContext = DIContextFactory.context();

        TheService theService = (TheService) DIContext.getBean(TheService.class);

        theService.save();
    }
}
