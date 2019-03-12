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
package cn.ajsgn.plugin.spring.mq.ali.rocket.regist;

import java.util.List;

import cn.ajsgn.plugin.spring.mq.ali.rocket.config.AliRocketMQConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.topic.MessageHandler;
import cn.ajsgn.plugin.spring.mq.ali.rocket.topic.TopicType;

/**
 * <p>创建mq规则</p>
 * @ClassName: AliRocketMQRegistRule
 * @Description: 创建mq规则
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 下午1:24:45
 */
public interface AliRocketMQRegistRule {
	
	/**
	 * <p>消费者处理器集合<p>
	 * <p>为了方便代码业务逻辑隔离，一个消费者可以有多个处理器<p>
	 * @Title: consumerHandlers
	 * @Description: 消费者处理器集合
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:25:05
	 * @return List<MessageHandler> 消息处理器集合
	 */
	public List<MessageHandler> consumerHandlers();
	
	/**
	 * <p>是否需要创建生产者<p>
	 * @Title: hasProducer
	 * @Description: 是否需要创建生产者
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:27:01
	 * @return boolean 是否需要创建生产者
	 */
	public boolean hasProducer();
	
	/**
	 * <p>是否需要创建消费者<p>
	 * @Title: hasConsumer
	 * @Description: 是否需要创建消费者
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:27:22
	 * @return boolean 是否需要创建消费者
	 */
	public boolean hasConsumer();
	
	/**
	 * <p>ons config<p>
	 * @Title: config
	 * @Description: ons config
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:27:41
	 * @return AliRocketMQConfig ons config
	 */
	public AliRocketMQConfig config();
	
	/**
	 * <p>消息类型<p>
	 * <p>ps:当前字段暂时没用，预留<p>
	 * @Title: topicType
	 * @Description: 消息类型
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:27:54
	 * @return TopicType 消息类型
	 */
	public TopicType topicType();
	
	/*
	 * topic name
	 */
	/**
	 * <p>topic name<p>
	 * <p>与阿里云名称保持一致<p>
	 * @Title: topicName
	 * @Description: topic name
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:28:25
	 * @return String topic name
	 */
	public String topicName();

	/**
	 * <p>message key<p>
	 * <p>mq消息全局id，阿里云建议业务唯一，可用于查询消息<p>
	 * @Title: messageKey
	 * @Description: message key
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:28:52
	 * @return String message key
	 */
	public String messageKey();

}
