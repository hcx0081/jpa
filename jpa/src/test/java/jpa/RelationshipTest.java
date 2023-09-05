package jpa;

import com.jpa.entity.*;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashSet;

/**
 * {@code @description:}
 */
public class RelationshipTest {
    /**
     * 单向一对多关联
     */
    @Test
    public void singleOneToManyTest() {
        // 根据持久性单元的名称创建EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        // 创建EntityManager
        EntityManager em = emf.createEntityManager();
        // 开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        
        Customer customer = new Customer();
        customer.setName("张三");
        Bill bill1 = new Bill();
        bill1.setName("账单1");
        Bill bill2 = new Bill();
        bill2.setName("账单2");
        HashSet<Bill> billSet = new HashSet<>();
        billSet.add(bill1);
        billSet.add(bill2);
        customer.setBillSet(billSet);// 设置关联关系
        
        // 执行持久化操作
        /* 无论什么持久化顺序，多端在持久化时都不会持久化外键，因为多端没有标识关联关系注解，所以都会产生不必要的update语句 */
        em.persist(customer);
        em.persist(bill1);
        em.persist(bill2);
        
        
        Customer cus = em.find(Customer.class, 1);
        System.out.println(cus);
        System.out.println(cus.getBillSet());
        
        // 提交事务
        transaction.commit();
        
        // 关闭
        em.close();
        emf.close();
    }
    
    
    /**
     * 单向多对一关联
     */
    @Test
    public void singleManyToOneTest() {
        // 根据持久性单元的名称创建EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        // 创建EntityManager
        EntityManager em = emf.createEntityManager();
        // 开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        
        Customer customer = new Customer();
        customer.setName("张三");
        
        Bill bill1 = new Bill();
        bill1.setName("账单1");
        bill1.setCustomer(customer);// 设置关联关系
        Bill bill2 = new Bill();
        bill2.setName("账单2");
        bill2.setCustomer(customer);// 设置关联关系
        
        // 执行持久化操作
        /* 建议先持久化一端，再持久化多端，因为多端的外键需要一端的主键，这样不会产生不必要的update语句 */
        em.persist(customer);
        em.persist(bill1);
        em.persist(bill2);
        // 提交事务
        transaction.commit();
        
        
        Bill bill = em.find(Bill.class, 1);
        System.out.println(bill);
        System.out.println(bill.getCustomer());
        
        em.close();
        emf.close();
    }
    
    
    /**
     * 双向一对多关联
     */
    @Test
    public void doubleOneToManyTest() {
        // 根据持久性单元的名称创建EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        // 创建EntityManager
        EntityManager em = emf.createEntityManager();
        // 开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        
        Customer customer = new Customer();
        customer.setName("张三");
        Bill bill1 = new Bill();
        bill1.setName("账单1");
        bill1.setCustomer(customer);// 设置关联关系
        Bill bill2 = new Bill();
        bill2.setName("账单2");
        bill2.setCustomer(customer);// 设置关联关系
        HashSet<Bill> billSet = new HashSet<>();
        billSet.add(bill1);
        billSet.add(bill2);
        customer.setBillSet(billSet);// 设置关联关系
        
        // 执行持久化操作
        /*
         * 先持久化一端，再持久化多端，会产生n条不必要的update语句
         * 先持久化多端，再持久化一端，会产生2n条不必要的update语句
         * 所以建议只在多端维护关联关系，且先持久化一端，再持久化多端，因为多端的外键需要一端的主键
         *  */
        em.persist(customer);
        em.persist(bill1);
        em.persist(bill2);
        
        // 提交事务
        transaction.commit();
        
        // 关闭
        em.close();
        emf.close();
    }
    
    
    /**
     * 双向一对一关联
     */
    @Test
    public void doubleOneToOneTest() {
        // 根据持久性单元的名称创建EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        // 创建EntityManager
        EntityManager em = emf.createEntityManager();
        // 开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        
        Manager manager = new Manager();
        manager.setMName("张三");
        Department department = new Department();
        department.setDName("研发部");
        manager.setDepartment(department);// 设置关联关系
        department.setManager(manager);// 设置关联关系
        
        // 执行持久化操作
        /* 建议先持久化不维护关联关系的一端，再持久化维护关联关系的一端，因为维护关联关系的一端的外键需要不维护关联关系的一端的主键，这样不会产生不必要的update语句 */
        em.persist(manager);
        em.persist(department);
        
        Manager mgr = em.find(Manager.class, 1);
        System.out.println(mgr);
        System.out.println(mgr.getDepartment());
        
        // 提交事务
        transaction.commit();
        
        // 关闭
        em.close();
        emf.close();
    }
    
    
    /**
     * 双向多对多关联
     */
    @Test
    public void doubleManyToManyTest() {
        // 根据持久性单元的名称创建EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        // 创建EntityManager
        EntityManager em = emf.createEntityManager();
        // 开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        
        Student student = new Student();
        student.setSname("张三");
        Course course = new Course();
        course.setCname("Java");
        HashSet<Student> studentSet = new HashSet<>();
        studentSet.add(student);
        HashSet<Course> courseSet = new HashSet<>();
        courseSet.add(course);
        student.setCourseSet(courseSet);// 设置关联关系
        course.setStudentSet(studentSet);// 设置关联关系
        
        // 执行持久化操作
        em.persist(student);
        em.persist(course);
        
        Student stu = em.find(Student.class, 1);
        System.out.println(stu);
        System.out.println(stu.getCourseSet());
        
        // 提交事务
        transaction.commit();
        
        // 关闭
        em.close();
        emf.close();
    }
}