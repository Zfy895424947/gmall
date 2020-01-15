package com.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gmall.beans.PmsProductImage;
import com.gmall.beans.PmsProductInfo;
import com.gmall.beans.PmsProductSaleAttr;
import com.gmall.service.ProductInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class ProductInfoController {

    @Reference
    ProductInfoService productInfoService;

    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> getSpuList(@RequestParam("catalog3Id") String catalog3Id) {
        return productInfoService.getSpuList(catalog3Id);
    }

    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsBaseAttrInfo) {
        return productInfoService.saveSpuInfo(pmsBaseAttrInfo);
    }

    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        return productInfoService.spuSaleAttrList(spuId);
    }

    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId) {
        return productInfoService.spuImageList(spuId);
    }
}
