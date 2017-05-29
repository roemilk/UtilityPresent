package redpig.utility.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 유효성 검사
 * Created by Kim YoungHun on 2016-08-04.
 */
public class ValidationCheck {
    private static final String Passwrod_PATTERN = "^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,18}$";

    /**
     * 요약 : 이메일 형식을 체크한다.
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * 요약 : 문자열에 특수문자가 포함되었는지 체크한다.
     *
     * @param str
     * @return true:포함됨 fasle:포함되지 않음
     */
    public static boolean checkSpecialLetters(String str) {
        if (!str.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝| ]*")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 패스워드가 영문 숫자 조합방식인지 6~18자리인지 체크한다.
     * @return
     */
    public static boolean checkPassword(final String hex) {
        Pattern pattern = Pattern.compile(Passwrod_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    /**
     *
     * 요약 : str 문자열이 len의 길이를 넘어갈때 len만큼 뒤의 문자열을 말줄임...처리한다.
     * @param str
     * @param len
     * @return
     */
    public static String checkStringLengthEllipsize(String str, int len) {
        String result = "";
        if(str.length() > len) {
            String temp = str.substring(0, len);
//			result = str.replaceFirst(temp, "...");
            result = temp + "...";
        }else {
            result = str;
        }
        return result;
    }
}
