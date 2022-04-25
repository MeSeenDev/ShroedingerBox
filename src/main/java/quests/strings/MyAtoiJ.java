package quests.strings;

public class MyAtoiJ {

    public static Integer myAtoi(String text) {
        try {
            return Integer.parseInt(text.replaceAll("[^[-+]?[0-9]+[0-9]*]", ""));
        } catch (Throwable throwable) {
            return 0;
        }
    }

}
