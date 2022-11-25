package com.disney.service;

import com.disney.model.Park;
import com.disney.restclient.TicketProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InParkService {

  private TicketProviderClient ticketProviderClient;

  @Autowired
  public InParkService(TicketProviderClient ticketProviderClient) {
    this.ticketProviderClient = ticketProviderClient;
  }

  public Park fetchInParkTickets() {
    return new Park(ticketProviderClient.retrieveAllTickets());
  }
}
