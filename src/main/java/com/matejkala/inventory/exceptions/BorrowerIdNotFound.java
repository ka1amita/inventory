package com.matejkala.inventory.exceptions;

public class BorrowerIdNotFound extends NotFoundException {

  protected final long borrowerId;

  public BorrowerIdNotFound(long borrowerId) {
    super("Borrower ID not found: \"%d\"");
    this.borrowerId = borrowerId;
  }

  @Override
  public String getMessage() {
    return String.format(super.getMessage(), borrowerId);
  }
}
