package com.matejkala.inventory.services;

import com.matejkala.inventory.exceptions.BorrowerIdNotFound;
import com.matejkala.inventory.models.Borrower;
import com.matejkala.inventory.models.Item;
import java.util.List;

public interface BorrowerService {

  Borrower enableAndSaveBorrower(Borrower borrower);

  Borrower findAndSaveBorrowerAsIs(Borrower borrower) throws BorrowerIdNotFound;

  List<Borrower> findAllEnabled();

  Borrower findBorrower(Borrower borrower) throws BorrowerIdNotFound;

  Borrower findDisableAndSaveBorrower(Borrower borrower) throws BorrowerIdNotFound;

  List<Borrower> filterByItem(Item item);
}
