package com.hibernate;

import com.hibernate.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

/**
 * @description:
 */
public class HQLTest {
    @Test
    public void selectTest() {
        // Create the SessionFactory from hibernate.cfg.xml
        // configure()方法默认读取hibernate.cfg.xml，若要读取指定配置文件，则需要传入指定配置文件名称
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        String hql = "from Person where id = :id";
        Query<Person> query = session.createQuery(hql);
        query.setParameter("id", 1);
        Person personList = query.uniqueResult();
        System.out.println(personList);
    }
}