package com.matejkala.inventory.controllers;

import com.matejkala.inventory.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @Autowired
  private final ItemService itemService;

  public MainController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping({"", "/"})
  public String getList(Model model) {

    model.addAttribute("items", itemService.findAllEnabled());
    return "redirect:/item/list/";
  }
}
