package com.jpa.repo;

import com.jpa.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * {@code @description:}
 */
@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findCatByAge(Integer age);
    
    @Transactional
    @Modifying
    @Query("select cat from Cat cat")
    List<Cat> findCats();
}