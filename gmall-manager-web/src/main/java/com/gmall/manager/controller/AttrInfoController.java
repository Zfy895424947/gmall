package com.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gmall.beans.PmsBaseAttrInfo;
import com.gmall.beans.PmsBaseAttrValue;
import com.gmall.service.AttrInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class AttrInfoController {

    @Reference
    AttrInfoService attrInfoService;

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(@RequestParam("catalog3Id") String catalog3Id) {
        return attrInfoService.attrInfoList(catalog3Id);
    }

    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(@RequestParam("attrId") String attrId) {
        return attrInfoService.getAttrValueList(attrId);
    }
    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo) {
        String success = attrInfoService.saveAttrInfo(pmsBaseAttrInfo);
        return "success";
    }



}
