package com.matejkala.inventory;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// Doesn't start the server but only the layer below that,
// where Spring handles the incoming HTTP request and hands it off to your controller.
// The full Spring application context is started but without the server.
@AutoConfigureMockMvc
@SpringBootTest
public class WithoutServerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldReturnDefaultMessage() throws Exception {
    this.mockMvc.perform(get("/borrower/list")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Borrowers")));
  }
}
