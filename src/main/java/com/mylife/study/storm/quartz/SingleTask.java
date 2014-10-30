package com.mylife.study.storm.quartz;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 混合过关算奖线程(单线程处理 为了安全角度出发)
 * User: zhangyong
 * Date: 13-1-9
 * Time: 上午11:58
 * To change this template use File | Settings | File Templates.
 */
public class SingleTask extends Thread {

    private static Logger logger = Logger.getLogger(SingleTask.class);

    private final BlockingQueue<Runnable> writeQueue = new LinkedBlockingQueue<Runnable>();

    public void init() {
        logger.info("启动任务单线程");
        super.setName("SingleTask");
        super.start();
    }

    public void run() {
        Runnable task = null;
        for (; ; ) {
            try {
                if ((task = writeQueue.take()) != null) {
                    task.run();
                }
            } catch (Throwable e) {
                logger.error("执行单任务异常", e);
            }
        }
    }

    public void postWrite(Runnable command) {
        writeQueue.offer(command);
    }

}
