package quests;

import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class ScrambliesPigLatin {

    public static String pigIt(String str) {
        String[] arr = str.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String in : arr) {
            if (in.replaceAll("\\w", "").length() > 0) {
                builder.append(in);
            } else {
                builder.append(in.substring(1)).append(in.substring(0, 1)).append("ay").append(" ");
            }
        }
        return builder.toString().trim();
    }

    public static String pigIt1(String str) {
        return stream(str.trim().split(" "))
                .map(v -> v.matches("[a-zA-Z]+") ? v.substring(1).concat(v.substring(0, 1)).concat("ay") : v)
                .collect(Collectors.joining(" "));
    }
}
