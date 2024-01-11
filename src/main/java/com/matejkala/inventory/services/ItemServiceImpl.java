package com.matejkala.inventory.services;

import com.matejkala.inventory.exceptions.ItemIdNotFound;
import com.matejkala.inventory.models.Borrower;
import com.matejkala.inventory.models.Item;
import com.matejkala.inventory.repositories.ItemRepo;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

  private final ItemRepo itemRepo;

  @Autowired
  public ItemServiceImpl(ItemRepo itemRepo) {
    this.itemRepo = itemRepo;
  }

  @Override
  public Item enableAndSaveItem(Item item) {
    item.enable();
    return save(item);
  }

  @Override
  public Item findAndSaveItemAsIs(Item item) throws ItemIdNotFound {
    findItem(item);
    return save(item);
  }

  private Item save(Item item) {
    return itemRepo.save(item);
  }

  @Override
  public List<Item> findAllEnabled() {
    return itemRepo.queryAllByEnabledIs(true);
  }

  @Override
  public Item findItem(Item item) throws ItemIdNotFound {
    return findByItemId(item.getId());
  }

  private Item findByItemId(long id) throws ItemIdNotFound {
    return itemRepo.findById(id).orElseThrow(() -> new ItemIdNotFound(id));
  }

  @Override
  public Item findDisableAndSaveItem(Item item) throws ItemIdNotFound {
    final Item found = findByItemId(item.getId());
    found.disable();
    return save(found);
  }

  @Override
  public List<Item> filterByBorrower(Borrower borrower) {
    return itemRepo.queryByBorrower(borrower);
  }

  @Override
  public List<Item> filterByBorrowerId(long id) {
    return itemRepo.queryByBorrowerId(id);
  }

  @Override
  public List<Item> filterByItemTitleContains(String title) {
    return itemRepo.queryByTitleContains(title);
  }

  @Override
  public List<Item> filterByItemDescriptionContains(String description) {
    return itemRepo.queryByDescriptionContains(description);
  }

  @Override
  public List<Item> filterByItemCreationDateIs(Date date) {
    return itemRepo.queryByCreationDateIs(date);
  }

  @Override
  public List<Item> filterByItemExpirationDateIs(Date date) {
    return itemRepo.queryByExpirationDateIs(date);
  }

  @Override
  public List<Item> filterByBorrowerNameContains(String name) {
    return itemRepo.queryByBorrowerNameContains(name);
  }
}
