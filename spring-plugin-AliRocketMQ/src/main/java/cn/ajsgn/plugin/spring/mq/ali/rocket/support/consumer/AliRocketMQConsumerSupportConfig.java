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

import cn.ajsgn.plugin.kit.PropertiesKit;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.AliRocketMQSupportConfig;

/**
 * <p>RocketMQ Consumer支持端参数配置</p>
 * <p>ps：消费者对象暂时不支持从spring ioc容器中注入</p>
 * @ClassName: AliRocketMQConsumerSupportConfig
 * @Description: RocketMQ Consumer支持端参数配置
 * @author Ajsgn@foxmail.com
 * @date 2019年4月3日 下午3:00:35
 */
public interface AliRocketMQConsumerSupportConfig extends AliRocketMQSupportConfig {
	
	/**
	 * <p>消费线程数量<p>
	 * @Title: consumeThreadNums
	 * @Description: 消费线程数量
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午2:42:58
	 * @return Integer 
	 */
	public default String consumeThreadNums() {
		return null;
	}
	
	/**
	 * <p>设置每条消息消费的最大超时时间,超过这个时间,这条消息将会被视为消费失败,等下次重新投递再次消费. 每个业务需要设置一个合理的值. 单位(分钟)<p>
	 * @Title: consumeTimeout
	 * @Description: 设置每条消息消费的最大超时时间
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午2:42:32
	 * @return Integer 
	 */
	public default String consumeTimeout() {
		return null;
	}
	
	/**
	 * <p>设置客户端接入来源，默认ALIYUN<p>
	 * @Title: onsChannel
	 * @Description: 设置客户端接入来源，默认ALIYUN
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午2:43:37
	 * @return String 
	 */
	public default String onsChannel() {
		return null;
	}
	
	/**
	 * <p>消费者订阅消息表达式<p>
	 * @Title: consumerSubExpression
	 * @Description: 消费者订阅消息表达式
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午3:02:18
	 * @return String 消费者订阅消息表达式
	 */
	public abstract String consumerSubExpression();
	
	/**
	 * <p>MQ topic name<p>
	 * @Title: topic
	 * @Description: MQ topic name
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午6:21:28
	 * @return String MQ topic name
	 */
	public abstract String topic();
	
	@Override
	public default Properties toProperties() {
		Properties properties = AliRocketMQSupportConfig.super.toProperties();
		if(null == properties) {
			properties = new Properties();
		}
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.ConsumeThreadNums, consumeThreadNums());
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.ConsumeTimeout, consumeTimeout());
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.OnsChannel, onsChannel());
		return properties;
	}
	
}
