package com.disney;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.disney.model.Ticket;
import com.disney.restclient.TicketProviderClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "TicketProvider")
class TicketProviderClientTest {

  @Autowired TicketProviderClient ticketProviderClient;

  @Pact(consumer = "InParkService")
  public RequestResponsePact retrieveAllTickets(PactDslWithProvider provider) {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");
    return provider
        .given("Tickets exist")
        .uponReceiving("get all Tickets")
        .path("/tickets")
        .method("GET")
        .willRespondWith()
        .status(200)
        .headers(headers)
        .body(
            "[{"
                + "  \"ticketVisualId\": \"000000001\","
                + "  \"guestName\": \"Micky\","
                + "  \"type\": \"annual-pass\""
                + "},"
                + "{"
                + "  \"ticketVisualId\": \"000000002\","
                + "  \"guestName\": \"Micky\","
                + "  \"type\": \"pass-en-scene\""
                + "}]")
        .toPact();
  }

  @Test
  @PactTestFor(pactMethod = "retrieveAllTickets", port = "9999")
  void testRetrieveAllTickets(MockServer mockServer) {
    // given
    ticketProviderClient.ticketProviderUrl = mockServer.getUrl();

    // when
    List<Ticket> response = ticketProviderClient.retrieveAllTickets();

    // then
    Assertions.assertThat(response)
        .hasSize(2)
        .extracting("ticketVisualId", "guestName", "type")
        .containsExactlyInAnyOrder(
            Tuple.tuple("000000001", "Micky", "annual-pass"),
            Tuple.tuple("000000002", "Micky", "pass-en-scene"));
  }
}
