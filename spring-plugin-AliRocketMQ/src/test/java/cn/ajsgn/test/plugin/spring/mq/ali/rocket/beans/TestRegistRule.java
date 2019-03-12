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

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.ajsgn.plugin.spring.mq.ali.rocket.config.AliRocketMQConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.regist.AliRocketMQRegistRule;
import cn.ajsgn.plugin.spring.mq.ali.rocket.topic.MessageHandler;
import cn.ajsgn.plugin.spring.mq.ali.rocket.topic.TopicType;

@Configuration    
public class TestRegistRule implements AliRocketMQRegistRule{	//测试topic创建
	
	@Value("${mq.ali.rocket.topicName}")
	private String topicName;
	@Value("${mq.ali.rocket.messageKey}")
	private String messageKey;
	
	@Autowired
	private TestMessageHandle testMessageHandle;

	@Override
	public List<MessageHandler> consumerHandlers() {
		return Arrays.asList(testMessageHandle);
	}

	@Override
	public boolean hasProducer() {
		return true;
	}

	@Override
	public boolean hasConsumer() {
		return true;
	}

	@Bean
	@ConfigurationProperties(prefix = "mq.ali.rocket.config")
	@Override
	public AliRocketMQConfig config() {
		return new AliRocketMQConfig();
	}

	@Override
	public TopicType topicType() {
		return TopicType.NORAML;
	}

	@Override
	public String topicName() {
		return topicName;
	}

	@Override
	public String messageKey() {
		return messageKey;
	}

}
