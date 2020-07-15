package com.star.account.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;

/**
 * @author zx
 * @date 2020/7/12
 */
@Slf4j
public class OKHTTPUtil {

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    private static final OkHttpClient client = new OkHttpClient();

    public static String post(String url, String params) {
        try {
            log.info(" 请求数据:{} ", params);
            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, params))
                    .build();

            Response response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(" 请求异常 ", e);

        } finally {
        }
        return "";
    }

    public static JSONObject postToJSON(String url, String params) {
        try {
            log.info(" 请求数据:{} ", params);
            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, params))
                    .build();

            Response response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);

            String result = response.body().string();
            String b = StringEscapeUtils.unescapeJava(result);
            JSONObject jsonObject = JSONObject.parseObject(b);
            log.info(" TradeCalJob Result :{}", jsonObject.toJSONString());
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            log.error(" 请求异常 ", e);

        } finally {
        }
        return null;
    }
}
