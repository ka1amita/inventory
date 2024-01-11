package com.matejkala.inventory.exceptions;

public abstract class NotFoundException extends InventoryException {

  public NotFoundException(String message) {
    super(message);
  }
}
