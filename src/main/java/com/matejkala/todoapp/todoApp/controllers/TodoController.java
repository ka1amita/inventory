package com.matejkala.todoapp.todoApp.controllers;

import com.matejkala.todoapp.todoApp.models.Todo;
import com.matejkala.todoapp.todoApp.services.AssigneeService;
import com.matejkala.todoapp.todoApp.services.TodoService;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("todo")
public class TodoController {

  TodoService todoService;
  AssigneeService assigneeService;

  @Autowired
  public TodoController(TodoService todoRepo, AssigneeService assigneeService) {
    this.todoService = todoRepo;
    this.assigneeService = assigneeService;
  }

  @GetMapping({"", "/", "/list"})
  public String getList(Model model) {
    model.addAttribute("todos", todoService.findAll());
    return "listTodo";
  }

  @GetMapping("/add")
  public String getAddTodo(Todo todo, Model model) {
    model.addAttribute("todo", todo);
    model.addAttribute("assignees", assigneeService.findAll());
    return "addTodo";
  }

  @PostMapping("/add")
  public String addTodoByTitle(@ModelAttribute Todo todo) {
    todoService.save(todo);
    return "redirect:/todo/";
  }

  @GetMapping("/{id}")
  public String showDetails(@PathVariable() long id, Model model) {
    Optional<Todo> hit = todoService.findById(id);
    if (!hit.isPresent()) {
      return "redirect:/todo/list";
    }
    model.addAttribute("todo", hit.get());
    return "todoDetail";
  }

  @GetMapping("/{id}/edit")
  public String getEditTodo(@PathVariable long id, Model model) {
    Optional<Todo> hit = todoService.findById(id);
    if (!hit.isPresent()) {
      return "redirect:/todo/list";
    }
    model.addAttribute("todo", hit.get());
    model.addAttribute("assignees", assigneeService.findAll());
    return "editTodo";
  }

  @PostMapping("/{id}/edit")
  public String editTodo(@ModelAttribute Todo todo) {
    todoService.save(todo);
    return "redirect:/todo/";
  }

  @PostMapping("/{id}/delete")
  public String deleteById(@ModelAttribute Todo todo) {
    todoService.delete(todo);
    return "redirect:/todo/";
  }

  @GetMapping("/{id}/filter-by-assignee-id")
  public String filterByAssignee(@PathVariable long id, Model model) {
    model.addAttribute("todos", todoService.queryByAssigneeId(id));
    return "listTodo";
  }

  @PostMapping("/filter-by-title")
  public String filterByTitle(@RequestParam(name = "input") String title, Model model) {
    model.addAttribute("todos", todoService.queryByTitleContains(title));
    return "listTodo";
  }

  @PostMapping("/filter-by-description")
  public String filterByContent(@RequestParam(name = "input") String description, Model model) {
    model.addAttribute("todos", todoService.queryByDescriptionContains(description));
    return "listTodo";
  }

  @PostMapping("/filter-by-assignee-name")
  public String filterByAssigneeName(@RequestParam(name = "input") String name, Model model) {
    model.addAttribute("todos", todoService.queryByAssigneeNameContains(name));
    return "listTodo";
  }

  @PostMapping("/filter-by-due-date")
  public String filterByDueDate(
      @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date date,
      Model model) {
    model.addAttribute("todos", todoService.queryByDueDateIs(date));
    return "listTodo";
  }

  @PostMapping("/filter-by-creation-date")
  public String filterByCreationDate(
      @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date date,
      Model model) {
    model.addAttribute("todos", todoService.queryByCreationDateIs(date));
    return "listTodo";
  }
}
