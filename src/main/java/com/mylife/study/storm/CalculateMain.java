package com.mylife.study.storm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

/**
 * Created by zhangyong on 14-9-23.
 * <p/>
 * 算奖相关的main函数
 */
public class CalculateMain {

    public static void main(String[] args) {
        try {
            //加载spring驱动
            SpringContainer springContainer = new SpringContainer();
            springContainer.start();

            //加载storm topology
            TopologyContainer topologyContainer = new TopologyContainer();
            topologyContainer.start();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
