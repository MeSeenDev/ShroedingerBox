package mainJava;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Main {



    public static void main(String[] args) throws IOException {



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


