package com.matejkala.inventory.controllers;

import static com.matejkala.inventory.controllers.ItemController.ADDED_MSG;
import static com.matejkala.inventory.controllers.ItemController.DELETED_MSG;
import static com.matejkala.inventory.controllers.ItemController.EDITED_MSG;
import static com.matejkala.inventory.controllers.ItemController.MSG;
import static java.lang.String.format;

import com.matejkala.inventory.exceptions.BorrowerIdNotFound;
import com.matejkala.inventory.models.Borrower;
import com.matejkala.inventory.services.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("borrower")
public class BorrowerController {

  private final BorrowerService borrowerService;
  @Autowired
  public BorrowerController(BorrowerService borrowerService) {
    this.borrowerService = borrowerService;
  }

  @GetMapping("")
  public String showBorrowerDetail(@ModelAttribute final Borrower borrower, Model model)
      throws BorrowerIdNotFound {
    Borrower found = borrowerService.findBorrower(borrower);
    model.addAttribute("borrower", found);
    return "borrowerDetail";
  }

  @GetMapping("add")
  public String getAddBorrowerForm(Borrower borrower, Model model) {
    return "addBorrower";
  }

  @PostMapping("add")
  public String addBorrower(@ModelAttribute final Borrower borrower, RedirectAttributes redirect) {
    final Borrower saved = borrowerService.enableAndSaveBorrower(borrower);
    redirect.addFlashAttribute(MSG, format(ADDED_MSG, saved.getName()));
    return "redirect:/borrower/list";
  }

  @GetMapping("edit")
  public String getEditBorrowerForm(@ModelAttribute final Borrower borrower, Model model)
      throws BorrowerIdNotFound {
    final Borrower found = borrowerService.findBorrower(borrower);
    model.addAttribute("borrower", found);
    return "editBorrower";
  }

  @PostMapping("edit")
  public String updateBorrower(@ModelAttribute final Borrower borrower, RedirectAttributes redirect)
      throws BorrowerIdNotFound {
    final Borrower saved = borrowerService.findAndSaveBorrowerAsIs(borrower);
    redirect.addFlashAttribute(MSG, format(EDITED_MSG, saved.getName()));
    return "redirect:/borrower/list";
  }

  @PostMapping("delete")
  public String deleteBorrower(@ModelAttribute final Borrower borrower, RedirectAttributes redirect)
      throws BorrowerIdNotFound {
    final Borrower saved = borrowerService.findDisableAndSaveBorrower(borrower);
    redirect.addFlashAttribute(MSG, format(DELETED_MSG, saved.getName()));
    return "redirect:/borrower/list";
  }
}
