package com.jpa;

import com.jpa.entity.Cat;
import com.jpa.repo.CatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringdatajpaApplicationTests {
    @Resource
    CatRepository catRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void insertTest() {
        Cat cat = new Cat();
        cat.setAge(20);
        cat.setFirstName("small");
        cat.setLastName("cat");
        catRepository.save(cat);
    }

    @Test
    void selectTest() {
        List<Cat> catList = catRepository.findAll();
        catList.forEach(System.out::println);

        catRepository.findById(1L).ifPresent(System.out::println);
    }

    @Test
    void deleteTest() {
        catRepository.deleteById(1L);
    }
}