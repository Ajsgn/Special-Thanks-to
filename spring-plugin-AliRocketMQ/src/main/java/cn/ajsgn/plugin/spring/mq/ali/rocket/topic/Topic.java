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
package cn.ajsgn.plugin.spring.mq.ali.rocket.topic;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;

import cn.ajsgn.plugin.spring.mq.ali.rocket.AliRocketMQEvent;
import cn.ajsgn.plugin.spring.mq.ali.rocket.exception.TopicNotSupportProduceException;
import cn.ajsgn.plugin.spring.mq.ali.rocket.regist.AliRocketMQRegistRule;

/**
 * <p>Topic 对象</p>
 * @ClassName: Topic
 * @Description: Topic 对象
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 下午1:33:14
 */
public class Topic {
	
	/**
	 * 名称
	 */
	private String topicName;
	/**
	 * 创建规则
	 */
	private AliRocketMQRegistRule rocketMQRegistRule;
	/**
	 * 生产者
	 */
	private ProducerBean producer;
	/**
	 * 消费者
	 */
	private Consumer consumer;
	/**
	 * 消费者处理器
	 */
	private Map<String, MessageHandler> handlers = new ConcurrentHashMap<String, MessageHandler>();
	/**
	 * 时间对象
	 */
	private AliRocketMQEvent event;

	public Topic(AliRocketMQRegistRule rocketMQRegistRule, ProducerBean producer, Consumer consumer) {
		this.topicName = rocketMQRegistRule.topicName();
		this.rocketMQRegistRule = rocketMQRegistRule;
		this.producer = producer;
		this.consumer = consumer;
	}
	
	public Topic(AliRocketMQRegistRule rocketMQRegistRule, ProducerBean producer, Consumer consumer, AliRocketMQEvent event) {
		this(rocketMQRegistRule, producer, consumer);
		this.event = event;
		try {
			init();
		} catch (Exception e) {
			destory();
			throw e;
		}
	}
	
	/**
	 * <p>注册消息处理器<p>
	 * <p>同一个对象只注册1次<p>
	 * @Title: registHandler
	 * @Description: 注册消息处理器
	 * @param handler 消息处理器对象
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:36:57
	 * @return boolean 注册成功与否
	 */
	public boolean registHandler(MessageHandler handler) {
		return registHandler(handler.consumerExpression(), handler);
	}

	/**
	 * <p>注册消息处理器<p>
	 * <p>同一个对象只注册1次<p>
	 * @Title: registHandler
	 * @Description: 注册消息处理器
	 * @param consumerExpression 监听的表达式
	 * @param handler 消息处理器
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:37:45
	 * @return boolean 注册成功与否
	 */
	private boolean registHandler(String consumerExpression, MessageHandler handler) {
		boolean result = false;
		if(null != handler && false == handlers.containsKey(String.valueOf(handler.hashCode()))) {
			//一个对象只注册1次
			//不通过表达式来注册，是因为方便让多个执行器来消费同一类消息（在代码层面做业务隔离）
			handlers.put(String.valueOf(handler.hashCode()), handler);
			//消息处理器订阅消费者
			consumer.subscribe(topicName, consumerExpression, handler);
		}
		return result;
	}
	
	/**
	 * <p>注册消息处理器<p>
	 * @Title: registHandler
	 * @Description: 注册消息处理器
	 * @param handlers 消息处理器集合
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:40:14
	 * @return boolean 注册成功与否
	 */
	public boolean registHandler(Set<MessageHandler> handlers) {
		boolean result = false;
		if(null != handlers) {
			for(MessageHandler handler : handlers) {
				registHandler(handler);
			}
		}
		return result;
	}
	
	/**
	 * <p>发送同步消息，消息具备消费可靠性<p>
	 * @Title: send
	 * @Description: 发送消息，消息具备可靠性
	 * @param message 消息对象
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:43:44
	 * @return SendResult 发送结果
	 */
	public SendResult send(Message message) {
		supportProduce();
		SendResult result = this.producer.send(message);
		return result;
	}

	/**
	 * <p>发送异步消息，消息具备消费可靠性<p>
	 * @Title: sendAsync
	 * @Description: 发送异步消息，消息具备消费可靠性
	 * @param message 消息对象
	 * @param sendCallback callback回调对象
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:44:09
	 * @return boolean 执行成功与否
	 */
	public boolean sendAsync(Message message, SendCallback sendCallback) {
		supportProduce();
		this.producer.sendAsync(message, sendCallback);
		return true;
	}
	
	/**
	 * <p>发送消息，没有ack，不保证消息消费可靠性<p>
	 * @Title: sendOneway
	 * @Description: 发送消息，没有ack，不保证消息消费可靠性
	 * @param message 消息对象
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:44:33
	 * @return boolean 
	 */
	public boolean sendOneway(Message message) {
		supportProduce();
		this.producer.sendOneway(message);
		return true;
	}
	
	/* 
	 * 判断当前topic是否支持生产者发送消息
	 */
	private void supportProduce() {
		if(null == this.producer)
			throw new TopicNotSupportProduceException(String.format("topic of %s cannot send message because of it not init producer", this.topicName));
	}
	
	/*
	 * 初始化
	 */
	private void init() {
		startProducer();
		startConsumer();
	}
	
	/*
	 * 关闭
	 */
	private void destory() {
		startProducer();
		shutdownConsumer();
	}
	
	/*
	 * 消费者启动
	 */
	private void startConsumer() {
		if(null != consumer) {
			consumer.start();
			if(null != event)
				event.pubConsumerCreate(this.topicName);
		}
	}
	
	/*
	 * 消费者关闭
	 */
	public void shutdownConsumer() {
		if(null != consumer) {
			consumer.shutdown();
			if(null != event)
				event.pubConsumerDestroy(this.topicName);
		}	
	}
	
	private void startProducer() {
		if(null != producer) {
			producer.start();
			if(null != event)
				event.pubProducerCreate(this.topicName);
		}
	}
	
	/*
	 * 生产者关闭
	 */
	public void shutdownProducer() {
		if(null != producer) {
			producer.shutdown();
			
			if(null != event)
				event.pubProducerDestory(this.topicName);
		}
	}

	/**
	 * <p>获取当前topic名称<p>
	 * @Title: getTopicName
	 * @Description: 获取当前topic名称
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:42:34
	 * @return String 当前topic名称
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * <p>获取当前topic类型<p>
	 * @Title: getTopicType
	 * @Description: 获取当前topic类型
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:43:06
	 * @return TopicType topic类型
	 */
	public TopicType getTopicType() {
		return this.rocketMQRegistRule.topicType();
	}
	
}
