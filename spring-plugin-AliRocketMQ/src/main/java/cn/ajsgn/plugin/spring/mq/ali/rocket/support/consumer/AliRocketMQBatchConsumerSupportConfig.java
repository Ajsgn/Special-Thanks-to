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
package cn.ajsgn.plugin.spring.mq.ali.rocket.support.consumer;

import java.util.Properties;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.batch.BatchMessageListener;

import cn.ajsgn.plugin.kit.PropertiesKit;

/**
 * <p>批量消费消费者配置</p>
 * @ClassName: AliRocketMQBatchConsumerSupportConfig
 * @Description: 批量消费消费者配置
 * @author Ajsgn@foxmail.com
 * @date 2019年4月3日 下午3:26:52
 */
public interface AliRocketMQBatchConsumerSupportConfig extends AliRocketMQConsumerSupportConfig {
	
	/**
	 * <p>BatchConsumer每次批量消费的最大消息数量, 默认值为1, 允许自定义范围为[1, 32], 实际消费数量可能小于该值.<p>
	 * @Title: consumeMessageBatchMaxSize
	 * @Description: BatchConsumer每次批量消费的最大消息数量, 默认值为1, 允许自定义范围为[1, 32], 实际消费数量可能小于该值.
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午3:23:09
	 * @return String 
	 */
	public default String consumeMessageBatchMaxSize() {
		return null;
	}
	
	/**
	 * <p>批量消费消息处理器<p>
	 * @Title: handler
	 * @Description: 批量消费消息处理器
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午3:26:03
	 * @return BatchMessageListener 
	 */
	public abstract BatchMessageListener handler();

	@Override
	default Properties toProperties() {
		Properties properties = AliRocketMQConsumerSupportConfig.super.toProperties();
		if(null == properties) {
			properties = new Properties();
		}
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.ConsumeMessageBatchMaxSize, consumeMessageBatchMaxSize());
		return properties; 
	}
	
}
