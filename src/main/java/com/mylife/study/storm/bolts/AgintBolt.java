package com.mylife.study.storm.bolts;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.elong.pb.common.util.security.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by zhangyong on 14/10/30.
 * 方案相关
 */
public class AgintBolt extends BaseBasicBolt{

    private static Logger log = LoggerFactory.getLogger(AgintBolt.class);

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String word = input.getString(0);
        log.info("agintBolt:" + word);
        collector.emit(new Values(MD5Util.md5Hex(word)));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }

}
