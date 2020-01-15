package com.gmall.service;

import com.gmall.beans.PmsBaseAttrInfo;
import com.gmall.beans.PmsBaseAttrValue;
import com.gmall.beans.PmsProductSaleAttr;

import java.util.List;

public interface AttrInfoService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

}
