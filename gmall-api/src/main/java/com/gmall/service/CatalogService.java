package com.gmall.service;

import com.gmall.beans.PmsBaseCatalog1;
import com.gmall.beans.PmsBaseCatalog2;
import com.gmall.beans.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService{
    List<PmsBaseCatalog1> getAllCatalog1();

    List<PmsBaseCatalog2> getCatalog2ById(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3ById(String catalog2Id);
}
