package com.hibernate;

import com.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.HashSet;

public class HibernateTest {
    @Test
    public void selectTest() {
        // Create the SessionFactory from hibernate.cfg.xml
        // configure()方法默认读取hibernate.cfg.xml，若要读取指定配置文件，则需要传入指定配置文件名称
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Person person = session.get(Person.class, 1);

        System.out.println(person);
        session.close();
    }

    @Test
    public void insertTest() {
        // Create the SessionFactory from hibernate.cfg.xml
        // configure()方法默认读取hibernate.cfg.xml，若要读取指定配置文件，则需要传入指定配置文件名称
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Person person = new Person();
        person.setName("张三");
        person.setAge(20);

        session.save(person);

        // 开启事务提交，否则提交不了
        session.beginTransaction().commit();

        session.close();
    }

    @Test
    public void oneToManyTest() {
        // Create the SessionFactory from hibernate.cfg.xml
        // configure()方法默认读取hibernate.cfg.xml，若要读取指定配置文件，则需要传入指定配置文件名称
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Customer customer = new Customer();
        customer.setName("王五");

        Bill bill = new Bill();
        bill.setName("账单1");
        bill.setCustomer(customer);// 建立关联关系

        session.save(customer);
        session.save(bill);

        session.beginTransaction().commit();
        session.close();
    }

    @Test
    public void ManyToManyTest() {
        // Create the SessionFactory from hibernate.cfg.xml
        // configure()方法默认读取hibernate.cfg.xml，若要读取指定配置文件，则需要传入指定配置文件名称
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Student student = new Student();
        student.setSname("张三");

        Course course = new Course();
        course.setCname("Java");

        HashSet<Course> courseSet = new HashSet<>();
        courseSet.add(course);

        student.setCourseSet(courseSet);

        session.save(student);
        session.save(course);

        session.beginTransaction().commit();
        session.close();
    }

    @Test
    public void lazyOneTest() {
        // Create the SessionFactory from hibernate.cfg.xml
        // configure()方法默认读取hibernate.cfg.xml，若要读取指定配置文件，则需要传入指定配置文件名称
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Customer customer = session.get(Customer.class, 1);

        // 查询指定用户
        // System.out.println(customer);
        // 查询指定用户的账单数量
        System.out.println(customer.getBillSet().size());

        session.close();
    }

    @Test
    public void lazyManyTest() {
        // Create the SessionFactory from hibernate.cfg.xml
        // configure()方法默认读取hibernate.cfg.xml，若要读取指定配置文件，则需要传入指定配置文件名称
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Bill bill = session.get(Bill.class, 10);
        System.out.println(bill);

        session.close();
    }
}