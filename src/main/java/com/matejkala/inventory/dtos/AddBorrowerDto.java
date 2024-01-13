package com.matejkala.inventory.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddBorrowerDto extends BorrowerDTO{

  @Pattern(regexp = "\\w*", message = "Provided name '${validatedValue}' is invalid!")
  @Size(max = 255, message = "Name '${validatedValue}' must be shorter than {max}!")
  @NotEmpty(message = "Name is empty!")
  private String name;
  @Email(message = "Provided email '${validatedValue}' is invalid!")
  @Size(max = 255, message = "Email '${validatedValue}' must be shorter than {max}!")
  @NotEmpty(message = "Email is empty!")
  private String email;
  @NotNull(message = "Value of enabled is not set!")
  private boolean enabled;

  public AddBorrowerDto() {
  }

  public AddBorrowerDto(String name, String email, boolean enabled) {
    this.name = name;
    this.email = email;
    this.enabled = enabled;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void enable() {
    setEnabled(true);
  }

  public void disable() {
    setEnabled(false);
  }
}
