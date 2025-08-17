package com.askarakhmetov.hibernate.sample1;

import com.askarakhmetov.hibernate.sample1.entity.Car;
import com.askarakhmetov.hibernate.sample1.entity.Engine;
import com.askarakhmetov.hibernate.sample1.entity.Wheel;
import com.askarakhmetov.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Sample of using @Embeddable and @ElementCollection
 */
public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .addAnnotatedClass(Car.class);
        try (SessionFactory sessionFactory = config.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Car car = Car.builder()
                    .vin(HibernateUtil.generateAlphanumericStr(9))
                    .color("White")
                    .brandName("KIA")
                    .modelName("Ceed")
                    .engine(Engine.builder()
                            .number(HibernateUtil.generateAlphanumericStr(9))
                            .horsePower(128)
                            .build())
                    .wheels(List.of(
                            Wheel.builder().radius(16).isWinter(true).build(),
                            Wheel.builder().radius(16).isWinter(true).build(),
                            Wheel.builder().radius(16).isWinter(true).build(),
                            Wheel.builder().radius(16).isWinter(true).build()
                    ))
                    .glasses(List.of(
                            HibernateUtil.generateAlphanumericStr(9),
                            HibernateUtil.generateAlphanumericStr(9),
                            HibernateUtil.generateAlphanumericStr(9),
                            HibernateUtil.generateAlphanumericStr(9)
                    ))
                    .build();

            session.persist(car);

            var cars = session.createQuery("select c from Car c", Car.class).list();
            cars.forEach(System.out::println);

            session.getTransaction().commit();
        }
    }
}