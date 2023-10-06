package arrays;

public class ByteArray {


    public static byte[] revertArray(byte[] array) {
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++)
            result[i] = array[array.length - 1 - i];

        return result;
    }


}
