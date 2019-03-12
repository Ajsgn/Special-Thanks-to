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
package cn.ajsgn.plugin.spring.mq.ali.rocket;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import cn.ajsgn.plugin.spring.mq.ali.rocket.event.AliTopicEventListener;

/**
 * <p>mq 事件通知</p>
 * <p>用于对topic的创建和销毁做监听动作执行</p>
 * <p>只有通过当前服务内注册与关闭的topic才会进行相应事件的监听</p>
 * @ClassName: AliRocketMQEvent
 * @Description: mq 事件通知
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 上午11:13:34
 */
public class AliRocketMQEvent {

	/**
	 * 事件通知订阅列表
	 */
	private Set<AliTopicEventListener> topicEventListeners = new HashSet<AliTopicEventListener>();
	/**
	 * topic 对象服务中心
	 */
	private AliRocketMQTopicCenter rocketMQTopicCenter;
	
	/**
	 * <p>通知指定topic生产者的创建成功<p>
	 * @Title: pubProducerCreate
	 * @Description: 通知指定topic生产者的创建成功
	 * @param topicName topic的名称，与阿里云上的名称对应
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 上午11:16:35
	 */
	public void pubProducerCreate(String topicName) {
		topicEventListeners.forEach(listener -> listener.topicProducerCreate(rocketMQTopicCenter, topicName));
	}
	
	/**
	 * <p>通知指定topic的消费者的创建成功<p>
	 * @Title: pubConsumerCreate
	 * @Description: 通知指定topic的消费者的创建成功
	 * @param topicName topic的名称，与阿里云上的名称对应
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 上午11:17:42
	 */
	public void pubConsumerCreate(String topicName) {
		topicEventListeners.forEach(listener -> listener.topicConsumerCreate(rocketMQTopicCenter, topicName));
	}
	
	/**
	 * <p>通知指定topic的生产者的关闭<p>
	 * @Title: pubProducerDestory
	 * @Description: 通知指定topic的生产者的关闭
	 * @param topicName topic的名称，与阿里云上的名称对应
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 上午11:20:04
	 */
	public void pubProducerDestory(String topicName) {
		topicEventListeners.forEach(listener -> listener.topicProducerDestory(rocketMQTopicCenter, topicName));
	}

	/**
	 * <p>通知指定topic的消费者的关闭<p>
	 * @Title: pubConsumerDestroy
	 * @Description: 通知指定topic的消费者的关闭
	 * @param topicName topic的名称，与阿里云上的名称对应
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 上午11:21:30
	 */
	public void pubConsumerDestroy(String topicName) {
		topicEventListeners.forEach(listener -> listener.topicConsumerDestory(rocketMQTopicCenter, topicName));
	}

	/*
	 * 赋值topicCenter对象，方便通知对象的回传
	 */
	protected void buildWith(AliRocketMQTopicCenter topicCenter) {
		this.rocketMQTopicCenter = topicCenter;
	}
	
	/*
	 * 注册事件监听器
	 */
	protected void registEventListener(AliTopicEventListener eventListener) {
		topicEventListeners.add(eventListener);
	}
	
	/*
	 * 注册事件监听器
	 */
	protected void registEventListeners(Collection<AliTopicEventListener> eventListeners) {
		if(null != eventListeners)
			topicEventListeners.addAll(eventListeners);
	}
	
}
