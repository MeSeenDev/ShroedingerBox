package mainJava;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

public class Main {


    public static void main(String[] args) throws IOException {




    }

    public static byte[] revertArray(byte[] array) {
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = array[array.length - 1 - i];

        return result;
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


    static class Reco {
        private final String reco;

        public Reco(String reco) {
            this.reco = reco;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Reco reco1 = (Reco) o;
            return Objects.equals(reco, reco1.reco);
        }

        @Override
        public int hashCode() {
            return Objects.hash(reco);
        }
    }


    public static int getAtqaInt() {
        return (atqa[0] & 0xFF) | ((atqa[1] & 0xFF) << 8);
    }

    public static byte[] atqa = new byte[]{4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

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



