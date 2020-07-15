package com.star.account.common.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zx
 * @date 2020/7/13
 */
public class TSUtil {

    public static JSONObject buidJSON(String api,String token){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_name", api);
        jsonObject.put("token", token);
        return jsonObject;
    }
}
