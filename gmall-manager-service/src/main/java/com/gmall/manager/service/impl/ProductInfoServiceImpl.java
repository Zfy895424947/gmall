package com.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gmall.beans.PmsProductImage;
import com.gmall.beans.PmsProductInfo;
import com.gmall.beans.PmsProductSaleAttr;
import com.gmall.beans.PmsProductSaleAttrValue;
import com.gmall.manager.mapper.ProductImageMapper;
import com.gmall.manager.mapper.ProductInfoMapper;
import com.gmall.manager.mapper.ProductSaleAttrMapper;
import com.gmall.manager.mapper.ProductSaleAttrValueMapper;
import com.gmall.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    ProductImageMapper productImageMapper;

    @Autowired
    ProductSaleAttrMapper productSaleAttrMapper;

    @Autowired
    ProductSaleAttrValueMapper productSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> getSpuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return productInfoMapper.select(pmsProductInfo);
    }

    @Override
    @Transactional
    public String saveSpuInfo(PmsProductInfo pmsBaseAttrInfo) {
        //添加PmsProductInfo
        productInfoMapper.insertSelective(pmsBaseAttrInfo);
        //添加PmsProductImage
        List<PmsProductImage> spuImageList = pmsBaseAttrInfo.getSpuImageList();
        for (PmsProductImage pmsProductImage : spuImageList) {
            pmsProductImage.setProductId(pmsBaseAttrInfo.getId());
            productImageMapper.insertSelective(pmsProductImage);
        }
        //添加PmsProductSaleAttr
        List<PmsProductSaleAttr> spuSaleAttrList = pmsBaseAttrInfo.getSpuSaleAttrList();
        for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
            pmsProductSaleAttr.setProductId(pmsBaseAttrInfo.getId());
            productSaleAttrMapper.insertSelective(pmsProductSaleAttr);
            //添加PmsProductSaleAttrValue
            List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList) {
                pmsProductSaleAttrValue.setProductId(pmsBaseAttrInfo.getId());
                productSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            }
        }
        return "success";
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(spuId);
        List<PmsProductSaleAttr> productSaleAttrs = productSaleAttrMapper.select(pmsProductSaleAttr);
        for (PmsProductSaleAttr productSaleAttr : productSaleAttrs) {
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(pmsProductSaleAttr.getProductId());
            pmsProductSaleAttrValue.setSaleAttrId(productSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> saleAttrValues = productSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            productSaleAttr.setSpuSaleAttrValueList(saleAttrValues);
        }
        return productSaleAttrs;
    }

    @Override
    public List<PmsProductImage> spuImageList(String spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        return productImageMapper.select(pmsProductImage);
    }
}
