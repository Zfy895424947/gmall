package com.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gmall.beans.PmsBaseSaleAttr;
import com.gmall.manager.mapper.BaseSaleAttrMapper;
import com.gmall.service.BaseSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BaseSaleAttrServiceImpl implements BaseSaleAttrService {

    @Autowired
    BaseSaleAttrMapper baseSaleAttrMapper;

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }
}
