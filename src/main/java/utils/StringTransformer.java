package utils;

import java.util.Collection;

public class StringTransformer {
    public static String transformToStringWithOneEnter(Collection<String> string) {
        StringBuilder sb = new StringBuilder();
        for (String str : string) {
            if(!str.isEmpty()) {
                sb.append(str).append("\n");
            }
        }
        return sb.toString().trim();
    }
    public static String transformToStringWithTwoEnters(Collection<String> string) {
        StringBuilder sb = new StringBuilder();
        for (String str : string) {
            if(!str.isEmpty()) {
                sb.append(str).append("\n\n");
            }
        }
        return sb.toString();
    }

    public static String transformToStringWithTabulation(Collection<String> string) {
        StringBuilder sb = new StringBuilder();
        for (String str : string) {
            sb.append(str).append("\n\t\t");
        }
        return sb.toString().trim();
    }
}
