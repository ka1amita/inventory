package com.matejkala.inventory.controllers;

import com.matejkala.inventory.services.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("borrower/list")
public class BorrowerListController {

  @Autowired
  BorrowerService borrowerService;

  public BorrowerListController(BorrowerService borrowerService) {
    this.borrowerService = borrowerService;
  }

  @GetMapping({"", "/"})
  public String getList(Model model) {
    model.addAttribute("borrowers", borrowerService.findAllEnabled());
    return "listBorrowers";
  }
}
