package org.di.framework.core;

import com.Config;

public class DIContextFactory {
    public static DIContext context() {
        Config module=new Config();
        module.configure();
        return new DIContext();
    }
}
