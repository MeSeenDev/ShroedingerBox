package mainJava;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.*;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws Exception {

        LocalDateTime localDateTime = LocalDateTime.parse("2002-01-01T23:55");

        ZoneId zoneId = ZoneId.of("Europe/Berlin");
        ZoneId zoneMow = ZoneId.of("Europe/Moscow");

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

        ZonedDateTime mowZone = zonedDateTime.withZoneSameInstant(zoneMow);

        ZoneOffset zoneOffset = mowZone.getOffset();

        System.out.println(mowZone);
        System.out.println(zoneOffset);
        System.out.println(Instant.from(mowZone).atOffset(zoneOffset).toLocalDateTime());
        // System.out.println(Dates.toMoscowTime(localDateTime,"Europe/Berlin"));


    }

    private static byte vatConverter(BigDecimal vatRate) throws Exception {
        return ((byte) Integer.parseInt(vatRate.toString()));
    }


    public static boolean isFileExtensionLike(File file, String extension) {
        return getFileExtension(file).equals("." + extension) || getFileExtension(file).equals(extension);
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public static String alignWidthText(String first, String second, int tapeWidth) {

        StringBuilder alignString = new StringBuilder();

        // Когда проблемы с открытием/закрытием смены, тут крэшится.
        int countSpace = tapeWidth; //  - (first.length() + second.length());
        if (first != null) countSpace -= first.length();
        if (second != null) countSpace += second.length();

        if (countSpace < 0) {
            return alignString.append(first).append(" ").append(second).substring(0, tapeWidth);
        }

        alignString.append(first);
        alignString.append(" ".repeat(countSpace));
        alignString.append(second);

        return alignString.toString();
    }

    public static String alignWidthText1(String first, String second, int tapeWidth) {
        if (first == null) first = "";
        if (second == null) second = "";

        StringBuilder alignString = new StringBuilder();

        int countSpace = tapeWidth - (first.length() + second.length());

        if (countSpace < 0) {
            return alignString.append(first).append(" ").append(second).substring(0, tapeWidth);
        }

        alignString.append(first);
        alignString.append(" ".repeat(countSpace));
        alignString.append(second);

        return alignString.toString();
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



