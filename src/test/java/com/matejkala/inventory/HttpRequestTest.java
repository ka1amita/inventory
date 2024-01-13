package com.matejkala.inventory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void responds_with_item_list() throws Exception {
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/item/list",
                                              String.class))
        .contains("Items");
  }

  @Test
  void responds_with_borrower_list() throws Exception {
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/borrower/list",
                                              String.class))
        .contains("Borrowers");
  }
}
