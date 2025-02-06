package com.askarakhmetov.java.util;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {

    public static String generateAlphanumericStr(int length) {
        return RandomStringUtils.insecure().nextAlphanumeric(length);
    }
}
