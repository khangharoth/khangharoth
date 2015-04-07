package org.di.framework.core;

import java.util.HashMap;
import java.util.Map;

public  class Mapper {
    private static Map<Class<?>, Class<?>> classMap = new HashMap<Class<?>, Class<?>>();

    public static <T> void  createMapping(Class<T> baseClass, Class<? extends T> subClass) {
        classMap.put(baseClass, subClass.asSubclass(baseClass));
    }


    public static <T> Class<? extends T> getMapping(Class<T> type) {
        Class<?> implementation = classMap.get(type);

        if(implementation == null)
            throw new IllegalArgumentException("Couldn't find the mapping for : " + type);

        return implementation.asSubclass(type);
    }


}
