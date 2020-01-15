package com.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gmall.beans.PmsSkuAttrValue;
import com.gmall.beans.PmsSkuImage;
import com.gmall.beans.PmsSkuInfo;
import com.gmall.beans.PmsSkuSaleAttrValue;
import com.gmall.manager.mapper.SkuAttrValueMapper;
import com.gmall.manager.mapper.SkuImageMapper;
import com.gmall.manager.mapper.SkuInfoMapper;
import com.gmall.manager.mapper.SkuSaleAttrValueMapper;
import com.gmall.service.SkuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    SkuInfoMapper skuInfoMapper;

    @Autowired
    SkuImageMapper skuImageMapper;

    @Autowired
    SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    @Transactional
    public String saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        //处理默认图片
        String skuDefaultImg = pmsSkuInfo.getSkuDefaultImg();
        if (StringUtils.isBlank(skuDefaultImg)){
            pmsSkuInfo.setSkuDefaultImg(pmsSkuInfo.getSkuImageList().get(0).getImgUrl());
        }
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());
        //插入pmsSkuInfo
        skuInfoMapper.insertSelective(pmsSkuInfo);
        String skuId = pmsSkuInfo.getId();
        System.out.println(skuId);
        //平台销售
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuId);
            skuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }
        //插入销售属性关联
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuId);
            skuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }
        //插入图片
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(skuId);
            skuImageMapper.insertSelective(pmsSkuImage);
        }
        return "success";
    }
}
