package mainJava;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {

    private static ExecutorService pub = Executors.newFixedThreadPool(2);
    private static ScheduledExecutorService susp = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) {


    }


    @Nullable
    protected <T extends Number> T getDigit(@NotNull final String parameter, @Nullable final T defaultValue) {
        T integerValue = defaultValue;
        final String value = "215.20";
        NumberFormat format = NumberFormat.getInstance(new Locale("RU-ru"));
        if (!value.isEmpty()) {
            try {
                Number number = format.parse(value);
                System.out.println(number.getClass());
            } catch (NumberFormatException | ParseException ex) {
                System.out.println(ex);
            }
        }
        return integerValue;
    }

}


