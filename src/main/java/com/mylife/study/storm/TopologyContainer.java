package com.mylife.study.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import com.mylife.study.storm.bolts.AgintBolt;
import com.mylife.study.storm.bolts.BonusBolt;
import com.mylife.study.storm.spout.TicketSpout;

import java.util.concurrent.Executors;

/**
 * Created by zhangyong on 14/10/29.
 * <p/>
 * 算奖拓扑接口
 */
public class TopologyContainer {

    public void start() {
        TopologyBuilder builder = new TopologyBuilder();

        //得到ticket相关数据 队列中 或者其他的东西
        builder.setSpout("ticketSpout",new TicketSpout());

        //方案
        builder.setBolt("agintBolt", new AgintBolt()).shuffleGrouping("ticketSpout");

        //返奖
     //   builder.setBolt("bonusBolt" ,new BonusBolt()).shuffleGrouping("agintBolt");

        Config conf = new Config();
        conf.setDebug(false);
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("CalculateToplogy", conf, builder.createTopology());
        try {
            Thread.sleep(100000000);
        }catch (Exception e){
            e.printStackTrace();
        }
        cluster.shutdown();
    }

    public void stop() {

    }

}
