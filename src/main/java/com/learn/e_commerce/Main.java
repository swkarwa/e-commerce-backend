package com.learn.e_commerce;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.learn.e_commerce", // root module
        "com.learn.e_commerce.product_categories" // submodule
})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}