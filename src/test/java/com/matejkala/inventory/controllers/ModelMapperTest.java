package com.matejkala.inventory.controllers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.matejkala.inventory.dtos.AddBorrowerDto;
import com.matejkala.inventory.models.Borrower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ModelMapperTest {

  @Autowired
  private ModelMapper modelMapper;
  private AddBorrowerDto addBorrowerDto = new AddBorrowerDto();

  @BeforeEach
  void setUp() {
    addBorrowerDto.setName("name");
    addBorrowerDto.setEmail("email");
    addBorrowerDto.setEnabled(true);
  }

  @Test
  public void maps_add_borrower_form_to_borrower_model() {
    Borrower borrower = modelMapper.map(addBorrowerDto, Borrower.class);
    assertThat(borrower.getName(), is(addBorrowerDto.getName()));
    assertThat(borrower.getEmail(), is(addBorrowerDto.getEmail()));
    assertThat(borrower.isEnabled(), is(addBorrowerDto.isEnabled()));
  }
}
