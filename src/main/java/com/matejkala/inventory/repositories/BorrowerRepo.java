package com.matejkala.inventory.repositories;

import com.matejkala.inventory.models.Borrower;
import com.matejkala.inventory.models.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepo extends JpaRepository<Borrower, Long> {

  List<Borrower> queryAllByEnabledIs(boolean enabled);

  List<Borrower> queryByItemsContains(Item item);
}
