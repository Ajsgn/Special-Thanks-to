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
package cn.ajsgn.test.plugin.spring.mq.ali.rocket.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.ajsgn.plugin.spring.mq.ali.rocket.AliRocketMQTopicCenter;
import cn.ajsgn.plugin.spring.mq.ali.rocket.event.AliTopicEventListener;

@Component
public class TestEventListener implements AliTopicEventListener {	//测试事件监听

	private Logger log = LoggerFactory.getLogger(TestEventListener.class);

	@Override
	public void topicConsumerCreate(AliRocketMQTopicCenter topicCenter, String topicName) {
		log.info(String.format("%s 消费者创建", topicName));
	}

	@Override
	public void topicConsumerDestory(AliRocketMQTopicCenter topicCenter, String topicName) {
		log.info(String.format("%s 消费者关闭", topicName));
	}

	@Override
	public void topicProducerCreate(AliRocketMQTopicCenter topicCenter, String topicName) {
		log.info(String.format("%s 生产者创建", topicName));
	}

	@Override
	public void topicProducerDestory(AliRocketMQTopicCenter topicCenter, String topicName) {
		log.info(String.format("%s 生产者关闭", topicName));
	}

}
