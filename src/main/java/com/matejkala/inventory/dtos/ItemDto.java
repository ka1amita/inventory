package com.matejkala.inventory.dtos;

import com.matejkala.inventory.models.Borrower;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class ItemDto {

  private final Long id;
  private final String title;
  private final String description;
  private final boolean toxic;
  private final boolean enabled;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private final Date creationDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private final Date expirationDate;
  private final Borrower borrower;

  public ItemDto(Long id,
      String title,
      String description,
      boolean toxic,
      boolean enabled,
      Date creationDate,
      Date expirationDate,
      Borrower borrower) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.toxic = toxic;
    this.enabled = enabled;
    this.creationDate = creationDate;
    this.expirationDate = expirationDate;
    this.borrower = borrower;
  }

  public Long getId() {
    return id;
  }


  public String getTitle() {
    return title;
  }


  public String getDescription() {
    return description;
  }


  public Date getCreationDate() {
    return creationDate;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }


  public boolean isEnabled() {
    return enabled;
  }

  public boolean isToxic() {
    return toxic;
  }

  public Borrower getBorrower() {
    return borrower;
  }
}
