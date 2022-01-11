//package com.example.dao;
//
//
//import com.example.dao.entity.*;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.dialect.H2Dialect;
//import org.hibernate.service.ServiceRegistry;
//
//
//public class HibernateConfiguration {
//
//    public static final SessionFactory sessionFactory;
//
//    static {
//        Configuration configuration = new Configuration()
//                .addAnnotatedClass(Product.class)
//                .addAnnotatedClass(User.class)
//                .addAnnotatedClass(Role.class)
//                .addAnnotatedClass(Item.class)
//                .addAnnotatedClass(Order.class)
//                .addAnnotatedClass(Basket.class)
//                .setProperty("hibernate.dialect", H2Dialect.class.getName())
////                .setProperty("hibernate.connection.driver_class", org.h2.Driver.class.getName())
//                .setProperty("hibernate.connection.url", "jdbc:h2:mem:InternetMarket")
//                .setProperty("hibernate.connection.username", "root")
//                .setProperty("hibernate.connection.password", "rootroot")
//                .setProperty("hibernate.hbm2ddl.auto", "create")
//                .setProperty("hibernate.show_sql", "true")
////                .setProperty("hibernate.hbm2ddl.auto", "update")
//                .setProperty("hibernate.format_sql", "true");
//
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
//                .build();
//        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//    }
//}
//
