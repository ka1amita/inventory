package com.matejkala.inventory.services;

import com.matejkala.inventory.exceptions.ItemIdNotFound;
import com.matejkala.inventory.models.Borrower;
import com.matejkala.inventory.models.Item;
import java.util.Date;
import java.util.List;

public interface ItemService {

  Item findItem(Item item) throws ItemIdNotFound;

  Item enableAndSaveItem(Item item);

  Item findAndSaveItemAsIs(Item item) throws ItemIdNotFound;

  Item findDisableAndSaveItem(Item item) throws ItemIdNotFound;

  List<Item> findAllEnabled();

  List<Item> filterByBorrower(Borrower borrower);

  List<Item> filterByBorrowerId(long id);

  List<Item> filterByItemTitleContains(String title);

  List<Item> filterByItemDescriptionContains(String description);

  List<Item> filterByBorrowerNameContains(String name);

  List<Item> filterByItemExpirationDateIs(Date date);

  List<Item> filterByItemCreationDateIs(Date date);
}
