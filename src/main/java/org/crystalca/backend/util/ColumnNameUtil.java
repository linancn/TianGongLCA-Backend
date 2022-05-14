package org.crystalca.backend.util;

public class ColumnNameUtil {
    public static String toDatabaseName(String oldStr) {
        return toDatabaseName(oldStr, "_");
    }

    public static String toDatabaseName(String oldStr, String separator) {
        StringBuffer sb = new StringBuffer();
        char[] c = oldStr.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 97 && c[i] <= 122) {
                sb.append((c[i] + ""));
            } else if (c[i] >= 65 && c[i] <= 90) {
                sb.append(separator + (c[i] + "").toLowerCase());
            } else {
                sb.append((c[i] + ""));
            }
        }
        return sb.toString();
    }
}
