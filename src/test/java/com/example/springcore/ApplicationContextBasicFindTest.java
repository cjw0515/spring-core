package com.example.springcore;

import com.example.springcore.service.TestService;
import com.example.springcore.service.TestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        TestService ts = ac.getBean("testService", TestService.class);
//        System.out.println("testService : " + ts);
//        System.out.println("testService.getClass() : " + ts.getClass());
        assertThat(ts).isInstanceOf(TestServiceImpl.class);
    }



    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    void findBeanByType(){
        TestService ts = ac.getBean(TestService.class);
//        System.out.println("testService : " + ts);
//        System.out.println("testService.getClass() : " + ts.getClass());
        assertThat(ts).isInstanceOf(TestServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입만으로 조회")
    void findBeanByType2(){
        TestService ts = ac.getBean("testService", TestServiceImpl.class);
//        System.out.println("testService : " + ts);
//        System.out.println("testService.getClass() : " + ts.getClass());
        assertThat(ts).isInstanceOf(TestServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            ac.getBean("XXX", TestService.class);
        });
    }

}
