package com.matejkala.inventory.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.matejkala.inventory.dtos.AddBorrowerDto;
import com.matejkala.inventory.dtos.EditBorrowerDto;
import com.matejkala.inventory.exceptions.BorrowerIdNotFound;
import com.matejkala.inventory.models.Borrower;
import com.matejkala.inventory.services.BorrowerService;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@WebMvcTest(controllers = BorrowerController.class)
class BorrowerControllerWebLayerTest {

  @Autowired
  private MockMvc mvc;
  @MockBean
  private BorrowerService borrowerService;
  @MockBean
  private ModelMapper modelMapper;
  private Borrower found;

  @BeforeEach
  void setUp() {
    found = new Borrower(1L, "borrower", "borrower@email.com", true, new ArrayList<>());
  }

  @Test
  void when_click_detail_button_then_takes_to_detail_page_with_capital_name_in_title()
      throws Exception {

    when(borrowerService.findBorrower(any(Borrower.class))).thenReturn(found);

    mvc.perform(clickDetailButton())
        .andDo(print())
        .andExpect(content().string(containsString(titleOf("Borrower Borrower"))))
        .andExpect(status().isOk());
  }

  private MockHttpServletRequestBuilder clickDetailButton() {
    return get("/borrower").queryParam("id", "1");
  }

  @Test
  void when_click_add_button_then_takes_to_add_borrower_form() throws Exception {

    mvc.perform(clickAddButton())
        .andDo(print())
        .andExpect(content().string(containsString(titleOf("Add Borrower"))))
        .andExpect(status().isOk());
  }

  private MockHttpServletRequestBuilder clickAddButton() {
    return get("/borrower/add");
  }

  @Test
  void when_submit_add_borrower_form_then_redirects_to_list_with_flash_message() throws Exception {

    when(borrowerService.enableAndSaveBorrower(any(Borrower.class))).thenReturn(found);
    when(modelMapper.map(any(AddBorrowerDto.class),any(Class.class))).thenReturn(found);

    mvc.perform(submitAddForm())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("message", containsString("Added")))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/list")).isTrue());
  }

  private MockHttpServletRequestBuilder submitAddForm() {
    return post("/borrower/add")
        .param("id", "0")
        .param("name", "Name")
        .param("email", "name@email.com")
        .param("enabled", "true");
  }

  @Test
  void when_submit_empty_add_borrower_form_then_responds_with_flash_error_message()
      throws Exception {

    when(borrowerService.enableAndSaveBorrower(any(Borrower.class))).thenReturn(found);
    EditBorrowerDto foundDto = new EditBorrowerDto();
    when(modelMapper.map(any(Borrower.class), any(Class.class))).thenReturn(foundDto);

    mvc.perform(submitEmptyAddForm())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("error", containsString("Email is empty!")))
        .andExpect(flash().attribute("error", containsString("Name is empty!")))
        .andExpect(flash().attribute("borrower", isA(AddBorrowerDto.class)))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/add")).isTrue());
  }

  private MockHttpServletRequestBuilder submitEmptyAddForm() {
    return post("/borrower/add")
        .param("name", "")
        .param("email", "");
  }

  @Test
  void when_submit_add_borrower_form_with_empty_name_then_responds_with_flash_error_message()
      throws Exception {

    when(borrowerService.enableAndSaveBorrower(any(Borrower.class))).thenReturn(found);
    EditBorrowerDto foundDto = new EditBorrowerDto();
    when(modelMapper.map(any(Borrower.class), any(Class.class))).thenReturn(foundDto);

    mvc.perform(submitAddFormWithEmptyName())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("error", containsString("Name is empty!")))
        .andExpect(flash().attribute("borrower", isA(AddBorrowerDto.class)))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/add")).isTrue());
  }

  private MockHttpServletRequestBuilder submitAddFormWithEmptyName() {
    return post("/borrower/add")
        .param("name", "")
        .param("email", "name@email.com");
  }

  @Test
  void when_submit_add_borrower_form_with_empty_email_then_responds_with_flash_error_message()
      throws Exception {

    when(borrowerService.enableAndSaveBorrower(any(Borrower.class))).thenReturn(found);
    EditBorrowerDto foundDto = new EditBorrowerDto();
    when(modelMapper.map(any(Borrower.class), any(Class.class))).thenReturn(foundDto);

    mvc.perform(submitAddFormWithEmptyEmail())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("error", containsString("Email is empty!")))
        .andExpect(flash().attribute("borrower", isA(AddBorrowerDto.class)))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/add")).isTrue());
  }

  private MockHttpServletRequestBuilder submitAddFormWithEmptyEmail() {
    return post("/borrower/add")
        .param("name", "name")
        .param("email", "");
  }

  @Test
  void when_submit_add_borrower_form_with_invalid_name_then_responds_with_flash_error_message()
      throws Exception {

    mvc.perform(submitAddFormWithInvalidName())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("error", containsString("Provided name 'invalid$' is invalid!")))
        .andExpect(flash().attribute("borrower", isA(AddBorrowerDto.class)))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/add")).isTrue());
  }

  private MockHttpServletRequestBuilder submitAddFormWithInvalidName() {
    return post("/borrower/add")
        .param("name", "invalid$")
        .param("email", "name@email.com");
  }

  @Test
  void when_submit_add_borrower_form_with_invalid_email_then_responds_with_flash_error_message()
      throws Exception {

    mvc.perform(submitAddFormWithInvalidEmail())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("error", containsString("Provided email 'invalid' is invalid!")))
        .andExpect(flash().attribute("borrower", isA(AddBorrowerDto.class)))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/add")).isTrue());
  }

  private MockHttpServletRequestBuilder submitAddFormWithInvalidEmail() {
    return post("/borrower/add")
        .param("name", "name")
        .param("email", "invalid");
  }

  @Test
  void when_submit_add_borrower_form_with_invalid_name_and_email_then_responds_with_flash_error_message()
      throws Exception {

    mvc.perform(submitAddFormWithInvalidNameAndEmail())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("error", containsString("Provided name 'invalid$' is invalid!")))
        .andExpect(flash().attribute("error", containsString("Provided email 'invalid' is invalid!")))
        .andExpect(flash().attribute("borrower", isA(AddBorrowerDto.class)))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/add")).isTrue());
  }

  private MockHttpServletRequestBuilder submitAddFormWithInvalidNameAndEmail() {
    return post("/borrower/add")
        .param("name", "invalid$")
        .param("email", "invalid");
  }

  @Test
  void when_click_edit_button_then_takes_to_edit_borrower_form() throws Exception {

    when(borrowerService.findBorrower(any(Borrower.class))).thenReturn(found);
    Borrower findBorrower = new Borrower(1L, null, null, false, null);
    when(modelMapper.map(any(EditBorrowerDto.class), any(Class.class))).thenReturn(findBorrower);
    EditBorrowerDto foundDto = new EditBorrowerDto(1L, "name", "name@email.com", true);
    when(modelMapper.map(any(Borrower.class), any(Class.class))).thenReturn(foundDto);

    mvc.perform(clickEditButton())
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(titleOf("Edit Borrower"))));
  }

  private MockHttpServletRequestBuilder clickEditButton() {
    return get("/borrower/edit").queryParam("id", "1");
  }

  @Test
  void when_click_edit_button_with_id_not_found_then_takes_to_borrower_list() throws Exception {

    when(modelMapper.map(any(EditBorrowerDto.class), any(Class.class))).thenReturn(new Borrower());
    when(borrowerService.findBorrower(any(Borrower.class))).thenThrow(new BorrowerIdNotFound(1L));

    mvc.perform(clickEditButton())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("error", containsString("Borrower ID not found: \"1\"")))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/list")).isTrue());
  }

  @Test
  void when_submit_edit_borrower_form_then_redirects_to_list_with_flash_message() throws Exception {

    when(borrowerService.findAndSaveBorrowerAsIs(any(Borrower.class))).thenReturn(found);
    Borrower findBorrower = new Borrower(1L, "Name", "name@email.com", true, null);
    when(modelMapper.map(any(EditBorrowerDto.class), any(Class.class))).thenReturn(findBorrower);
    EditBorrowerDto foundDto = new EditBorrowerDto(1L, "name", "name@email.com", true);
    when(modelMapper.map(any(Borrower.class), any(Class.class))).thenReturn(foundDto);

    mvc.perform(submitEditForm())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("message", containsString("Edited")))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/list")).isTrue());
  }

  private MockHttpServletRequestBuilder submitEditForm() {
    return post("/borrower/edit")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "1")
        .param("name", "Name")
        .param("email", "name@email.com")
        .param("enabled", "true");
  }

  @Test
  void when_submit_edit_borrower_form_with_invalid_name_then_responds_with_error_message_and_the_keeps_the_data()
      throws Exception {

    mvc.perform(submitEditFormWithInvalidName())
        .andDo(print())
        .andExpect(model().attribute("error", containsString("Provided name 'invalid$' is invalid!")))
        .andExpect(model().attribute("borrower", isA(EditBorrowerDto.class)))
        .andExpect(content().string(containsString(titleOf("Edit Borrower"))));
  }

  private MockHttpServletRequestBuilder submitEditFormWithInvalidName() {
    return post("/borrower/edit")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "1")
        .param("name", "invalid$")
        .param("email", "name@email.com")
        .param("enabled", "true");
  }

  @Test
  void when_submit_edit_borrower_form_with_invalid_email_then_responds_with_error_message_and_the_keeps_the_data()
      throws Exception {

    mvc.perform(submitEditFormWithInvalidEmail())
        .andDo(print())
        .andExpect(model().attribute("error", containsString("Provided email 'invalid' is invalid!")))
        .andExpect(model().attribute("borrower", isA(EditBorrowerDto.class)))
        .andExpect(content().string(containsString(titleOf("Edit Borrower"))));
  }

  private MockHttpServletRequestBuilder submitEditFormWithInvalidEmail() {
    return post("/borrower/edit")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "1")
        .param("name", "Name")
        .param("email", "invalid")
        .param("enabled", "true");
  }

  @Test
  void when_submit_edit_borrower_form_with_invalid_name_and_email_then_responds_with_error_message_and_the_keeps_the_data()
      throws Exception {

    mvc.perform(submitEditFormWithInvalidNameAndEmail())
        .andDo(print())
        .andExpect(model().attribute("error", containsString("Provided name 'invalid$' is invalid!")))
        .andExpect(model().attribute("error", containsString("Provided email 'invalid' is invalid!")))
        .andExpect(model().attribute("borrower", isA(EditBorrowerDto.class)))
        .andExpect(content().string(containsString(titleOf("Edit Borrower"))));
  }

  private MockHttpServletRequestBuilder submitEditFormWithInvalidNameAndEmail() {
    return post("/borrower/edit")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "1")
        .param("name", "invalid$")
        .param("email", "invalid")
        .param("enabled", "true");
  }


  @Test
  void when_submit_edit_borrower_form_with_id_not_found_then_responds_with_error_message_and_redirects_to_borrower_list()
      throws Exception {

    when(modelMapper.map(any(EditBorrowerDto.class), any(Class.class))).thenReturn(new Borrower());
    when(borrowerService.findAndSaveBorrowerAsIs(any(Borrower.class))).thenThrow(new BorrowerIdNotFound(1L));

    mvc.perform(submitEditForm())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("error", containsString("Borrower ID not found: \"1\"")))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/list")).isTrue());
  }

  @Test
  void when_click_delete_button_then_redirects_to_list_with_flash_message() throws Exception {

    when(borrowerService.findDisableAndSaveBorrower(any(Borrower.class))).thenReturn(found);
    mvc.perform(clickDeleteButton())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("message", containsString("Deleted")))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/list")).isTrue());
  }

  private MockHttpServletRequestBuilder clickDeleteButton() {
    return post("/borrower/delete")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "1");
  }

  @Test
  void when_click_delete_button_with_id_not_found_then_takes_to_borrower_list() throws Exception {

    when(borrowerService.findDisableAndSaveBorrower(any(Borrower.class))).thenThrow(new BorrowerIdNotFound(1L));
    mvc.perform(clickDeleteButton())
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(flash().attribute("error", containsString("Borrower ID not found: \"1\"")))
        .andDo(
            r -> assertThat(r.getResponse().getRedirectedUrl().endsWith("borrower/list")).isTrue());
  }

  private static String titleOf(String title) {
    return String.format("<title>%s</title>",title);
  }
}
