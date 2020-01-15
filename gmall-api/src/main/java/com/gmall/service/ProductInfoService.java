package com.gmall.service;

import com.gmall.beans.PmsProductImage;
import com.gmall.beans.PmsProductInfo;
import com.gmall.beans.PmsProductSaleAttr;

import java.util.List;

public interface ProductInfoService {
    List<PmsProductInfo> getSpuList(String catalog3Id);

    String saveSpuInfo(PmsProductInfo pmsBaseAttrInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);
}
