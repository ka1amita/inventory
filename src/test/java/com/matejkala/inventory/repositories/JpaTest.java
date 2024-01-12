package com.matejkala.inventory.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.matejkala.inventory.models.Borrower;
import com.matejkala.inventory.models.Item;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class JpaTest {

  @Autowired
  private ItemRepo itemRepo;
  @Autowired
  private BorrowerRepo borrowerRepo;
  private Borrower borrower;
  private Item item;

  @BeforeEach
  void setUp() {
    //TODO recreate the DB
    borrower = new Borrower("name", "some@email.com", true);
    item = new Item("title", "description", true, true, new Date(), new Date(), null);
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void injects_dependencies() {
    assertNotNull(itemRepo);
    assertNotNull(borrowerRepo);
  }

  @Test
  void persist_borrower_without_item() {
    borrowerRepo.save(borrower);

    assertEquals(1, borrowerRepo.findAll().size());
    assertEquals(borrower.getId(), borrowerRepo.findAll().get(0).getId());
    assertEquals(borrower.getName(), borrowerRepo.findAll().get(0).getName());
    assertEquals(borrower.getEmail(), borrowerRepo.findAll().get(0).getEmail());
  }

  @Test
  void persist_item_without_borrower() {
    itemRepo.save(item);

    assertEquals(1, itemRepo.findAll().size());
    assertEquals(item, itemRepo.findAll().get(0));
  }

  @Test
  void assign_an_item_to_persisted_borrower() {
    borrowerRepo.save(borrower);
    item.setBorrower(borrower);
    itemRepo.save(item);

    assertEquals(borrower.getId(), itemRepo.findAll().get(0).getBorrower().getId());
    assertEquals(borrower.getName(), itemRepo.findAll().get(0).getBorrower().getName());
    assertEquals(borrower.getEmail(), itemRepo.findAll().get(0).getBorrower().getEmail());
  }

  @Test
  void updates_borrower() {
    Borrower saved = borrowerRepo.save(borrower);
    assert borrowerRepo.findAll().size() == 1;
    saved.setName("newName");
    Borrower updated = borrowerRepo.save(saved);

    assertEquals("newName", borrowerRepo.findById(updated.getId()).get().getName());
  }

  @Test
  void updates_item() {
    Item saved = itemRepo.save(item);
    assert itemRepo.findAll().size() == 1;
    saved.setTitle("newTitle");
    Item updated = itemRepo.save(saved);

    assertEquals("newTitle", itemRepo.findById(updated.getId()).get().getTitle());
  }

  @Test
  void deletes_borrower() {
    borrowerRepo.save(borrower);
    assert borrowerRepo.findAll().size() == 1;

    borrowerRepo.delete(borrower);
    assertEquals(0, borrowerRepo.findAll().size());
  }

  @Test
  void deletes_item() {
    itemRepo.save(item);
    assert itemRepo.findAll().size() == 1;

    itemRepo.delete(item);
    assertEquals(0, itemRepo.findAll().size());
  }
}
