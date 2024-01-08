package com.matejkala.todoapp.todoApp.services;

import com.matejkala.todoapp.todoApp.models.Todo;
import com.matejkala.todoapp.todoApp.repositories.TodoRepo;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

  private final TodoRepo todoRepo;

  public TodoServiceImpl(TodoRepo todoRepo) {
    this.todoRepo = todoRepo;
  }

  @Override
  public List<Todo> findAll() {
    return todoRepo.findAll();
  }

  @Override
  public Todo save(Todo todo) {
    return todoRepo.save(todo);
  }

  @Override
  public void delete(Todo todo) {
    todoRepo.delete(todo);
  }

  @Override
  public List<Todo> queryByAssigneeId(long id) {
    return todoRepo.queryByAssigneeId(id);
  }

  @Override
  public List<Todo> queryByTitleContains(String title) {
    return todoRepo.queryByTitleContains(title);
  }

  @Override
  public List<Todo> queryByDescriptionContains(String description) {
    return todoRepo.queryByDescriptionContains(description);
  }

  @Override
  public Optional<Todo> findById(long id) {
    return todoRepo.findById(id);
  }

  @Override
  public List<Todo> queryByAssigneeNameContains(String name) {
    return todoRepo.queryByAssigneeNameContains(name);
  }

  @Override
  public List<Todo> queryByDueDateIs(Date date) {
    return todoRepo.queryByDueDateIs(date);
  }

  @Override
  public List<Todo> queryByCreationDateIs(Date date) {
    return todoRepo.queryByCreationDateIs(date);
  }
}
