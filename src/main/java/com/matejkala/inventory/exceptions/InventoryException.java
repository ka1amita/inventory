package com.matejkala.inventory.exceptions;

public abstract class InventoryException extends Exception {

  protected InventoryException(String message) {
    super(message);
  }
}
