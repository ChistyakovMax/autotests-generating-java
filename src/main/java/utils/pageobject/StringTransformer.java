package utils.pageobject;

import java.util.Set;

public class StringTransformer {
    public static String transformToString(Set<String> setOfString) {
        StringBuilder sb = new StringBuilder();
        for (String str : setOfString) {
            sb.append(str).append("\n\n");
        }
        return sb.toString();
    }
}
