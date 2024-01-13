package com.matejkala.inventory.controllers;

import static com.matejkala.inventory.controllers.ModelAttributeNames.BORROWER;
import static com.matejkala.inventory.controllers.ModelAttributeNames.ERR;

import com.matejkala.inventory.dtos.AddBorrowerDto;
import com.matejkala.inventory.dtos.BorrowerDTO;
import com.matejkala.inventory.dtos.EditBorrowerDto;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ValidationAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  String handleInvalidMethodArgument(MethodArgumentNotValidException e,
      Model model,
      RedirectAttributes redirect) {
    Director<?, ?> director = Director.of(e, model, redirect);
    director.addAttributes();
    return director.getDestination();
  }

  private static abstract class Director<T extends BorrowerDTO, M extends Model> {

    private static final String DEFAULT_REDIRECTION = "redirect:";
    protected String destination;
    protected M model;
    protected String attributeName;
    protected T target;
    protected String errAttributeName;
    protected ValidationErrors validationErrors;

    protected Director(String destination, M model, String attributeName,
        MethodArgumentNotValidException e) {
      this.destination = destination;
      this.model = model;
      this.attributeName = attributeName;
      this.target = (T) e.getTarget(); // cast checked in the static factory method
      this.errAttributeName = ERR;
      this.validationErrors = ValidationErrors.of(e);
    }

    static Director<?, ?> of(MethodArgumentNotValidException e,
        Model model,
        RedirectAttributes redirect) {

      Director<?, ?> director;
      if (e.getTarget() instanceof AddBorrowerDto) {
        director = new AddBorrowerDirector("redirect:/borrower/add", redirect, e);
      } else if (e.getTarget() instanceof EditBorrowerDto) {
        director = new EditBorrowerDirector("editBorrower", model, e);
      } else {
        director = new DefaultDirector(DEFAULT_REDIRECTION, redirect, e);
      }
      return director;
    }

    String getDestination() {
      return destination;
    }

    abstract void addAttributes();
  }

  private static abstract class BorrowerDirector<T extends BorrowerDTO, M extends Model> extends Director<T, M> {


    protected BorrowerDirector(String destination, M model, MethodArgumentNotValidException e) {
      super(destination, model, BORROWER, e);
    }
  }

  private static class AddBorrowerDirector extends
      BorrowerDirector<AddBorrowerDto, RedirectAttributes> {

    AddBorrowerDirector(String destination, RedirectAttributes redirect,
        MethodArgumentNotValidException e) {
      super(destination, redirect, e);
    }

    @Override
    void addAttributes() {
      model.addFlashAttribute(attributeName, target);
      model.addFlashAttribute(errAttributeName, validationErrors.getErrorAttribute());
    }
  }

  private static class EditBorrowerDirector extends BorrowerDirector<EditBorrowerDto, Model> {

    EditBorrowerDirector(String destination, Model model, MethodArgumentNotValidException e) {
      super(destination, model, e);
    }

    @Override
    void addAttributes() {
      model.addAttribute(attributeName, target);
      model.addAttribute(errAttributeName, validationErrors.getErrorAttribute());
    }
  }

  private static class DefaultDirector extends Director<BorrowerDTO, RedirectAttributes> {

    protected DefaultDirector(String destination, RedirectAttributes redirect,
        MethodArgumentNotValidException e) {
      super(destination, redirect, "", e);
    }

    @Override
    void addAttributes() {
      model.addFlashAttribute(attributeName, target);
      model.addFlashAttribute(errAttributeName, validationErrors.getErrorAttribute());
    }
  }

  private static class ValidationErrors {

    private final List<String> errors;

    private ValidationErrors(List<String> errors) {
      this.errors = errors;
    }

    static ValidationErrors of(MethodArgumentNotValidException ex) {
      List<String> messages =
          ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
      return new ValidationErrors(messages);
    }

    public String getErrorAttribute() {
      return errors.stream().reduce("", (a, b) -> String.join(" ", a, b));
    }
  }
}
