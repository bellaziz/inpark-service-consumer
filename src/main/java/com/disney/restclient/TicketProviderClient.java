package com.disney.restclient;

import com.disney.model.Ticket;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TicketProviderClient {

  private RestTemplate restTemplate;

  @Value("${ticket.provider.base.url}")
  public String ticketProviderUrl;

  public TicketProviderClient() {
    this.restTemplate = new RestTemplate();
  }

  public List<Ticket> retrieveAllTickets() {
    return Arrays.asList(
        Objects.requireNonNull(restTemplate.getForObject(ticketProviderUrl+"/tickets", Ticket[].class)));
  }
}
