package com.matejkala.todoapp.todoApp.services;

import com.matejkala.todoapp.todoApp.models.NoSuchAssigneeException;
import com.matejkala.todoapp.todoApp.models.Assignee;
import com.matejkala.todoapp.todoApp.repositories.AssigneeRepo;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssigneeServiceImp implements AssigneeService {

  private final AssigneeRepo assigneeRepo;

  @Autowired
  public AssigneeServiceImp(AssigneeRepo assigneeRepo) {
    this.assigneeRepo = assigneeRepo;
  }

  @Override
  public List<Assignee> findAll() {
    return assigneeRepo.findAll();
  }

  @Override
  public void save(Assignee assignee) {
    assigneeRepo.save(assignee);
  }

  @Override
  public void delete(Assignee assignee) {
    assigneeRepo.delete(assignee);
  }

  @Override
  public void deleteById(Long id) {
    assigneeRepo.deleteById(id);
  }

  @Override
  public void renameById(Long id) {
    if (assigneeRepo.findById(id).isPresent()) {
      Assignee assignee = assigneeRepo.findById(id).get();
      assigneeRepo.save(assignee);
    }
  }

  public Assignee findById(Long id) throws NoSuchElementException {
    return assigneeRepo.findById(id).orElseThrow(NoSuchAssigneeException::new);
  }

  public void setAssigneeName(Assignee renamedAssignee) {
    Assignee assignee = findById(renamedAssignee.getId());
    assignee.setName(renamedAssignee.getName());
    assigneeRepo.save(assignee);
  }
}
