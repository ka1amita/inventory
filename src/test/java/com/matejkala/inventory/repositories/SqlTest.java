package com.matejkala.inventory.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SqlTest {

  @Autowired
  private BorrowerRepo borrowerRepo;
  @Autowired
  private ItemRepo itemRepo;

  @Test
  @Sql(scripts = "classpath:sql-test.sql")
  void dbUnit_inserts_data() {
    assertEquals("sql", borrowerRepo.findById(1L).get().getName());
    assertEquals("sql", itemRepo.findById(1L).get().getTitle());
  }
}
