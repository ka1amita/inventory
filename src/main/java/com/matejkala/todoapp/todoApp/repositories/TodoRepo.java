package com.matejkala.todoapp.todoApp.repositories;

import com.matejkala.todoapp.todoApp.models.Todo;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {

  List<Todo> queryByDoneIsNot(boolean active);

  List<Todo> queryByAssigneeId(long id);

  List<Todo> queryByAssigneeNameContains(String name);

  List<Todo> queryByDueDateIs(Date dueDate);

  List<Todo> queryByCreationDateIs(Date creationDate);

  List<Todo>  queryByTitleContains(String name);

  List<Todo> queryByDescriptionContains(String name);
}