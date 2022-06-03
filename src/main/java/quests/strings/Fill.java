package quests.strings;

/**
 * @author Vyacheslav Doroshenko
 */
public class Fill {


    public static String fillWithZeros(int num) {
        if (num < 99999) {
            StringBuilder numBuilder = new StringBuilder();
            int numLength = String.valueOf(num).length();
            for (int i = numLength; i < 6; i++) {
                numBuilder.append(0);
            }
            return numBuilder.append(num).toString();
        }
        return  String.valueOf(num);
    }

    public static String fillWithZerosNew(int num) {
        if (num < 99999) {
            StringBuilder numBuilder = new StringBuilder();
            int numLength = String.valueOf(num).length();
            numBuilder.append("0".repeat(Math.max(0, 6 - numLength)));
            return numBuilder.append(num).toString();
        }
        return String.valueOf(num);
    }
}

