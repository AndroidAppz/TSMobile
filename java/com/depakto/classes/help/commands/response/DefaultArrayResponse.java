package com.depakto.classes.help.commands.response;

import com.depakto.classes.help.StringUtil;
import com.depakto.tsmobile.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class DefaultArrayResponse extends HashMap<String, String> {
    private final List<HashMap<String, String>> array = new ArrayList();

    public DefaultArrayResponse(String raw) {
        StringTokenizer tkn = new StringTokenizer(raw, "|", false);
        while (tkn.hasMoreTokens()) {
            this.array.add(parse(tkn.nextToken()));
        }
    }

    private HashMap<String, String> parse(String raw) {
        StringTokenizer st = new StringTokenizer(raw, " ", false);
        HashMap<String, String> options = new HashMap();
        while (st.hasMoreTokens()) {
            String tmp = st.nextToken();
            int pos = tmp.indexOf("=");
            if (pos == -1) {
                options.put(tmp, BuildConfig.FLAVOR);
            } else {
                options.put(StringUtil.decode(tmp.substring(0, pos)), StringUtil.decode(tmp.substring(pos + 1)));
            }
        }
        return options;
    }

    public List<HashMap<String, String>> getArray() {
        return this.array;
    }

    public String toString() {
        String str = BuildConfig.FLAVOR;
        for (HashMap<String, String> opt : this.array) {
            str = str + opt + " | ";
        }
        return str;
    }
}
