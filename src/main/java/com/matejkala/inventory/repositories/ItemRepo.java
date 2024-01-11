package com.matejkala.inventory.repositories;

import com.matejkala.inventory.models.Borrower;
import com.matejkala.inventory.models.Item;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

  List<Item> queryByBorrowerId(long id);

  List<Item>  queryByTitleContains(String name);

  List<Item> queryByDescriptionContains(String name);

  List<Item> queryAllByEnabledIs(boolean enabled);

  List<Item> queryByCreationDateIs(Date creationDate);

  List<Item> queryByExpirationDateIs(Date expirationDate);

  List<Item> queryByBorrowerNameContains(String name);

  List<Item> queryByBorrower(Borrower borrower);
}
