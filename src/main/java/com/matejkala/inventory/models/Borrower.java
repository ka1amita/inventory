package com.matejkala.inventory.models;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.AUTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "borrowers") // defaults to borrower
public class Borrower {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;
  private String name;
  private String email;
  private boolean enabled;

  @OneToMany(mappedBy = "borrower", fetch = LAZY, cascade = ALL)
  private List<Item> items;

  public Borrower() {
  }

  public Borrower(String name, String email, boolean enabled) {
    this(0L, name, email, enabled, new ArrayList<>());
  }

  public Borrower(Long id, String name, String email, boolean enabled, List<Item> items) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.enabled = enabled;
    this.items = this.items;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
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

  @Override
  public String toString() {
    return name;
  }
}
