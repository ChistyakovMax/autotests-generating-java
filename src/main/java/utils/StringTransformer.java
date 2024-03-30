package utils;

import java.util.Collection;

public class StringTransformer {
    public static String transformToString(Collection<String> string, int enterCnt, int tabCnt) {
        StringBuilder sb = new StringBuilder();
        for (String str : string) {
            sb.append(str.trim());
            for (int i = 0; i != enterCnt; i++) {
                sb.append("\n");
            }
            for (int i = 0; i <= tabCnt; i++) {
                sb.append("\t");
            }
        }
        return sb.toString().trim();
    }
}
