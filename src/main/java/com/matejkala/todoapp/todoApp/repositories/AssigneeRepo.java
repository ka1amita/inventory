package com.matejkala.todoapp.todoApp.repositories;

import com.matejkala.todoapp.todoApp.models.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssigneeRepo extends JpaRepository<Assignee, Long> {
}
