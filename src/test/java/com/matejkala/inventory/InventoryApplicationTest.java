package com.matejkala.inventory;

import static org.assertj.core.api.Assertions.assertThat;

import com.matejkala.inventory.controllers.BorrowerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InventoryApplicationTest {

  @Autowired
  private BorrowerController controller;

  @Test
  void contextLoads() {
    assertThat(controller).isNotNull();
  }
}
