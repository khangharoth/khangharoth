package org.di.framework.core;

import java.lang.reflect.Constructor;

import static org.di.framework.core.Mapper.getMapping;

public class DIContext {

    public DIContext() {
    }

    @SuppressWarnings("unchecked")
    public Object getBean(Class aClass) throws Exception {
        if(aClass != null) {
            return getDependencyResolvedObject(aClass);
        }
        return null;
    }

    private Object getDependencyResolvedObject(Class aClass) throws Exception {
        for(Constructor constructor: aClass.getConstructors()) {
                    Class[] parameterTypes = constructor.getParameterTypes();
                    Object[] objArr = new Object[parameterTypes.length];
                    int i = 0;
                    for(Class c : parameterTypes) {
                        Class dependency = getMapping(c);
                        if(c.isAssignableFrom(dependency)) {
                            Object newInstance = getInstance(constructor, dependency);
                            objArr[i++] = newInstance;
                        }
                    }
                   return aClass.getConstructor(parameterTypes).newInstance(objArr);
        }
        return null;
    }

    private Object getInstance(Constructor constructor, Class dependency) throws Exception {
        if(constructor.isAnnotationPresent(Inject.class)) {
             return getDependencyResolvedObject(dependency);
        } else{
             return dependency.getConstructor().newInstance();
        }
    }
}
