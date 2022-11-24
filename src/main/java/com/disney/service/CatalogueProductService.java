package com.disney.service;

import com.disney.model.Catalogue;
import com.disney.restclient.ProductProviderRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogueProductService {

  private ProductProviderRestClient productProviderRestClient;

  @Autowired
  public CatalogueProductService(ProductProviderRestClient productProviderRestClient) {
    this.productProviderRestClient = productProviderRestClient;
  }

  public Catalogue fetchCatalogue() {
    return new Catalogue(productProviderRestClient.retrieveAllProducts());
  }
}
