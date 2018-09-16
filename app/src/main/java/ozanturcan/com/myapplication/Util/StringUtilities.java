package ozanturcan.com.myapplication.Util;

public class StringUtilities {

    public static String convertToFirtIndexUpperCase(String string) {
        if (string == null || string.isEmpty()) {
            return "";
        } else {
            String s1 = string.substring(0, 1).toUpperCase();
            String nameCapitalized = s1 + string.substring(1);
            return nameCapitalized;
        }
    }
}
