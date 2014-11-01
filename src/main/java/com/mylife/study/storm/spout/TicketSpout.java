package com.mylife.study.storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by zhangyong on 14/10/30.
 * 彩票相关的计算
 */
public class TicketSpout extends BaseRichSpout {

    private static Logger log = Logger.getLogger(TicketSpout.class);

    private SpoutOutputCollector collector;

    private volatile boolean end = false;

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        try {
            log.error("TicketSpout初始化开始");
            this.collector = spoutOutputCollector;
            log.error("TicketSpout初始化结束");
        } catch (Exception e) {
            log.error("TicketSpout初始化出错：", e);
        }
    }

    @Override
    public void nextTuple() {
        log.info("nextuple ka开始");
        if(!end) {
            collector.emit(new Values("mylife"), "mylife");
            end = true;
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
