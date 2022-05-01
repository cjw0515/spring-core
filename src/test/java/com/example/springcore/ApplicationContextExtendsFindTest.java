package com.example.springcore;

import com.example.springcore.service.TestService;
import com.example.springcore.service.TestServiceImpl;
import com.example.springcore.service.TestServiceImpl2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            ac.getBean(TestService.class);
        });
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정해주면 된다.")
    void findBeanByParentTypeBeanName(){
        TestService testService1 = ac.getBean("testService", TestService.class);
        assertThat(testService1).isInstanceOf(TestService.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        TestServiceImpl bean = ac.getBean(TestServiceImpl.class);
        assertThat(bean).isInstanceOf(TestServiceImpl.class);
    }

    @Test
    @DisplayName("부모 타입으로 조회 ")
    void findByParentName(){
        Map<String, TestService> beansOfType = ac.getBeansOfType(TestService.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String s : beansOfType.keySet()) {
            System.out.println("key : " + s + " value : " + beansOfType.get(s));
        }
    }



    @Configuration
    static class TestConfig {
        @Bean
        public TestService testService() {
            return new TestServiceImpl();
        }

        @Bean
        public TestService testService2() {
            return new TestServiceImpl2();
        }
    }
}
