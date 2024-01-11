package com.matejkala.inventory.exceptions;

public class ItemIdNotFound extends NotFoundException {

  protected final long itemId;

  public ItemIdNotFound(long itemId) {
    super("Item ID not found: \"%d\"");
    this.itemId = itemId;
  }

  @Override
  public String getMessage() {
    return String.format(super.getMessage(), itemId);
  }
}
