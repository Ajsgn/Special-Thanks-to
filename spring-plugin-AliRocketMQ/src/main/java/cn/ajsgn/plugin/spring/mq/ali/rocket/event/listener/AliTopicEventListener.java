/*
 * Copyright (c) 2019, Ajsgn 杨光 (Ajsgn@foxmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ajsgn.plugin.spring.mq.ali.rocket.event.listener;

import cn.ajsgn.plugin.spring.mq.ali.rocket.support.consumer.AliRocketMQConsumerSupportConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.producer.AliRocketMQProducerSupportConfig;

/**
 * <p>topic 时间监听接口</p>
 * @ClassName: AliTopicEventListener
 * @Description: topic 时间监听接口
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 下午1:21:11
 */
public interface AliTopicEventListener {
	
	/**
	 * <p>生产者开启<p>
	 * @Title: topicProducerStart
	 * @Description: 生产者开启
	 * @param producerSupportConfig 生产者配置信息
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月4日 上午11:56:08
	 */
	public void topicProducerStart(AliRocketMQProducerSupportConfig producerSupportConfig);
	
	/**
	 * <p>消费者开启<p>
	 * @Title: topicConsumerStart
	 * @Description: 消费者开启
	 * @param comsumerSupportConfig 消费者配置信息
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月4日 上午11:56:55
	 */
	public void topicConsumerStart(AliRocketMQConsumerSupportConfig comsumerSupportConfig);

}
