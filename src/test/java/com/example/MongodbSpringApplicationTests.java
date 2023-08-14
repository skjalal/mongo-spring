package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;

class MongodbSpringApplicationTests {

  @Test
  void contextLoads() {
    assertEquals("3.1.2", SpringBootVersion.getVersion());
  }
}
