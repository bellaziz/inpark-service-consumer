package com.disney.controller;

import com.disney.model.Catalogue;
import com.disney.service.CatalogueProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCatalogueController {

  @Autowired CatalogueProductService catalogueProductService;

  @ApiOperation(value = "Get all Catalogue product")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
  @GetMapping(value = "/catalogue", produces = MediaType.APPLICATION_JSON_VALUE)
  public Catalogue retrieveAllProducts() throws JsonProcessingException {
    return catalogueProductService.fetchCatalogue();
  }
}
