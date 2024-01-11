package com.matejkala.inventory.controllers;

import com.matejkala.inventory.exceptions.BorrowerIdNotFound;
import com.matejkala.inventory.exceptions.NotFoundException;
import com.matejkala.inventory.exceptions.ItemIdNotFound;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class NotFoundAdvice {

  private static final String ERR = "error";

  @ExceptionHandler(ItemIdNotFound.class)
  String handleItemNotFound(Exception e, RedirectAttributes redirect) {
    redirect.addFlashAttribute(ERR, e.getMessage());
    return "redirect:/item/list";
  }

  @ExceptionHandler(BorrowerIdNotFound.class)
  String handleBorrowerNotFound(Exception e, RedirectAttributes redirect) {
    redirect.addFlashAttribute(ERR, e.getMessage());
    return "redirect:/borrower/list";
  }

  @ExceptionHandler(NotFoundException.class)
  String handleItemExceptions(Exception e, RedirectAttributes redirect) {
    redirect.addFlashAttribute(ERR, e.getMessage());
    return "redirect:/item/list";
  }
}
