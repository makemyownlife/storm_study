package com.mylife.study.storm.bolts.group;

import backtype.storm.generated.GlobalStreamId;
import backtype.storm.grouping.CustomStreamGrouping;
import backtype.storm.task.WorkerTopologyContext;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyong on 14/11/1.
 * 定制分组方法 比如某个固定的方案在执行某个task
 */
public class HashCustomStreamGrouping implements CustomStreamGrouping, Serializable {

    private static Logger logger = LoggerFactory.getLogger(HashCustomStreamGrouping.class);

    //总数目
    private List<Integer> taskIdList = null;

    @Override
    public void prepare(WorkerTopologyContext workerTopologyContext, GlobalStreamId globalStreamId, List<Integer> taskIdList) {
        this.taskIdList = taskIdList;
        logger.info("可执行task数目:" + taskIdList.size() + " integers==" + taskIdList);
    }

    @Override
    public List<Integer> chooseTasks(int i, List<Object> objects) {
        logger.info("objects:" + objects);
        List<Integer> boltIds = new ArrayList();
        if (CollectionUtils.isNotEmpty(objects)) {
            //相同hash的string才会进入同一个bolt
            String value = objects.get(0).toString();
            int mod = Math.abs(value.hashCode() % taskIdList.size());
            logger.info("mod:" + mod);
            boltIds.add(taskIdList.get(mod));
        }
        return boltIds;
    }
}
