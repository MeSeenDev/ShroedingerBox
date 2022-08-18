package string;

/**
 * @author Vyacheslav Doroshenko
 */
public class PhoneFormatter {

    public static String prefixFormat(String providerPhone){
        if (providerPhone != null && providerPhone.trim().length() > 0) {
            providerPhone = providerPhone.replaceAll("\\D", "");
            if (providerPhone.startsWith("8")) {
                providerPhone = "+7" + providerPhone.substring(1);
            } else if (providerPhone.startsWith("7")) {
                providerPhone = "+" + providerPhone;
            }
            return providerPhone;
        }
        return "";
    }
}
