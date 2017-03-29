package com.depakto.classes.help;

public class StringUtil {
    public static String encode(String str) {
        return str.replace("\\", "\\\\").replace(" ", "\\s").replace("/", "\\/").replace("|", "\\p").replace("\b", "\\b").replace("\f", "\\f").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t").replace(new Character('\u0007').toString(), "\\a").replace(new Character('\u000b').toString(), "\\v");
    }

    public static String decode(String str) {
        return str.replace("\\\\", "\\[$mksave]").replace("\\s", " ").replace("\\/", "/").replace("\\p", "|").replace("\\b", "\b").replace("\\f", "\f").replace("\\n", "\n").replace("\\r", "\r").replace("\\t", "\t").replace("\\a", new Character('\u0007').toString()).replace("\\v", new Character('\u000b').toString()).replace("\\[$mksave]", "\\");
    }

    public static boolean getBoolean(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        if (getInt(str) != 1) {
            return false;
        }
        return true;
    }

    public static double getDouble(String str) {
        if (str == null || str.isEmpty()) {
            return -1.0d;
        }
        return Double.parseDouble(str);
    }

    public static long getLong(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        return Long.parseLong(str);
    }

    public static int getInt(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        return Integer.parseInt(str);
    }
}
