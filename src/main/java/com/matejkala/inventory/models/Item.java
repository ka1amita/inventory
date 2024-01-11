package com.matejkala.inventory.models;


import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.TemporalType.DATE;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "items") // defaults to item
public class Item {
  @Id
  @GeneratedValue(strategy = AUTO)
  private long id;
  private String title;
  private String description;
  private boolean toxic;
  private boolean enabled;
  @Temporal(value = DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date creationDate;
  @Temporal(value = DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date expirationDate;
  @ManyToOne(fetch = LAZY)
  //@JoinColumn(name = "borrower_id") // the default
  private Borrower borrower;

  public Item() {
  }

  public Item(String title,
      String description,
      boolean toxic,
      boolean enabled,
      Date creationDate,
      Date expirationDate,
      Borrower borrower) {
    this.title = title;
    this.description = description;
    this.toxic = toxic;
    this.enabled = enabled;
    this.creationDate = creationDate;
    this.expirationDate = expirationDate;
    this.borrower = borrower;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date dueDate) {
    this.expirationDate = dueDate;
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

  public boolean isToxic() {
    return toxic;
  }

  public void setToxic(boolean toxic) {
    this.toxic = toxic;
  }

  public Borrower getBorrower() {
    return borrower;
  }

  public void setBorrower(Borrower borrower) {
    this.borrower = borrower;
  }
}