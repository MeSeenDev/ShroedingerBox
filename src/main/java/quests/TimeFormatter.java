package quests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeFormatter {
    private static int seconds = 0;

    public static String formatDuration(int sec) {
        seconds = sec;
        if (sec == 0) return "now";
        add(seconds, Time.years);
        add(seconds, Time.days);
        add(seconds, Time.hours);
        add(seconds, Time.minutes);
        add(seconds, Time.seconds);
        // your code goes here
        return getTime();
    }

    private static String getTime() {
        StringBuilder builder = new StringBuilder();
        if (haveTime(Time.years)) {
            builder.append(Time.years.getQuantity()).append(" ").append(Time.years.getName());
        }
        if (haveTime(Time.days)) {
            if (haveTime(Time.years)) {
                if (haveTime(Time.hours) || haveTime(Time.minutes) || haveTime(Time.seconds)) {
                    builder.append(", ");
                } else {
                    builder.append(" and ");
                }
            }
            builder.append(Time.days.getQuantity()).append(" ").append(Time.days.getName());
        }
        if (haveTime(Time.hours)) {
            if (haveTime(Time.days) || haveTime(Time.years)) {
                if (haveTime(Time.minutes) || haveTime(Time.seconds)) {
                    builder.append(", ");
                } else {
                    builder.append(" and ");
                }
            }
            builder.append(Time.hours.getQuantity()).append(" ").append(Time.hours.getName());
        }
        if (haveTime(Time.minutes)) {
            if (haveTime(Time.hours) || haveTime(Time.days) || haveTime(Time.years)) {
                if (haveTime(Time.seconds)) {
                    builder.append(", ");
                } else {
                    builder.append(" and ");
                }
            }
            builder.append(Time.minutes.getQuantity()).append(" ").append(Time.minutes.getName());
        }
        if (haveTime(Time.seconds)) {
            if (haveTime(Time.minutes) || haveTime(Time.hours) || haveTime(Time.days) || haveTime(Time.years))
                builder.append(" and ");
            builder.append(Time.seconds.getQuantity()).append(" ").append(Time.seconds.getName());
        }
        return builder.toString();
    }

    private static boolean haveTime(Time time) {
        return time.getQuantity() > 0;

    }

    private static void add(int sec, Time enu) {
        if (sec / enu.getSec() > 0) {
            enu.setQuantity(sec / enu.getSec());
            seconds -= (enu.getSec() * enu.getQuantity());
        } else {
            enu.setQuantity(0);
        }
    }

    private enum Time {

        years(31536000, "year"), days(86400, "day"), hours(3600, "hour"), minutes(60, "minute"), seconds(1, "second");
        private final int sec;
        private int quantity;
        private String name;

        Time(int sec, String year) {
            this.sec = sec;
            this.name = year;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            if (quantity == 1) {
                if (name.charAt(name.length() - 1) == 's') {
                    name = name.substring(0, name.length() - 1);
                }

            }
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
            if (quantity > 1) {
                if (name.charAt(name.length() - 1) != 's')
                    name = name + "s";
            }
        }

        public int getSec() {
            return sec;
        }
    }


}


class Scramblies {

    public static boolean scramble(String str1, String str2) {
        List<String> word1 = new ArrayList<>(Arrays.asList(str1.split("")));
        for (String s : str2.split("")) {
            if (word1.contains(s)) {
                word1.remove(s);
            } else {
                return false;
            }
        }
        return true;
    }
}
