package com.matejkala.todoapp.todoApp.services;

import com.matejkala.todoapp.todoApp.models.Todo;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TodoService {

  List<Todo> findAll();

  Todo save(Todo todo);

  void delete(Todo todo);

  List<Todo> queryByAssigneeId(long id);

  List<Todo> queryByTitleContains(String title);

  List<Todo> queryByDescriptionContains(String description);

  Optional<Todo> findById(long id);

  List<Todo> queryByAssigneeNameContains(String name);

  List<Todo> queryByDueDateIs(Date date);

  List<Todo> queryByCreationDateIs(Date date);
}
