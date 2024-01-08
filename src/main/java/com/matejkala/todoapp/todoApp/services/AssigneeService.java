package com.matejkala.todoapp.todoApp.services;

import com.matejkala.todoapp.todoApp.models.Assignee;
import java.util.List;

public interface AssigneeService {

  List<Assignee> findAll();

  void save(Assignee assignee);

  void delete(Assignee assignee);

  void deleteById(Long id);

  void renameById(Long id);

  Assignee findById(Long id);

  void setAssigneeName(Assignee assignee);
}
