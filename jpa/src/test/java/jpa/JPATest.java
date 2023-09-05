package jpa;

import com.jpa.entity.Person;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * {@code @description:}
 */
public class JPATest {
    @Test
    public void selectTest() {
        // 根据持久性单元的名称创建EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        // 创建EntityManager
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, 1);
        System.out.println(person);
        
        // 关闭
        em.close();
        emf.close();
    }
    
    @Test
    public void insertTest() {
        // 根据持久性单元的名称创建EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        // 创建EntityManager
        EntityManager em = emf.createEntityManager();
        // 开启事务
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        
        Person person = new Person();
        person.setName("张三");
        person.setAge(20);
        // 执行持久化操作
        em.persist(person);
        
        // 提交事务
        transaction.commit();
        
        // 关闭
        em.close();
        emf.close();
    }
    
    
    @Test
    public void secondLevelCacheTest() {
        // 根据持久性单元的名称创建EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        // 创建EntityManager
        EntityManager em = emf.createEntityManager();
        Person person1 = em.find(Person.class, 1);
        // 关闭
        em.close();
        
        em = emf.createEntityManager();
        Person person2 = em.find(Person.class, 1);
        // 关闭
        em.close();
        
        emf.close();
    }
}