package com.mylife.study.storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by zhangyong on 14/10/30.
 * 彩票相关的计算
 */
public class TicketSpout extends BaseRichSpout {

    private static Logger log = Logger.getLogger(TicketSpout.class);

    private SpoutOutputCollector collector;

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        try {
            log.error("TicketSpout初始化开始");
            this.collector = collector;
            log.error("TicketSpout初始化结束");
        } catch (Exception e) {
            log.error("TicketSpout初始化出错：", e);
        }
    }

    @Override
    public void nextTuple() {

    }

}
