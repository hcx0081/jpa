package jpa;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Arrays;

/**
 * @Description:
 */
public class JPQLTest {
    @Test
    public void selectTest() {
        // 根据持久性单元的名称创建EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        // 创建EntityManager
        EntityManager em = emf.createEntityManager();
        String jpql = "select name, age from Person where id = ?11";
        Query query = em.createQuery(jpql);
        query.setParameter(11, 1);
        Object result = query.getSingleResult();
        System.out.println(Arrays.toString((Object[]) result));
        // [张三, 20]
        
        em.close();
        emf.close();
    }
}