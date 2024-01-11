package com.matejkala.inventory.controllers;

import static java.lang.String.format;

import com.matejkala.inventory.services.BorrowerService;
import com.matejkala.inventory.exceptions.ItemIdNotFound;
import com.matejkala.inventory.models.Item;
import com.matejkala.inventory.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("item")
public class ItemController {

  static final String ADDED_MSG = "Added: \"%s\"";
  static final String DELETED_MSG = "Deleted: \"%s\"";
  static final String EDITED_MSG = "Edited: \"%s\"";
  static final String ITEMS_FOUND = "Found: %d items";
  static final String MSG = "message";
  private final ItemService itemService;
  private final BorrowerService borrowerService;

  @Autowired
  public ItemController(ItemService itemService, BorrowerService borrowerService) {
    this.itemService = itemService;
    this.borrowerService = borrowerService;
  }

  @GetMapping("")
  public String showItemDetail(@ModelAttribute final Item item, Model model)
      throws ItemIdNotFound {
    final Item found = itemService.findItem(item);
    model.addAttribute("item", found);
    return "itemDetail";
  }

  @GetMapping("add")
  public String getAddItemForm(Item item, Model model) {
    model.addAttribute("borrowers", borrowerService.findAllEnabled());
    return "addItem";
  }

  @PostMapping("add")
  public String addItem(@ModelAttribute final Item item, RedirectAttributes redirect) {
    final Item saved = itemService.enableAndSaveItem(item);
    redirect.addFlashAttribute(MSG, format(ADDED_MSG, saved.getTitle()));
    return "redirect:/item/list";
  }

  @GetMapping("edit")
  public String getEditItemForm(@ModelAttribute final Item item, Model model)
      throws ItemIdNotFound {
    final Item found = itemService.findItem(item);
    model.addAttribute("item", found);
    //TODO add with Home (Spot or Borrower) (Borrower can be null when not borrowed but Home can't)
    model.addAttribute("borrowers", borrowerService.findAllEnabled());
    return "editItem";
  }

  @PostMapping("edit")
  public String updateItem(@ModelAttribute final Item item, RedirectAttributes redirect)
      throws ItemIdNotFound {
    final Item saved = itemService.findAndSaveItemAsIs(item);
    redirect.addFlashAttribute(MSG, format(EDITED_MSG, saved.getTitle()));
    return "redirect:/item/list";
  }

  @PostMapping("delete")
  public String deleteItem(@ModelAttribute final Item item, RedirectAttributes redirect)
      throws ItemIdNotFound {
    final Item saved = itemService.findDisableAndSaveItem(item);
    redirect.addFlashAttribute(MSG, format(DELETED_MSG, saved.getTitle()));
    return "redirect:/item/list";
  }
}
