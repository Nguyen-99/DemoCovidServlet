package com.nguyenz.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.FileNotFoundException;

public class HibernateUtils {
    private static SessionFactory sessionFactory = buildSessionFactory();

    public HibernateUtils() throws FileNotFoundException {
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutDown(){
        // Giải phóng cache và Connection Pools
        getSessionFactory().close();
    }

    private static SessionFactory buildSessionFactory(){
        // Tạo danh sách dịch vụ từ file config
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        // Tạo metadata cung cấp các thông tin vè DB,charset,vv
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        // Từ metadata lấy được SesionFactory,class đảm nhiệm việc tạo Sesion
        return metadata.getSessionFactoryBuilder().build();
    }
}
