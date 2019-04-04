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
package cn.ajsgn.plugin.spring.mq.ali.rocket.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import cn.ajsgn.plugin.spring.mq.ali.rocket.support.producer.AliRocketMQNormalProducerSupportConfig;

@Component
@ConfigurationProperties(prefix = "mq.ali.rocket.config")
public class NormalProducerConfig implements AliRocketMQNormalProducerSupportConfig{
	
//	private String topicName;
	private String accesskey;
	private String secretKey;
	private String groupId;
	private String nameServerAddr;

	@Override
	public String accessKey() {
		return accesskey;
	}

	@Override
	public String secretKey() {
		return secretKey;
	}

	@Override
	public String groupId() {
		return groupId;
	}

	@Override
	public String springIOCBeanName() {
		return "normalProducer";
	}

	@Override
	public String nameSrvAddr() {
		return nameServerAddr;
	}

}
