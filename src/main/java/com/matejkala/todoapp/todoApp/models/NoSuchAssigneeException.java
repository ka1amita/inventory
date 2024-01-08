package com.matejkala.todoapp.todoApp.models;

import java.util.NoSuchElementException;

public class NoSuchAssigneeException extends NoSuchElementException {
  public NoSuchAssigneeException() {
    super("No such Assignee");
  }
}
