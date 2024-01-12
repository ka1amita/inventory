package com.matejkala.inventory.repositories;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(value = "classpath:application-flyway-test.properties")
public class FlywayTest {

  @Autowired
  private ItemRepo itemRepo;
  @Autowired
  private BorrowerRepo borrowerRepo;

  @BeforeEach
  public void before() {
  }

  @Test
  public void applies_repeatable_migrations_to_the_test_h2_db() {
    // currently works only with H2. Even when Mysql specified in the `application-flyway-test.properties`, borrowerRepo is still configured to H2
    assertFalse(borrowerRepo.findAll().isEmpty());
    assertFalse(itemRepo.findAll().isEmpty());
  }
}
