package org.tiangonglca.backend.util;

public class DataVersionUtil {
    public static String newVersion() {
        return "00.00.000";
    }

    public static Integer toNumber(String str) {
        try {
            return Integer.parseInt(str.replace(".", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public static String toString(Integer num) {
        return new StringBuffer(String.format("%07d", num)).insert(4, ".").insert(2, ".").toString();
    }

    public static String updateVersion(String version) {
        if (version != null) {
            return toString(toNumber(version) + 1);
        }
        return updateVersion(newVersion());
    }
}
