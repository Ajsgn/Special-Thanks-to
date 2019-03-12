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
package cn.ajsgn.plugin.spring.mq.ali.rocket.event;

import cn.ajsgn.plugin.spring.mq.ali.rocket.AliRocketMQTopicCenter;

/**
 * <p>topic 时间监听接口</p>
 * @ClassName: AliTopicEventListener
 * @Description: topic 时间监听接口
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 下午1:21:11
 */
public interface AliTopicEventListener {
	
	/**
	 * <p>指定topic消费者创建事件<p>
	 * @Title: topicConsumerCreate
	 * @Description: 指定topic消费者创建
	 * @param topicCenter 消息中心对象
	 * @param topicName topic 名称
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:21:27
	 */
	public void topicConsumerCreate(AliRocketMQTopicCenter topicCenter, String topicName);
	
	/**
	 * <p>指定topic消费者关闭事件<p>
	 * @Title: topicConsumerDestory
	 * @Description: 指定消费者关闭事件
	 * @param topicCenter 消息中心对象
	 * @param topicName topic 名称
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:22:11
	 */
	public void topicConsumerDestory(AliRocketMQTopicCenter topicCenter, String topicName);
	
	/**
	 * <p>指定topic生产者创建事件<p>
	 * @Title: topicProducerCreate
	 * @Description: 指定topic生产者创建事件
	 * @param topicCenter 消息中心对象
	 * @param topicName topic 名称
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:22:53
	 */
	public void topicProducerCreate(AliRocketMQTopicCenter topicCenter, String topicName);
	
	/**
	 * <p>指定topic生产者关闭事件<p>
	 * @Title: topicProducerDestory
	 * @Description: 指定topic生产者关闭事件
	 * @param topicCenter 消息中心对象
	 * @param topicName topic 名称
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:23:27
	 */
	public void topicProducerDestory(AliRocketMQTopicCenter topicCenter, String topicName);
	
}
