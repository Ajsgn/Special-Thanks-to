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
package cn.ajsgn.plugin.spring.mq.ali.rocket.test.listener;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.ajsgn.plugin.spring.mq.ali.rocket.event.listener.AliTopicEventListener;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.consumer.AliRocketMQConsumerSupportConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.producer.AliRocketMQProducerSupportConfig;

@Component
public class EventListener implements AliTopicEventListener {

	@Override
	public void topicProducerStart(AliRocketMQProducerSupportConfig producerSupportConfig) {
		System.out.println("生产者开启成功：" + JSONObject.toJSONString(producerSupportConfig.toProperties()));
	}

	@Override
	public void topicConsumerStart(AliRocketMQConsumerSupportConfig comsumerSupportConfig) {
		System.out.println("消费者开启成功：" + JSONObject.toJSONString(comsumerSupportConfig.toProperties()));

	}

}
