package com.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gmall.beans.PmsBaseAttrInfo;
import com.gmall.beans.PmsBaseAttrValue;
import com.gmall.manager.mapper.AttrInfoMapper;
import com.gmall.manager.mapper.BaseAttrValueMapper;
import com.gmall.service.AttrInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttrInfoServiceImpl implements AttrInfoService {

    @Autowired
    AttrInfoMapper attrInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> attrInfos = attrInfoMapper.select(pmsBaseAttrInfo);
        for (PmsBaseAttrInfo attrInfo : attrInfos) {
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(attrInfo.getId());
            List<PmsBaseAttrValue> baseAttrValues = baseAttrValueMapper.select(pmsBaseAttrValue);
            attrInfo.setAttrValueList(baseAttrValues);
        }
        return attrInfos;
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrInfo = new PmsBaseAttrValue();
        pmsBaseAttrInfo.setAttrId(attrId);
        return baseAttrValueMapper.select(pmsBaseAttrInfo);
    }

    @Override
    @Transactional
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {

        if (StringUtils.isBlank(pmsBaseAttrInfo.getId())) {
            //如果ID不为空则是添加
            attrInfoMapper.insertSelective(pmsBaseAttrInfo);
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        } else {
            //如果ID为空则是修改
            //修改属性
            Example example = new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id", pmsBaseAttrInfo.getId());
            attrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo, example);
            //先删除所有属性值在增加
            PmsBaseAttrValue baseAttrValue = new PmsBaseAttrValue();
            baseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
            baseAttrValueMapper.delete(baseAttrValue);
            //修改属性值
            List<PmsBaseAttrValue> valueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : valueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        }
        return "success";
    }
}
