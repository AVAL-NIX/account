package com.star.account.job.Filter;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.star.account.common.utils.OKHTTPUtil;
import com.star.account.common.utils.TSUtil;
import com.star.account.model.entity.TsAccount;
import com.star.account.service.TsAccountService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: // TODO
 * <br>
 * @date: 2019/10/30 10:55
 * @author: zhengxin
 * @version: mall V1.0
 * @since: JDK 1.8
 */
@Component
public class TushareFilter {


    @Autowired
    TsAccountService tsAccountService;

    @Scheduled(cron = "0 15 10 ? * *")
    public void run() {
        LOGGER.info(" taks start ");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.isNotNull("token");
        List<TsAccount> list = tsAccountService.list(queryWrapper);
        for (TsAccount tsAccount : list) {
            if (StringUtils.isBlank(tsAccount.getToken())) {
                continue;
            }
            try {
                Map<String, String> paramsMap = new HashMap<>();
                paramsMap.put("list_status", "L");
                JSONObject map = TSUtil.buidJSON("stock_basic", tsAccount.getToken());
                map.put("params", paramsMap);
                map.put("fields", "");
                String a = OKHTTPUtil.post("http://api.waditu.com", map.toJSONString());
                String b = StringEscapeUtils.unescapeJava(a);
                JSONObject jsonObject = JSONObject.parseObject(b);
                LOGGER.info("jsonObject:" + jsonObject.toString());
                tsAccount.setCount(tsAccount.getCount()+1);
                tsAccount.updateById();
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e);
            }
        }
        LOGGER.info(" taks end ");
    }


    public static final Logger LOGGER = LogManager.getLogger(TushareFilter.class);

}
