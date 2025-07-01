package com.learn.e_commerce;

import com.learn.e_commerce.service.CategoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /*
    * This is optional if, @Service is provided on CategoryImpl class
    *
    * */
//    @Bean
//    public CategoryImpl initCategoryService(){
//        return new CategoryImpl();
//    }
}
