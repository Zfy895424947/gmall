package com.gmall.manager.mapper;

import com.gmall.beans.PmsBaseAttrInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AttrInfoMapper extends Mapper<PmsBaseAttrInfo> {

    List<PmsBaseAttrInfo> getAttrValueList(String attrId);

}
