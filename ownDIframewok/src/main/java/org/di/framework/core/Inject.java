package org.di.framework.core;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ CONSTRUCTOR })
@Retention(RUNTIME)
public @interface Inject {}
