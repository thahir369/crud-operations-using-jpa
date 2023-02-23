package com.halloWorld;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class CrudOperationsUsingJpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudOperationsUsingJpaApplication.class, args);
  }
}
