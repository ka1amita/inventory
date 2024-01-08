package com.matejkala.todoapp.todoApp.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "todos")
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;
  private String description;
  private boolean urgent;
  private boolean done;
  @Temporal(value = TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date creationDate;
  @Temporal(value = TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dueDate;
  @ManyToOne
  private Assignee assignee;

  public Todo() {
  }

  public Todo(String title, String description, boolean urgent, boolean done,
      Date creationDate, Date dueDate, Assignee assignee) {
    this.title = title;
    this.description = description;
    this.urgent = urgent;
    this.done = done;
    this.creationDate = creationDate;
    this.dueDate = dueDate;
    this.assignee = assignee;
  }

  public Assignee getAssignee() {
    return assignee;
  }

  public void setAssignee(Assignee assignee) {
    this.assignee = assignee;
  }
  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public boolean isUrgent() {
    return urgent;
  }

  public void setUrgent(boolean urgent) {
    this.urgent = urgent;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }
}