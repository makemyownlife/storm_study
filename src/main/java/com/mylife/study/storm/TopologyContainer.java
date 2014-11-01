package com.mylife.study.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import com.mylife.study.storm.bolts.AgintBolt;
import com.mylife.study.storm.bolts.BonusBolt;
import com.mylife.study.storm.bolts.group.HashCustomStreamGrouping;
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

        //方案 每个方案的金额 若是不同的线程执行 对象是不一样的，还是会重新创建新的 bolt 对象

        //还有一个问题 就是 比如我起 两个agiintbolt 是每台机器两个？ 还是每个jvm两个？ 我需要弄明白
      //builder.setBolt("agintBolt", new AgintBolt(),2).shuffleGrouping("ticketSpout");
        builder.setBolt("agintBolt", new AgintBolt(),1).customGrouping("ticketSpout", new HashCustomStreamGrouping());

        //返奖 将金额写入到表中
        builder.setBolt("bonusBolt" ,new BonusBolt(),2).shuffleGrouping("agintBolt");

        Config conf = new Config();
        conf.setDebug(false);
        //这里是进程数
        //conf.setNumWorkers(10);
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
