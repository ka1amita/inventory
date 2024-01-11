package com.matejkala.inventory.services;

import com.matejkala.inventory.exceptions.BorrowerIdNotFound;
import com.matejkala.inventory.models.Borrower;
import com.matejkala.inventory.models.Item;
import com.matejkala.inventory.repositories.BorrowerRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowerServiceImp implements BorrowerService {

  private final BorrowerRepo borrowerRepo;

  @Autowired
  public BorrowerServiceImp(BorrowerRepo borrowerRepo) {
    this.borrowerRepo = borrowerRepo;
  }

  @Override
  public Borrower enableAndSaveBorrower(Borrower borrower) {
    borrower.enable();
    return save(borrower);
  }

  @Override
  public Borrower findAndSaveBorrowerAsIs(Borrower borrower) throws BorrowerIdNotFound {
    findBorrower(borrower);
    return save(borrower);
  }

  private Borrower save(Borrower borrower) {
    return borrowerRepo.save(borrower);
  }

  @Override
  public Borrower findDisableAndSaveBorrower(Borrower borrower) throws BorrowerIdNotFound {
    Borrower found = findByBorrowerId(borrower.getId());
    found.disable();
    return save(found);
  }

  @Override
  public List<Borrower> findAllEnabled() {
    return borrowerRepo.queryAllByEnabledIs(true);
  }

  @Override
  public Borrower findBorrower(Borrower borrower) throws BorrowerIdNotFound {
    return findByBorrowerId(borrower.getId());
  }

  private Borrower findByBorrowerId(Long id) throws BorrowerIdNotFound {
    return borrowerRepo.findById(id).orElseThrow(() -> new BorrowerIdNotFound(id));
  }

  @Override
  public List<Borrower> filterByItem(Item item) {
    return borrowerRepo.queryByItemsContains(item);
  }
}
