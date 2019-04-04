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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.ajsgn.plugin.spring.mq.ali.rocket.event.listener.AliTopicEventListener;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.consumer.AliRocketMQConsumerSupportConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.producer.AliRocketMQProducerSupportConfig;

/**
 * <p>mq 事件通知</p>
 * <p>用于对topic的创建和销毁做监听动作执行</p>
 * <p>只有通过当前服务内注册与关闭的topic才会进行相应事件的监听</p>
 * @ClassName: AliRocketMQEvent
 * @Description: mq 事件通知
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 上午11:13:34
 */
public class AliRocketMQEvent implements ApplicationContextAware {

	/**
	 * 事件通知订阅列表
	 */
	private Set<AliTopicEventListener> topicEventListeners = new HashSet<AliTopicEventListener>();
	
	/**
	 * <p>通知指定topic生产者的创建成功<p>
	 * @Title: pubProducerStrat
	 * @Description: 通知指定topic生产者的创建成功
	 * @param topicName topic的名称，与阿里云上的名称对应
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 上午11:16:35
	 */
	public void pubProducerStrat(AliRocketMQProducerSupportConfig producerSupportConfig) {
		topicEventListeners.forEach(listener -> listener.topicProducerStart(producerSupportConfig));
	}
	
	/**
	 * <p>通知指定topic的消费者的创建成功<p>
	 * @Title: pubConsumerStrat
	 * @Description: 通知指定topic的消费者的创建成功
	 * @param topicName topic的名称，与阿里云上的名称对应
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 上午11:17:42
	 */
	public void pubConsumerStrat(AliRocketMQConsumerSupportConfig comsumerSupportConfig) {
		topicEventListeners.forEach(listener -> listener.topicConsumerStart(comsumerSupportConfig));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, AliTopicEventListener> aliTopicEventListeners = applicationContext.getBeansOfType(AliTopicEventListener.class);
		if(null != aliTopicEventListeners) {
			for(Map.Entry<String, AliTopicEventListener> entry : aliTopicEventListeners.entrySet()) {
				topicEventListeners.add(entry.getValue());
			}
		}
	}

	
}
