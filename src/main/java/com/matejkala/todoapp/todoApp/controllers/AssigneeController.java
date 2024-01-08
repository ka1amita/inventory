package com.matejkala.todoapp.todoApp.controllers;

import com.matejkala.todoapp.todoApp.models.Assignee;
import com.matejkala.todoapp.todoApp.services.AssigneeService;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("assignee")
public class AssigneeController {

  @Autowired
  AssigneeService assigneeService;

  public AssigneeController(AssigneeService assigneeService) {
    this.assigneeService = assigneeService;
  }

  @GetMapping({"/", "/list"})
  public String getList(Assignee assignee, Model model) {
    model.addAttribute("assignees", assigneeService.findAll());
    return "listAssignee";
  }

  @GetMapping("/add")
  public String getAddAssignee(Assignee assignee, Model model) {
    model.addAttribute("assignee", assignee);
    return "addAssignee";
  }

  @PostMapping("/add")
  public String addAssignee(@ModelAttribute Assignee assignee) {
//  public String addAssignee(@RequestBody Assignee assignee) { ???
    assigneeService.save(assignee);
    return "redirect:/assignee/";
  }

  @PostMapping("/{id}/delete")
  public String deleteAssignee(@PathVariable Long id) {
    assigneeService.deleteById(id);
    return "redirect:/assignee/";
  }

  @GetMapping("/{id}/edit")
  public String getEditAssignee(@PathVariable long id, Model model) {
    try {
      model.addAttribute("assignee", assigneeService.findById(id));
      return "editAssignee";
    } catch (NoSuchElementException e) {
      return "redirect:/assignee/list";
    }
  }

  @PostMapping("/{id}/edit")
  public String editAssignee(@ModelAttribute Assignee assignee) {
    assigneeService.save(assignee);
    return "redirect:/assignee/";
  }
}
