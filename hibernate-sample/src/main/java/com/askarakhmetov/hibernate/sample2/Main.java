package com.askarakhmetov.hibernate.sample2;

import com.askarakhmetov.hibernate.sample2.entity.Engine;
import com.askarakhmetov.hibernate.sample2.entity.EngineType;
import com.askarakhmetov.hibernate.sample2.entity.FuelType;
import com.askarakhmetov.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Sample of using @Enumerated and @Converter
 */
public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .addAnnotatedClass(Engine.class);
        try (SessionFactory sessionFactory = config.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            var engine = Engine.builder()
                    .number(HibernateUtil.generateAlphanumericStr(9))
                    .fuelType(FuelType.BENZINE)
                    .engineType(EngineType.ATMOSPHERIC)
                    .build();

            session.persist(engine);

            var engines = session.createQuery("select e from Engine e", Engine.class).list();
            engines.forEach(System.out::println);

            session.getTransaction().commit();
        }
    }
}
