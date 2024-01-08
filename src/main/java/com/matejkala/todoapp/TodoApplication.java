package com.matejkala.todoapp;

import com.matejkala.todoapp.todoApp.repositories.AssigneeRepo;
import com.matejkala.todoapp.todoApp.repositories.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {
  private TodoRepo todoRepo;
  private AssigneeRepo assigneeRepo;

  @Autowired
  public TodoApplication(TodoRepo todoRepo, AssigneeRepo assigneeRepo) {
    this.todoRepo = todoRepo;
    this.assigneeRepo = assigneeRepo;
  }

  public static void main(String[] args) {
    SpringApplication.run(TodoApplication.class, args);
  }

  @Override
  public void run(String... args) {

//    Assignee assignee = new Assignee("Matej", "matej@email.com");
//    assigneeRepo.save(assignee);
//    Assignee assignee2 = new Assignee("Tomas", "tomas@email.com");
//    assigneeRepo.save(assignee2);
//    Assignee assignee3 = new Assignee("Kuba", "kuba@email.com");
//    assigneeRepo.save(assignee3);
//
//    Todo todo = new Todo("I have to learn Object Relational Mapping",
//                         "I have to learn Object Relational Mapping", true, false,
//                         Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),
//                         assignee);
//    todoRepo.save(todo);
//    Todo todo1 = new Todo("I have to learn OOP", "I have to learn OOP", true, false,
//                          Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),
//                          assignee);
//    todoRepo.save(todo1);
//    Todo todo2 =
//        new Todo("I have to learn Design Patterns", "I have to learn Design Patterns", true, false,
//                 Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), assignee);
//    todoRepo.save(todo2);
//    Todo todo3 =
//        new Todo("Do it ASAP!", "Do it ASAP!", true, false, Calendar.getInstance().getTime(),
//                 Calendar.getInstance().getTime(), assignee);
//    todoRepo.save(todo3);
//    Todo todo4 = new Todo("Uff, this is finished", "Uff, this is finished", true, false,
//                          Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),
//                          assignee);
//    todoRepo.save(todo4);
  }
}
