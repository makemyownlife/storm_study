package com.mylife.study.storm;

import backtype.storm.topology.TopologyBuilder;
import com.mylife.study.storm.bolts.AgintBolt;
import com.mylife.study.storm.bolts.BonusBolt;
import com.mylife.study.storm.spout.TicketSpout;

/**
 * Created by zhangyong on 14/10/29.
 * <p/>
 * 算奖拓扑接口
 */
public class TopologyContainer {

    public void start() {
        TopologyBuilder builder = new TopologyBuilder();

        //得到ticket相关数据 队列中 或者其他的东西
        builder.setSpout("ticketSpout",new TicketSpout(),2);

        //方案
        builder.setBolt("aagintBolt", new AgintBolt(),2).shuffleGrouping("ticketSpout");

        //返奖
        builder.setBolt("bonusBolt" ,new BonusBolt() , 2);

    }

    public void stop() {

    }


}
