package com.matejkala.todoapp.todoApp.controllers;

import com.matejkala.todoapp.todoApp.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @Autowired
  private final TodoService todoService;

  public IndexController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping({"", "/", "/list"})
  public String getList(Model model) {

    model.addAttribute("todos", todoService.findAll());
    return "listTodo";
  }
}
