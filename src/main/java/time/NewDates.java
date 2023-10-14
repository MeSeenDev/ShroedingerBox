package time;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class NewDates {

    // enter your code
    public static SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.UK);
    public static SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-'M'M-'W'W-E':'HH:mm", Locale.UK);

    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-'M'M-'W'W-E:HH:mm") //2023-M10-W2-Sat:19:57
            .withLocale(Locale.UK)
            .withZone(ZoneId.systemDefault());// для Instant важно !


    public static LocalDateTime toMoscowTime(LocalDateTime localDateTime, String zoneName) {
        ZoneId zoneId = ZoneId.of(zoneName);
        ZoneId zoneMow = ZoneId.of("Europe/Moscow");

        ZonedDateTime localZonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

        ZonedDateTime mowZonedDateTime = localZonedDateTime.withZoneSameInstant(zoneMow);

        ZoneOffset mowOffset = mowZonedDateTime.getOffset();

        return Instant.from(mowZonedDateTime)
                .atOffset(mowOffset)
                .toLocalDateTime();
    }

    public static LocalDateTime toMoscowTimeCut(LocalDateTime localDateTime, String zoneName) {
        return localDateTime.
                atZone(ZoneId.of(zoneName))
                .withZoneSameInstant(ZoneId.of("Europe/Moscow"))
                .toLocalDateTime();

    }

}
