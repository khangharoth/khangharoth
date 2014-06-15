package com.adhoc;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

public class SimpleGroovy {

    public static void main(String[] args) throws ScriptException {
        String script = "(1..10).sum()";
        ScriptEngineManager factory = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = factory.getEngineFactories();

        ScriptEngine engine = factory.getEngineByName("groovy");

        Object scriptResult = engine.eval(script);

        System.out.println(factories);
    }
}
