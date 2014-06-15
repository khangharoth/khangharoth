package com.adhoc;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.AbstractInvocable;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.List;

/*
   Taken from : http://thegridman.com/uncategorized/groovy-oracle-coherence-yeah-baby/
 */

public class GroovyScriptInvocable extends AbstractInvocable implements PortableObject {
    private String script;

    public GroovyScriptInvocable() {
    }

    public GroovyScriptInvocable(String script) {
        this.script = script;
    }

    @Override
    public void run() {
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            List<ScriptEngineFactory> factories = factory.getEngineFactories();
            ScriptEngine engine = factory.getEngineByName("groovy");
            engine.put("invocationService", getService());
            Object scriptResult = engine.eval(script);
            setResult(scriptResult);
        } catch (ScriptException e) {
            throw ensureRuntimeException(e, "Error running Groovy script\n" + script);
        }
    }

    @Override
    public void readExternal(PofReader pofReader) throws IOException {
        this.script = pofReader.readString(0);
    }

    @Override
    public void writeExternal(PofWriter pofWriter) throws IOException {
        pofWriter.writeString(0, this.script);
    }
}
