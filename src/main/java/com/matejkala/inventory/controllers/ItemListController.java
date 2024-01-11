package com.matejkala.inventory.controllers;

import com.matejkala.inventory.services.BorrowerService;
import com.matejkala.inventory.models.Item;
import com.matejkala.inventory.services.ItemService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("item/list")
public class ItemListController {

  ItemService itemService;
  BorrowerService borrowerService;

  @Autowired
  public ItemListController(ItemService itemService, BorrowerService borrowerService) {
    this.itemService = itemService;
    this.borrowerService = borrowerService;
  }

  @GetMapping({"", "/"})
  public String getList(Model model) {
    List<Item> items = itemService.findAllEnabled();
    model.addAttribute("items", items);
    model.addAttribute(ItemController.MSG, String.format(ItemController.ITEMS_FOUND, items.size()));
    return "listItems";
  }

  @GetMapping("/filter-by-borrower-id")
  public String filterByBorrower(@RequestParam long id, Model model) {
    List<Item> items = itemService.filterByBorrowerId(id);
    model.addAttribute("items", items);
    model.addAttribute(ItemController.MSG, String.format(ItemController.ITEMS_FOUND, items.size()));
    return "listItems";
  }

  @PostMapping("/filter-by-title")
  public String filterByTitle(@RequestParam(name = "input") String title, Model model) {
    List<Item> items = itemService.filterByItemTitleContains(title);
    model.addAttribute("items", items);
    model.addAttribute(ItemController.MSG, String.format(ItemController.ITEMS_FOUND, items.size()));
    return "listItems";
  }

  @PostMapping("/filter-by-description")
  public String filterByDescription(@RequestParam(name = "input") String description, Model model) {
    List<Item> items = itemService.filterByItemDescriptionContains(description);
    model.addAttribute("items", items);
    model.addAttribute(ItemController.MSG, String.format(ItemController.ITEMS_FOUND, items.size()));
    return "listItems";
  }

  @PostMapping("/filter-by-borrower-name")
  public String filterByBorrowerName(@RequestParam(name = "input") String name, Model model) {
    List<Item> items = itemService.filterByBorrowerNameContains(name);
    model.addAttribute("items", items);
    model.addAttribute(ItemController.MSG, String.format(ItemController.ITEMS_FOUND, items.size()));
    return "listItems";
  }

  @PostMapping("/filter-by-due-date")
  public String filterByDueDate(
      @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date date,
      Model model) {
    List<Item> items = itemService.filterByItemExpirationDateIs(date);
    model.addAttribute("items", items);
    model.addAttribute(ItemController.MSG, String.format(ItemController.ITEMS_FOUND, items.size()));
    return "listItems";
  }

  @PostMapping("/filter-by-creation-date")
  public String filterByCreationDate(
      @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date date,
      Model model) {
    List<Item> items = itemService.filterByItemCreationDateIs(date);
    model.addAttribute("items", items);
    model.addAttribute(ItemController.MSG, String.format(ItemController.ITEMS_FOUND, items.size()));
    return "listItems";
  }
}
