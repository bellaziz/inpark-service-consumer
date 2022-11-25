package com.disney.controller;

import com.disney.model.Park;
import com.disney.service.InParkService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InParkServiceController {

  @Autowired
  InParkService inParkService;

  @ApiOperation(value = "Get all in park tickets")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
  @GetMapping(value = "/inpark-ticket", produces = MediaType.APPLICATION_JSON_VALUE)
  public Park retrieveAllTickets() {
    return inParkService.fetchInParkTickets();
  }
}
