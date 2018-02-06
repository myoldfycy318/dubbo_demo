package com.lv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserAction
 *
 * @author Zhang ShanMin
 * @date 2016/3/28
 * @time 12:06
 */
@Controller
@RequestMapping("/test")
public class TestAction {


    @RequestMapping(value = "toJson")
    @ResponseBody
    public Object test() throws Exception {
        Map<String,String> s1 = new HashMap<String, String>();
        s1.put("serverCode1","serverName1");
        s1.put("serverCode2","serverName2");
        s1.put("serverCode3","serverName3");

        Map<String,Object> z1 = new HashMap<String, Object>();
        z1.put("zooName","区名1");
        z1.put("zooCode","区Code2");
        z1.put("zooServers",s1);

        Map<String,Object> z2 = new HashMap<String, Object>();
        z2.put("zooName","区名2");
        z2.put("zooCode","区Code2");
        z2.put("zooServers",s1);

        List zs = new ArrayList();
        zs.add(z1);
        zs.add(z2);

        Map map = new HashMap();
        map.put("zooServerList",zs);

        Map<String,String> feeCode1 = new HashMap<String, String>();
        feeCode1.put("chargePointCode","C001");
        feeCode1.put("chargePointName","计费点1");
        feeCode1.put("chargePointAmount","1000");
        Map<String,String> feeCode2 = new HashMap<String, String>();
        feeCode2.put("chargePointCode","C002");
        feeCode2.put("chargePointName","计费点2");
        feeCode2.put("chargePointAmount","2000");
        Map<String,String> feeCode3 = new HashMap<String, String>();
        feeCode3.put("chargePointCode","C003");
        feeCode3.put("chargePointName","计费点3");
        feeCode3.put("chargePointAmount","3000");

        List feeCodeList = new ArrayList();
        feeCodeList.add(feeCode1);
        feeCodeList.add(feeCode2);
        feeCodeList.add(feeCode3);

        map.put("chargePoints",feeCodeList);
//        return JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
        return map;

    }

}
