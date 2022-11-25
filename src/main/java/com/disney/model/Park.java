package com.disney.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Park {
  private List<Ticket> tickets;
}
