package com.star.account.job.Filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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


    @Scheduled(cron = "*/5 * * * * ?")
    public void run() {
        LOGGER.info(" taks start ");


        LOGGER.info(" taks end ");
    }



    public static final Logger LOGGER = LogManager.getLogger(TushareFilter.class);

}
