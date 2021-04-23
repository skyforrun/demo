package com.hgnu.study.psvm;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author zj
 * @Description
 * @Date 2021/4/16 11:45
 */
public class T {
    public static void main(String[] args) {
       String json = "{\"appeal\": \"pap\",\n" +
               "  \"idsnFrontUrl\": \"资金\",\n" +
               "  \"idsnBackUrl\": \"demoData\"}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        String appeal = jsonObject.getString("appeal");
        System.out.println(appeal);
    }
}
