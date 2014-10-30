package com.mylife.study.storm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhangyong on 14/10/30.
 */
public class SpringContainer {

    private static final Logger logger = LoggerFactory.getLogger(SpringContainer.class);

    static ClassPathXmlApplicationContext context;

    public static ClassPathXmlApplicationContext getContext() {
        return context;
    }

    public void start() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.start();
    }

    public void stop() {
        try {
            if (context != null) {
                context.stop();
                context.close();
                context = null;
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }
}
