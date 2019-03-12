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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;

import cn.ajsgn.plugin.spring.mq.ali.rocket.config.AliRocketMQConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.exception.NoTopicFoundException;
import cn.ajsgn.plugin.spring.mq.ali.rocket.regist.AliRocketMQRegistRule;
import cn.ajsgn.plugin.spring.mq.ali.rocket.topic.MessageHandler;
import cn.ajsgn.plugin.spring.mq.ali.rocket.topic.Topic;

/**
 * <p>topic 管理</p>
 * @ClassName: AliRocketMQTopicCenter
 * @Description: topic 管理
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 下午1:10:30
 */
public class AliRocketMQTopicCenter {
	/**
	 * topic 集合
	 */
	private Map<String, Topic> topics = new ConcurrentHashMap<String, Topic>();
	/**
	 * 事件对象
	 */
	private AliRocketMQEvent event = null;
	
	private Logger log = LoggerFactory.getLogger(AliRocketMQTopicCenter.class);
	
	/**
	 * <p>根据注册规则，注册一个topic，并根据规则实例化生产者/消费者<p>
	 * @Title: regist
	 * @Description: 根据注册规则，注册一个topic，并根据规则实例化生产者/消费者
	 * @param rocketMQRegistRule 实例化规则
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:11:40
	 */
	public void regist(AliRocketMQRegistRule rocketMQRegistRule) throws NoTopicFoundException {
		if(null != rocketMQRegistRule) {
			AliRocketMQConfig config = rocketMQRegistRule.config();	//ali ons 配置
			boolean producerSupport = rocketMQRegistRule.hasProducer();	//是否创建生产者
			boolean consumerSupport = rocketMQRegistRule.hasConsumer();	//是否创建消费者
			
			if(null != config && (true == producerSupport | consumerSupport)) {
				// 获取topic名称
				String topicName = rocketMQRegistRule.topicName();
				// 获取消费者执行器
				List<MessageHandler> messageHandlers= rocketMQRegistRule.consumerHandlers();
				ProducerBean producer = null;	//构建出来的生产者
				Consumer consumer = null;	//构建出来的消费者
				if(true == producerSupport) {
					//生产者实例化
					producer = new ProducerBean();
					producer.setProperties(config.toProperties());
				}
				if(true == consumerSupport) { 
					//消费者实例化
					consumer = ONSFactory.createConsumer(config.toProperties());
				}
				try {
					//构建topic对象
					Topic topic = new Topic(rocketMQRegistRule, producer, consumer, event);
					topic.registHandler(new HashSet<MessageHandler>(messageHandlers));
					//映射管理
					topics.put(topicName, topic);
				} catch(Exception e) {
					log.error(e.getMessage(), e);
					shutdownTopic(topicName);
				}
			}
		}
	}
	
	/**
	 * <p>发送同步消息，消息具备消费可靠性<p>
	 * @Title: routeMessage
	 * @Description: 发送消息，消息具备可靠性
	 * @param message 消息对象
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:17:53
	 * @return SendResult 发送结果
	 */
	public SendResult routeMessage(Message message) throws NoTopicFoundException {
		Topic topic = sendCheck(message);
		SendResult result = topic.send(message);
		return result;
	}

	/**
	 * <p>发送异步消息，消息具备消费可靠性<p>
	 * @Title: routeAsync
	 * @Description: 发送异步消息，消息具备可靠性
	 * @param message 消息对象
	 * @param sendCallback callback回调对象
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:18:36
	 * @return boolean 执行成功与否
	 */
	public boolean routeAsync(Message message, SendCallback sendCallback) throws NoTopicFoundException {
		Topic topic = sendCheck(message);
		boolean result = topic.sendAsync(message, sendCallback);
		return result;
	}
	
	/**
	 * <p>发送消息，没有ack，不保证消息消费可靠性<p>
	 * @Title: routeOneway
	 * @Description: 发送消息，没有ack，不保证消息可靠性
	 * @param message 消息对象
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:19:27
	 * @return boolean 
	 */
	public boolean routeOneway(Message message) throws NoTopicFoundException {
		Topic topic = sendCheck(message);
		boolean result = topic.sendOneway(message);
		return result;
	}
	
	/*
	 * 发送消息前检查，判断topic初始化状态
	 */
	private Topic sendCheck(Message message) {
		if(null == message)
			throw new NoTopicFoundException("topic of null not init...");
		Topic topic = topics.get(message.getTopic());
		if(null == topic) {
			throw new NoTopicFoundException(String.format("topic of %s not init...", message.getTopic()));
		}
		return topic;
	}

	/**
	 * <p>注册事件对象<p>
	 * @Title: registEvent
	 * @Description: 注册事件对象
	 * @param event 事件对象本身
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:15:05
	 */
	protected void registEvent(AliRocketMQEvent event) {
		this.event = event;
	}
	
	/**
	 * <p>关闭指定topic<p>
	 * @Title: shutdownTopic
	 * @Description: 关闭指定topic
	 * @param topicName topic 名称
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:15:27
	 */
	public void shutdownTopic(String topicName) throws NoTopicFoundException {
		if(true == topics.containsKey(topicName)) {
			Topic topic = topics.get(topicName);
			if(null != topic) {
				topic.shutdownProducer();
				topic.shutdownConsumer();
				topics.remove(topicName);
			}
		}
	}
	
}
