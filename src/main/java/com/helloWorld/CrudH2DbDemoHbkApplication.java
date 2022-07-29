package com.helloWorld;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class CrudH2DbDemoHbkApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudH2DbDemoHbkApplication.class, args);
  }
}
