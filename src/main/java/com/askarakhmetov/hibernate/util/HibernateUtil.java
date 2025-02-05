package com.askarakhmetov.hibernate.util;

import com.askarakhmetov.hibernate.sample1.entity.Car;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static Configuration createHibernateConfig() {
        return new Configuration()
                .addAnnotatedClass(Car.class);
    }

    public static String generateAlphanumericStr(int length) {
        return RandomStringUtils.insecure().nextAlphanumeric(length);
    }
}
