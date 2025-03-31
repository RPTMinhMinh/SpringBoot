package vn.com.t3h.utils;

import java.time.LocalDateTime;

public class GenerateCode {
    public static String generateUniqueCode(String code) {
        code = code.toUpperCase();
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear() % 100;
        int uniqueNumber = (int) (Math.random() * 90000) + 10000;
        return String.format(code+"%d%d", year, uniqueNumber);
    }

}
