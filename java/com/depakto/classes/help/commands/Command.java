package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.wrapper.QueryError;
import com.depakto.classes.help.api.wrapper.Wrapper;
import com.depakto.classes.help.commands.parameter.Parameter;
import com.depakto.classes.help.commands.response.DefaultArrayResponse;
import com.depakto.tsmobile.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Command {
    private boolean answered = false;
    private String command;
    private QueryError error;
    private ArrayList<Parameter> params = new ArrayList();
    private String raw;
    private DefaultArrayResponse response;
    private boolean sent = false;

    public Command(String command) {
        this.command = command;
    }

    protected void add(Parameter p) {
        this.params.add(p);
    }

    public void feed(String str) {
        this.raw = str;
        if (this.response == null) {
            this.response = new DefaultArrayResponse(str);
        }
    }

    public void feedError(String err) {
        if (this.error == null) {
            this.error = new QueryError((HashMap) new DefaultArrayResponse(err).getArray().get(0));
        }
    }

    public QueryError getError() {
        return this.error;
    }

    public String getName() {
        return this.command;
    }

    public Wrapper getFirstResponse() {
        List<HashMap<String, String>> resp = getResponse();
        if (resp.size() > 0) {
            return new Wrapper((HashMap) resp.get(0));
        }
        return new Wrapper(new HashMap());
    }

    public List<HashMap<String, String>> getResponse() {
        if (this.response == null) {
            return new DefaultArrayResponse(BuildConfig.FLAVOR).getArray();
        }
        return this.response.getArray();
    }

    public boolean isAnswered() {
        return this.answered;
    }

    public boolean isSent() {
        return this.sent;
    }

    public void setAnswered() {
        this.answered = true;
    }

    public void setSent() {
        this.sent = true;
    }

    public String toString() {
        String str = this.command;
        Iterator it = this.params.iterator();
        while (it.hasNext()) {
            str = str + " " + ((Parameter) it.next());
        }
        return str;
    }

    public String getRaw() {
        return this.raw;
    }
}
