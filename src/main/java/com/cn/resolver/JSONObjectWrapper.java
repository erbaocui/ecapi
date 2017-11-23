package com.cn.resolver;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by home on 2017/11/23.
 */
public class JSONObjectWrapper {
    private JSONObject jsonObject;

    public JSONObjectWrapper(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJSONObject() {
        return jsonObject;
    }
}